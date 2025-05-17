package com.transporte.proyecto.services;

import java.util.List;
import java.util.Optional;
import com.transporte.proyecto.entities.Billete;

public interface BilleteService {
    public Billete save(Billete billete);
    public List<Billete> get();
    public Optional <Billete> getById(Long id);
    public Optional <Billete> update(Long id, Billete billete);
    public Optional <Billete> delete(Long id);
}
