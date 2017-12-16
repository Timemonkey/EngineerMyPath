/* Piso
 *
 * João Gonçalves
 *
 * 14/12/17
 */
package model;

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
