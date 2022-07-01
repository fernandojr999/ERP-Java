/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sankhya
 */
public class DataSet {
    private ArrayList<Registro> registros;
    private Registro registro;
    private int estado;
    private String tabela;

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public DataSet() {
        registros = new ArrayList<Registro>();
        estado = 0;
    }
    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
        
        if (estado == 2) {
            Registro reg = new Registro();
            registros.add(reg);
        }
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }
    
    public void salvar() throws Exception{
        if (!((estado == 1) || (estado == 2))){
             throw new Exception("O dataset deve estar em estado de inserção ou edição");
        }
        
        String comando;
        ArrayList<Campo> campos = registros.get(0).getCampos();
        if(estado == 1){
            comando = "UPDATE "+tabela+" "+
                             " set ";
            for (int i = 0; i <= campos.size() - 1; i++) {
                if (campos.get(i).getType().equals("VARCHAR")) {
                    comando = comando + campos.get(i).getKey() +" = '"+campos.get(i).getValue()+"'";
                } else {
                    comando = comando + campos.get(i).getKey() +" = "+campos.get(i).getValue();
                }
                
                if (!(i == (campos.size() - 1))) {
                    comando = comando + ", ";
                }
            }
            
            comando = comando + " WHERE ID = " + registros.get(0).getFieldValueByName("id");
        } else {
            registros.get(0).getFieldByName("id").setValue(""+NewID(tabela));
            
            comando = "INSERT INTO "+tabela+"( ";
            
            for (int i = 0; i <= campos.size() - 1; i++) {
                comando = comando + campos.get(i).getKey();
                
                if (!(i == (campos.size() - 1))) {
                    comando = comando + ", ";
                }
            }
            
            comando = comando + ")";
            comando = comando + " VALUES (";
                    
            for (int i = 0; i <= campos.size() - 1; i++) {
                if (campos.get(i).getType().equals("VARCHAR")) {
                    comando = comando + "'"+campos.get(i).getValue()+"'";
                } else {
                    comando = comando + campos.get(i).getValue(); 
                }
                
                
                if (!(i == (campos.size() - 1))) {
                    comando = comando + ", ";
                }
            }
            
            comando = comando + ") ";
        
        }
        
        Dao dao = new Dao();
        dao.execCommand(comando);
    }
    
    public int NewID(String tabela){
        try {
            Dao d = new Dao();
            DataSet ds = d.getQuery("SELECT IDENTIFICADOR FROM IDENTIFICADORES WHERE TABELANOME = '"+tabela+"'");
            d.execCommand("UPDATE IDENTIFICADORES SET IDENTIFICADOR = IDENTIFICADOR + 1 WHERE TABELANOME = '"+tabela+"'");
            return Integer.parseInt(ds.getRegistros().get(0).getFieldValueByName("IDENTIFICADOR"));
        } catch (SQLException ex) {
            Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
