/* MenuInicial
 *
 * João Gonçalves
 *
 * 17/12/17
 */
package model.States;

import model.Data.AppData;

/**
 *
 * @author João
 */
public class MenuInicial extends StateAdapter {
    
    public MenuInicial(AppData modelo) {
        super(modelo);
    }
    
    @Override
    public IStates PesquisaSala(String sala) {
        //Funcao que faz o pesquisa de localizacao
        return new PesquisaSala(getAppData());
    }

    @Override
    public IStates PesquisaItinerario(String sala1, String sala2) {
        //Falta que faz pesquisa de itinerario
        return new PesquisaItinerario(getAppData());
    }
}
