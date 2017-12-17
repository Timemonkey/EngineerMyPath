/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;
import model.Data.AppData;
import model.States.IStates;

/**
 *
 * @author João
 */
public class ObservableApp extends Observable{
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
     
    
}
