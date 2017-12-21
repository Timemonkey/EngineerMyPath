/* ObservableApp
 *
 * João Gonçalves
 *
 * 17/12/17
 */
package model;

import java.util.Observable;
import model.Data.AppData;
import model.States.IStates;

public class ObservableApp extends Observable {
    private App app;
    
    public ObservableApp() {
        this.app = new App();
    }
    
    public IStates getState() {
        return app.getState();
    }
    
    public void setState(IStates state) {
        app.setState(state);
    }
    
    public AppData getAppData() {
        return app.getAppData();
    }
    
    public void PesquisaSala(String sala) {
        setState(getState().PesquisaSala(sala));
        
        setChanged();
        notifyObservers();
    }

    public void PesquisaItinerario(String sala1, String sala2) {
        setState(getState().PesquisaItinerario(sala1, sala2));
        
        setChanged();
        notifyObservers();
    }

    public void Anterior() {
        setState(getState().Anterior());
        
        setChanged();
        notifyObservers();
    }

    public void Seguinte() {
        setState(getState().Seguinte());
        
        setChanged();
        notifyObservers();
    }

    public void TerminarPesquisa() {
        setState(getState().TerminarPesquisa());
        
        setChanged();
        notifyObservers();
    }
     
    public boolean existeLoc(String loc){
        return getAppData().pesquisaMapa(loc);
    }
}
