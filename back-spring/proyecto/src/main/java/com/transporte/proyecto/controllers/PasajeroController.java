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
import com.transporte.proyecto.dtos.PasajeroDTO;
import com.transporte.proyecto.entities.Pasajero;
import com.transporte.proyecto.services.PasajeroServiceManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "Pasajeros", description = "Operaciones relacionadas con los pasajeros")
@RestController
@RequestMapping("/api/pasajeros")
public class PasajeroController {
    @Autowired
    PasajeroServiceManager pasajeroServiceManager;
    
    @Operation(summary = "Crear un nuevo pasajero")
    @ApiResponse(responseCode = "201", description = "Pasajero creado exitosamente")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PasajeroDTO> save(@RequestBody PasajeroDTO pasajeroDTO) {
        PasajeroDTO newPasajeroDTO = pasajeroServiceManager.save(pasajeroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPasajeroDTO);
    }

    @Operation(summary = "Listar todos los pasajeros")
    @GetMapping()
    public List<PasajeroDTO> listarPasajeros(){
    	List<Pasajero> pasajeros = pasajeroServiceManager.get();
        return pasajeros.stream().map(PasajeroDTO::new).toList();
    }
    
    @Operation(summary = "Obtener un pasajero por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pasajero encontrado"),
        @ApiResponse(responseCode = "404", description = "Pasajero no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PasajeroDTO> optenerPasajeroId( @PathVariable Long id){
    	Optional<Pasajero> optionalPasajero = pasajeroServiceManager.getById(id);

        if (optionalPasajero.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PasajeroDTO dto = new PasajeroDTO(optionalPasajero.get());
        return ResponseEntity.ok(dto);
    }
    
    @Operation(summary = "Actualizar un pasajero existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pasajero actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Pasajero no encontrado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<PasajeroDTO> updatePasajero(@PathVariable Long id,
                                            @RequestBody PasajeroDTO pasajeroDTO) {
        // Verifica si el pasajero existe
        Optional<Pasajero> pasajeroOptional = this.pasajeroServiceManager.getById(id);

        if (pasajeroOptional.isPresent()) {
        	Pasajero pasajeroModificado = pasajeroOptional.get();

            // Actualiza los valores del billete existente con los nuevos datos
        	pasajeroModificado.setNombre(pasajeroDTO.getNombre());
            pasajeroModificado.setApellidoPaterno(pasajeroDTO.getApellidoPaterno());
            pasajeroModificado.setApellidoMaterno(pasajeroDTO.getApellidoMaterno());
            pasajeroModificado.setTelefono(pasajeroDTO.getTelefono());
            pasajeroModificado.setEmail(pasajeroDTO.getEmail());

            // Llama al servicio para actualizar el pasajero
            Optional<Pasajero> optionalPasajero = this.pasajeroServiceManager.update(id, pasajeroModificado);

            return ResponseEntity.status(HttpStatus.CREATED).body(new PasajeroDTO(optionalPasajero.get()));
        }

        // Si el pasajero no existe, devuelve un 404
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Eliminar un pasajero por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pasajero eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Pasajero no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<PasajeroDTO> deletePasajero(@PathVariable Long id){
        Optional<Pasajero> optionalPasajero = this.pasajeroServiceManager.delete(id);

        if(optionalPasajero.isPresent()){
        	PasajeroDTO dto = new PasajeroDTO(optionalPasajero.get());
        	return ResponseEntity.ok(dto);

        }
        return ResponseEntity.notFound().build();
    }
}
