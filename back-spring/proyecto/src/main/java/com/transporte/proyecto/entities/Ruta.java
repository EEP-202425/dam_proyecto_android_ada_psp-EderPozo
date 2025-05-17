package com.transporte.proyecto.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ruta")
public class Ruta {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "origen", nullable = false)
	@NotNull(message = "El origen no puede ser nulo")
	private Ciudad origen;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "destino", nullable = false)
	@NotNull(message = "El destino no puede ser nulo")
	private Ciudad destino;
	
	@OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Vehiculo> vehiculos;

	public Ruta() {
	}

	public Ruta(Long id, @NotNull(message = "El origen no puede ser nulo") Ciudad origen,
			@NotNull(message = "El destino no puede ser nulo") Ciudad destino, List<Vehiculo> vehiculos) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.vehiculos = vehiculos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ciudad getOrigen() {
		return origen;
	}

	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}

	public Ciudad getDestino() {
		return destino;
	}

	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
}