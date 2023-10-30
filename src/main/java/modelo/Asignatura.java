
package modelo;

/**
 *
 * @author abdel
 */
public class Asignatura {
    private String codigo; //Estándar de código: AS001
    private String carrera;
    private String nombre;
    private int creditos;

    public Asignatura(String codigo, String carrera, String nombre, int creditos) {
        this.codigo = codigo;
        this.carrera = carrera;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public Asignatura() {
        
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
}
