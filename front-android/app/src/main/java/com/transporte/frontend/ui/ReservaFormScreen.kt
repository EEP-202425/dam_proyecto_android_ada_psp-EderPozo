
package com.transporte.frontend.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun ReservaFormScreen(
    onContinuar: (String, String, String, String, String, String) -> Unit,
    onCancelar: () -> Unit
) {
    val context = LocalContext.current
    val ciudades = listOf("MADRID", "BARCELONA", "VALENCIA", "SEVILLA")
    val tipos = listOf("BUS", "TREN", "AVION")

    var origen by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

    val today = Calendar.getInstance()

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Selecciona tu reserva", style = MaterialTheme.typography.titleLarge)

        DropdownField("Origen", origen, ciudades) { origen = it }
        DropdownField("Destino", destino, ciudades) { destino = it }
        DropdownField("Tipo de Transporte", tipo, tipos) { tipo = it }

        Button(onClick = {
            DatePickerDialog(context, { _, y, m, d ->
                fecha = "$d/${m+1}/$y"
            }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)).show()
        }) {
            Text(if (fecha.isEmpty()) "Seleccionar Fecha" else "Fecha: $fecha")
        }

        Button(onClick = {
            TimePickerDialog(context, { _, h, m ->
                hora = String.format("%02d:%02d", h, m)
            }, today.get(Calendar.HOUR_OF_DAY), today.get(Calendar.MINUTE), true).show()
        }) {
            Text(if (hora.isEmpty()) "Seleccionar Hora" else "Hora: $hora")
        }

        LaunchedEffect(origen, destino) {
            precio = if (origen.isNotBlank() && destino.isNotBlank() && origen != destino) {
                ((origen.hashCode() - destino.hashCode()).toString().length * 2).toString()
            } else ""
        }

        Text("Precio: €$precio")

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                if (origen.isNotEmpty() && destino.isNotEmpty() && tipo.isNotEmpty() && fecha.isNotEmpty() && hora.isNotEmpty() && precio.isNotEmpty()) {
                    onContinuar(origen, destino, tipo, fecha, hora, precio)
                }
            }) {
                Text("Continuar")
            }
            OutlinedButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(label: String, selected: String, options: List<String>, onSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        TextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onSelected(it)
                        expanded = false
                    }
                )
            }
        }
    }
}
