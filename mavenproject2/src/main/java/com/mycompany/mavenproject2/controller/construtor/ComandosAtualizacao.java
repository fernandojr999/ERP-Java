/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2.controller.construtor;

import com.mycompany.mavenproject2.dao.Dao;
import com.mycompany.mavenproject2.dao.DataSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sankhya
 */
public class ComandosAtualizacao {
    Dao dao;
    int versaoAtual;

    public ComandosAtualizacao() {
        dao = new Dao();
        versaoAtual = 0;
        atualizar();
    }
    
    private void atualizaVersaoBanco(int versao){
        dao.execCommand("UPDATE INFORMACOESBD SET VERSAO = "+versao);
        versaoAtual = versao;
    }
    
    private void atualizar(){
        try {
            DataSet ds = dao.getQuery("SELECT * FROM INFORMACOESBD");
            versaoAtual = Integer.parseInt(ds.getRegistros().get(0).getFieldValueByName("VERSAO"));
            
            if(versaoAtual < 1){
                dao.execCommand("CREATE TABLE IDENTIFICADORES( "+
                                "       ID INT PRIMARY KEY, "+
                                "       TABELANOME VARCHAR(30), "+
                                "       IDENTIFICADOR INT)"); 
                atualizaVersaoBanco(1);
            }
            
            if(versaoAtual < 2){
                dao.execCommand("CREATE TABLE PRDORDENSPRODUCAO( "+
                                "       ID INT PRIMARY KEY, "+
                                "       PRODUTO INT, "+
                                "       DATAINSERCAO DATE, "+
                                "       DATALIMITE DATE, "+
                                "       FOREIGN KEY (PRODUTO) REFERENCES PRODUTO(ID))");
                
                dao.execCommand("INSERT INTO IDENTIFICADORES (ID, TABELANOME, IDENTIFICADOR) VALUES (2, 'PRDORDENSPRODUCAO', 1)"); 
                atualizaVersaoBanco(2);
            }
            
            if(versaoAtual < 3){
                dao.execCommand("RENAME TABLE PRODUTO TO PRODUTOS"); 
                atualizaVersaoBanco(3);
            }
            
            if(versaoAtual < 4){
                dao.execCommand("CREATE TABLE PRDMATERIASPRIMA( "+
                                "       ID INT PRIMARY KEY, "+
                                "       MATERIAPRIMA INT, "+
                                "       ORDEMPRODUCAO INT, "+
                                "       QUANTIDADE NUMERIC(10,8), "+
                                "       FOREIGN KEY (MATERIAPRIMA) REFERENCES PRODUTOS(ID), "+
                                "       FOREIGN KEY (ORDEMPRODUCAO) REFERENCES PRDORDENSPRODUCAO(ID))");
                
                dao.execCommand("INSERT INTO IDENTIFICADORES (ID, TABELANOME, IDENTIFICADOR) VALUES (3, 'PRDMATERIASPRIMA', 1)"); 
                atualizaVersaoBanco(4);
            }
            
            if(versaoAtual < 5){
                dao.execCommand("CREATE TABLE SYSCADASTROS( "+
                                "       ID INT PRIMARY KEY, "+
                                "       TABELANOME VARCHAR(30), "+
                                "       LEGENDA VARCHAR(100))");
                
                dao.execCommand("INSERT INTO IDENTIFICADORES (ID, TABELANOME, IDENTIFICADOR) VALUES (4, 'SYSCADASTROS', 1)"); 
                atualizaVersaoBanco(5);
            }
            
            if(versaoAtual < 6){
                dao.execCommand("CREATE TABLE SYSCADASTROCAMPOS( "+
                                "       ID INT PRIMARY KEY, "+
                                "       CADASTRO INT, "+
                                "       NOME VARCHAR(30), "+
                                "       LEGENDA VARCHAR(100),"+
                                "       TIPO VARCHAR(20), "+
                                "       FOREIGN KEY (CADASTRO) REFERENCES SYSCADASTROS(ID))");
                
                dao.execCommand("INSERT INTO IDENTIFICADORES (ID, TABELANOME, IDENTIFICADOR) VALUES (5, 'SYSCADASTROCAMPOS', 1)"); 
                atualizaVersaoBanco(6);
            }
            
            if(versaoAtual < 7){
                dao.execCommand("ALTER TABLE SYSCADASTROS ADD NOME VARCHAR(100)");
                atualizaVersaoBanco(7);
            }
            
            if(versaoAtual < 8){
                dao.execCommand("ALTER TABLE SYSCADASTROS ADD HTMLCAMPOS TEXT");
                atualizaVersaoBanco(8);
            }
            
            if(versaoAtual < 9){            
                dao.execCommand("INSERT INTO IDENTIFICADORES (ID, TABELANOME, IDENTIFICADOR) VALUES (6, 'PRODUTOS', 1)");
                atualizaVersaoBanco(9);
            }
            
            if(versaoAtual < 10){            
                dao.execCommand("ALTER TABLE SYSCADASTROS ADD CLASSE VARCHAR(60)");
                atualizaVersaoBanco(10);
            }
            
            if(versaoAtual < 11){            
                dao.execCommand("ALTER TABLE SYSCADASTROCAMPOS ADD TABELAREFERENCIADA VARCHAR(40)");
                atualizaVersaoBanco(11);
            }
            
            if(versaoAtual < 14){            
                dao.execCommand("ALTER TABLE SYSCADASTROCAMPOS ADD CAMPODESCREF VARCHAR(40)");
                atualizaVersaoBanco(14);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComandosAtualizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
