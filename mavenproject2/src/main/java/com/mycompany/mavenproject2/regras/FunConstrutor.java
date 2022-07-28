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
public class FunConstrutor {
    public boolean gerarHTMLCadastro(int cadastro){
        try {
            String form = "";
            form = "<form action=\"./Cadastro?cad="+cadastro+"\" method=\"POST\">";
            
            Dao dao = new Dao();
            dao.setTabela("SYSCADASTROCAMPOS");
            DataSet ds = dao.getRecords("WHERE CADASTRO = "+cadastro);
            
            for (int i = 0; i <= ds.getRegistros().size() - 1; i++) {
                form = form + 
                        " <label>"+ds.getRegistros().get(i).getFieldValueByName("legenda")+"</label> <br>\n" +
                        " <input type=\"text\" name=\""+ds.getRegistros().get(i).getFieldValueByName("nome")+"\" id=\""+ds.getRegistros().get(i).getFieldValueByName("nome")+"\" >\n" +
                        "            \n" +
                        " <br><br>";
            }
            form = form + "<br><br>\n" +
                          "<input type=\"submit\">";
            form = form +"</form>";
            
            String comando = "UPDATE SYSCADSTROS SET HTMLCAMPOS = '"+form+"'";
            dao.execCommand(comando);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FunConstrutor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public String retornaFormCadastro(int cadastro){
        try {
            String form = "";
            form = "<form action=\"./Cadastro?idcad="+cadastro+"\" method=\"POST\">";
            
            Dao dao = new Dao();
            dao.setTabela("SYSCADASTROCAMPOS");
            DataSet ds = dao.getRecords("WHERE CADASTRO = "+cadastro);
            
            for (int i = 0; i <= ds.getRegistros().size() - 1; i++) {
                if (ds.getRegistros().get(i).getFieldValueByName("tipo").equals("5")) {
                    form = form + " <label>"+ds.getRegistros().get(i).getFieldValueByName("legenda")+"</label> <br>\n";
                    
                    Dao daoTab = new Dao();
                    daoTab.setTabela(ds.getRegistros().get(i).getFieldValueByName("tabelareferenciada"));
                    DataSet dsTab = daoTab.getRecords("");
                    
                    form = form + "<select name=\""+ds.getRegistros().get(i).getFieldValueByName("nome")+"\" id=\""+ds.getRegistros().get(i).getFieldValueByName("nome")+"\">";
                    for (int j = 0; j <= dsTab.getRegistros().size() - 1; j++) {
                        form = form + "<option value=\""+dsTab.getRegistros().get(j).getFieldValueByName("id")+"\">"+
                                      dsTab.getRegistros().get(j).getFieldValueByName(ds.getRegistros().get(i).getFieldValueByName("campodescref"))+"</option>";
                    }
                        
                    form = form + "</select>";
                }else{
                    form = form + 
                            " <label>"+ds.getRegistros().get(i).getFieldValueByName("legenda")+"</label> <br>\n" +
                            " <input type=\"text\" name=\""+ds.getRegistros().get(i).getFieldValueByName("nome")+"\" id=\""+ds.getRegistros().get(i).getFieldValueByName("nome")+"\" >\n" +
                            "            \n" +
                            " <br><br>";
                }
            }
            form = form + "<br><br>\n" +
                          "<input type=\"submit\">";
            form = form +"</form>";

            return form;
        } catch (SQLException ex) {
            Logger.getLogger(FunConstrutor.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
}
