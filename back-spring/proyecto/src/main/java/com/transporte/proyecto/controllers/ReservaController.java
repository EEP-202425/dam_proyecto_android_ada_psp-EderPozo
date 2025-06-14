package com.transporte.proyecto.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transporte.proyecto.dtos.ReservaRequest;
import com.transporte.proyecto.entities.Billete;
import com.transporte.proyecto.entities.Ciudad;
import com.transporte.proyecto.entities.Pasajero;
import com.transporte.proyecto.entities.TipoVehiculo;
import com.transporte.proyecto.repositories.BilleteRepository;
import com.transporte.proyecto.repositories.PasajeroRepository;

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
    
    @GetMapping("/reservas")
    public List<Billete> listarReservas() {
        List<Billete> lista = new ArrayList<>();
        billeteRepository.findAll().forEach(lista::add);
        return lista;
    }
}
