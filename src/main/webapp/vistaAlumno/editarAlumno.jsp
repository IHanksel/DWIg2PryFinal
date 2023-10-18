<%-- 
    Document   : editarAlumno
    Created on : 15 oct. 2023, 18:59:03
    Author     : abdel
--%>

<%@page import="modelo.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar alumnos</title>
        <link href="/SistemaUniversidad/bootstrap/adminlte.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            Alumno alumno = (Alumno) request.getAttribute("dato");
        %>    
        <center>
            <h1>Modificar Alumno</h1>
            <form action="/SistemaUniversidad/ControlMantenimiento" method="post">
                <table class="table table-hover">
                    <tr><td>Código<td><input name="codigo" value="<%=alumno.getCodigo()%>"
                                             readonly>
                    <tr><td>Nombres<td><input name="nombres" value="<%=alumno.getNombres()%>">
                    <tr><td>Apellidos<td><input name="apellidos" value="<%=alumno.getApellidos()%>">
                    <tr><td>DNI<td><input name="dni" value="<%=alumno.getDni()%>">
                    <tr><td>Dirección<td><input name="direccion" value="<%=alumno.getDireccion()%>">
                    <tr><td>Teléfono<td><input name="telefono" value="<%=alumno.getTelefono()%>">
                    <tr><td>Carrera<td><input name="carrera" value="<%=alumno.getCarrera()%>">
                            <input type="hidden" name="opcion" value="3">
                            <input type="submit">       
                </table>   
            </form>
        </center>
    </body>
</html>
