/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller;

import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
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
public class Pedido extends Base {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
                String op = request.getParameter("op");
            if ((op == null) || op.equals("")) {
                Dao dao = new Dao();
                dao.setTabela("PEDIDO");
                    try {
                        String where = request.getParameter("q");
                        String consulta = "SELECT PED.*, PES.NOME, "+
                                          "       CASE "+
                                          "           WHEN STATUS = 1 THEN 'Em cadastro' "+  
                                          "           WHEN STATUS = 2 THEN 'Confirmado' "+
                                          "           WHEN STATUS = 3 THEN 'Cancelado' "+
                                          "       END AS STATUSDESC "+
                                          "FROM PEDIDO PED "+
                                          "INNER JOIN PESSOA PES ON PES.ID = PED.CLIENTE ";  
                        if (where == null){
                           request.setAttribute("registros", dao.getQuery(consulta));
                           //request.setAttribute("registros", dao.getRecords(""));
                        } else {
                            request.setAttribute("registros", dao.getQuery(consulta+" WHERE PES.NOME LIKE '"+ where +"%'"));
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                getServletContext().getRequestDispatcher("/Pedido/Pedido-Lista.jsp").forward(request, response);
            }else{
                if (op.equals("n")) {
                    try {
                        Dao dao = new Dao();
                        dao.setTabela("PESSOA");
                        request.setAttribute("clientes", dao.getRecords(""));
                        getServletContext().getRequestDispatcher("/Pedido/Pedido-Cadastro.jsp").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (op.equals("d")) {
                        Dao dao = new Dao();
                        dao.execCommand("DELETE FROM PEDIDO WHERE ID = "+request.getParameter("id"));
                        response.sendRedirect(request.getContextPath() + "/Pedido");
                    } else{
                        if (op.equals("e")) {
                            try {
                                Dao dao = new Dao();
                                dao.setTabela("PEDIDO");
                                request.setAttribute("registro", dao.getRecords("where id = "+request.getParameter("id")));
                                
                                Dao daoPes = new Dao();
                                daoPes.setTabela("PESSOA");
                                request.setAttribute("clientes", daoPes.getRecords(""));
                                
                                Dao daoPro = new Dao();
                                daoPro.setTabela("PRODUTOS");
                                request.setAttribute("produtos", daoPro.getRecords(""));
                                
                                Dao daoIte = new Dao();
                                String consulta = "SELECT ITE.*, PRO.NOME "+
                                                    "FROM PEDIDOITEM ITE "+
                                                    "INNER JOIN PRODUTOS PRO ON PRO.ID = ITE.PRODUTO "+
                                                    "WHERE ITE.PEDIDO = "+request.getParameter("id");
                                request.setAttribute("itens", daoIte.getQuery(consulta));
                                
                                getServletContext().getRequestDispatcher("/Pedido/Pedido-Cadastro.jsp").forward(request, response);
                            } catch (SQLException ex) {
                                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // configuração para corrigir questões de acento
        request.setCharacterEncoding("utf8");
        
        Dao dao = new Dao();
        dao.setTabela("PEDIDO");
        try {
            DataSet ds = dao.getRecords("where id = "+request.getParameter("id"));
            ds.setTabela("PEDIDO");
            
            if (!ds.getRegistros().isEmpty()) {
               ds.setEstado(1);
               ds.getRegistros().get(0).setCampos(dao.getCampos());
               ds.getRegistros().get(0).getFieldByName("id").setValue(request.getParameter("id"));
            } else {
               ds.setEstado(2); 
               ds.getRegistros().get(0).setCampos(dao.getCampos());
            }
            ds.getRegistros().get(0).getFieldByName("cliente").setValue(request.getParameter("cliente"));
            ds.getRegistros().get(0).getFieldByName("data").setValue(request.getParameter("data"));
            ds.getRegistros().get(0).getFieldByName("valortotal").setValue("0");
            ds.getRegistros().get(0).getFieldByName("status").setValue("1");
            try {
                ds.salvar();
            } catch (Exception ex) {
                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath() + "/Pedido");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
