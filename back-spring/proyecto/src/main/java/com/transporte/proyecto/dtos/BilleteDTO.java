package com.transporte.proyecto.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import com.transporte.proyecto.entities.Billete;

public class BilleteDTO {
	private Long id;
    private LocalDate fecha;
    private LocalTime horaSalida;
    private LocalTime horaLlegada;
    private Double precio;
    private String numeroAsiento;
    private String origen;
    private String destino;

    public BilleteDTO(Billete billete) {
        this.id = billete.getId();
        this.fecha = billete.getFecha();
        this.horaSalida = billete.getHoraSalida();
        this.horaLlegada = billete.getHoraLlegada();
        this.precio = billete.getPrecio();
        this.numeroAsiento = billete.getNumeroAsiento();
        this.origen = billete.getRuta().getOrigen().toString();
        this.destino = billete.getRuta().getDestino().toString();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
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

	public String getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
}
