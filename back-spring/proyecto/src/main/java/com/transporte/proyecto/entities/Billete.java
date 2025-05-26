package com.transporte.proyecto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "billete")
public class Billete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha no puede ser nula")
    private String fecha;

    @NotNull(message = "La hora no puede ser nula")
    private String hora;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "La ciudad de origen no puede ser nula")
    private Ciudad origen;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "La ciudad de destino no puede ser nula")
    private Ciudad destino;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo de vehículo no puede ser nulo")
    private TipoVehiculo tipoVehiculo;

    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pasajero_id")
    @JsonBackReference
    private Pasajero pasajero;

    // Getters y Setters

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

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }
}
