package com.transporte.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.transporte.frontend.ui.AppNavigation
import com.transporte.frontend.ui.theme.TransporteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TransporteAppTheme {
                AppNavigation()
            }
        }
    }
}
