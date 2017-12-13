/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author João
 */
public class Edificio extends Planta{
    
    public Edificio(String nome, double px, double py) {
        super(nome);
        setPontoAcesso(px, py);
    }
    
}
