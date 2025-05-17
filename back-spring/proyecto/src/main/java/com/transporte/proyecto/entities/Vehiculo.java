package com.transporte.proyecto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "La matricula no puede ser nula")
	private String matricula;
	
	@NotNull(message = "El modelo no puede ser nulo")
	private String modelo;
	
	@NotNull(message = "La capacidad del vehiculo no puede ser nula")
	private int capacidad;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_vehiculo", nullable = false)
	@NotNull(message = "El tipo de vehiculo no puede ser nulo")
	private TipoVehiculo tipoVehiculo;
	
	private String nombreEmpresaTransporte;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "ruta_id", nullable = false)
    @JsonBackReference
	private Ruta ruta;

	public Vehiculo() {
	}

	public Vehiculo(Long id, @NotNull(message = "La matricula no puede ser nula") String matricula,
			@NotNull(message = "El modelo no puede ser nulo") String modelo,
			@NotNull(message = "La capacidad del vehiculo no puede ser nula") int capacidad,
			@NotNull(message = "El tipo de vehiculo no puede ser nulo") TipoVehiculo tipoVehiculo,
			String nombreEmpresaTransporte, Ruta ruta) {
		this.id = id;
		this.matricula = matricula;
		this.modelo = modelo;
		this.capacidad = capacidad;
		this.tipoVehiculo = tipoVehiculo;
		this.nombreEmpresaTransporte = nombreEmpresaTransporte;
		this.ruta = ruta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getNombreEmpresaTransporte() {
		return nombreEmpresaTransporte;
	}

	public void setNombreEmpresaTransporte(String nombreEmpresaTransporte) {
		this.nombreEmpresaTransporte = nombreEmpresaTransporte;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
}