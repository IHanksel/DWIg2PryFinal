package controlador;

import dao.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;

public class ControlRegistro extends HttpServlet {

    AlumnoDAO alumnoDAO = new AlumnoDAO();
    DocenteDAO docenteDAO = new DocenteDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        if (opcion == 1) {
            ingresarDatosAlumno(request, response);
        }
        if (opcion == 2) {
            ingresarDatosDocente(request, response);
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
