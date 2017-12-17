/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.States;

import model.Data.AppData;

/**
 *
 * @author João
 */
public class PesquisaSala extends StateAdapter {
    
    public PesquisaSala(AppData modelo) {
        super(modelo);
    }
    
    @Override
    public IStates TerminarPesquisa() {
        return new MenuInicial(getAppData());
    }
}
