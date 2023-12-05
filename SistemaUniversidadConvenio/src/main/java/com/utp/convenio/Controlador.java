package com.utp.convenio;

import com.utp.convenio.modelo.Convenio;
import com.utp.convenio.modelo.Servicio;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Controlador {

    @Autowired
    private Servicio servi;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("listado", servi.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String nuevo(Model modelo) {
        modelo.addAttribute("titulo", "Ingresar nuevo dato");
        modelo.addAttribute("convenio", new Convenio());
        return "form";
    }

    @PostMapping("/save")
    public String save(@Valid Convenio convenio, Model model) {
        servi.save(convenio);
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model modelo) {
        Optional<Convenio> convenio = servi.findById(id);
        modelo.addAttribute("titulo", "Formulario Editar");
        modelo.addAttribute("convenio", convenio);
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, Model modelo) {
        servi.deleteById(id);
        return "redirect:/listar";
    }

}
