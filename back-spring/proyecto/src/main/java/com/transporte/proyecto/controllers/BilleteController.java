package com.transporte.proyecto.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.transporte.proyecto.dtos.BilleteDTO;
import com.transporte.proyecto.entities.Billete;
import com.transporte.proyecto.services.BilleteServiceManager;

@RestController
@RequestMapping("/api/billetes")
public class BilleteController {

    @Autowired
    BilleteServiceManager billeteServiceManager;

    @PostMapping()
    public ResponseEntity<Billete> save(@RequestBody Billete billete) {
        Billete newBillete = billeteServiceManager.save(billete);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBillete);
    }

    @GetMapping()
    public List<BilleteDTO> listarBilletes() {
        List<Billete> billetes = billeteServiceManager.get();
        return billetes.stream().map(BilleteDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BilleteDTO> obtenerBilleteId(@PathVariable Long id) {
        Optional<Billete> optionalBillete = billeteServiceManager.getById(id);

        return optionalBillete
                .map(billete -> ResponseEntity.ok(new BilleteDTO(billete)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBillete(@PathVariable Long id, @RequestBody Billete billete) {
        Optional<Billete> billeteOptional = this.billeteServiceManager.getById(id);

        if (billeteOptional.isPresent()) {
            Billete billeteModificado = billeteOptional.get();

            billeteModificado.setOrigen(billete.getOrigen());
            billeteModificado.setDestino(billete.getDestino());
            billeteModificado.setTipoVehiculo(billete.getTipoVehiculo());
            billeteModificado.setFecha(billete.getFecha());
            billeteModificado.setHora(billete.getHora());
            billeteModificado.setPrecio(billete.getPrecio());
            billeteModificado.setPasajero(billete.getPasajero());

            Optional<Billete> optionalBillete = this.billeteServiceManager.update(id, billeteModificado);
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalBillete.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BilleteDTO> deleteBillete(@PathVariable Long id) {
        Optional<Billete> optionalBillete = this.billeteServiceManager.delete(id);

        return optionalBillete
                .map(billete -> ResponseEntity.ok(new BilleteDTO(billete)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
