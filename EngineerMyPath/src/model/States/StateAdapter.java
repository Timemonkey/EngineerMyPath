/* StateAdapter
 *
 * João Gonçalves
 *
 * 17/12/17
 */
package model.States;

import model.Data.AppData;

public class StateAdapter implements IStates 
{ 
    private AppData appData;
    
    public StateAdapter(AppData appData){
        this.appData = appData;
    }
    
     public AppData getAppData() 
    {
         return appData;
    }
    
     public void setModelo(AppData appData) 
    {
        this.appData = appData;
    }    

    @Override
    public IStates PesquisaSala(String sala) {
        return this;
    }

    @Override
    public IStates PesquisaItinerario(String sala1, String sala2) {
        return this;
    }

    @Override
    public IStates Anterior() {
        return this;
    }

    @Override
    public IStates Seguinte() {
        return this;
    }

    @Override
    public IStates TerminarPesquisa() {
        return this;
    }
}
