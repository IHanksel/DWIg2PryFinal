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
public class ControlRegistro extends HttpServlet {

    AlumnoDAO alumnoDAO = new AlumnoDAO();
    DocenteDAO docenteDAO = new DocenteDAO();
    AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
    CarreraProfesionalDAO carreraProfesionalDAO = new CarreraProfesionalDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        if (opcion == 1) {
            ingresarDatosAlumno(request, response);
        }
        if (opcion == 2) {
            ingresarDatosDocente(request, response);
        }
        if (opcion == 3) {
            ingresarDatosCarreraProfesional(request, response);
        }
        if (opcion == 4) {
            ingresarDatosAsignatura(request, response);
        }
    }

    protected void ingresarDatosAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Alumno alumno = new Alumno();
        alumno.setCodigo(request.getParameter("codigo"));
        alumno.setNombres(request.getParameter("nombres"));
        alumno.setApellidos(request.getParameter("apellidos"));
        alumno.setDni(request.getParameter("dni"));
        alumno.setDireccion(request.getParameter("direccion"));
        alumno.setTelefono(request.getParameter("telefono"));

        String carreraSeleccionada = request.getParameter("carrera");
        alumno.setCarrera(carreraSeleccionada);

        alumnoDAO.guardar(alumno);
        String pagina = "/vistaAlumno/registrarAlumno.jsp";
        request.getRequestDispatcher(pagina).forward(request, response);
    }

    protected void ingresarDatosDocente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Docente docente = new Docente();
        docente.setCodigo(request.getParameter("codigo"));
        docente.setNombres(request.getParameter("nombres"));
        docente.setApellidos(request.getParameter("apellidos"));
        docente.setDni(request.getParameter("dni"));
        docente.setCorreo(request.getParameter("correo"));

        String carreraSeleccionada = request.getParameter("especialidad");
        docente.setEspecialidad(carreraSeleccionada);

        docenteDAO.guardar(docente);
        String pagina = "/vistaDocente/registrarDocente.jsp";
        request.getRequestDispatcher(pagina).forward(request, response);
    }

    protected void ingresarDatosCarreraProfesional(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CarreraProfesional carreraProfesional = new CarreraProfesional();
        carreraProfesional.setCodigo(request.getParameter("codigo"));
        carreraProfesional.setNombre(request.getParameter("nombre"));
        carreraProfesional.setModalidad(request.getParameter("modalidad"));

        carreraProfesionalDAO.guardar(carreraProfesional);
        String pagina = "/vistaCarreraProfesional/registrarCarreraProfesional.jsp";
        request.getRequestDispatcher(pagina).forward(request, response);
    }

    protected void ingresarDatosAsignatura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Asignatura asignatura = new Asignatura();
        asignatura.setCodigo(request.getParameter("codigo"));
        asignatura.setCarrera(request.getParameter("carrera"));
        asignatura.setNombre(request.getParameter("nombre"));
        asignatura.setCreditos(Integer.parseInt(request.getParameter("creditos")));

        asignaturaDAO.insertar(asignatura);
        String pagina = "/vistaAsignatura/registrarAsignatura.jsp";
        request.getRequestDispatcher(pagina).forward(request, response);
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
