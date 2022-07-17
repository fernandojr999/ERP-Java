/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller.construtor;

import com.mycompany.mavenproject2.controller.Base;
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
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
