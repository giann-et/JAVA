package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "alimentos")
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int puntosHambre;
    private int puntosFelicidad;
    private int puntosSuciedad;
    private int puntosEnergia;

    public Alimento() {}

    public Alimento(String nombre, int puntosHambre, int puntosFelicidad, int puntosSuciedad, int puntosEnergia) {
        this.nombre = nombre;
        this.puntosHambre = puntosHambre;
        this.puntosFelicidad = puntosFelicidad;
        this.puntosSuciedad = puntosSuciedad;
        this.puntosEnergia = puntosEnergia;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPuntosHambre() { return puntosHambre; }
    public int getPuntosFelicidad() { return puntosFelicidad; }
    public int getPuntosSuciedad() { return puntosSuciedad; }
    public int getPuntosEnergia() { return puntosEnergia; }
}