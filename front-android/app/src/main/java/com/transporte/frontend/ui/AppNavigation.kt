package com.transporte.frontend.ui

import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.transporte.frontend.model.ReservaCompleta

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val reserva = remember { mutableStateOf<ReservaCompleta?>(null) }

    NavHost(navController = navController, startDestination = "inicio") {

        composable("inicio") {
            ReservaFormScreen(
                onContinuar = { origen, destino, tipo, fecha, hora, precio ->
                    reserva.value = ReservaCompleta(
                        nombre = "",
                        apellidoPaterno = "",
                        apellidoMaterno = "",
                        email = "",
                        telefono = "",
                        origen = origen,
                        destino = destino,
                        tipo = tipo,
                        fecha = fecha,
                        hora = hora,
                        precio = precio.toDouble()
                    )
                    navController.navigate("pasajero")
                },
                onCancelar = {}
            )
        }

        composable("pasajero") {
            PasajeroFormScreen(
                reserva = reserva,
                onContinuar = {
                    navController.navigate("resumen")
                },
                onCancelar = {
                    navController.popBackStack()
                }
            )
        }

        composable("resumen") {
            ResumenReservaScreen(
                reserva = reserva,
                onCorregir = {
                    navController.popBackStack("inicio", inclusive = false)
                },
                onFinalizar = {
                    navController.navigate("final")
                }
            )
        }

        composable("final") {
            FinalizarReservaScreen(
                reserva = reserva,
                onVolverInicio = {
                    navController.navigate("inicio") {
                        popUpTo("inicio") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onVerReservas = {
                    navController.navigate("listaReservas")
                }
            )
        }

        composable("listaReservas") {
            VerReservaScreen(navController = navController)
        }
    }
}
