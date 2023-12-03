<%@page import="modelo.Matricula"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<%
    Matricula matricula = (Matricula) request.getAttribute("dato");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Matricula</title>
        <link href="/SistemaUniversidad/bootstrap/adminlte.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <center>
        <h1>Modificar Matrícula</h1>
        <form action="/SistemaUniversidad/ControlMatricula" method="post">
            <table class="table table-hover">
                <tr><td>ID<td><input name="id" value="<%=matricula.getId()%>" readonly>
                <tr><td>Código<td><input name="codigoAlumno" value="<%=matricula.getCodigoAlumno()%>">
                <tr><td>Semestre<td><input name="semestre" value="<%=matricula.getSemestre()%>">
                <tr><td>Asignatura<td><input name="nombreAsignatura" value="<%=matricula.getNombreAsignatura()%>">
                <tr><td>Turno<td><input name="turno" value="<%=matricula.getTurno()%>">
                <tr><td>Fecha de Matrícula<td><input name="fechaMatricula" value="<%=matricula.getFechaMatricula().format(formatter)%>">
                        <input type="hidden" name="opcion" value="4">
                        <input type="submit"> 
            </table>   
        </form>
    </center>
</body>
</html>
