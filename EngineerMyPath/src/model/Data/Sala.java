/* Sala
 *
 * João Gonçalves
 *
 * 14/12/17
 */
package model.Data;

public class Sala extends Planta{
    
    public Sala(Planta parent,String nome, double px, double py) {
        super(nome);
        setPontoAcesso(px, py);
        setParent(parent);
    }
    
}
