/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller.construtor;

import com.mycompany.mavenproject2.controller.Base;
import com.mycompany.mavenproject2.controller.Login;
import com.mycompany.mavenproject2.controller.Produto;
import com.mycompany.mavenproject2.controller.Usuario;
import com.mycompany.mavenproject2.dao.Campo;
import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import com.mycompany.mavenproject2.regras.FunConstrutor;
import com.mycompany.mavenproject2.regras.ModCadastro;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sankhya
 */
public class Cadastro extends Base {
    public void preencherPropriedadesCadastro(HttpServletRequest request){
        try {
            Dao dao = new Dao();
            dao.setTabela("SYSCADASTROS");
            DataSet ds = dao.getRecords(" WHERE ID = "+request.getParameter("cad"));
            
            request.setAttribute("cadastro-legenda", ds.getRegistros().get(0).getFieldValueByName("LEGENDA"));
            request.setAttribute("cadastro-nome", ds.getRegistros().get(0).getFieldValueByName("NOME"));
            request.setAttribute("cadastro-id", ds.getRegistros().get(0).getFieldValueByName("ID"));
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
            preencherPropriedadesCadastro(request);
            String op = request.getParameter("op");
            if ((op == null) || op.equals("")) {
                String pesquisa = request.getParameter("q");
                if ((pesquisa != null)) {
                    try {
                        ModCadastro mod = new ModCadastro(Integer.parseInt(request.getParameter("cad")));
                        Dao dao = new Dao();
                        dao.setTabela(mod.getTabNome());
                        DataSet ds = dao.getRecords("");
                        request.setAttribute("dataset-registros", ds);
                    } catch (SQLException ex) {
                        Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                getServletContext().getRequestDispatcher("/Construtor/CadastroBase-Lista.jsp").forward(request, response);
            } else if (op.equals("n")){
                FunConstrutor fun = new FunConstrutor();
                request.setAttribute("cadastro-form", fun.retornaFormCadastro(Integer.parseInt(request.getParameter("cad"))));
                getServletContext().getRequestDispatcher("/Construtor/CadastroBase-Cadastro.jsp").forward(request, response);
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
            request.setCharacterEncoding("utf8");
            ModCadastro mod = new ModCadastro(Integer.parseInt(request.getParameter("idcad")));
            
            Dao dao = new Dao();
            dao.setTabela(mod.getTabNome());
            try {
                int id = 0;
                if(request.getParameter("id") != null){
                    id = Integer.parseInt(request.getParameter("id"));
                }
                DataSet ds = dao.getRecords("where id = "+id);
                ds.setTabela(mod.getTabNome());

                if (!ds.getRegistros().isEmpty()) {
                   ds.setEstado(1);
                   ds.getRegistros().get(0).setCampos(dao.getCampos());
                   ds.getRegistros().get(0).getFieldByName("id").setValue(request.getParameter("id"));
                } else {
                   ds.setEstado(2); 
                   ds.getRegistros().get(0).setCampos(dao.getCampos());
                }
                
                ArrayList<Campo> campos = ds.getRegistros().get(0).getCampos();
                for (int i = 0; i <= campos.size() - 1; i++) {
                    if (!campos.get(i).getKey().equals("ID")) {
                        ds.getRegistros().get(0).getFieldByName(campos.get(i).getKey()).setValue(request.getParameter(campos.get(i).getKey()));
                    }
                }
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

            response.sendRedirect(request.getContextPath() + "/Cadastro?cad="+request.getParameter("idcad"));
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
