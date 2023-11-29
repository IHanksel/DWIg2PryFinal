<%-- 
    Document   : universidad
    Created on : 29 nov. 2023, 17:20:25
    Author     : abdel
--%>

<%@page import="modelo.Universidad"%>
<%@page import="modelo.Lectura"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Buscador de Universidades</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        form {
            margin-top: 20px;
            margin-bottom: 20px;
            text-align: center;
        }

        input[type="text"] {
            width: 420px;
            padding: 10px;
            font-size: 16px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #3498db;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        table {
            width: 80%;
            max-width: 800px;
            border-collapse: collapse;
            margin-top: 20px;
        }

        td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
            vertical-align: top;
        }

        p {
            margin: 0;
            padding-bottom: 5px;
        }

        a {
            color: #3498db;
            text-decoration: none;
            
        }

        a:hover {
            text-decoration: underline;
        }
        
        a.volver {
            padding: 20px;
        }
    </style>
</head>
<body>

    <a class="volver" href="index.html">Volver a Sistema de Matrícula</a>
    <% String nombre = ""; Lectura obj = new Lectura(); if (request.getParameter("nombre") != null) { nombre = request.getParameter("nombre"); } %>

    <center>
        <form>
            Buscar Universidades: <input name="nombre" type="text">
            <input type="submit">
        </form>

        <table>
            <tr>
                <% int cuenta = 0; for (Universidad x : obj.listarUniversidad(nombre)) { %>
                <td>
                    <p><%= x.getNombre()%></p>
                    <p><a href="<%= x.getUrl()%>" target="_blank"><%= x.getUrl()%></a></p>
                </td>
                <%
                    cuenta++;
                    if (cuenta % 3 == 0) {
                        out.print("</tr>");
                    }
                } %>
            </tr>
        </table>
    </center>
</body>
</html>
