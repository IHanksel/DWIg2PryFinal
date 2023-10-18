<%-- 
    Document   : mantenimientoDocente
    Created on : 10 oct. 2023, 19:01:36
    Author     : abdel
--%>

<%@page import="java.util.List"%>
<%@page import="modelo.Docente"%>
<%@page import="dao.DocenteDAO"%>
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
        <link href="../css/mantenimiento.css" rel="stylesheet" type="text/css"/>
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
                                    <a href="#">Docente</a>
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
                        <h1>Mantenimiento de Docentes</h1>
                    </div>
                </div>
            </section>

            <section>
                <table id="tablaresdocentes" class="table table-hover">
                    <thead>
                        <tr class="text-white bg-black">
                            <th>Código</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>DNI</th>
                            <th>Especialidad</th>
                            <th>Correo</th>
                            <th>Eliminar</th>
                            <th>Modificar</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                            DocenteDAO docenteDAO = new DocenteDAO();
                            try {
                                List<Docente> listaDocentes = docenteDAO.obtenerTodos();
                                for (Docente docente : listaDocentes) {
                        %>
                        <tr>
                            <td><%=docente.getCodigo()%></td>
                            <td><%=docente.getNombres()%></td>
                            <td><%=docente.getApellidos()%></td>
                            <td><%=docente.getDni()%></td>
                            <td><%=docente.getEspecialidad()%></td>
                            <td><%=docente.getCorreo()%></td>
                            <td>
                                <a href="/SistemaUniversidad/ControlMantenimiento?opcion=8&codigo=<%=docente.getCodigo()%>">Eliminar</a>
                            </td>
                            <td>
                                <a href="/SistemaUniversidad/ControlMantenimiento?opcion=7&codigo=<%=docente.getCodigo()%>">Modificar</a>
                            </td>
                        </tr>
                        <%
                                }
                            } catch (Exception e) {
                                out.println("Error al obtener la lista de docentes: " + e.getMessage());
                            }
                        %>
                    </tbody>
                </table>
            </section>
        </main>


        <footer>
            <p class="fondo">Todos los derechos reservados</p>
        </footer>
    </body>
</html>
