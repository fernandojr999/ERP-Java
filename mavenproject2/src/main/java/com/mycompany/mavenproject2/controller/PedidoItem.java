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

/**
 *
 * @author sankhya
 */
public class PedidoItem extends Base {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                    dao.execCommand("DELETE FROM PESSOA WHERE ID = "+request.getParameter("id"));
                    response.sendRedirect(request.getContextPath() + "/Cliente");
                } else{
                    if (op.equals("e")) {
                        try {
                            Dao dao = new Dao();
                            dao.setTabela("PESSOA");
                            request.setAttribute("registro", dao.getRecords("where id = "+request.getParameter("id")));
                            getServletContext().getRequestDispatcher("/Pessoa/Cliente-Cadastro.jsp").forward(request, response);
                        } catch (SQLException ex) {
                            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
