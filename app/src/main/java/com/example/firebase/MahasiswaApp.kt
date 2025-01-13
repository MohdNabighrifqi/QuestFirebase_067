package com.example.firebase

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.firebase.Navigation.MainControllerPage


@Composable
fun MahasiswaApp() {
    Scaffold (
        modifier = Modifier
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            MainControllerPage()
        }
    }
}