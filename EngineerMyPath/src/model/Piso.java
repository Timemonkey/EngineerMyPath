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
public class Piso extends Planta {
    
    public Piso(String nome, double px, double py, Grelha mapa, String path_imagem) {
        super(nome);
        this.setPontoAcesso(px, py);
        this.setMapa(mapa);
        this.setPathImagem(path_imagem);
    }
    
}
