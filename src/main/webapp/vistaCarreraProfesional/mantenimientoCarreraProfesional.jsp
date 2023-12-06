<%-- 
    Document   : mantenimientoCarreraProfesional
    Created on : 10 oct. 2023, 19:00:58
    Author     : abdel
--%>

<%@page import="modelo.CarreraProfesional"%>
<%@page import="dao.CarreraProfesionalDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="/SistemaUniversidad/bootstrap/adminlte.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="/SistemaUniversidad/css/reset.css" />
        <link rel="stylesheet" href="/SistemaUniversidad/css/header.css" />
        <link rel="stylesheet" href="/SistemaUniversidad/css/style.css" />
        <link rel="stylesheet" href="/SistemaUniversidad/css/footer.css" />
        <link rel="stylesheet" href="/SistemaUniversidad/css/nosotros_y_jsp.css" />
        <link href="/SistemaUniversidad/css/mantenimiento.css" rel="stylesheet" type="text/css"/>

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
                                    <a href="/SistemaUniversidad/vistaTaller/registrarTaller.xhtml">Taller</a>
                                    <a href="/SistemaUniversidad/vistaCertificacion/registrarCertificacion.xhtml">Certificaciones</a>
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
                                    <a href="#">Carrera profesional</a>
                                    <a></a>
                                    <a href="http://localhost:8085/listar">Convenios</a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="dropdown">
                                <a class="dropbtn">Reportes</a>
                                <div class="dropdown-content">
                                    <a href="/SistemaUniversidad/vistaAlumno/reporteAlumno.xhtml">Alumno</a>
                                    <a href="/SistemaUniversidad/vistaDocente/reporteDocente.jsp">Docente</a>
                                    <a href="/SistemaUniversidad/vistaAsignatura/reporteAsignatura.jsp">Asignatura</a>
                                    <a href="/SistemaUniversidad/vistaCarreraProfesional/reporteCarreraProfesional.jsp">Carrera profesional</a>
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
                        <h1>Mantenimiento de Carreras  profesionales</h1>
                    </div>
                </div>
            </section>

            <section>
                <%
                    CarreraProfesionalDAO carreraProfesionalDAO = new CarreraProfesionalDAO();
                %> 
                <table id="tablaresalumno" class="table table-hover">
                    <thead>
                        <tr class="text-white bg-black">
                            <th>Código<th>Nombre<th>Modalidad<th>Modificar<th>Eliminar
                    </thead>

                    <%
                        for (CarreraProfesional carreraProfesional : carreraProfesionalDAO.listado()) {
                    %>
                    <tr><td><%=carreraProfesional.getCodigo()%><td><%=carreraProfesional.getNombre()%>
                        <td><%=carreraProfesional.getModalidad()%>
                        <td><a href="/SistemaUniversidad/ControlMantenimiento?opcion=4&codigo=<%=carreraProfesional.getCodigo()%>">Modificar</a>
                        <td><a href="/SistemaUniversidad/ControlMantenimiento?opcion=5&codigo=<%=carreraProfesional.getCodigo()%>">Eliminar</a>

                            <%
                                }
                            %>

                </table>
            </section>
        </main>

        <footer>
            <p class="fondo">Todos los derechos reservados</p>
        </footer>
    </body>
</html>
