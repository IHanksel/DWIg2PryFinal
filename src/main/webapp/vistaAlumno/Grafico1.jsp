<%@page import="java.util.Map"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="modelo.Alumno, dao.AlumnoDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Grafico de alumnos por carrera</title>
    <script src="/SistemaUniversidad/js/Chart.min.js" type="text/javascript"></script>
</head>
<body>
    <%
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        Map<String, Integer> carreraAlumnoCount = alumnoDAO.contarAlumnosPorCarrera();

        String op = request.getParameter("op");
    %>

    <div class="container">
        <div>
            <canvas id="myChart"></canvas>
        </div>
    </div>

    <script>
        var ctx = document.getElementById("myChart").getContext("2d");
        var myChart = new Chart(ctx, {
            type: "<%=op%>",
            data: {
                labels: [
                    <%
                    for (Map.Entry<String, Integer> entry : carreraAlumnoCount.entrySet()) {
                        out.print("'" + entry.getKey() + "',");
                    }
                    %>
                ],
                datasets: [
                    {
                        label: "Cantidad de Alumnos",
                        data: [
                            <%
                            for (Map.Entry<String, Integer> entry : carreraAlumnoCount.entrySet()) {
                                out.print(entry.getValue() + ",");
                            }
                            %>
                        ],
                        backgroundColor: [
                            'rgba(0, 0, 0, 0.1)',
                            'rgba(0, 0, 0, 0.3)',
                            'rgba(0, 0, 0, 0.5)',
                            'rgba(0, 0, 0, 0.7)',
                            'rgba(0, 0, 0, 0.9)',
                        ],
                    },
                ],
            },
        });
    </script>
</body>
</html>
