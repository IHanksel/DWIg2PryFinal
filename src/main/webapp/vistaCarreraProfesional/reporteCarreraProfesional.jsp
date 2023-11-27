<%-- 
    Document   : reporteCarreraProfesional
    Created on : 10 oct. 2023, 19:00:46
    Author     : abdel
--%>

<%@page import="modelo.CarreraProfesional"%>
<%@page import="dao.CarreraProfesionalDAO"%>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="/SistemaUniversidad/css/reset.css" />
        <link rel="stylesheet" href="/SistemaUniversidad/css/header.css" />
        <link rel="stylesheet" href="/SistemaUniversidad/css/style.css" />
        <link rel="stylesheet" href="/SistemaUniversidad/css/footer.css" />
        <link rel="stylesheet" href="/SistemaUniversidad/css/nosotros_y_jsp.css" />
        <link href="../css/reportes.css" rel="stylesheet" type="text/css"/>
        <link href="../css/adminlte.min.css" rel="stylesheet" type="text/css"/>

    </head>
    <body id="inicio">
        <header class="header-transparente">
            <div class="caja">
                <nav>
                    <ul>
                        <li>
                            <div class="dropdown">
                                <a class="dropbtn">Registrar</a>
                                <div class="dropdown-content">
                                    <a href="/SistemaUniversidad/vistaAlumno/registrarAlumno.jsp">Alumno</a>
                                    <a href="/SistemaUniversidad/vistaDocente/registrarDocente.jsp">Docente</a>
                                    <a href="/SistemaUniversidad/vistaAsignatura/registrarAsignatura.jsp">Asignatura</a>
                                    <a href="/SistemaUniversidad/vistaCarreraProfesional/registrarCarreraProfesional.jsp">Carrera profesional</a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="dropdown">
                                <a class="dropbtn">Mantenimiento</a>
                                <div class="dropdown-content">
                                    <a href="/SistemaUniversidad/vistaAlumno/mantenimientoAlumno.jsp">Alumno</a>
                                    <a href="/SistemaUniversidad/vistaDocente/mantenimientoDocente.jsp">Docente</a>
                                    <a href="/SistemaUniversidad/vistaAsignatura/mantenimientoAsignatura.jsp">Asignatura</a>
                                    <a href="/SistemaUniversidad/vistaCarreraProfesional/mantenimientoCarreraProfesional.jsp">Carrera profesional</a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="dropdown">
                                <a class="dropbtn">Reportes</a>
                                <div class="dropdown-content">
                                    <a href="/SistemaUniversidad/vistaAlumno/reporteAlumno.jsp">Alumno</a>
                                    <a href="/SistemaUniversidad/vistaDocente/reporteDocente.jsp">Docente</a>
                                    <a href="/SistemaUniversidad/vistaAsignatura/reporteAsignatura.jsp">Asignatura</a>
                                    <a href="#">Carrera profesional</a>
                                    <a href="/SistemaUniversidad/vistaMatricula/reporteMatricula.jsp">Matrícula</a>
                                    <a></a>
                                    <a href="/SistemaUniversidad/vistaAlumno/graficoAlumno.jsp">Gráfico Alumno</a>
                                    <a href="/SistemaUniversidad/vistaDocente/graficoDocente.jsp">Gráfico Docente</a>
                                </div>
                            </div>
                        </li>

                        <li>
                            <a href="/SistemaUniversidad/vistaMatricula/paginaMatricula.jsp" class="boton-blanco">Matrícula Alumnos</a>
                        </li>
                        <li><a href="/SistemaUniversidad/index.html">Inicio</a></li>
                        <li><a href="/SistemaUniversidad/nosotros.html">Nosotros</a></li>
                    </ul>
                </nav>
            </div>
        </header>

        <main>
            <section class="titulo">
                <div class="banner-wrapper">
                    <div class="texto">
                        <h1>Reporte de Carreras profesionales</h1>
                    </div>
                </div>
            </section>


            <%
                CarreraProfesionalDAO carreraProfesionalDAO = new CarreraProfesionalDAO();
                String carrera = "";
                String modalidad = "";

                if (request.getParameter("carrera") != null) {
                    carrera = request.getParameter("carrera");
                }
                if (request.getParameter("modalidad") != null) {
                    modalidad = request.getParameter("modalidad");
                }

                List<CarreraProfesional> carrerasPorModalidad = carreraProfesionalDAO.SeleccionarCarreraPorModalidad(modalidad);
            %>

            <form>
                <div class="form-group">

                    <label class="control-label">Modalidad:</label>    
                    <select class="form-control" name="modalidad" onchange="submit()">
                        <option>--Elegir--</option>
                        <%
                            for (CarreraProfesional carreraProfesional : carreraProfesionalDAO.SeleccionarModalidad()) {
                                if (carreraProfesional.getModalidad().equals(modalidad)) {
                                    out.print("<option value=" + carreraProfesional.getModalidad() + " selected>" + carreraProfesional.getModalidad());
                                } else {
                                    out.print("<option value=" + carreraProfesional.getModalidad() + ">" + carreraProfesional.getModalidad());
                                }
                            }
                        %>    
                    </select> 



                </div>    
            </form>
            <table class="table table-hover">
                <thead>
                    <tr class="text-white bg-black">
                        <th>Código<th>Nombre     
                    </tr>
                </thead>   
                <%
                    for (CarreraProfesional carreraProfesional : carrerasPorModalidad) {
                %>
                <tr>
                    <td><%=carreraProfesional.getCodigo()%></td>
                    <td><%=carreraProfesional.getNombre()%></td>

                </tr>
                <%
                    }
                %> 
            </table>

        </main>

        <footer>
            <p class="fondo">Todos los derechos reservados</p>
        </footer>
    </body>
</html>
