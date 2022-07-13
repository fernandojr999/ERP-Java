<%-- 
    Document   : CadastroBase
    Created on : 1 de jul. de 2022, 11:55:14
    Author     : sankhya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= request.getAttribute("cadastro-legenda") %></title>
    </head>
    <body>
        <%@include file="../header.html" %>
        <h1><%= request.getAttribute("cadastro-legenda") %></h1>
        <a href="./">.. Voltar</a>
        <a href="./Cadastro?cad=<%= request.getAttribute("cadastro-nome") %>&op=n">Novo </a>|
        Pesquisar: <input type="text" id="pesquisa" name="pesquisa"> <button onclick="pesquisarOnClick()">Ir</button>
        <hr>
    </body>
    
    
    <script>
        function pesquisarOnClick(){
            window.location.href = "./Cadastro?cad=<%= request.getAttribute("cadastro-nome") %>&q="+document.querySelector("#pesquisa").value;
        }

    </script>
</html>
