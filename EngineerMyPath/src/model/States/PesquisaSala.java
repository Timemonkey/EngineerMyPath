/* PesquisaSala
 *
 * João Gonçalves
 *
 * 17/12/17
 */
package model.States;

import model.Data.AppData;

public class PesquisaSala extends StateAdapter {
    
    public PesquisaSala(AppData modelo) {
        super(modelo);
    }
    
    @Override
    public IStates TerminarPesquisa() {
            
        return new MenuInicial(getAppData());
    }
}
