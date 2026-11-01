package com.yuanyue.reader.ui.online

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yuanyue.reader.data.BookSourceAddResult
import com.yuanyue.reader.model.BookSourceEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSourceManagerScreen(
    viewModel: BookSourceManagerViewModel,
    onBack: () -> Unit,
) {
    val sources by viewModel.sources.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var showAddDialog by remember { mutableStateOf(false) }
    var isBusy by remember { mutableStateOf(false) }
    var addError by remember { mutableStateOf<String?>(null) }
    var canForce by remember { mutableStateOf(false) }
    var pendingDelete by remember { mutableStateOf<BookSourceEntity?>(null) }

    fun addSource(json: String, force: Boolean) {
        scope.launch {
            isBusy = true
            addError = null
            canForce = false
            when (val result = viewModel.addSource(json, skipValidation = force)) {
                is BookSourceAddResult.Added -> {
                    showAddDialog = false
                    val notice = buildString {
                        append(result.message)
                        result.warning?.takeIf { it.isNotBlank() }?.let {
                            append("；")
                            append(it)
                        }
                    }
                    snackbarHostState.showSnackbar(notice)
                }

                is BookSourceAddResult.Unavailable -> {
                    addError = result.message
                    canForce = true
                }

                is BookSourceAddResult.Failed -> {
                    addError = result.message
                }
            }
            isBusy = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("书源管理") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "添加书源")
            }
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(sources, key = { it.id }) { source ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(source.name)
                        Text(
                            text = source.url,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                        )
                    }
                    IconButton(onClick = { pendingDelete = source }) {
                        Icon(Icons.Default.Delete, contentDescription = "删除")
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddSourceDialog(
            isLoading = isBusy,
            error = addError,
            canForce = canForce,
            onDismiss = {
                if (!isBusy) {
                    showAddDialog = false
                    addError = null
                    canForce = false
                }
            },
            onConfirm = { json -> addSource(json, force = false) },
            onForce = { json -> addSource(json, force = true) },
        )
    }

    pendingDelete?.let { source ->
        AlertDialog(
            onDismissRequest = { pendingDelete = null },
            title = { Text("删除书源") },
            text = { Text("确定删除「${source.name}」及其缓存数据吗？") },
            confirmButton = {
                TextButton(
                    onClick = {
                        scope.launch {
                            viewModel.deleteSource(source)
                            pendingDelete = null
                        }
                    },
                ) {
                    Text("删除")
                }
            },
            dismissButton = {
                TextButton(onClick = { pendingDelete = null }) {
                    Text("取消")
                }
            },
        )
    }
}

@Composable
private fun AddSourceDialog(
    isLoading: Boolean,
    error: String?,
    canForce: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    onForce: (String) -> Unit,
) {
    var json by remember { mutableStateOf("") }
    var fieldError by remember { mutableStateOf<String?>(null) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("添加在线书源") },
        text = {
            Column {
                OutlinedTextField(
                    value = json,
                    onValueChange = {
                        json = it
                        fieldError = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading,
                    label = { Text("书源 JSON") },
                    placeholder = { Text("粘贴书源 JSON，或填写 JSON 文件网址") },
                    minLines = 6,
                    maxLines = 12,
                )
                Text(
                    text = "支持「阅读 3.0」书源格式。添加前会先检测书源是否可用。",
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
                (fieldError ?: error)?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(top = 8.dp),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                if (canForce) {
                    Text(
                        text = "检测没有通过。如果确认书源本身可用，可以仍要添加。",
                        modifier = Modifier.padding(top = 6.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        },
        confirmButton = {
            Row {
                if (canForce) {
                    TextButton(
                        enabled = !isLoading,
                        onClick = { onForce(json.trim()) },
                    ) {
                        Text("仍然添加")
                    }
                }
                TextButton(
                    enabled = !isLoading,
                    onClick = {
                        if (json.isBlank()) {
                            fieldError = "请粘贴书源 JSON"
                        } else {
                            onConfirm(json.trim())
                        }
                    },
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(18.dp))
                    } else {
                        Text("检测并添加")
                    }
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, enabled = !isLoading) {
                Text("取消")
            }
        },
    )
}
