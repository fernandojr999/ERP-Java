<%-- 
    Document   : Produto-Cad
    Created on : 7 de jun. de 2022, 19:08:45
    Author     : sankhya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import="com.mycompany.mavenproject2.dao.DataSet"%>
<!DOCTYPE html>
<html>
    <%@include file="../header.html" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários - Cadastro</title>
    </head>
    <body>
        <h1>Cadastro de Usuários</h1>
        <a href="./Usuario">Voltar</a>
        
        <hr>
        <form action="./Usuario" method="POST">
            <%
            DataSet ds = (DataSet) request.getAttribute("registro"); 
            
            if (ds != null){
            %>
            <label>ID</label> <br>
            <input type="number" readonly name="id" id="id" value="<%= ds.getRegistros().get(0).getFieldValueByName("id") %>"><br><br>
            <%
            }
            %>
            
          
            <label>Nome</label> <br>
            <input type="text" name="nome" id="nome" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("nome")%>"<%}%> >
            
            <br><br>  
            <label>Login</label> <br>
            <input type="text" name="login" id="login" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("login")%>"<%}%> >
            
            <br><br>  
            <label>Senha</label> <br>
            <input type="password" name="senha" id="senha" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("senha")%>"<%}%> >
            
            <br><br>    
            <input type="submit">
        </form>
    </body>
</html>
