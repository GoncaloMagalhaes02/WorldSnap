package com.example.worlsnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.worlsnap.navigation.WorldSnapNavigation
import com.example.worlsnap.ui.theme.WorldSnapTheme
import com.example.worlsnap.ui.theme.WorldsnapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldSnapTheme() {
                WorldSnapNavigation()
            }
        }
    }
}