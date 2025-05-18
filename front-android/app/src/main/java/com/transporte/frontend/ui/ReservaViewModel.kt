package com.transporte.frontend.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transporte.frontend.model.ReservaCompleta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReservaViewModel : ViewModel() {

    private val _reserva = MutableStateFlow<ReservaCompleta?>(null)
    val reserva: StateFlow<ReservaCompleta?> = _reserva

    fun setReservaTemp(
        origen: String,
        destino: String,
        tipo: String,
        fecha: String,
        hora: String,
        precio: String
    ) {
        _reserva.value = ReservaCompleta(
            nombre = "",
            apellidoPaterno = "",
            apellidoMaterno = "",
            email = "",
            telefono = "",
            origen = origen,
            destino = destino,
            tipo = tipo,
            fecha = fecha,
            hora = hora,
            precio = precio.toDoubleOrNull() ?: 0.0
        )
    }

    fun completarPasajero(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
	email: String,
        telefono: String
    ) {
        _reserva.value = _reserva.value?.copy(
            nombre = nombre,
            apellidoPaterno = apellidoPaterno,
            apellidoMaterno = apellidoMaterno,
	    email = email,
            telefono = telefono
        )
    }

    fun completarContacto(email: String) {
        _reserva.value = _reserva.value?.copy(email = email)
    }

    fun clearReserva() {
        viewModelScope.launch {
            _reserva.value = null
        }
    }
}
