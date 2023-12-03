/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.MatriculaDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Matricula;

/**
 *
 * @author abdel
 */
public class ControlMatricula extends HttpServlet {

    MatriculaDAO matriculaDAO = new MatriculaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        switch (opcion) {
            case 1:
                ingresarDatosMatricula(request, response);
                break;
            case 2:
                modificarRegistroMatricula(request, response);
                break;
            case 3:
                eliminarRegistroMatricula(request, response);
                break;
            case 4:
                cambiarDatosModificadosDeMatricula(request, response);
                break;
            default:
                
                break;
        }
    }

    protected void ingresarDatosMatricula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Matricula matricula = new Matricula();
            matricula.setCodigoAlumno(request.getParameter("codigo"));
            matricula.setSemestre(Integer.parseInt(request.getParameter("semestre")));
            matricula.setNombreAsignatura(request.getParameter("asignatura"));
            matricula.setTurno(request.getParameter("turno"));

            String fechaMatriculaStr = request.getParameter("fecha_matricula");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaMatricula = LocalDate.parse(fechaMatriculaStr, formatter);
            matricula.setFechaMatricula(fechaMatricula);

            matriculaDAO.guardar(matricula);
            String pagina = "/vistaMatricula/paginaMatricula.jsp";
            response.sendRedirect(request.getContextPath() + "/vistaMatricula/paginaMatricula.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la matrícula");
            request.getRequestDispatcher("/vistaMatricula/paginaMatricula.jsp").forward(request, response);
        }
    }

    protected void modificarRegistroMatricula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idMatricula = Integer.parseInt(request.getParameter("id"));
        Matricula matricula = matriculaDAO.consulta(idMatricula);
        request.setAttribute("dato", matricula);
        String pag = "/vistaMatricula/editarMatricula.jsp";
        request.getRequestDispatcher(pag).forward(request, response);

    }

    protected void cambiarDatosModificadosDeMatricula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Matricula matricula = new Matricula();

        matricula.setId(Integer.parseInt(request.getParameter("id")));
        matricula.setCodigoAlumno(request.getParameter("codigoAlumno"));
        int semestre = Integer.parseInt(request.getParameter("semestre"));
        matricula.setSemestre(semestre);
        matricula.setNombreAsignatura(request.getParameter("nombreAsignatura"));
        matricula.setTurno(request.getParameter("turno"));
        String fechaMatriculaStr = request.getParameter("fechaMatricula");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaMatricula = LocalDate.parse(fechaMatriculaStr, formatter);
        matricula.setFechaMatricula(fechaMatricula);

        matriculaDAO.modificarMatricula(matricula);

        String pag = "/vistaMatricula/paginaMatricula.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

    protected void eliminarRegistroMatricula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            matriculaDAO.eliminarMatricula(id);

            response.sendRedirect(request.getContextPath() + "/vistaMatricula/paginaMatricula.jsp");
        } catch (Exception e) {
            System.out.println("Error al eliminar registro de matrícula: " + e.getMessage());
        }
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
