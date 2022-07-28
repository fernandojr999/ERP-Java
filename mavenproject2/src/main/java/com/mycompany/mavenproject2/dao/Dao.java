/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2.dao;

import com.mycompany.mavenproject2.bd.Conexao;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sankhya
 */
public class Dao {
    private String Tabela;
    private ArrayList<Campo> campos;

    public Dao() {
        campos = new ArrayList<Campo>();
    }

    public ArrayList<Campo> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Campo> campos) {
        this.campos = campos;
    }

    public String getTabela() {
        return Tabela;
    }

    public void setTabela(String Tabela) {
        this.Tabela = Tabela;
    }
    
    public DataSet getRecords(String where) throws SQLException{
        Conexao con = new Conexao();
        DataSet ds = new DataSet();
        
        con.Conectar();
        Statement stmt = Conexao.con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);  
        ResultSet res = stmt.executeQuery("select * from "+Tabela+" "+where);
        
        ResultSetMetaData meta = null;
        meta = res.getMetaData();
        
        if (res!=null){
            // Alimenta os campos do dao
            campos.clear();
            int numberOfColumns = meta.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++){
                Campo campo = new Campo();
                campos.add(campo);
                campo.setType(meta.getColumnTypeName(i));
                campo.setKey(meta.getColumnName(i));
            } 

            while(res.next()) {
                Registro reg = new Registro();
                ds.getRegistros().add(reg);

                // Obtem o número de colunas
                numberOfColumns = meta.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++){
                    Campo campo = new Campo();
                    reg.getCampos().add(campo);
                    campo.setType(meta.getColumnTypeName(i));
                    campo.setKey(meta.getColumnName(i));
                    campo.setValue(res.getString(meta.getColumnName(i)));
                }
            }
            
        }
        
        return ds;
    }
    
    public DataSet getQuery(String query) throws SQLException{
        Conexao con = new Conexao();
        DataSet ds = new DataSet();
        
        con.Conectar();
        Statement stmt = Conexao.con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);  
        ResultSet res = stmt.executeQuery(query);
        
        ResultSetMetaData meta = null;
        meta = res.getMetaData();
        
        if (res!=null){
            // Alimenta os campos do dao
            campos.clear();
            int numberOfColumns = meta.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++){
                Campo campo = new Campo();
                campos.add(campo);
                campo.setType(meta.getColumnTypeName(i));
                campo.setKey(meta.getColumnName(i));
            } 

            while(res.next()) {
                Registro reg = new Registro();
                ds.getRegistros().add(reg);

                // Obtem o número de colunas
                numberOfColumns = meta.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++){
                    Campo campo = new Campo();
                    reg.getCampos().add(campo);
                    campo.setType(meta.getColumnTypeName(i));
                    campo.setKey(meta.getColumnName(i));
                    campo.setValue(res.getString(meta.getColumnName(i)));
                }
            }
            
        }
        
        return ds;
    }
    
    public void execCommand(String comando){
        try {
            Conexao con = new Conexao();
            
            con.Conectar();
            Statement stmt;
            try {
                stmt = Conexao.con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate(comando);
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Conexao.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
