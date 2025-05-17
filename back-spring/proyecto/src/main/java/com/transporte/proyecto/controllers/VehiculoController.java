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
import com.transporte.proyecto.entities.Vehiculo;
import com.transporte.proyecto.services.VehiculoServiceManager;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    VehiculoServiceManager vehiculoServiceManager;

    @PostMapping()
    public ResponseEntity<Vehiculo> save(@RequestBody Vehiculo vehiculo){
    	Vehiculo newVehiculo = vehiculoServiceManager.save(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVehiculo);
    }

    @GetMapping()
    public List<Vehiculo> listarVehiculos(){
        return this.vehiculoServiceManager.get();
    }
    
    @GetMapping("/{id}")
    public Vehiculo optenerVehiculoId( @PathVariable Long id){
        return this.vehiculoServiceManager.getById(id).get();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehiculo(@PathVariable Long id,
                                            @RequestBody Vehiculo vehiculo) {
        // Verifica si el vehiculo existe
        Optional<Vehiculo> vehiculoOptional = this.vehiculoServiceManager.getById(id);

        if (vehiculoOptional.isPresent()) {
        	Vehiculo vehiculoModificado = vehiculoOptional.get();

            // Actualiza los valores del vehiculo existente con los nuevos datos
            vehiculoModificado.setMatricula(vehiculo.getMatricula());
            vehiculoModificado.setModelo(vehiculo.getModelo());
            vehiculoModificado.setCapacidad(vehiculo.getCapacidad());
            vehiculoModificado.setTipoVehiculo(vehiculo.getTipoVehiculo());
            vehiculoModificado.setNombreEmpresaTransporte(vehiculo.getNombreEmpresaTransporte());
            vehiculoModificado.setRuta(vehiculo.getRuta());

            // Llama al servicio para actualizar el vehiculo
            Optional<Vehiculo> optionalVehiculo = this.vehiculoServiceManager.update(id, vehiculoModificado);

            return ResponseEntity.status(HttpStatus.CREATED).body(optionalVehiculo.get());
        }

        // Si el vehiculo no existe, devuelve un 404
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Vehiculo> deleteVehiculo(@PathVariable Long id){
        Optional<Vehiculo> optionalVehiculo = this.vehiculoServiceManager.delete(id);

        if(optionalVehiculo.isPresent()){
            return ResponseEntity.ok(optionalVehiculo.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
