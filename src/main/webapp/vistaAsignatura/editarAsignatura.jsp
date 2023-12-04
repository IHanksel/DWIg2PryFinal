<%-- 
    Document   : editarAlumno
    Created on : 15 oct. 2023, 18:59:03
    Author     : abdel
--%>

<%@page import="modelo.Asignatura"%>
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
            Asignatura asignatura = (Asignatura) request.getAttribute("dato");
        %>    
    <center>
        <h1>Modificar Asignatura</h1>
        <form action="/SistemaUniversidad/ControlMantenimiento" method="post">
            <table class="table table-hover">
                <tr>
                    <td>Codigo
                    <td><input name="codigo" value="<%=asignatura.getCodigo()%>">
                <tr>
                    <td>Carrera
                    <td><input name="carrera" value="<%=asignatura.getCarrera()%>">
                <tr>
                    <td>Nombre de Asignatura
                    <td><input name="nombre" value="<%=asignatura.getNombre()%>">
                <tr>
                    <td>Creditos
                    <td><input name="creditos" value="<%=asignatura.getCreditos()%>">
                        <input type="hidden" name="opcion" value="12">
                        <input type="submit">       
            </table>   
        </form>
    </center>
</body>
</html>
