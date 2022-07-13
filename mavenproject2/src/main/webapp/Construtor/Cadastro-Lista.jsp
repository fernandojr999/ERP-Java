<%-- 
    Document   : Produto-Lista
    Created on : 7 de jun. de 2022, 18:59:45
    Author     : sankhya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import="com.mycompany.mavenproject2.dao.DataSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produtos</title>
    </head>
    <body>
        <%@include file="../header.html" %>
        <h1>Cadastros</h1>
        <a href="./">.. Voltar</a>
        <a href="./CriarCadastro?op=n">Novo </a>|
        Pesquisar: <input type="text" id="pesquisa" name="pesquisa"> <button onclick="pesquisarOnClick()">Ir</button>
        <hr>
        
        <%
        DataSet ds = (DataSet) request.getAttribute("registros"); 
        %>
        <table border="1px">
        <tr>
          <th># ID</th>
          <th>Nome</th>
          <th>Legenda</th>
          <th>Tabela</th>
          <th>Ações</th>
        </tr>
        <%
        for (int i = 0; i <= ds.getRegistros().size() - 1; i++){
        %>
        <tr>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("id") %></th>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("nome") %></th>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("legenda") %></th>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("tabelanome") %></th>
            <th><a href="./CriarCadastro?op=e&id=<%= ds.getRegistros().get(i).getFieldValueByName("id") %>">Editar</a>|
                <a href="./CriarCadastro?op=d&id=<%= ds.getRegistros().get(i).getFieldValueByName("id") %>">Excluir</a>|
                <a href="./CriarCadastro?op=g&id=<%= ds.getRegistros().get(i).getFieldValueByName("id") %>">Gerar HTML</a>|
            </th>
            
        </tr>
        <%
            }
        %>
        </table>

        
    </body>
    
    <script>
        function pesquisarOnClick(){
            window.location.href = "./CriarCadastro?q="+document.querySelector("#pesquisa").value;
        }

    </script>
</html>
