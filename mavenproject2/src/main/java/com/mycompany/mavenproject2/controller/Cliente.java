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

/**
 *
 * @author sankhya
 */
public class Cliente extends Base {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
                String op = request.getParameter("op");
            if ((op == null) || op.equals("")) {
                Dao dao = new Dao();
                dao.setTabela("PESSOA");
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
                getServletContext().getRequestDispatcher("/Pessoa/Cliente-Lista.jsp").forward(request, response);
            }else{
                if (op.equals("n")) {
                    getServletContext().getRequestDispatcher("/Pessoa/Cliente-Cadastro.jsp").forward(request, response);
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
        // configuração para corrigir questões de acento
        request.setCharacterEncoding("utf8");
        
        Dao dao = new Dao();
        dao.setTabela("PESSOA");
        try {
            DataSet ds = dao.getRecords("where id = "+request.getParameter("id"));
            ds.setTabela("PESSOA");
            
            if (!ds.getRegistros().isEmpty()) {
               ds.setEstado(1);
               ds.getRegistros().get(0).setCampos(dao.getCampos());
               ds.getRegistros().get(0).getFieldByName("id").setValue(request.getParameter("id"));
            } else {
               ds.setEstado(2); 
               ds.getRegistros().get(0).setCampos(dao.getCampos());
            }
            ds.getRegistros().get(0).getFieldByName("nome").setValue(request.getParameter("nome"));
            ds.getRegistros().get(0).getFieldByName("cnpjcpf").setValue(request.getParameter("cnpjcpf"));
            ds.getRegistros().get(0).getFieldByName("telefone").setValue(request.getParameter("telefone"));
            ds.getRegistros().get(0).getFieldByName("cidade").setValue(request.getParameter("cidade"));
            ds.getRegistros().get(0).getFieldByName("estado").setValue(request.getParameter("estado"));
            ds.getRegistros().get(0).getFieldByName("bairro").setValue(request.getParameter("bairro"));
            ds.getRegistros().get(0).getFieldByName("rua").setValue(request.getParameter("rua"));
            ds.getRegistros().get(0).getFieldByName("numero").setValue(request.getParameter("numero"));
            try {
                ds.salvar();
            } catch (Exception ex) {
                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath() + "/Cliente");
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
