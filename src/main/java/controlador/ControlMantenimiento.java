/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.AlumnoDAO;
import dao.AsignaturaDAO;
import dao.CarreraProfesionalDAO;
import dao.DocenteDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Asignatura;
import modelo.CarreraProfesional;
import modelo.Docente;

/**
 *
 * @author abdel
 */
public class ControlMantenimiento extends HttpServlet {

    AlumnoDAO alumnoDAO = new AlumnoDAO();
    CarreraProfesionalDAO carreraProfesionalDAO = new CarreraProfesionalDAO();
    DocenteDAO docenteDAO = new DocenteDAO();
    AsignaturaDAO asignaturaDAO = new AsignaturaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        if (opcion == 1) {
            modificarRegistroAlumno(request, response);
        }
        if (opcion == 2) {
            eliminarRegistroAlumno(request, response);
        }
        if (opcion == 3) {
            cambiarDatosModificadosDeAlumno(request, response);
        }

        if (opcion == 4) {
            modificarRegistroCarreraProfesional(request, response);
        }
        if (opcion == 5) {
            eliminarRegistroCarreraProfesional(request, response);
        }
        if (opcion == 6) {
            cambiarDatosModificadosDeCarreraProfesional(request, response);
        }

        if (opcion == 7) {
            modificarRegistroDocente(request, response);
        }
        if (opcion == 8) {
            eliminarRegistroDocente(request, response);
        }
        if (opcion == 9) {
            cambiarDatosModificadosDeDocente(request, response);
        }

        if (opcion == 10) {
            modificarAsignatura(request, response);
        }
        if (opcion == 11) {
            eliminarAsignatura(request, response);
        }
        if (opcion == 12) {
            cambiarDatosModificadosDeAsignatura(request, response);
        }
    }

    protected void eliminarRegistroAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        alumnoDAO.eliminarAlumnoYMatriculas(codigo);
        String pag = "/vistaAlumno/mantenimientoAlumno.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void modificarRegistroAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        Alumno alumno = alumnoDAO.consulta(codigo);
        request.setAttribute("dato", alumno);
        String pag = "/vistaAlumno/editarAlumno.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void cambiarDatosModificadosDeAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Alumno alumno = new Alumno();
        alumno.setCodigo(request.getParameter("codigo"));
        alumno.setNombres(request.getParameter("nombres"));
        alumno.setApellidos(request.getParameter("apellidos"));
        alumno.setDni(request.getParameter("dni"));
        alumno.setDireccion(request.getParameter("direccion"));
        alumno.setTelefono(request.getParameter("telefono"));
        alumno.setCarrera(request.getParameter("carrera"));

        alumnoDAO.modificarAlumno(alumno);
        String pag = "/vistaAlumno/mantenimientoAlumno.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void eliminarRegistroCarreraProfesional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        carreraProfesionalDAO.eliminarCarreraProfesional(codigo);
        String pag = "/vistaCarreraProfesional/mantenimientoCarreraProfesional.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void modificarRegistroCarreraProfesional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        CarreraProfesional carreraProfesional = carreraProfesionalDAO.consulta(codigo);
        request.setAttribute("dato", carreraProfesional);
        String pag = "/vistaCarreraProfesional/editarCarreraProfesional.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void cambiarDatosModificadosDeCarreraProfesional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CarreraProfesional carreraProfesional = new CarreraProfesional();
        carreraProfesional.setCodigo(request.getParameter("codigo"));
        carreraProfesional.setNombre(request.getParameter("nombre"));
        carreraProfesional.setModalidad(request.getParameter("modalidad"));

        carreraProfesionalDAO.modificarCarreraProfesional(carreraProfesional);
        String pag = "/vistaCarreraProfesional/mantenimientoCarreraProfesional.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void eliminarRegistroDocente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        docenteDAO.eliminarDocente(codigo);
        String pag = "/vistaDocente/mantenimientoDocente.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void modificarRegistroDocente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        Docente docente = docenteDAO.consulta(codigo);
        request.setAttribute("dato", docente);
        String pag = "/vistaDocente/editarDocente.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void cambiarDatosModificadosDeDocente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Docente docente = new Docente();
        docente.setCodigo(request.getParameter("codigo"));
        docente.setNombres(request.getParameter("nombres"));
        docente.setApellidos(request.getParameter("apellidos"));
        docente.setDni(request.getParameter("dni"));
        docente.setEspecialidad(request.getParameter("especialidad"));
        docente.setCorreo(request.getParameter("correo"));

        docenteDAO.modificarDocente(docente);
        String pag = "/vistaDocente/mantenimientoDocente.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void modificarAsignatura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        Asignatura asignatura = asignaturaDAO.consulta(codigo);
        request.setAttribute("dato", asignatura);
        String pag = "/vistaAsignatura/editarAsignatura.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void eliminarAsignatura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        asignaturaDAO.eliminarAsignatura(codigo);
        String pag = "/vistaAsignatura/mantenimientoAsignatura.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void cambiarDatosModificadosDeAsignatura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Asignatura asignatura = new Asignatura();
        asignatura.setCodigo(request.getParameter("codigo"));
        asignatura.setCarrera(request.getParameter("carrera"));
        asignatura.setNombre(request.getParameter("nombre"));
        asignatura.setCreditos(Integer.parseInt(request.getParameter("creditos")));

        asignaturaDAO.modificarAsignatura(asignatura);
        String pag = "/vistaAsignatura/mantenimientoAsignatura.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
