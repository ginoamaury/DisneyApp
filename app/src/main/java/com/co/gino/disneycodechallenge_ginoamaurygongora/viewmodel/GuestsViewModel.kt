package com.co.gino.disneycodechallenge_ginoamaurygongora.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.co.gino.domain.exceptions.NoDataGuestException
import com.co.gino.domain.exceptions.TechnicalException
import com.co.gino.domain.models.Guest
import com.co.gino.domain.services.GetGuestsService
import com.co.gino.infrastructure.dependencyInjection.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuestsViewModel @Inject constructor(
    private val getGuestsService: GetGuestsService,
    @DefaultDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val loading = MutableStateFlow(GuestsUiState().loading)
    private val success = MutableStateFlow(GuestsUiState().success)
    private val error = MutableStateFlow(GuestsUiState().error)

    private val _uiState = MutableStateFlow(GuestsUiState())
    val uiState: StateFlow<GuestsUiState> get() = _uiState

    var guestsWithReservationChecked = mutableStateListOf<Guest>()
    var guestsWithoutReservationChecked = mutableStateListOf<Guest>()
    var showReservationNeeded = mutableStateOf(false)

    init {
        viewModelScope.launch {
            combine(
                loading,
                success,
                error
            ) { loading, success, error ->
                GuestsUiState(loading, success, error)
            }.catch { throwable ->
                throw throwable
            }.collect {
                _uiState.value = it
            }
        }
        getGuests()
    }

    fun getGuests(){
        viewModelScope.launch(ioDispatcher) {
            loading.value = true
            try {
                getGuestsService.invoke().collect { guests ->
                    success.value = guests
                    loading.value = false
                }
            } catch (e: Exception) {
                loading.value = false
                error.value = true
                if(e is NoDataGuestException) Log.d("EXCEPTION",e.message.toString())
                else if (e is TechnicalException) Log.d("EXCEPTION",e.message.toString())
            }

        }
    }

    fun checkGuest(guest: Guest, isChecked:Boolean) {
        when (guest.hasReservation) {
            true -> {
                if (!isChecked) guestsWithReservationChecked.remove(guest)
                else guestsWithReservationChecked.add(guest)
            }
            false ->{
                if (!isChecked) guestsWithoutReservationChecked.remove(guest)
                else{
                    guestsWithoutReservationChecked.add(guest)
                    if(guestsWithReservationChecked.isEmpty()) showReservationNeeded.value = true
                }
            }
        }
    }

    fun setReservationNeededStatus (status:Boolean){
        showReservationNeeded.value = status
    }

}

data class GuestsUiState(
    var loading: Boolean = false,
    var success: List<Guest> = emptyList(),
    var error: Boolean = false,
)