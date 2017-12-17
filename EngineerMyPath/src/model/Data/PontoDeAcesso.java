/* PontoDeAcesso
 *
 * João Ferreira
 *
 * 17/12/17
 */
package model.Data;

public class PontoDeAcesso {
    private Planta origem, destino;
    private double [] coordenada;
    
    PontoDeAcesso(Planta origem, Planta destino, double[] coordenada){
        this.origem = origem;
        this.destino = destino;
        this.coordenada = coordenada;
    }
    
    public Planta getOrigem(){
        return origem;
    }
    
    public Planta getDestino(){
        return destino;
    }
    
    public double[] getCoordenada(){
        return coordenada;
    }
}
