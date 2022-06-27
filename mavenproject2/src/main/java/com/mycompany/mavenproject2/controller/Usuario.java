/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller;

import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import com.mycompany.mavenproject2.regras.FunUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario extends Base {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CONTEXTO_NOME  = "USUARIO";
         if (validaAcesso(request, response)){
                String op = request.getParameter("op");
            if ((op == null) || op.equals("")) {
                Dao dao = new Dao();
                dao.setTabela("USUARIO");
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
                getServletContext().getRequestDispatcher("/Usuario/Usuario-Lista.jsp").forward(request, response);
            }else{
                if (op.equals("n")) {
                    getServletContext().getRequestDispatcher("/Usuario/Usuario-Cadastro.jsp").forward(request, response);
                } else {
                    if (op.equals("d")) {
                        Dao dao = new Dao();
                        dao.execCommand("DELETE FROM USUARIO WHERE ID = "+request.getParameter("id"));
                        response.sendRedirect(request.getContextPath() + "/Usuario");
                    } else{
                        if (op.equals("e")) {
                            try {
                                Dao dao = new Dao();
                                dao.setTabela("USUARIO");
                                request.setAttribute("registro", dao.getRecords("where id = "+request.getParameter("id")));
                                getServletContext().getRequestDispatcher("/Usuario/Usuario-Cadastro.jsp").forward(request, response);
                            } catch (SQLException ex) {
                                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            if (op.equals("massa")){
                                FunUsuario fun = new FunUsuario();
                                fun.cadastroUsuarioMassa(Integer.parseInt(request.getParameter("qtd")));
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
        dao.setTabela("USUARIO");
        try {
            DataSet ds = dao.getRecords("where id = "+request.getParameter("id"));
            ds.setTabela("USUARIO");
            
            if (!ds.getRegistros().isEmpty()) {
               ds.setEstado(1);
               ds.getRegistros().get(0).setCampos(dao.getCampos());
               ds.getRegistros().get(0).getFieldByName("id").setValue(request.getParameter("id"));
            } else {
               ds.setEstado(2); 
               ds.getRegistros().get(0).setCampos(dao.getCampos());
            }
            ds.getRegistros().get(0).getFieldByName("nome").setValue(request.getParameter("nome"));
            ds.getRegistros().get(0).getFieldByName("login").setValue(request.getParameter("login"));
            ds.getRegistros().get(0).getFieldByName("senha").setValue(Login.gerarHash(request.getParameter("senha")));
            try {
                ds.salvar();
            } catch (Exception ex) {
                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath() + "/Usuario");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
