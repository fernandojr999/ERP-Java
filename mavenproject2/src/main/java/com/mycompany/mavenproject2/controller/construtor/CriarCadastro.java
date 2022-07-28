/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller.construtor;

import com.mycompany.mavenproject2.controller.Base;
import com.mycompany.mavenproject2.controller.Produto;
import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import com.mycompany.mavenproject2.regras.FunConstrutor;
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
public class CriarCadastro extends Base {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
                String op = request.getParameter("op");
            if ((op == null) || op.equals("")) {
                Dao dao = new Dao();
                dao.setTabela("SYSCADASTROS");
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
                getServletContext().getRequestDispatcher("/Construtor/Cadastro-Lista.jsp").forward(request, response);
            }else{
                if (op.equals("n")) {
                    getServletContext().getRequestDispatcher("/Construtor/Cadastro-Cadastro.jsp").forward(request, response);
                } else {
                    if (op.equals("d")) {
                        Dao dao = new Dao();
                        dao.execCommand("DELETE FROM SYSCADASTROS WHERE ID = "+request.getParameter("id"));
                        response.sendRedirect(request.getContextPath() + "/CriarCadastro");
                    } else{
                        if (op.equals("e")) {
                            try {
                                Dao daoIte = new Dao();
                                daoIte.setTabela("SYSCADASTROCAMPOS");
                                request.setAttribute("campos", daoIte.getRecords("where CADASTRO = "+request.getParameter("id")));
                                
                                Dao dao = new Dao();
                                dao.setTabela("SYSCADASTROS");
                                request.setAttribute("registro", dao.getRecords("where id = "+request.getParameter("id")));
                                getServletContext().getRequestDispatcher("/Construtor/Cadastro-Cadastro.jsp").forward(request, response);
                            } catch (SQLException ex) {
                                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if(op.equals("g")){
                            FunConstrutor fun = new FunConstrutor();
                            fun.gerarHTMLCadastro(Integer.parseInt(request.getParameter("id")));
                            response.sendRedirect(request.getContextPath() + "/CriarCadastro?op=e&id="+request.getParameter("id"));
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
            request.setCharacterEncoding("utf8");
        
            Dao dao = new Dao();
            dao.setTabela("SYSCADASTROS");
            try {
                DataSet ds = dao.getRecords("where id = "+request.getParameter("id"));
                ds.setTabela("SYSCADASTROS");

                if (!ds.getRegistros().isEmpty()) {
                   ds.setEstado(1);
                   ds.getRegistros().get(0).setCampos(dao.getCampos());
                   ds.getRegistros().get(0).getFieldByName("id").setValue(request.getParameter("id"));
                } else {
                   ds.setEstado(2); 
                   ds.getRegistros().get(0).setCampos(dao.getCampos());
                }
                ds.getRegistros().get(0).getFieldByName("nome").setValue(request.getParameter("nome"));
                ds.getRegistros().get(0).getFieldByName("legenda").setValue(request.getParameter("legenda"));
                ds.getRegistros().get(0).getFieldByName("tabelanome").setValue(request.getParameter("tabelanome"));
                ds.getRegistros().get(0).getFieldByName("classe").setValue(request.getParameter("classe"));
                try {
                    ds.salvar();
                } catch (Exception ex) {
                    Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
            }

            response.sendRedirect(request.getContextPath() + "/CriarCadastro");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
