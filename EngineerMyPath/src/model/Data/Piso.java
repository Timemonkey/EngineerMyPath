/* Piso
 *
 * João Gonçalves, João Ferreira
 *
 * 16/12/17
 */
package model.Data;

public class Piso extends Planta {
    
    public Piso(Planta parent,String nome) {
        super(nome);
        setParent(parent);
    }
    
    public int getFloorNumber(){
        String strArr[] = getNome().split(" ");
        return Integer.parseInt(strArr[1]);
    }
    
}
