package com.transporte.proyecto.controllers;

import com.transporte.proyecto.dtos.ReservaRequest;
import com.transporte.proyecto.entities.*;
import com.transporte.proyecto.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private BilleteRepository billeteRepository;

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @PostMapping("/reservas")
    public ResponseEntity<String> crearReserva(@RequestBody ReservaRequest req) {
        try {
            Pasajero pasajero = new Pasajero();
            pasajero.setNombre(req.nombre);
            pasajero.setApellidoPaterno(req.apellidoPaterno);
            pasajero.setApellidoMaterno(req.apellidoMaterno);
            pasajero.setEmail(req.email);
            pasajero.setTelefono(req.telefono);
            pasajeroRepository.save(pasajero);

            Billete billete = new Billete();
            billete.setOrigen(Ciudad.valueOf(req.origen));
            billete.setDestino(Ciudad.valueOf(req.destino));
            billete.setTipoVehiculo(TipoVehiculo.valueOf(req.tipo));
            billete.setFecha(req.fecha);
            billete.setHora(req.hora);
            billete.setPrecio(req.precio);
            billete.setPasajero(pasajero);
            System.out.println("Guardando billete con pasajero.Id: " + pasajero.getId() + ", destino: " + req.destino);
            billeteRepository.save(billete);
            System.out.println("Billete guardado exitosamente");

            return ResponseEntity.ok("Billete: ID = " + billete.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear reserva: " + e.getMessage());
        }
    }
}
