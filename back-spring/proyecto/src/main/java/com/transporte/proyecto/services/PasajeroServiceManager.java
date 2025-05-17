package com.transporte.proyecto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transporte.proyecto.entities.Pasajero;
import com.transporte.proyecto.repositories.PasajeroRepository;

@Service
public class PasajeroServiceManager implements PasajeroService{

	@Autowired
	PasajeroRepository pasajeroRepository;
    @Override
    @Transactional
	public Pasajero save(Pasajero pasajero) {
    	return this.pasajeroRepository.save(pasajero);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pasajero> get() {
		return (List<Pasajero>) this.pasajeroRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pasajero> getById(Long id) {
		return this.pasajeroRepository.findById(id);
	}

	@Override
	@Transactional
	public Optional<Pasajero> update(Long id, Pasajero pasajero) {
        Optional<Pasajero> pasajeroAValidar = this.getById(id);

        if(pasajeroAValidar.isPresent()){
        	Pasajero pasajeroModificado = pasajeroAValidar.orElseThrow();
        	
        	pasajeroModificado.setNombre(pasajero.getNombre());
            pasajeroModificado.setApellidoPaterno(pasajero.getApellidoPaterno());
            pasajeroModificado.setApellidoMaterno(pasajero.getApellidoMaterno());
            pasajeroModificado.setTelefono(pasajero.getTelefono());
            pasajeroModificado.setEmail(pasajero.getEmail());

            return Optional.of(this.pasajeroRepository.save(pasajeroModificado));
        }

        return pasajeroAValidar;
	}

	@Override
	@Transactional
	public Optional<Pasajero> delete(Long id) {
		Optional<Pasajero> pasajero = this.getById(id);
        if(pasajero.isPresent()){
            this.pasajeroRepository.deleteById(id);
        }
        return pasajero;
	}
}
