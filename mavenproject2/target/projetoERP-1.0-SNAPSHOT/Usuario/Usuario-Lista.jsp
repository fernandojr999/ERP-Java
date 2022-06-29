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
        <title>Usuários</title>
    </head>
    <body>
        <%@include file="../header.html" %>
        <h1>Usuários</h1>
        <a href="./">.. Voltar</a>
        <a href="./Usuario?op=n">Novo </a>|
        Pesquisar: <input type="text" id="pesquisa" name="pesquisa"> <button onclick="pesquisarOnClick()">Ir</button>
        <a href="./Usuario?op=massa&qtd=1000">Cadastrar massa de usuários</a>
        <hr>
        
        <%
        DataSet ds = (DataSet) request.getAttribute("registros"); 
        %>
        <table border="1px">
        <tr>
          <th># ID</th>
          <th>Nome</th>
          <th>Login</th>
          <th>Ações</th>
        </tr>
        <%
        for (int i = 0; i <= ds.getRegistros().size() - 1; i++){
        %>
        <tr>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("id") %></th>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("nome") %></th>
            <th><%= ds.getRegistros().get(i).getFieldValueByName("login") %></th>
            <th><a href="./Usuario?op=e&id=<%= ds.getRegistros().get(i).getFieldValueByName("id") %>">Editar</a>|
                <a href="./Usuario?op=d&id=<%= ds.getRegistros().get(i).getFieldValueByName("id") %>">Excluir</a>|
                <a href="./Usuario?op=a&id=<%= ds.getRegistros().get(i).getFieldValueByName("id") %>">Alt. Senha</a>
            </th>
            
        </tr>
        <%
            }
        %>
        </table>
        
        
        <%
        int qtdPaginas = (int)request.getAttribute("qtdPaginas"); 
        for (int i = 0; i <= qtdPaginas - 1; i++){
        %>
        <a href="./Usuario?pagina=<%=i%>"><%=i + 1%></a>
        <%
            }
        %>

        
    </body>
    
    <script>
        function pesquisarOnClick(){
            window.location.href = "./Usuario?q="+document.querySelector("#pesquisa").value;
        }

    </script>
</html>
