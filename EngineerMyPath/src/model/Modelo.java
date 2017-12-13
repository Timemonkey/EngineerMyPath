/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.Grelha.CelulaMapa;

public class Modelo extends Observable implements Pesquisa {
    private Planta plantaGeral;
    private List<List<CelulaMapa>> listaPerc;
    private int numPercursos;
    
    public Modelo(String nome, Grelha mapa, String path_imagem){
        plantaGeral = new Campus(nome, mapa, path_imagem);
        listaPerc = new ArrayList<List<CelulaMapa>>();
        numPercursos = 0;
    }
    
    public Planta getPlantaGeral(){
        return plantaGeral;
    }
    
    public void setPlantaGeral(Planta p){
        this.plantaGeral = p;
    }

    @Override
    public List<CelulaMapa> getProximoPercurso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CelulaMapa> pesquisaPerc(String loc1, String loc2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Planta pesquisaMapa(String loc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
