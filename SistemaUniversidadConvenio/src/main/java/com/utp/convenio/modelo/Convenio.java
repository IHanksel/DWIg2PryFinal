package com.utp.convenio.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "convenio")
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre_universidad;
    private String pais;
    private String tipo;
    private int duracion_mes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_universidad() {
        return nombre_universidad;
    }

    public void setNombre_universidad(String nombre_universidad) {
        this.nombre_universidad = nombre_universidad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDuracion_mes() {
        return duracion_mes;
    }

    public void setDuracion_mes(int duracion_mes) {
        this.duracion_mes = duracion_mes;
    }

    
}
