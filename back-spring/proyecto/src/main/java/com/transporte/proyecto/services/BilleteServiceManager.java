package com.transporte.proyecto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transporte.proyecto.entities.Billete;
import com.transporte.proyecto.repositories.BilleteRepository;

@Service
public class BilleteServiceManager implements BilleteService{

    @Autowired
    BilleteRepository billeteRepository;
    
    @Override
    @Transactional
	public Billete save(Billete billete) {
    	return this.billeteRepository.save(billete);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Billete> get() {
		return (List<Billete>) this.billeteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Billete> getById(Long id) {
		return this.billeteRepository.findById(id);
	}

	@Override
	@Transactional
	public Optional<Billete> update(Long id, Billete billete) {
        Optional<Billete> billeteAValidar = this.getById(id);

        if(billeteAValidar.isPresent()){
        	Billete billeteModificado = billeteAValidar.orElseThrow();

            billeteModificado.setRuta(billete.getRuta());
            billeteModificado.setHoraSalida(billete.getHoraSalida());
            billeteModificado.setHoraLlegada(billete.getHoraLlegada());
            billeteModificado.setNumeroAsiento(billete.getNumeroAsiento());
            billeteModificado.setPasajero(billete.getPasajero());
            billeteModificado.setFecha(billete.getFecha());
            billeteModificado.setPrecio(billete.getPrecio());

            return Optional.of(this.billeteRepository.save(billeteModificado));
        }

        return billeteAValidar;
	}

	@Override
	@Transactional
	public Optional<Billete> delete(Long id) {
		Optional<Billete> billete = this.getById(id);
        if(billete.isPresent()){
            this.billeteRepository.deleteById(id);
        }
        return billete;
	}
}
