package com.transporte.proyecto.dtos;

import com.transporte.proyecto.entities.Billete;

public class BilleteDTO {
	private Long id;
	private String fecha;
	private String hora;
	private Double precio;
	private String origen;
	private String destino;
	private String tipoVehiculo;
	
	public BilleteDTO() {
	}

	public BilleteDTO(Billete billete) {
		this.id = billete.getId();
		this.fecha = billete.getFecha();
		this.hora = billete.getHora();
		this.precio = billete.getPrecio();
		this.origen = billete.getOrigen().toString();
		this.destino = billete.getDestino().toString();
		this.tipoVehiculo = billete.getTipoVehiculo().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
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

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
}
