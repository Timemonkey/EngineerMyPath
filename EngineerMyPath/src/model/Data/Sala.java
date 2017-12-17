/* Sala
 *
 * João Gonçalves, João Ferreira
 *
 * 17/12/17
 */
package model.Data;

public class Sala extends Planta{
    
    public Sala(Planta parent,String nome, int px, int py) {
        super(nome);
        addPontoAcesso(parent,this,px,py);
        setParent(parent);
    }
    
}
