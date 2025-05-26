package com.transporte.frontend.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.transporte.frontend.model.Billete
import com.transporte.frontend.net.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun VerReservaScreen(navController: NavController) {
    var billetes by remember { mutableStateOf<List<Billete>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        val api = RetrofitClient.apiService
        api.getReservas().enqueue(object : Callback<List<Billete>> {
            override fun onResponse(call: Call<List<Billete>>, response: Response<List<Billete>>) {
                if (response.isSuccessful) {
                    billetes = response.body() ?: emptyList()
                } else {
                    error = "Error al cargar reservas: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Billete>>, t: Throwable) {
                error = "Fallo en la conexión: ${t.localizedMessage}"
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text("Listado de Reservas", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Total de reservas encontradas: ${billetes.size}")
        Spacer(modifier = Modifier.height(8.dp))

        if (error != null) {
            Text(error!!, color = MaterialTheme.colorScheme.error)
        } else {
            billetes.forEach { billete ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Id: ${String.format("%02d", billete.id)}")
                        Text("Origen: ${billete.origen}")
                        Text("Destino: ${billete.destino}")
                        Text("Fecha: ${billete.fecha}")
                        Text("Hora: ${billete.hora}")
                        Text("Tipo: ${billete.tipoVehiculo}")
                        Text("Precio: €${billete.precio}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("inicio") {
                    popUpTo("inicio") { inclusive = false }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }
    }
}
