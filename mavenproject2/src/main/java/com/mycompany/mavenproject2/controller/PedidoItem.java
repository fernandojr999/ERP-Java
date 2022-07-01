/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller;

import com.mycompany.mavenproject2.dao.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoItem extends Base {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
            String op = request.getParameter("op");
            if (op.equals("i")) {
                int pedido = Integer.parseInt(request.getParameter("pedido"));
                int produto = Integer.parseInt(request.getParameter("produto"));
                double quantidade = Double.parseDouble(request.getParameter("quantidade"));
                double valorunitario = Double.parseDouble(request.getParameter("valorunitario"));
                double valortotal = valorunitario * quantidade;
                
                String comanIn = "INSERT INTO PEDIDOITEM (ID, PEDIDO, PRODUTO, QUANTIDADE, VALORUNITARIO, VALORTOTAL) "+
                                 "VALUES (NULL, "+pedido+", "+produto+","+quantidade+","+valorunitario+","+valortotal+")";
                
                String comanUp = "UPDATE PEDIDO SET VALORTOTAL = (SELECT SUM(VALORTOTAL) FROM PEDIDOITEM WHERE PEDIDO = "+pedido+") "+
                                 "WHERE ID = "+pedido;
                
                Dao dao = new Dao();
                dao.execCommand(comanIn);
                dao.execCommand(comanUp);
                
                response.sendRedirect(request.getContextPath() + "/Pedido?op=e&id="+pedido);
            } else {
                if (op.equals("d")) {
                    Dao dao = new Dao();
                    dao.execCommand("DELETE FROM PEDIDOITEM WHERE ID = "+request.getParameter("id"));
                    response.sendRedirect(request.getContextPath() + "/Pedido?op=e&id="+request.getParameter("pedido"));
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
