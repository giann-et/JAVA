package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "pou_estado")
public class Pou {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int hambre;
    private int felicidad;
    private int suciedad;
    private int energia;

    public Pou() {}

    public Pou(String nombre) {
        this.nombre = nombre;
        this.hambre = 40;
        this.felicidad = 60;
        this.suciedad = 15;
        this.energia = 75;
    }

    public void verificarLimites() {
        if (this.hambre < 0) this.hambre = 0;
        if (this.hambre > 100) this.hambre = 100;
        if (this.felicidad < 0) this.felicidad = 0;
        if (this.felicidad > 100) this.felicidad = 100;
        if (this.suciedad < 0) this.suciedad = 0;
        if (this.suciedad > 100) this.suciedad = 100;
        if (this.energia < 0) this.energia = 0;
        if (this.energia > 100) this.energia = 100;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public int getHambre() { return hambre; }
    public void setHambre(int hambre) { this.hambre = hambre; }
    public int getFelicidad() { return felicidad; }
    public void setFelicidad(int felicidad) { this.felicidad = felicidad; }
    public int getSuciedad() { return suciedad; }
    public void setSuciedad(int suciedad) { this.suciedad = suciedad; }
    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = energia; }
}