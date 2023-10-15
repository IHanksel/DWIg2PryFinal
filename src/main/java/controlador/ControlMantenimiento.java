package controlador;

import com.google.gson.Gson;
import dao.*;
import modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlMantenimiento extends HttpServlet {

    AlumnoDAO alumnoDAO = new AlumnoDAO();
    DocenteDAO docenteDAO = new DocenteDAO();
    
    // Constantes de las URL de las páginas
    private static final String PAG_MANTENIMIENTO_ALUMNO = "/vistaAlumno/mantenimientoAlumno.jsp";
    private static final String PAG_MANTENIMIENTO_DOCENTE = "/vistaDocente/mantenimientoDocente.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        switch (opcion) {
            case 1:
                filtrarAlumnosPorNombre(request, response);
                break;
            case 2:
                eliminarRegistro(request, response, alumnoDAO, PAG_MANTENIMIENTO_ALUMNO);
                break;
            case 3:
                modificarRegistroAlumno(request, response);
                break;
            case 4:
                filtrarDocentesPorNombre(request, response);
                break;
            case 5:
                eliminarRegistro(request, response, docenteDAO, PAG_MANTENIMIENTO_DOCENTE);
                break;
            case 6:
                modificarRegistroDocente(request, response);
                break;
            default:
                // Manejar opción no válida, si es necesario
                break;
        }
    }

    protected void filtrarAlumnosPorNombre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String consulta = request.getParameter("consulta");
        Gson gs = new Gson();
        out.print(gs.toJson(alumnoDAO.filtrarPorNombreAlumno(consulta)));
    }
    
    protected void modificarRegistroAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        String nuevosNombres = request.getParameter("nuevosNombres");
        String nuevosApellidos = request.getParameter("nuevosApellidos");
        String nuevoDni = request.getParameter("nuevoDni");
        String nuevaDireccion = request.getParameter("nuevaDireccion");
        String nuevoTelefono = request.getParameter("nuevoTelefono");
        String nuevaCarrera = request.getParameter("nuevaCarrera");

        Alumno alumno = new Alumno();
        alumno.setCodigo(codigo);
        alumno.setNombres(nuevosNombres);
        alumno.setApellidos(nuevosApellidos);
        alumno.setDni(nuevoDni);
        alumno.setDireccion(nuevaDireccion);
        alumno.setTelefono(nuevoTelefono);
        alumno.setCarrera(nuevaCarrera);

        alumnoDAO.modificarRegistroAlumno(alumno);

        String pag = "/vistaAlumno/mantenimientoAlumno.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }
    
    // El "eliminarRegistroAlumno" anterior [descomentar en caso de errores con el actual]
    /*protected void eliminarRegistroAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        alumnoDAO.eliminarRegistroAlumno(codigo);
        String pag = PAG_MANTENIMIENTO_ALUMNO;
        request.getRequestDispatcher(pag).forward(request, response);
    }*/
    
    protected void eliminarRegistroAlumno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
           String codigo = request.getParameter("codigo");
           alumnoDAO.eliminarRegistroAlumno(codigo);
           String pag = PAG_MANTENIMIENTO_ALUMNO;
           request.getRequestDispatcher(pag).forward(request, response);
       } catch (Exception e) {
           request.setAttribute("error", "Error al eliminar el registro del alumno: " + e.getMessage());
           request.getRequestDispatcher("/error.jsp").forward(request, response);
       }
    }

    
    protected void filtrarDocentesPorNombre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String consulta = request.getParameter("consulta");
        Gson gs = new Gson();
        out.print(gs.toJson(docenteDAO.filtrarPorNombreDocente(consulta)));
    }
    
    protected void modificarRegistroDocente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        String nuevosNombres = request.getParameter("nuevosNombres");
        String nuevosApellidos = request.getParameter("nuevosApellidos");
        String nuevoDni = request.getParameter("nuevoDni");
        String nuevaEspecialidad = request.getParameter("nuevaEspecialidad");
        String nuevoCorreo = request.getParameter("nuevoCorreo");

        Docente docente = new Docente();
        docente.setCodigo(codigo);
        docente.setNombres(nuevosNombres);
        docente.setApellidos(nuevosApellidos);
        docente.setDni(nuevoDni);
        docente.setEspecialidad(nuevaEspecialidad);
        docente.setCorreo(nuevoCorreo);

        docenteDAO.modificarRegistroDocente(docente);

        String pag = "/vistaDocente/mantenimientoDocente.jsp";
        request.getRequestDispatcher(pag).forward(request, response);
    }

   // El "eliminarRegistroDocente" anterior [descomentar en caso de errores con el actual]
   /*protected void eliminarRegistroDocente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        docenteDAO.eliminarRegistroDocente(codigo);
        String pag = PAG_MANTENIMIENTO_DOCENTE;
        request.getRequestDispatcher(pag).forward(request, response);
    }*/
    
    protected void eliminarRegistroDocente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
           String codigo = request.getParameter("codigo");
           docenteDAO.eliminarRegistroDocente(codigo);
           String pag = PAG_MANTENIMIENTO_DOCENTE;
           request.getRequestDispatcher(pag).forward(request, response);
       } catch (Exception e) {
           request.setAttribute("error", "Error al eliminar el registro del docente: " + e.getMessage());
           request.getRequestDispatcher("/error.jsp").forward(request, response);
       }
    }
    
    private void eliminarRegistro(HttpServletRequest request, HttpServletResponse response, Object dao, String page)
            throws ServletException, IOException {
        try {
            String codigo = request.getParameter("codigo");

            if (dao instanceof AlumnoDAO) {
                ((AlumnoDAO) dao).eliminarRegistroAlumno(codigo);
            } else if (dao instanceof DocenteDAO) {
                ((DocenteDAO) dao).eliminarRegistroDocente(codigo);
            }

            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al eliminar el registro: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
