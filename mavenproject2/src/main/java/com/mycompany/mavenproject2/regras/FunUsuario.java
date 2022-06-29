/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2.regras;

import com.mycompany.mavenproject2.controller.Login;
import com.mycompany.mavenproject2.dao.Dao;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sankhya
 */
public class FunUsuario {
    public void cadastroUsuarioMassa(int qtd){
        String comando;
        String nome;
        String login;
        String senha = "";
        
        Random gerador = new Random();
        for (int i = 0; i < qtd; i++) {
            nome = "NOME_"+gerador.nextLong();
            login = "LOGIN_"+gerador.nextLong();
            try {
                senha = Login.gerarHash("SENHA_"+gerador.nextLong());
            } catch (Exception ex) {
                Logger.getLogger(FunUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            comando = "INSERT INTO USUARIO (ID, NOME, LOGIN, SENHA) VALUES "+
                      "(null,'"+nome+"', '"+login+"', '"+senha+"')";

            Dao dao = new Dao();
            dao.execCommand(comando);
        }
    }
}
