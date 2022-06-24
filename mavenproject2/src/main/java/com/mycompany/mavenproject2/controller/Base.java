/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject2.controller;
import static com.mycompany.mavenproject2.controller.Login.gerarHash;
import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import com.mycompany.mavenproject2.dao.Registro;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
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
public class Base extends HttpServlet {
    public String SESSAO_USUARIO_ID;
    
    public boolean validaAcesso(HttpServletRequest request, HttpServletResponse response){
        try {
            String usuarioId = "";
            String sessionId = "";
            Cookie listaCookies[] = request.getCookies();
            if (listaCookies != null) {
                for (Cookie c : listaCookies) {
                    if (c.getName().equals("usuarioid")) {
                        usuarioId = c.getValue();
                    } else {
                        if (c.getName().equals("sessaoid")) {
                            sessionId = c.getValue();
                        }
                    }
                }
            }
            
            Dao dao = new Dao();
            dao.setTabela("USUARIO");
            DataSet ds;    
            try {
                ds = dao.getRecords("where id = "+usuarioId);
                String usuario = ds.getRegistros().get(0).getFieldValueByName("login");
                String senha = ds.getRegistros().get(0).getFieldValueByName("senha");
                
                if (gerarHash("melancia"+usuario+senha).equals(sessionId)) {
                    Registro reg = (Registro) request.getSession().getAttribute("usuario");
                    SESSAO_USUARIO_ID = reg.getFieldValueByName("id");
                    request.setAttribute("SESSAO_USUARIO_DADOS",request.getSession().getAttribute("usuario"));
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            response.sendRedirect(request.getContextPath() + "/Login?op=LOGIN");
        } catch (IOException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
