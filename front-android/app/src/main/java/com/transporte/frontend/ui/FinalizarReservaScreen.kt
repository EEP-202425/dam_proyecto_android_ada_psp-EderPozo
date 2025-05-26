package com.transporte.frontend.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.transporte.frontend.model.ReservaCompleta
import com.transporte.frontend.net.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

@Composable
fun FinalizarReservaScreen(
    reserva: State<ReservaCompleta?>,
    onVolverInicio: () -> Unit,
    onVerReservas: () -> Unit
) {
    val context = LocalContext.current
    var mensaje by remember { mutableStateOf("Procesando reserva...") }
    var enviada by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        reserva.value?.let { reservaCompleta ->
            Log.d("Reserva", "Email enviado: ${reservaCompleta.email}")

            RetrofitClient.apiService.crearReserva(reservaCompleta.toRequest()).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    mensaje = if (response.isSuccessful) {
                        "Reserva realizada: ${response.body()}"
                    } else {
                        "Error al reservar: ${response.message()}"
                    }
                    enviada = true
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    mensaje = "Fallo de conexión: ${t.message}"
                    enviada = true
                }
            })
        } ?: run {
            mensaje = "Error: reserva no disponible"
            enviada = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = mensaje, style = MaterialTheme.typography.bodyLarge)

        if (enviada) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = onVolverInicio) {
                    Text("Volver al Inicio")
                }
                Button(onClick = onVerReservas) {
                    Text("Ver Reservas")
                }
            }
        }
    }
}
