/* PontoDeAcesso
 *
 * João Ferreira
 *
 * 17/12/17
 */
package model.Data;

import java.util.Arrays;

public class PontoDeAcesso {
    private Planta planta,destino;
    private int [] coordenada;
    
    PontoDeAcesso(Planta planta, Planta destino, int[] coordenada){
        this.planta = planta;
        this.destino = destino;
        this.coordenada = coordenada;
    }
    
    public Planta getPlanta(){
        return planta;
    }
    
    public Planta getDestino(){
        return destino;
    }
    
    public int getX(){
        return coordenada[0];
    }
    
    public int getY(){
        return coordenada[1];
    }

    @Override
    public String toString() {
        return "Destino : " + destino.getNome() + " | Planta : " + planta.getNome() + " | Coordenada : " + getX() + "," + getY();
    }
    
    
}
