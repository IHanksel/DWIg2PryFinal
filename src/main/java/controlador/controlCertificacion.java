/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author julin
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import dao.CertificacionDAO;
import beans.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class controlCertificacion implements Serializable {

    private Certificacion certificacion;
    private List<SelectItem> listaCertificaciones;
    private List<Certificacion> listaCertificacion;
    private String texto;

    @PostConstruct
    public void init() {
        listaCertificaciones = new ArrayList<>();
        listaCertificacion = new CertificacionDAO().ListCertificacion();

        for (Certificacion x : listaCertificacion) {
            SelectItem item = new SelectItem(x.getId(), x.getNombre());
            listaCertificaciones.add(item);
        }
    }

    public controlCertificacion() {
        texto = "";
        certificacion = new Certificacion();
    }

    public void grabar() {

        new CertificacionDAO().graba(certificacion);

        listaCertificacion = new CertificacionDAO().ListCertificacion();

        certificacion = new Certificacion();
    }

    public Certificacion getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(Certificacion certificacion) {
        this.certificacion = certificacion;
    }

    public List<SelectItem> getListaCertificaciones() {
        return listaCertificaciones;
    }

    public void setListaCertificaciones(List<SelectItem> listaCertificaciones) {
        this.listaCertificaciones = listaCertificaciones;
    }

    public List<Certificacion> getListaCertificacion() {
        return listaCertificacion;
    }

    public void setListaCertificacion(List<Certificacion> listaCertificacion) {
        this.listaCertificacion = listaCertificacion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
