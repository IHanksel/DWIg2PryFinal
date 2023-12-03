/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import modelo.Alumno;
import dao.AlumnoDAO;

/**
 *
 * @author Ian Hansen
 */
@ManagedBean
@SessionScoped
public class AlumnoBean implements Serializable {

    private String apellido;
    private List<Alumno> listaAlumnos;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void filtrarAlumnos() {
        AlumnoDAO obj = new AlumnoDAO();
        listaAlumnos = (apellido != null) ? obj.filtrarPorNombreAlumno(apellido) : obj.listado();
    }

}
