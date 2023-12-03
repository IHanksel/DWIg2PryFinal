<%-- 
    Document   : graficoDocente
    Created on : 10 oct. 2023, 19:01:45
    Author     : abdel
--%>

<%@page import="java.util.Map"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="modelo.Docente, dao.DocenteDAO" %>
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
        <script src="/SistemaUniversidad/js/Chart.min.js" type="text/javascript"></script>
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
                                    <a href="#">Gráfico Docente</a>
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
                        <h1>Gráfico de Docentes</h1>
                    </div>
                </div>
            </section>

            <section>
                <div class="container">
                    <div>
                        <canvas id="myChart"></canvas>
                    </div>
                </div>

                <script>
                    var ctx = document.getElementById("myChart").getContext("2d");

                    
                    var labels = [
                    <%
                        DocenteDAO docenteDAO = new DocenteDAO();
                        Map<String, Integer> especialidadDocenteCount = docenteDAO.contarDocentesPorEspecialidad();
                        for (Map.Entry<String, Integer> entry : especialidadDocenteCount.entrySet()) {
                            out.print("'" + entry.getKey() + "',");
                        }
                    %>

                    ];

                    var data = [
                    <%
                        for (Map.Entry<String, Integer> entry : especialidadDocenteCount.entrySet()) {
                            out.print(entry.getValue() + ",");
                        }
                    %>
                    ];

                    <%
                        String op = "bar";
                    %>

                    var myChart = new Chart(ctx, {
                        type: "<%=op%>",
                        data: {
                            labels: labels,
                            datasets: [{
                                    label: "Cantidad de Docentes",
                                    data: data,
                                    backgroundColor: [
                                        'rgba(0, 0, 0, 0.1)',
                                        'rgba(0, 0, 0, 0.3)',
                                        'rgba(0, 0, 0, 0.5)',
                                        'rgba(0, 0, 0, 0.7)',
                                        'rgba(0, 0, 0, 0.9)',
                                    ],
                                }],
                        },
                    });
                </script>
            </section>
        </main>



        <footer>
            <p class="fondo">Todos los derechos reservados</p>
        </footer>

    </body>
</html>
