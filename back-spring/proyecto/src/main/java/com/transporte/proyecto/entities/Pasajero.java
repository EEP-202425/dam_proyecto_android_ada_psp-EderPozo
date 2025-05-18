package com.transporte.proyecto.entities;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pasajero")
public class Pasajero {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "El nombre no puede ser nulo")
	private String nombre;
	
	@NotNull(message = "El apellido paterno no puede ser nulo")
	private String apellidoPaterno;
	
	@NotNull(message = "El apellido materno no puede ser nulo")
	private String apellidoMaterno;
	
	@NotNull(message = "El teléfono no puede ser nulo")
	private String telefono;
	
	@NotNull(message = "El email no puede ser nulo")
	private String email;
		
	@OneToMany(mappedBy = "pasajero", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Billete> billetes = new ArrayList<>();

	public Pasajero() {
	}

	public Pasajero(Long id, @NotNull(message = "El nombre no puede ser nulo") String nombre,
			@NotNull(message = "El apellido paterno no puede ser nulo") String apellidoPaterno, 
			@NotNull(message = "El apellido materno no puede ser nulo") String apellidoMaterno,
			@NotNull(message = "El teléfono no puede ser nulo")String telefono, 
			@NotNull(message = "El email no puede ser nulo")String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.telefono = telefono;
		this.email = email;
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
	public List<Billete> getBilletes() {
		return billetes;
	}

	public void setBilletes(List<Billete> billetes) {
		this.billetes = billetes;
	}
	
	public void addBillete(Billete billete) {
		billetes.add(billete);
		billete.setPasajero(this);
	}

	public void removeBillete(Billete billete) {
		billetes.remove(billete);
		billete.setPasajero(null);
	}
}