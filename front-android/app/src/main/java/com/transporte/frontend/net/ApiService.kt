package com.transporte.frontend.net

import com.transporte.frontend.model.Billete
import com.transporte.frontend.model.ReservaRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/ciudades")
    fun getCiudades(): Call<List<String>>

    @GET("api/tipos")
    fun getTipos(): Call<List<String>>

    @POST("api/reservas")
    fun crearReserva(@Body reserva: ReservaRequest): Call<String>

    @GET("api/reservas")
    fun getReservas(): Call<List<Billete>>
}
