package com.aarontechnology.nseterminal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.aarontechnology.nseterminal.data.DatabaseManager
import com.aarontechnology.nseterminal.ui.screens.DatabasePickerScreen
import com.aarontechnology.nseterminal.ui.screens.MainTerminalScreen
import com.aarontechnology.nseterminal.ui.theme.BgDark
import com.aarontechnology.nseterminal.ui.theme.NSETerminalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NSETerminalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BgDark
                ) {
                    var isDbConfigured by remember {
                        mutableStateOf(DatabaseManager.isDatabaseConfigured(this@MainActivity))
                    }

                    if (!isDbConfigured) {
                        DatabasePickerScreen(
                            onDatabaseReady = {
                                isDbConfigured = true
                            }
                        )
                    } else {
                        MainTerminalScreen(
                            onResetDatabase = {
                                DatabaseManager.resetDatabase(this@MainActivity)
                                isDbConfigured = false
                            }
                        )
                    }
                }
            }
        }
    }
}
