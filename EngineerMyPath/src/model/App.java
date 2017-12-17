/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Data.AppData;
import model.States.IStates;

/**
 *
 * @author João
 */
public class App {
    private AppData appData;
    private IStates state;  
    
    public App(){
        this.appData = new AppData();
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
}
