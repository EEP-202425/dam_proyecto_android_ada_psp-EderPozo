package com.transporte.proyecto.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.transporte.proyecto.entities.Pasajero;
import com.transporte.proyecto.services.PasajeroServiceManager;

@RestController
@RequestMapping("/api/pasajeros")
public class PasajeroController {
    @Autowired
    PasajeroServiceManager pasajeroServiceManager;
    
    @PostMapping()
    public ResponseEntity<Pasajero> save(@RequestBody Pasajero pasajero){
    	Pasajero newPasajero = pasajeroServiceManager.save(pasajero);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPasajero);
    }

    @GetMapping()
    public List<Pasajero> listarPasajeros(){
        return this.pasajeroServiceManager.get();
    }
    
    @GetMapping("/{id}")
    public Pasajero optenerPasajeroId( @PathVariable Long id){
        return this.pasajeroServiceManager.getById(id).get();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePasajero(@PathVariable Long id,
                                            @RequestBody Pasajero pasajero) {
        // Verifica si el pasajero existe
        Optional<Pasajero> pasajeroOptional = this.pasajeroServiceManager.getById(id);

        if (pasajeroOptional.isPresent()) {
        	Pasajero pasajeroModificado = pasajeroOptional.get();

            // Actualiza los valores del billete existente con los nuevos datos
        	pasajeroModificado.setNombre(pasajero.getNombre());
            pasajeroModificado.setApellidoPaterno(pasajero.getApellidoPaterno());
            pasajeroModificado.setApellidoMaterno(pasajero.getApellidoMaterno());
            pasajeroModificado.setTelefono(pasajero.getTelefono());
            pasajeroModificado.setEmail(pasajero.getEmail());

            // Llama al servicio para actualizar el pasajero
            Optional<Pasajero> optionalPasajero = this.pasajeroServiceManager.update(id, pasajeroModificado);

            return ResponseEntity.status(HttpStatus.CREATED).body(optionalPasajero.get());
        }

        // Si el pasajero no existe, devuelve un 404
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Pasajero> deletePasajero(@PathVariable Long id){
        Optional<Pasajero> optionalPasajero = this.pasajeroServiceManager.delete(id);

        if(optionalPasajero.isPresent()){
            return ResponseEntity.ok(optionalPasajero.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
