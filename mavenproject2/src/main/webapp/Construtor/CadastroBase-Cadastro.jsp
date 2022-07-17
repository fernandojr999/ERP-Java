<%-- 
    Document   : CadastroBase-Cadastro
    Created on : 15 de jul. de 2022, 21:31:43
    Author     : sankhya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <%@include file="../header.html" %>
            <h2><%= request.getAttribute("cadastro-legenda") %></h2>
            <a href="./Cadastro?cad=<%= request.getAttribute("cadastro-id") %>" class="btn btn-danger btn-sm" role="button">
                Cancelar
            </a>
            <hr>
            <%= request.getAttribute("cadastro-form") %>
        </div>
    </body>
</html>
