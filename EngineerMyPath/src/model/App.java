/* App
 *
 * João Gonçalves
 *
 * 17/12/17
 */
package model;

import model.Data.AppData;
import model.States.IStates;
import model.States.MenuInicial;

public class App {
    private AppData appData;
    private IStates state;  
    
    public App(){
        this.appData = new AppData();
        state = new MenuInicial(appData);
    }
    
    public IStates getState() {
        return state;
    }
    
    public void setState(IStates state) {
        this.state = state;
    }
    
    public AppData getAppData(){
        return this.appData;
    }
    
    public void setAppData(AppData appData){
        this.appData = appData;
    }
    
    public void PesquisaSala(String sala) {
        setState(getState().PesquisaSala(sala));
    }

    public void PesquisaItinerario(String sala1, String sala2) {
        setState(getState().PesquisaItinerario(sala1, sala2));
    }

    public void Anterior() {
        setState(getState().Anterior());
    }

    public void Seguinte() {
        setState(getState().Seguinte());
    }

    public void TerminarPesquisa() {
        setState(getState().TerminarPesquisa());
    }
    
    public boolean existeLoc(String loc){
        return appData.pesquisaMapa(loc);
    }
}
