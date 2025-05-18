
package com.transporte.proyecto.controllers;

import com.transporte.proyecto.entities.Ciudad;
import com.transporte.proyecto.entities.TipoVehiculo;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TransporteController {

    @GetMapping("/origenes")
    public List<String> getOrigenes() {
        return Arrays.stream(Ciudad.values())
                .map(Enum::name)
                .toList();
    }

    @GetMapping("/destinos")
    public List<String> getDestinos() {
        return Arrays.stream(Ciudad.values())
                .map(Enum::name)
                .toList();
    }

    @GetMapping("/tipos-transporte")
    public List<String> getTiposTransporte() {
        return Arrays.stream(TipoVehiculo.values())
                .map(Enum::name)
                .toList();
    }

    @GetMapping("/precio")
    public double getPrecio(@RequestParam String origen, @RequestParam String destino, @RequestParam String tipo) {
        return (origen.length() + destino.length() + tipo.length()) * 2.5;
    }
}
