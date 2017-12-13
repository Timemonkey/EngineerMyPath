/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.Grelha.CelulaMapa;

public interface Pesquisa {
    
    public List<CelulaMapa> getProximoPercurso();
    public List<CelulaMapa> pesquisaPerc(String loc1, String loc2);
    public Planta pesquisaMapa(String loc);
    
}
