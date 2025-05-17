package com.transporte.proyecto.services;

import java.util.List;
import java.util.Optional;
import com.transporte.proyecto.entities.Vehiculo;

public interface VehiculoService {
    public Vehiculo save(Vehiculo vehiculo);
    public List<Vehiculo> get();
    public Optional <Vehiculo> getById(Long id);
    public Optional <Vehiculo> update(Long id, Vehiculo vehiculo);
    public Optional <Vehiculo> delete(Long id);
}
