/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller.admin;

import com.mycompany.mavenproject2.controller.Base;
import com.mycompany.mavenproject2.controller.Produto;
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
public class AdministracaoSistema extends Base {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
                String op = request.getParameter("op");
            if ((op == null) || op.equals("")) {
                Dao dao = new Dao();
                dao.setTabela("CONFIGURACOES");
                    /*try {
                        request.setAttribute("registros", dao.getRecords(""));
                    } catch (SQLException ex) {
                        Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                getServletContext().getRequestDispatcher("/AdministracaoSistema/ConfiguracoesSistema.jsp").forward(request, response);
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
