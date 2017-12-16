/* Planta
 *
 * João Gonçalves
 *
 * 14/12/17
 */
package model;

import java.util.*;

public abstract class Planta {
    private String nome;
    private Grelha mapa;
    private Map<String, Planta> childMaps;
    private double PontoAcesso[];
    private String pathImagem;
    
    
    public Planta(String nome){
        this.nome = nome;
        childMaps = new HashMap<String, Planta>();
    }

    public String getNome() {
        return nome;
    }
    
    public void addChild(String key, Planta child){
        childMaps.put(key, child);
    }
    
    public Planta getChild(String key){
        return childMaps.get(key);
    }
    
    public boolean hasChild(String key){
        return childMaps.containsKey(key);
    }
    
    public boolean hasChilds(){
        return !childMaps.isEmpty();
    }

    public Grelha getMapa() {
        return mapa;
    }

    public void setMapa(Grelha mapa) {
        this.mapa = mapa;
    }

    public String getPathImagem() {
        return pathImagem;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    public double[] getPontoAcesso() {
        return PontoAcesso;
    }

    public void setPontoAcesso(double px, double py) {
        this.PontoAcesso[0] = px;
        this.PontoAcesso[1] = py;
    }
    
    public double getPontoAcessoX(){
        return this.PontoAcesso[0];
    }
    
    public double getPontoAcessoY(){
        return this.PontoAcesso[1];
    }
    
}
