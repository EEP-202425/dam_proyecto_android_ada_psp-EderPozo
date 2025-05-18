package com.transporte.frontend.model

import java.io.Serializable

data class ReservaCompleta(
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val email: String,
    val telefono: String,
    val origen: String,
    val destino: String,
    val tipo: String,
    val fecha: String,
    val hora: String,
    val precio: Double
) : Serializable {

    fun toRequest(): ReservaRequest {
        return ReservaRequest(
            nombre = nombre,
            apellidoPaterno = apellidoPaterno,
            apellidoMaterno = apellidoMaterno,
            email = email,
            telefono = telefono,
            origen = origen,
            destino = destino,
            tipo = tipo,
            fecha = fecha,
            hora = hora,
            precio = precio
        )
    }
}
