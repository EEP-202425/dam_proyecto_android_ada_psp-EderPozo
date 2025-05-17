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
import com.transporte.proyecto.entities.Ruta;
import com.transporte.proyecto.services.RutaServiceManager;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {
    @Autowired
    RutaServiceManager rutaServiceManager;
    
    @PostMapping()
    public ResponseEntity<Ruta> save(@RequestBody Ruta ruta){
    	Ruta newRuta = rutaServiceManager.save(ruta);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRuta);
    }

    @GetMapping()
    public List<Ruta> listarRutas(){
        return this.rutaServiceManager.get();
    }
    
    @GetMapping("/{id}")
    public Ruta optenerRutaId( @PathVariable Long id){
        return this.rutaServiceManager.getById(id).get();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRuta(@PathVariable Long id,
                                            @RequestBody Ruta ruta) {
        // Verifica si el ruta existe
        Optional<Ruta> rutaOptional = this.rutaServiceManager.getById(id);

        if (rutaOptional.isPresent()) {
        	Ruta rutaModificado = rutaOptional.get();

            // Actualiza los valores del ruta existente con los nuevos datos
            rutaModificado.setOrigen(ruta.getOrigen());
            rutaModificado.setDestino(ruta.getDestino());
            rutaModificado.setVehiculos(ruta.getVehiculos());

            // Llama al servicio para actualizar el ruta
            Optional<Ruta> optionalRuta = this.rutaServiceManager.update(id, rutaModificado);

            return ResponseEntity.status(HttpStatus.CREATED).body(optionalRuta.get());
        }

        // Si el ruta no existe, devuelve un 404
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Ruta> deleteRuta(@PathVariable Long id){
        Optional<Ruta> optionalRuta = this.rutaServiceManager.delete(id);

        if(optionalRuta.isPresent()){
            return ResponseEntity.ok(optionalRuta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
