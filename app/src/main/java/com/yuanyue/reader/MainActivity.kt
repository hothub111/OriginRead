package com.yuanyue.reader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yuanyue.reader.ui.navigation.AppNavHost
import com.yuanyue.reader.ui.theme.YuanyueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YuanyueTheme {
                AppNavHost(
                    container = (application as YuanyueApplication).container,
                )
            }
        }
    }
}
