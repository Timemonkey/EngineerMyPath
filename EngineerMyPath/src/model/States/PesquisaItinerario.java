/* PesquisaItinerario
 *
 * João Gonçalves
 *
 * 17/12/17
 */
package model.States;

import model.Data.AppData;

public class PesquisaItinerario extends StateAdapter {
    
    public PesquisaItinerario(AppData modelo) {
        super(modelo);
    }
    
    @Override
    public IStates Anterior() {
        //Falta Implementar
        return this;
    }

    @Override
    public IStates Seguinte() {
        //Falta Implementar
        return this;
    }

    @Override
    public IStates TerminarPesquisa() {
        return new MenuInicial(getAppData());
    }
}
