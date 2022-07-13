/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller.construtor;

import com.mycompany.mavenproject2.controller.Base;
import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CriarCadastroCampo extends Base {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
            String op = request.getParameter("op");
            if (op.equals("i")) {
                String cadastro = request.getParameter("cadastro");
                String tipo = request.getParameter("tipo");
                String nome = request.getParameter("nome");
                String legenda = request.getParameter("legenda");
                int id = new DataSet().NewID("SYSCADASTROCAMPOS");
                
                String comanIn = "INSERT INTO SYSCADASTROCAMPOS (ID, CADASTRO, NOME, LEGENDA, TIPO) "+
                                 "VALUES ("+id+", "+cadastro+", '"+nome+"','"+legenda+"','"+tipo+"')";
                
                Dao dao = new Dao();
                dao.execCommand(comanIn);
                
                response.sendRedirect(request.getContextPath() + "/CriarCadastro?op=e&id="+cadastro);
            } else {
                if (op.equals("d")) {
                    Dao dao = new Dao();
                    dao.execCommand("DELETE FROM SYSCADASTROCAMPOS WHERE ID = "+request.getParameter("id"));
                    response.sendRedirect(request.getContextPath() + "/CriarCadastro?op=e&id="+request.getParameter("cadastro"));
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
