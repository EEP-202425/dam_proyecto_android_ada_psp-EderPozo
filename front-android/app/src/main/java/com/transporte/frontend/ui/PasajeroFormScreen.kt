
package com.transporte.frontend.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PasajeroFormScreen(
    onVerReserva: (String, String, String, String, Any?) -> Unit,
    onCancelar: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = apellidoPaterno, onValueChange = { apellidoPaterno = it }, label = { Text("Apellido Paterno") })
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = apellidoMaterno, onValueChange = { apellidoMaterno = it }, label = { Text("Apellido Materno") })
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = telefono, onValueChange = { telefono = it }, label = { Text("Teléfono") })
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = onCancelar) {
                Text("Cancelar")
            }
            Button(
                onClick = {
                    if (nombre.isNotEmpty() && apellidoPaterno.isNotEmpty() && email.isNotEmpty()) {
                        onVerReserva(nombre, apellidoPaterno, apellidoMaterno, email, telefono)
                    }
                }
            ) {
                Text("Ver Reserva")
            }
        }
    }
}
