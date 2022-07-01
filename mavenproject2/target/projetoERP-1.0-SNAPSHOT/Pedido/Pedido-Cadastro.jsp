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
                <th><input type="number" name="item-quantidade" id="item-quantidade" value="0"></th>
                <th><input type="number" name="item-valorunitario" id="item-valorunitario" value="0"></th>
                <th><input type="number" name="item-valortotal" id="item-valortotal" value="0" disabled></th>
                <th><button onclick="adicionarItemOnClick()">Adicionar</button>
                </th>
            </tr>
            
            <%
            for (int i = 0; i <= dsItens.getRegistros().size() - 1; i++){
            %>
            <tr>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("id") %></th>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("nome") %></th>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("quantidade") %></th>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("valorunitario") %></th>
                <th><%= dsItens.getRegistros().get(i).getFieldValueByName("valortotal") %></th>
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
                let pedido = document.querySelector("#id").value;
                let produto = document.querySelector("#item-produto").value;
                let quantidade = document.querySelector("#item-quantidade").value;
                let valorunitario = document.querySelector("#item-valorunitario").value;
                
                window.location.href = "./PedidoItem?op=i&pedido="+pedido+"&quantidade="+quantidade+"&produto="+produto+"&valorunitario="+valorunitario;
            } 
            
        </script>
    </body>
</html>
