/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;
import java.io.Serializable;
/**
 *
 * @author Samanta
 */
public class Taller implements Serializable{
   private String id;
   private String nombre;
   private String duracion;
   private String horario;
   private double monto;
   
   public Taller(String id, String nombre,String duracion, String horario, double monto) {
        this.id=id;
        this.nombre = nombre;
        this.duracion=duracion;
        this.horario=horario;
        this.monto = monto;
    }

   public Taller(){
       
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

   
}
