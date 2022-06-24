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
        <title>Produto - Cadastro</title>
    </head>
    <body>
        <h1>Cadastro de Produto</h1>
        <a href="./Produto">Voltar</a>
        
        <hr>
        <form action="./Produto" method="POST">
            <%
            DataSet ds = (DataSet) request.getAttribute("registro"); 
            
            if (ds != null){
            %>
            <label>ID</label> <br>
            <input type="number" readonly name="id" id="id" value="<%= ds.getRegistros().get(0).getFieldValueByName("id") %>"><br><br>
            <%
            }
            %>
            
          
            <label>Descrição</label> <br>
            <input type="text" name="nome" id="nome" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("nome")%>"<%}%> >
            
            <br><br>  
            <label>Valor de Venda</label> <br>
            <input type="number" name="valorvenda" id="valorvenda" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("valorvenda")%>"<%}%> >
            
            <br><br>    
            <input type="submit">
        </form>
    </body>
</html>
