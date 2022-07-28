/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2.regras;

import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sankhya
 */
public class ModCadastro {
    private int cadId;
    private String cadLegenda;
    private String tabNome;

    public ModCadastro(int cadId) {
        this.cadId = cadId;
        
        Dao dao = new Dao();
        dao.setTabela("SYSCADASTROS");
        try {
            DataSet ds = dao.getRecords("WHERE ID = "+cadId);
            this.cadLegenda = ds.getRegistros().get(0).getFieldValueByName("LEGENDA");
            this.tabNome = ds.getRegistros().get(0).getFieldValueByName("TABELANOME");
        } catch (SQLException ex) {
            Logger.getLogger(ModCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    public int getCadId() {
        return cadId;
    }

    public void setCadId(int cadId) {
        this.cadId = cadId;
    }
    
    public String getCadLegenda() {
        return cadLegenda;
    }

    public void setCadLegenda(String cadLegenda) {
        this.cadLegenda = cadLegenda;
    }

    public String getTabNome() {
        return tabNome;
    }

    public void setTabNome(String tabNome) {
        this.tabNome = tabNome;
    }
}
