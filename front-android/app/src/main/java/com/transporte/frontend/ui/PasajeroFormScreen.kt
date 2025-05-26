package com.transporte.frontend.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.transporte.frontend.model.ReservaCompleta

@Composable
fun PasajeroFormScreen(
    reserva: MutableState<ReservaCompleta?>,
    onContinuar: () -> Unit,
    onCancelar: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var telefonoError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre", color = Color.Black) },
            textStyle = TextStyle(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = apellidoPaterno,
            onValueChange = { apellidoPaterno = it },
            label = { Text("Apellido Paterno", color = Color.Black) },
            textStyle = TextStyle(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = apellidoMaterno,
            onValueChange = { apellidoMaterno = it },
            label = { Text("Apellido Materno", color = Color.Black) },
            textStyle = TextStyle(color = Color.Black)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            },
            label = { Text("Email", color = Color.Black) },
            isError = emailError,
            textStyle = TextStyle(color = Color.Black)
        )
        if (emailError) {
            Text(
                text = "Formato de email inválido",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = {
                if (it.length <= 9 && it.all { char -> char.isDigit() }) {
                    telefono = it
                    telefonoError = it.length < 9
                }
            },
            label = { Text("Teléfono", color = Color.Black) },
            isError = telefonoError,
            textStyle = TextStyle(color = Color.Black)
        )
        if (telefonoError) {
            Text(
                text = "Debe ingresar 9 números",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onCancelar) {
                Text("Cancelar")
            }
            Button(onClick = {
                emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                telefonoError = telefono.length < 9

                if (!emailError && !telefonoError && nombre.isNotEmpty() && apellidoPaterno.isNotEmpty()) {
                    reserva.value = reserva.value?.copy(
                        nombre = nombre,
                        apellidoPaterno = apellidoPaterno,
                        apellidoMaterno = apellidoMaterno,
                        email = email,
                        telefono = telefono
                    )
                    onContinuar()
                }
            }) {
                Text("Ver Reserva")
            }
        }
    }
}
