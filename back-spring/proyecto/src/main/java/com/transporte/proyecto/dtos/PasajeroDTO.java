package com.transporte.proyecto.dtos;

import com.transporte.proyecto.entities.Pasajero;

public class PasajeroDTO {
	private Long id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String telefono;
	private String email;
		
	public PasajeroDTO() {
	}

	public PasajeroDTO(Pasajero pasajero) {
		this.id = pasajero.getId();
		this.nombre = pasajero.getNombre();
		this.apellidoPaterno = pasajero.getApellidoPaterno();
		this.apellidoMaterno = pasajero.getApellidoMaterno();
		this.telefono = pasajero.getTelefono();
		this.email = pasajero.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
