package com.transporte.frontend.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.transporte.frontend.model.ReservaCompleta

@Composable
fun VerReservaScreen(
    reserva: State<ReservaCompleta?>,
    onCorregir: () -> Unit,
    onFinalizar: () -> Unit
) {
    reserva.value?.let { reserva ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Resumen de la Reserva", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            Text("Nombre: ${reserva.nombre}")
            Text("Apellidos: ${reserva.apellidoPaterno} ${reserva.apellidoMaterno}")
            Text("Email: ${reserva.email}")
            Text("Teléfono: ${reserva.telefono}")
            Text("Origen: ${reserva.origen}")
            Text("Destino: ${reserva.destino}")
            Text("Tipo: ${reserva.tipo}")
            Text("Fecha: ${reserva.fecha}")
            Text("Hora: ${reserva.hora}")
            Text("Precio: €${"%.2f".format(reserva.precio)}")

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onCorregir) {
                    Text("Corregir")
                }
                Button(onClick = onFinalizar) {
                    Text("Finalizar Reserva")
                }
            }
        }
    } ?: run {
        Text("No hay datos de reserva disponibles.")
    }
}
