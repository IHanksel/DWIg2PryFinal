/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author julin
 */
import java.io.Serializable;

public class Certificacion implements Serializable {

    private String id;
    private String nombre;
    private String empresa;
    private double costo;
    private String certificado;
    private int duracion;

    public Certificacion(String id, String nombre, String empresa, double costo, String certificado, int duracion) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.costo = costo;
        this.duracion = duracion;
        this.certificado = certificado;
    }

    public Certificacion() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getCertificado() {
        return certificado;
    }
    
    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

}
