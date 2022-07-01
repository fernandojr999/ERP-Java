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
        <title>Pedido - Cadastro</title>
    </head>
    <body>
        <h1>Cadastro de Pedido</h1>
        <a href="./Pedido">Voltar</a>
        
        <hr>
        <form action="./Pedido" method="POST">
            <%
            DataSet ds = (DataSet) request.getAttribute("registro"); 
            DataSet dsClientes = (DataSet) request.getAttribute("clientes"); 
            DataSet dsProdutos = (DataSet) request.getAttribute("produtos");
            DataSet dsItens = (DataSet) request.getAttribute("itens");
            if (ds != null){
            %>
            <label>ID</label> <br>
            <input type="number" readonly name="id" id="id" value="<%= ds.getRegistros().get(0).getFieldValueByName("id") %>"><br><br>
            <%
            }
            %>
            
            <label>Cliente</label> <br>
            <select name="cliente">
            <%
            for (int i = 0; i <= dsClientes.getRegistros().size() - 1; i++){
                if (ds != null){
            %>
                <option value="<%= dsClientes.getRegistros().get(i).getFieldValueByName("id")%>" 
                               <%if(dsClientes.getRegistros().get(i).getFieldValueByName("id").equals(ds.getRegistros().get(0).getFieldValueByName("cliente"))){%>selected<%}%>>
                                   <%= dsClientes.getRegistros().get(i).getFieldValueByName("nome")%>
                </option>
            <%
                } else {
            %>
                <option value="<%= dsClientes.getRegistros().get(i).getFieldValueByName("id")%>">
                                   <%= dsClientes.getRegistros().get(i).getFieldValueByName("nome")%>
                </option>
            <%
            
                }
            }    
            %>
            </select>
            
            <br><br>
            <label>Data</label> <br>
            <input type="date" name="data" id="data" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("data")%>"<%}%> >
            
            <br><br>  
            <label>Valor de Venda</label> <br>
            <input type="number" disabled name="valortotal" id="valortotal" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("valortotal")%>"<%}%> >
            
            <br><br>  
            <label>Status</label> <br>
            <input type="text" disabled name="status" id="status" <%if (ds != null){%>value="<%= ds.getRegistros().get(0).getFieldValueByName("status")%>"<%}%> >
            <br><br>    
            <input type="submit">
            
          </form> 
            <%
            if (dsItens != null){
            %>
            <h3>Itens do Pedido</h3>
            
            <table border="1px">
            <tr>
              <th># ID</th>
              <th>Produto</th>
              <th>Quantidade</th>
              <th>Valor Unitário</th>
              <th>Valor Total</th>
              <th>Ações</th>
            </tr>
            <tr>
                <th></th>
                <th>
                    <select name="item-produto" id="item-produto">
                    <%
                    for (int i = 0; i <= dsProdutos.getRegistros().size() - 1; i++){
                    %>
                        <option value="<%= dsProdutos.getRegistros().get(i).getFieldValueByName("id")%>"><%= dsProdutos.getRegistros().get(i).getFieldValueByName("nome")%></option>
                    <%
                        }
                    %>
                    </select>
                </th>
                <th><input type="text" name="item-nome" id="item-nome" value="0"></th>
                <th><input type="text" name="item-legenda" id="item-legenda" value="0"></th>
                <select name="item-tipo" id="item-tipo">
                    <option value="1">String</option>
                    <option value="2">Integer</option>
                    <option value="3">Date</option>
                    <option value="3">Double</option>
                </select>
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
                <th><a href="./PedidoItem?op=d&id=<%= dsItens.getRegistros().get(i).getFieldValueByName("id") %>">Excluir</a>
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
                
                window.location.href = "./CriarCadastroCampo?op=i&cadastro="+cadastro+"&nome="+nome+"&legenda="+legenda+"&tipo="+tipo;
            } 
            
        </script>
    </body>
</html>
