<%-- 
    Document   : editarAlumno
    Created on : 15 oct. 2023, 18:59:03
    Author     : abdel
--%>

<%@page import="modelo.CarreraProfesional"%>
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
            CarreraProfesional carreraProfesional = (CarreraProfesional) request.getAttribute("dato");
        %>    
        <center>
            <h1>Modificar Carrera Profesional</h1>
            <form action="/SistemaUniversidad/ControlMantenimiento" method="post">
                <table class="table table-hover">
                    <tr><td>Código<td><input name="codigo" value="<%=carreraProfesional.getCodigo()%>"
                                             readonly>
                    <tr><td>Nombre<td><input name="nombre" value="<%=carreraProfesional.getNombre()%>">
                    <tr><td>Modalidad<td><input name="modalidad" value="<%=carreraProfesional.getModalidad()%>">
                            <input type="hidden" name="opcion" value="6">
                            <input type="submit">       
                </table>   
            </form>
        </center>
    </body>
</html>
