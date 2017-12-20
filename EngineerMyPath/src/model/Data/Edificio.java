/* Edificio
 *
 * João Gonçalves, João Ferreira
 *
 * 17/12/17
 */
package model.Data;

public class Edificio extends Planta{
    private int nPisos;
    
    public Edificio(Planta parent,String nome, int px, int py) {
        super(nome);
        setParent(parent);
        addPontoAcesso(parent,this,px, py);
    }
    
    public void setNPisos(int nPisos){
        this.nPisos = nPisos;
    }
    
    public int getNPisos(){
        return nPisos;
    }
    
}
