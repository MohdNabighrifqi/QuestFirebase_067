package com.example.firebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.repository.MahasiswaRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import androidx.lifecycle.SavedStateHandle
import com.example.firebase.Navigation.DestinasiDetail
import com.example.firebase.model.Mahasiswa
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

sealed class HomeDetailUiState{
    data class Success(val mahasiswa: List<Mahasiswa>): HomeDetailUiState()
    data class Error(val exception: Throwable) : HomeDetailUiState()
    object Loading : HomeDetailUiState()
}

class HomeDetailViewModel(
    private val mhs: MahasiswaRepository
) : ViewModel() {
    var mhsUIState: HomeDetailUiState by mutableStateOf(HomeDetailUiState.Loading)
        private set

    init {
        getMhs()
    }

    fun deleteMhs(mahasiswa: Mahasiswa) {
        viewModelScope.launch {
            try {
                mhs.deleteMahasiswa("")
            } catch (e: Exception) {
                mhsUIState = HomeDetailUiState.Error(e)
            }
        }
    }

    fun getMhs() {
        viewModelScope.launch {
            mhs.getMahasiswa()
                .onStart {
                    mhsUIState = HomeDetailUiState.Loading
                }
                .catch {
                    mhsUIState = HomeDetailUiState.Error(it)
                }
                .collect{
                    mhsUIState = if (it.isEmpty()) {
                        HomeDetailUiState.Error(Exception("Belum ada daftar Mahasiswa"))
                    } else {
                        HomeDetailUiState.Success(it)
                    }
                }
        }
    }
}