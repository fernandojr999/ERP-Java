/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller;

import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import com.mycompany.mavenproject2.dao.Registro;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sankhya
 */
public class Login extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        if (op.equals("LOGIN")) {
            getServletContext().getRequestDispatcher("/Usuario/Login.jsp").forward(request, response);
        } else {
            if (op.equals("LOGOUT")){
                Cookie pg2 = new Cookie("sessaoid", "");
                pg2.setPath("/");
                response.addCookie(pg2);
                response.sendRedirect(request.getContextPath() + "/Login?op=LOGIN");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         // configuração para corrigir questões de acento
        request.setCharacterEncoding("utf8");
        
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        
        Dao dao = new Dao();
        dao.setTabela("USUARIO");
            DataSet ds;
        try {
            senha = gerarHash(senha);
            ds = dao.getRecords("where login = '"+usuario+"' and senha = '"+senha+"'");
            
            if (!ds.getRegistros().isEmpty()) {
                Cookie pg2 = new Cookie("sessaoid", gerarHash("melancia"+usuario+senha));
                pg2.setPath("/");
                response.addCookie(pg2);

                Cookie pg3 = new Cookie("usuarioid", ds.getRegistros().get(0).getFieldValueByName("id"));
                pg2.setPath("/");
                response.addCookie(pg3);
                
                request.getSession().setAttribute("usuario", ds.getRegistros().get(0));
                
                response.sendRedirect(request.getContextPath() + "/");
            }else{
                response.sendRedirect(request.getContextPath() + "/Login?op=LOGIN");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public static String gerarHash(String senha) throws Exception {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder texto = new StringBuilder();
        for (byte b : hash) {
          texto.append(String.format("%02X", 0xFF & b));
        }
        return texto.toString();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
