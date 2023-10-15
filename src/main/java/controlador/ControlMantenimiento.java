package controlador;

import com.google.gson.Gson;
import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlMantenimiento extends HttpServlet {

    AlumnoDAO alumnoDAO = new AlumnoDAO();
    DocenteDAO docenteDAO = new DocenteDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        if (opcion == 1) {
            filtrarAlumnosPorNombre(request, response);
        }
        if (opcion == 2) {
            eliminarRegistroAlumno(request, response);
        }
    }

    protected void filtrarAlumnosPorNombre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String consulta = request.getParameter("consulta");
        Gson gs = new Gson();
        out.print(gs.toJson(alumnoDAO.filtrarPorNombreAlumno(consulta)));
    }

    protected void eliminarRegistroAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        alumnoDAO.eliminarRegistroAlumno(codigo);
        String pag = "/vistaAlumno/mantenimientoAlumno.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }
    
    */protected void filtrarDocentesPorNombre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String consulta = request.getParameter("consulta");
        Gson gs = new Gson();
        out.print(gs.toJson(docenteDAO.filtrar(consulta)));
    }
    
    protected void eliminarRegistroDocente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        docenteDAO.eliminarRegistroDocente(codigo);
        String pag = "/vistaDocente/mantenimientoDocente.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }*/

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
