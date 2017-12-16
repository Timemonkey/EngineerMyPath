/* Sala
 *
 * João Gonçalves
 *
 * 14/12/17
 */
package model;

public class Sala extends Planta{
    
    public Sala(String nome, double px, double py) {
        super(nome);
        setPontoAcesso(px, py);
    }
    
}
