package com.example.firebase.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.firebase.MahasiswaApplications


object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(MahasiswaApplication().container.mahasiswaRepository)
        }
        initializer {
            InsertViewModel(MahasiswaApplication().container.mahasiswaRepository)
        }
    }
}
