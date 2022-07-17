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
        <title>Cadastro</title>
    </head>
    <body>
        <h1>Construtor de Cadastros</h1>
        <a href="./CriarCadastro">Voltar</a>
        
        <hr>
        <form action="./CriarCadastro" method="POST">
            <%
            DataSet ds = (DataSet) request.getAttribute("registro");
            DataSet dsItens = (DataSet) request.getAttribute("campos");
            
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
            <label>Legenda</label> <br>
            <input type="text" name="legenda" id="legenda" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("legenda")%>"<%}%> >
            
            <br><br>
            <label>Tabela</label> <br>
            <input type="text" name="tabelanome" id="tabelanome" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("tabelanome")%>"<%}%> >
            
            <br><br>
            <label>Classe</label> <br>
            <input type="text" name="classe" id="classe" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("classe")%>"<%}%> >
   
            <br><br>
            <input type="submit">
            
          </form> 
            
          <%
            if (dsItens != null){
            %>
            <h3>Campos do Cadastro</h3>
            
            <table border="1px">
            <tr>
              <th># ID</th>
              <th>Nome</th>
              <th>Legenda</th>
              <th>Tipo</th>
              <th>Tabela Referenciada</th>
              <th>Campo Descrição</th>
              <th>Ações</th>
            </tr>
            <tr>
                <th></th>
                <th><input type="text" name="item-nome" id="item-nome"></th>
                <th><input type="text" name="item-legenda" id="item-legenda"></th>
                <th>
                    <select name="item-tipo" id="item-tipo">
                        <option value="1">String</option>
                        <option value="2">Integer</option>
                        <option value="3">Date</option>
                        <option value="4">Double</option>
                        <option value="5">Tabela Referenciada</option>
                    </select>
                </th>
                <th><input type="text" name="item-tabelareferenciada" id="item-tabelareferenciada"></th>
                <th><input type="text" name="item-campodescref" id="item-campodescref"></th>
                <th><button onclick="adicionarItemOnClick()">Adicionar</button>
                </th>
            </tr>
            
            <%
            for (int i = 0; i <= dsItens.getRegistros().size() - 1; i++){
            %>
            <tr>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("id") %></th>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("nome") %></th>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("legenda") %></th>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("tipo") %></th>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("tabelareferenciada") %></th>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("campodescref") %></th>
                <th><a href="./CriarCadastroCampo?op=d&id=<%= dsItens.getRegistros().get(i).getFieldValueByName("id") %>&cadastro=<%= ds.getRegistros().get(0).getFieldValueByName("id") %>">Excluir</a>
                </th>

            </tr>
            <%
                }
            %>
            </table>
            <%
            }
            %>
            
            
        
        
        <script>
            function adicionarItemOnClick(){
                let cadastro = document.querySelector("#id").value;
                let nome = document.querySelector("#item-nome").value;
                let legenda = document.querySelector("#item-legenda").value;
                let tipo = document.querySelector("#item-tipo").value;
                let tabelaReferenciada = document.querySelector("#item-tabelareferenciada").value;
                let campoDescRef = document.querySelector("#item-campodescref").value;
                window.location.href = "./CriarCadastroCampo?op=i&cadastro="+cadastro+"&nome="+nome+"&legenda="+legenda+
                                       "&tipo="+tipo+"&tabelaReferenciada="+tabelaReferenciada+"&campoDescRef="+campoDescRef;
            } 
        </script>
    </body>
</html>
