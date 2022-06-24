/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2.dao;

import java.util.ArrayList;

/**
 *
 * @author sankhya
 */
public class Registro {
    
    private ArrayList<Campo> campos;

    public Registro() {
        campos = new ArrayList<Campo>();
    }

    public ArrayList<Campo> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Campo> campos) {
        this.campos = campos;
    }
    
    public String getFieldValueByName(String name){
        for (int i = 0; i <= campos.size() - 1; i++) {
            if ( campos.get(i).getKey().toUpperCase().equals(name.toUpperCase())){
                return campos.get(i).getValue();
            }
        }
        return "";
    }
    
    public Campo getFieldByName(String name){
        for (int i = 0; i <= campos.size() - 1; i++) {
            if ( campos.get(i).getKey().toUpperCase().equals(name.toUpperCase())){
                return campos.get(i);
            }
        }
        return null;
    }
}
