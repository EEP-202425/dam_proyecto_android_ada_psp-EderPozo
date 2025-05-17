package com.transporte.proyecto.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "billete")
public class Billete {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ruta_id")
	@NotNull(message = "La ruta no puede ser nula")
	private Ruta ruta;
	
	@NotNull(message = "La fecha no puede ser nula")
	private LocalDate fecha;

	@NotNull(message = "La hora de salida no puede ser nula")
	private LocalTime horaSalida;

	@NotNull(message = "La hora de llegada no puede ser nula")
	private LocalTime horaLlegada;

	private Double precio;

	private String numeroAsiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pasajero_id", nullable = false)
	@JsonBackReference
	private Pasajero pasajero;

	public Billete() {
	}
	
	public Billete(Long id, Ruta ruta, @NotNull(message = "La fecha no puede ser nula") LocalDate fecha,
			@NotNull(message = "La hora de salida no puede ser nula") LocalTime horaSalida,
			@NotNull(message = "La hora de llegada no puede ser nula") LocalTime horaLlegada, Double precio,
			String numeroAsiento, Pasajero pasajero) {
		super();
		this.id = id;
		this.ruta = ruta;
		this.fecha = fecha;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.precio = precio;
		this.numeroAsiento = numeroAsiento;
		this.pasajero = pasajero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public LocalTime getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(LocalTime horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
}