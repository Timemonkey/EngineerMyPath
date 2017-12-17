/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.*;
import model.States.*;

/**
 *
 * @author João
 */
class PesquisaSalaPanel extends JPanel implements Observer {
    
    private ObservableApp observableApp;
    
    
    
    
    public PesquisaSalaPanel(ObservableApp oApp){
        this.observableApp = oApp;
        this.observableApp.addObserver(this);
    }
    
    @Override
    public void update(Observable o, Object o1) {
        setVisible(observableApp.getState() instanceof PesquisaSala);
    }
    
}
