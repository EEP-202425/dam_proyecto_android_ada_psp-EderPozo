
package com.transporte.frontend.model

data class ReservaRequest(
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
)
