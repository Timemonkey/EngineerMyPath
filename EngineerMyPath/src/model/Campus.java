/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;

/**
 *
 * @author João
 */
public class Campus extends Planta {

    public Campus(String nome, Grelha mapa, String path_imagem) {
        super(nome);
        this.setMapa(mapa);
        this.setPathImagem(path_imagem);
    }
    
}
