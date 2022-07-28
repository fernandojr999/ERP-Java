package com.mycompany.mavenproject2.controller.construtor;

import com.mycompany.mavenproject2.controller.Base;
import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CriarCadastroFiltro extends Base {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        if (validaAcesso(request, response)){
            String op = request.getParameter("op");
            if (op.equals("i")) {
                String cadastro = request.getParameter("cadastro");
                String tipo = request.getParameter("tipo");
                String campoNome = request.getParameter("campo");
                String sequencia = request.getParameter("sequencia");
                
                int id = new DataSet().NewID("SYSCADASTROFILTROS");
                
                String comanIn = "INSERT INTO SYSCADASTROFILTROS (ID, CADASTRO, CAMPONOME, SEQUENCIA, TIPO) "+
                                 "VALUES ("+id+", "+cadastro+", '"+campoNome+"','"+sequencia+"','"+tipo+")";
                
                Dao dao = new Dao();
                dao.execCommand(comanIn);
                
                response.sendRedirect(request.getContextPath() + "/CriarCadastro?op=e&id="+cadastro);
            } else {
                if (op.equals("d")) {
                    Dao dao = new Dao();
                    dao.execCommand("DELETE FROM SYSCADASTROFILTROS WHERE ID = "+request.getParameter("id"));
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
    }// </editor-fold>

}
