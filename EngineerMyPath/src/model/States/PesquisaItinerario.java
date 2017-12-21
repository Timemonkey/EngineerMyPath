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
        this.getAppData().setPercursoAnterior();
        return this;
    }

    @Override
    public IStates Seguinte() {
        this.getAppData().setProximoPercurso();
        return this;
    }

    @Override
    public IStates TerminarPesquisa() {
        this.getAppData().reset();
        return new MenuInicial(getAppData());
    }
}
