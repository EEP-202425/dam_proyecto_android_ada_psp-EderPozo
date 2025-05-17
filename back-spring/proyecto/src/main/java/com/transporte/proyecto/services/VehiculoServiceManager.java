package com.transporte.proyecto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.transporte.proyecto.entities.Vehiculo;
import com.transporte.proyecto.repositories.VehiculoRepository;

@Service
public class VehiculoServiceManager implements VehiculoService{

    @Autowired
    VehiculoRepository vehiculoRepository;
    @Override
    @Transactional
	public Vehiculo save(Vehiculo vehiculo) {
    	return this.vehiculoRepository.save(vehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> get() {
		return (List<Vehiculo>) this.vehiculoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Vehiculo> getById(Long id) {
		return this.vehiculoRepository.findById(id);
	}

	@Override
	@Transactional
	public Optional<Vehiculo> update(Long id, Vehiculo vehiculo) {
        Optional<Vehiculo> vehiculoAValidar = this.getById(id);

        if(vehiculoAValidar.isPresent()){
        	Vehiculo vehiculoModificado = vehiculoAValidar.orElseThrow();

            vehiculoModificado.setMatricula(vehiculo.getMatricula());
            vehiculoModificado.setModelo(vehiculo.getModelo());
            vehiculoModificado.setCapacidad(vehiculo.getCapacidad());
            vehiculoModificado.setTipoVehiculo(vehiculo.getTipoVehiculo());
            vehiculoModificado.setNombreEmpresaTransporte(vehiculo.getNombreEmpresaTransporte());
            vehiculoModificado.setRuta(vehiculo.getRuta());

            return Optional.of(this.vehiculoRepository.save(vehiculoModificado));
        }

        return vehiculoAValidar;
	}

	@Override
	@Transactional
	public Optional<Vehiculo> delete(Long id) {
		Optional<Vehiculo> vehiculo = this.getById(id);
        if(vehiculo.isPresent()){
            this.vehiculoRepository.deleteById(id);
        }
        return vehiculo;
	}
}
