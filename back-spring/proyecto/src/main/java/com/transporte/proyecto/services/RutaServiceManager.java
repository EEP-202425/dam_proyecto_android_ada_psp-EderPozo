package com.transporte.proyecto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transporte.proyecto.entities.Ruta;
import com.transporte.proyecto.repositories.RutaRepository;

@Service
public class RutaServiceManager implements RutaService{

    @Autowired
    RutaRepository rutaRepository;
    @Override
    @Transactional
	public Ruta save(Ruta ruta) {
    	return this.rutaRepository.save(ruta);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ruta> get() {
		return (List<Ruta>) this.rutaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Ruta> getById(Long id) {
		return this.rutaRepository.findById(id);
	}

	@Override
	@Transactional
	public Optional<Ruta> update(Long id, Ruta ruta) {
        Optional<Ruta> rutaAValidar = this.getById(id);

        if(rutaAValidar.isPresent()){
        	Ruta rutaModificado = rutaAValidar.orElseThrow();

            rutaModificado.setOrigen(ruta.getOrigen());
            rutaModificado.setDestino(ruta.getDestino());
            rutaModificado.setVehiculos(ruta.getVehiculos());

            return Optional.of(this.rutaRepository.save(rutaModificado));
        }

        return rutaAValidar;
	}

	@Override
	@Transactional
	public Optional<Ruta> delete(Long id) {
		Optional<Ruta> ruta = this.getById(id);
        if(ruta.isPresent()){
            this.rutaRepository.deleteById(id);
        }
        return ruta;
	}
}
