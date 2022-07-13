package com.mycompany.mavenproject2.controller.construtor;

import com.mycompany.mavenproject2.controller.Base;
import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cadastro extends Base {
    private String cadastrotabela;
    private String cadastroNome;
    private String cadastroLegenda;

    public String getCadastrotabela() {
        return cadastrotabela;
    }

    public void setCadastrotabela(String cadastrotabela) {
        this.cadastrotabela = cadastrotabela;
    }

    public String getCadastroNome() {
        return cadastroNome;
    }

    public void setCadastroNome(String cadastroNome) {
        this.cadastroNome = cadastroNome;
    }

    public String getCadastroLegenda() {
        return cadastroLegenda;
    }

    public void setCadastroLegenda(String cadastroLegenda) {
        this.cadastroLegenda = cadastroLegenda;
    }
    
    public void preencherPropriedadesCadastro(HttpServletRequest request){
        try {
            Dao dao = new Dao();
            dao.setTabela("SYSCADASTROS");
            DataSet ds = dao.getRecords(" WHERE ID = "+request.getParameter("cad"));
            setCadastrotabela(ds.getRegistros().get(0).getFieldValueByName("TABELANOME"));
            setCadastroNome(ds.getRegistros().get(0).getFieldValueByName("NOME"));
            setCadastroLegenda(ds.getRegistros().get(0).getFieldValueByName("LEGENDA"));
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (validaAcesso(request, response)){
            preencherPropriedadesCadastro(request);
            //request.setAttribute("registro", dao.getRecords("where id = "+request.getParameter("id")));
            getServletContext().getRequestDispatcher("/Construtor/CadastroBase.jsp").forward(request, response);
            
            
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
