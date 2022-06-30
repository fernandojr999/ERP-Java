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
        <title>Clientes</title>
    </head>
    <body>
        <%@include file="../header.html" %>
        <h1>Clientes</h1>
        <a href="./">.. Voltar</a>
        <a href="./Cliente?op=n">Novo </a>|
        Pesquisar: <input type="text" id="pesquisa" name="pesquisa"> <button onclick="pesquisarOnClick()">Ir</button>
        <hr>
        
        <%
        DataSet ds = (DataSet) request.getAttribute("registros"); 
        %>
        <table border="1px">
        <tr>
          <th># ID</th>
          <th>Nome</th>
          <th>CNPJ/CPF</th>
          <th>Telefone</th>
          <th>Cidade/Estado</th>
          <th>Ações</th>
        </tr>
        <%
        for (int i = 0; i <= ds.getRegistros().size() - 1; i++){
        %>
        <tr>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("id") %></th>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("nome") %></th>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("cnpjcpf") %></th>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("telefone") %></th>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("cidade") %> - <%= ds.getRegistros().get(i).getFieldValueByName("estado") %></th>
            <th><a href="./Cliente?op=e&id=<%= ds.getRegistros().get(i).getFieldValueByName("id") %>">Editar</a>|
                <a href="./Cliente?op=d&id=<%= ds.getRegistros().get(i).getFieldValueByName("id") %>">Excluir</a>
            </th>
            
        </tr>
        <%
            }
        %>
        </table>

        
    </body>
    
    <script>
        function pesquisarOnClick(){
            window.location.href = "./Cliente?q="+document.querySelector("#pesquisa").value;
        }

    </script>
</html>
