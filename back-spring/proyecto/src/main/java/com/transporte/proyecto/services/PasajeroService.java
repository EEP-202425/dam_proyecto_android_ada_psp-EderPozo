package com.transporte.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.transporte.proyecto.dtos.PasajeroDTO;
import com.transporte.proyecto.entities.Pasajero;

public interface PasajeroService {
    public PasajeroDTO save(PasajeroDTO pasajeroDTO);
    public List<Pasajero> get();
    public Optional <Pasajero> getById(Long id);
    public Optional <Pasajero> update(Long id, Pasajero pasajero);
    public Optional <Pasajero> delete(Long id);
}
