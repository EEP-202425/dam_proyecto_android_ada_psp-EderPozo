package com.transporte.proyecto.services;

import java.util.List;
import java.util.Optional;
import com.transporte.proyecto.entities.Ruta;

public interface RutaService {
    public Ruta save(Ruta ruta);
    public List<Ruta> get();
    public Optional <Ruta> getById(Long id);
    public Optional <Ruta> update(Long id, Ruta ruta);
    public Optional <Ruta> delete(Long id);
}
