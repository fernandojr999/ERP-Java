/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller;

import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Produto extends Base {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
                String op = request.getParameter("op");
            if ((op == null) || op.equals("")) {
                Dao dao = new Dao();
                dao.setTabela("PRODUTO");
                    try {
                        String where = request.getParameter("q");
                        if (where == null){
                           request.setAttribute("registros", dao.getRecords(""));
                        } else {
                            request.setAttribute("registros", dao.getRecords("WHERE nome LIKE '"+ where +"%'"));
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                getServletContext().getRequestDispatcher("/Produto/Produto-Lista.jsp").forward(request, response);
            }else{
                if (op.equals("n")) {
                    getServletContext().getRequestDispatcher("/Produto/Produto-Cadastro.jsp").forward(request, response);
                } else {
                    if (op.equals("d")) {
                        Dao dao = new Dao();
                        dao.execCommand("DELETE FROM PRODUTO WHERE ID = "+request.getParameter("id"));
                        response.sendRedirect(request.getContextPath() + "/Produto");
                    } else{
                        if (op.equals("e")) {
                            try {
                                Dao dao = new Dao();
                                dao.setTabela("PRODUTO");
                                request.setAttribute("registro", dao.getRecords("where id = "+request.getParameter("id")));
                                getServletContext().getRequestDispatcher("/Produto/Produto-Cadastro.jsp").forward(request, response);
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
        dao.setTabela("PRODUTO");
        try {
            DataSet ds = dao.getRecords("where id = "+request.getParameter("id"));
            ds.setTabela("PRODUTO");
            
            if (!ds.getRegistros().isEmpty()) {
               ds.setEstado(1);
               ds.getRegistros().get(0).setCampos(dao.getCampos());
               ds.getRegistros().get(0).getFieldByName("id").setValue(request.getParameter("id"));
               ds.getRegistros().get(0).getFieldByName("usuarioalterou").setValue(SESSAO_USUARIO_ID);
            } else {
               ds.setEstado(2); 
               ds.getRegistros().get(0).setCampos(dao.getCampos());
               ds.getRegistros().get(0).getFieldByName("usuarioincluiu").setValue(SESSAO_USUARIO_ID);
            }
            ds.getRegistros().get(0).getFieldByName("nome").setValue(request.getParameter("nome"));
            ds.getRegistros().get(0).getFieldByName("valorvenda").setValue(request.getParameter("valorvenda"));
            
            
            
            try {
                ds.salvar();
            } catch (Exception ex) {
                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath() + "/Produto");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
