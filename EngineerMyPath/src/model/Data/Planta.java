/* Planta
 *
 * João Gonçalves, João Ferreira
 *
 * 17/12/17
 */
package model.Data;

import java.util.*;

public abstract class Planta {
    private String nome;
    private Grelha mapa;
    private Map<String, Planta> childMaps;
    private List<PontoDeAcesso> PontosAcesso;
    private String pathImagem;
    private Planta parent;
    
    
    public Planta(String nome){
        this.nome = nome;
        childMaps = new HashMap<>();
        PontosAcesso = new ArrayList<>();
    }
    
    public Planta getParent(){
        return parent;
    }
    
    public void setParent(Planta parent){
        this.parent = parent;
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

    public PontoDeAcesso getPontoAcessoByIndex(int index) {
        return PontosAcesso.get(index);
    }
    
    public PontoDeAcesso getPontoAcessoByDestino(String destino){
        for (PontoDeAcesso pa : PontosAcesso) {
            if(pa.getDestino().getNome().compareTo(destino) == 0)
                return pa;
        }
        return null;
    }

    public void addPontoAcesso(Planta origem, Planta destino, int px, int py) {
        int[] coordenada = {px,py};
        PontosAcesso.add(new PontoDeAcesso(origem,destino,coordenada));
    }
    
    public void addPontoAcesso(int px, int py) {
        int[] coordenada = {px,py};
        PontosAcesso.add(new PontoDeAcesso(null,this,coordenada));
    }
}
