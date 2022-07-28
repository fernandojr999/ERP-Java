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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <title><%= request.getAttribute("cadastro-legenda") %></title>
    </head>
    <body>
        <div>
            <%@include file="../header.html" %>
            <h2><%= request.getAttribute("cadastro-legenda") %></h2>
            <button class="btn btn-dark btn-sm" onclick="pesquisarOnClick()" role="button">
                Voltar
            </button>
            <a href="./Cadastro?cad=<%= request.getAttribute("cadastro-id") %>&op=n" class="btn btn-success btn-sm" onclick="pesquisarOnClick()" role="button">
                Novo
            </a>
            Pesquisar: <input type="text" id="pesquisa" name="pesquisa"> 
            <a class="btn btn-primary btn-sm" onclick="pesquisarOnClick()" role="button">
                Pesquisar
            </a>
            <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                Filtros Avançados
            </a>
            <p>
            </p>
            <div class="collapse" id="collapseExample">
                <div class="card card-body">
                  Não foram localizados filtros adicionais.
                </div>
            </div>
            <hr>
        </div>
    </body>
    
    
    <script>
        function pesquisarOnClick(){
            window.location.href = "./Cadastro?cad=<%= request.getAttribute("cadastro-id") %>&q="+document.querySelector("#pesquisa").value;
        }

    </script>
</html>
