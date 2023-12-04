/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import dao.TallerDAO;
import beans.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class controlTaller implements Serializable {

    private Taller taller;
    private List<SelectItem> listataller;
    private List<Taller> listaller;
    private String texto;

    @PostConstruct
    public void init() {
        listataller = new ArrayList<>();
        listaller = new TallerDAO().LisTaller();

        for (Taller x : new TallerDAO().LisTaller()) {
            SelectItem item = new SelectItem(x.getId(), x.getNombre());
            listataller.add(item);
        }

    }

    public controlTaller() {
        texto = "";
        taller = new Taller();

    }

    public void grabar() {
        // Llamar al método graba() del TallerDAO sin establecer el ID
        new TallerDAO().graba(taller);

        // Actualizar la lista de talleres después de grabar
        listaller = new TallerDAO().LisTaller();

        // Reiniciar el objeto taller para futuras entradas
        taller = new Taller();
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public List<SelectItem> getListataller() {
        return listataller;
    }

    public void setListataller(List<SelectItem> listataller) {
        this.listataller = listataller;
    }

    public List<Taller> getListaller() {
        return listaller;
    }

    public void setListaller(List<Taller> listaller) {
        this.listaller = listaller;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
