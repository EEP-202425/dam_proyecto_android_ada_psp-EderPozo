package com.transporte.frontend.model

data class Billete(
    val id: Long,
    val fecha: String,
    val hora: String,
    val origen: String,
    val destino: String,
    val tipoVehiculo: String,
    val precio: Double
)