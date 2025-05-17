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
import com.transporte.proyecto.dtos.BilleteDTO;
import com.transporte.proyecto.entities.Billete;
import com.transporte.proyecto.services.BilleteServiceManager;

@RestController
@RequestMapping("/api/billetes")
public class BilleteController {
    @Autowired
    BilleteServiceManager billeteServiceManager;
    
    @PostMapping()
    public ResponseEntity<Billete> save(@RequestBody Billete billete){
    	Billete newBillete = billeteServiceManager.save(billete);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBillete);
    }

    @GetMapping()
    public List<BilleteDTO> listarBilletes() {
        List<Billete> billetes = billeteServiceManager.get();
        return billetes.stream().map(BilleteDTO::new).toList();
    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<BilleteDTO> optenerBilleteId(@PathVariable Long id) {
        Optional<Billete> optionalBillete = billeteServiceManager.getById(id);

        if (optionalBillete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BilleteDTO dto = new BilleteDTO(optionalBillete.get());
        return ResponseEntity.ok(dto);
    }   
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBillete(@PathVariable Long id,
                                            @RequestBody Billete billete) {
        // Verifica si el billete existe
        Optional<Billete> billeteOptional = this.billeteServiceManager.getById(id);

        if (billeteOptional.isPresent()) {
        	Billete billeteModificado = billeteOptional.get();

            // Actualiza los valores del billete existente con los nuevos datos
        	billeteModificado.setRuta(billete.getRuta());
            billeteModificado.setHoraSalida(billete.getHoraSalida());
            billeteModificado.setHoraLlegada(billete.getHoraLlegada());
            billeteModificado.setNumeroAsiento(billete.getNumeroAsiento());
            billeteModificado.setPasajero(billete.getPasajero());
            billeteModificado.setFecha(billete.getFecha());
            billeteModificado.setPrecio(billete.getPrecio());

            // Llama al servicio para actualizar el billete
            Optional<Billete> optionalBillete = this.billeteServiceManager.update(id, billeteModificado);

            return ResponseEntity.status(HttpStatus.CREATED).body(optionalBillete.get());
        }

        // Si el billete no existe, devuelve un 404
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<BilleteDTO> deleteBillete(@PathVariable Long id){
        Optional<Billete> optionalBillete = this.billeteServiceManager.delete(id);

        if(optionalBillete.isPresent()){
        	BilleteDTO dto = new BilleteDTO(optionalBillete.get());
        	return ResponseEntity.ok(dto);

        }
        return ResponseEntity.notFound().build();
    }
}
