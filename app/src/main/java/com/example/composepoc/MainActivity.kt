package com.example.composepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.composepoc.presentation.screens.ProductListingScreen
import com.example.composepoc.ui.theme.ComposePOCTheme
import dagger.hilt.android.AndroidEntryPoint

/*
* It provides the main entry point of the app's UI i.e. the launcher activity
* which attaches the ProductListingScreen Composable component (Product listing screen)
* */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePOCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ProductListingScreen()
                }
            }
        }
    }
}
