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
        <title>Cliente - Cadastro</title>
    </head>
    <body>
        <h1>Cadastro de Clientes</h1>
        <a href="./Cliente">Voltar</a>
        
        <hr>
        <form action="./Cliente" method="POST">
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
            <label>CNPJ/CPF</label> <br>
            <input type="text" name="cnpjcpf" id="cnpjcpf" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("cnpjcpf")%>"<%}%> >
            <br><br>  
            <label>Telefone</label> <br>
            <input type="text" name="telefone" id="telefone" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("telefone")%>"<%}%> >
            
            <br><br>  
            <label>Cidade:</label> 
            <input type="text" name="cidade" id="cidade" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("cidade")%>"<%}%> >
            <label>Estado:</label> 
            <input type="text" name="estado" id="estado" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("estado")%>"<%}%> > 
            <label>Bairro:</label> 
            <input type="text" name="bairro" id="bairro" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("bairro")%>"<%}%> >
            <label>Rua:</label> 
            <input type="text" name="rua" id="rua" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("rua")%>"<%}%> >
            <label>Numero:</label> 
            <input type="text" name="numero" id="numero" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("numero")%>"<%}%> >
            <br><br>    
            <input type="submit">
        </form>
    </body>
</html>
