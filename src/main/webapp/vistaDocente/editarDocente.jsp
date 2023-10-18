<%-- 
    Document   : editarAlumno
    Created on : 15 oct. 2023, 18:59:03
    Author     : abdel
--%>

<%@page import="modelo.Docente"%>
<%@page import="modelo.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Docentes</title>
        <link href="/SistemaUniversidad/bootstrap/adminlte.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            Docente docente = (Docente) request.getAttribute("dato");
        %>    
        <center>
            <h1>Modificar Docente</h1>
            <form action="/SistemaUniversidad/ControlMantenimiento" method="post">
                <table class="table table-hover">
                    <tr><td>Código<td><input name="codigo" value="<%=docente.getCodigo()%>"
                                             readonly>
                    <tr><td>Nombres<td><input name="nombres" value="<%=docente.getNombres()%>">
                    <tr><td>Apellidos<td><input name="apellidos" value="<%=docente.getApellidos()%>">
                    <tr><td>DNI<td><input name="dni" value="<%=docente.getDni()%>">
                    <tr><td>Especialidad<td><input name="especialidad" value="<%=docente.getEspecialidad()%>">
                    <tr><td>Correo<td><input name="correo" value="<%=docente.getCorreo()%>">
                            <input type="hidden" name="opcion" value="9">
                            <input type="submit">       
                </table>   
            </form>
        </center>
    </body>
</html>
