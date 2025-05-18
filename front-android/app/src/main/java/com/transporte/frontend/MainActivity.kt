
package com.transporte.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.*
import com.transporte.frontend.ui.*
import com.transporte.frontend.ui.theme.TransporteAppTheme
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    private val viewModel: ReservaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TransporteAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "reserva_form") {
                    composable("reserva_form") {
                        ReservaFormScreen(
                            onContinuar = { origen, destino, tipo, fecha, hora, precio ->
                                viewModel.setReservaTemp(origen, destino, tipo, fecha, hora, precio)
                                navController.navigate("pasajero_form")
                            },
                            onCancelar = { /* Puedes cerrar app o volver */ }
                        )
                    }
                    composable("pasajero_form") {
                        PasajeroFormScreen(
                            onVerReserva = { nombre, apePat, apeMat, email,telefono ->
                                viewModel.completarPasajero(nombre, apePat, apeMat, email,
                                    telefono.toString()
                                )
                                navController.navigate("ver_reserva")
                            },
                            onCancelar = { navController.popBackStack() }
                        )
                    }
                    composable("ver_reserva") {
                        viewModel.reserva.collectAsState().let { reserva ->
                            VerReservaScreen(
                                reserva = reserva,
                                onCorregir = { navController.popBackStack() },
                                onFinalizar = { navController.navigate("finalizado") }
                            )
                        }
                    }
                    composable("finalizado") {
                        viewModel.reserva.collectAsState().let { reserva ->
                            FinalizarReservaScreen(reserva = reserva) {
                                navController.navigate("reserva_form") {
                                    popUpTo("reserva_form") { inclusive = true }
                                }
                                viewModel.clearReserva()
                            }
                        }
                    }
                }
            }
        }
    }
}
