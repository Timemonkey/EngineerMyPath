/* Edificio
 *
 * João Gonçalves
 *
 * 14/12/17
 */
package model;

public class Edificio extends Planta{
    private int nPisos;
    public Edificio(String nome, double px, double py) {
        super(nome);
        setPontoAcesso(px, py);
    }
    
    public void setNPisos(int nPisos){
        this.nPisos = nPisos;
    }
    
    public int getNPisos(){
        return nPisos;
    }
    
}
