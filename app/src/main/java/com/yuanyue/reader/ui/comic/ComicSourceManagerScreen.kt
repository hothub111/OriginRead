package com.yuanyue.reader.ui.comic

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
import com.yuanyue.reader.model.ComicSourceEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicSourceManagerScreen(
    viewModel: ComicSourceManagerViewModel,
    onBack: () -> Unit,
) {
    val sources by viewModel.sources.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    var showAddDialog by remember { mutableStateOf(false) }
    var isAdding by remember { mutableStateOf(false) }
    var addError by remember { mutableStateOf<String?>(null) }
    var pendingDelete by remember { mutableStateOf<ComicSourceEntity?>(null) }
    var comicJson by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("漫画源管理") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "添加漫画源")
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
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(source.name)
                        Text(
                            text = source.url,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall,
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
        var fieldError by remember { mutableStateOf<String?>(null) }
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("导入漫画源 JSON") },
            text = {
                Column {
                    OutlinedTextField(
                        value = comicJson,
                        onValueChange = {
                            comicJson = it
                            fieldError = null
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isAdding,
                        label = { Text("漫画源 JSON") },
                        placeholder = { Text("粘贴 JSON 或填写 JSON 网址") },
                        minLines = 6,
                        maxLines = 12,
                    )
                    (fieldError ?: addError)?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    enabled = !isAdding,
                    onClick = {
                        if (comicJson.isBlank()) {
                            fieldError = "请粘贴漫画源 JSON"
                        } else {
                            scope.launch {
                                isAdding = true
                                addError = null
                                when (val result = viewModel.addSource(comicJson.trim())) {
                                    is com.yuanyue.reader.data.OperationResult.Success -> {
                                        showAddDialog = false
                                    }

                                    is com.yuanyue.reader.data.OperationResult.Error -> {
                                        addError = result.message
                                    }
                                }
                                isAdding = false
                            }
                        }
                    },
                ) {
                    if (isAdding) {
                        CircularProgressIndicator(modifier = Modifier.size(18.dp))
                    } else {
                        Text("导入")
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("取消")
                }
            },
        )
    }

    pendingDelete?.let { source ->
        AlertDialog(
            onDismissRequest = { pendingDelete = null },
            title = { Text("删除漫画源") },
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
