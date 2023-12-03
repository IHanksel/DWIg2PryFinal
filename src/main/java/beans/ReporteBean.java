/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package beans;

import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import modelo.Alumno;
import dao.AlumnoDAO;
import javax.annotation.PostConstruct;

/**
 *
 * @author Ian Hansen
 */
@ManagedBean
@ViewScoped
public class ReporteBean implements Serializable {

    @PostConstruct
    public void init() {
        mostrarDatos();
    }

    private String consulta;
    private List<Alumno> listaAlumnos;

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void filtrarAlumnosPorNombre() {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        listaAlumnos = alumnoDAO.filtrarPorNombreAlumno(consulta);
    }

    public void mostrarDatos() {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        listaAlumnos = alumnoDAO.listado();
    }

}
