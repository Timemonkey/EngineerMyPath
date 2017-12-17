/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Gui;

import java.awt.Color;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import model.ObservableApp;

/**
 *
 * @author João
 */
public class AppPanel extends JPanel implements Observer {
    
    private ObservableApp observableApp;
    
    private MenuInicialPanel menuInicialPanel;
    private PesquisaItinerarioPanel pesquisaItinerarioPanel;
    private PesquisaSalaPanel pesquisaSalaPanel;
    
    public AppPanel(ObservableApp oApp){
        this.observableApp = oApp;
        this.observableApp.addObserver(this);
        
        setupComponents();
        setupLayout();
        
    }
    
    private void setupComponents(){
        menuInicialPanel = new MenuInicialPanel(observableApp);
        pesquisaItinerarioPanel = new PesquisaItinerarioPanel(observableApp);
        pesquisaSalaPanel = new PesquisaSalaPanel(observableApp);
    }
    
    private void setupLayout(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        add(Box.createVerticalGlue());
        add(menuInicialPanel);
        add(pesquisaItinerarioPanel);
        add(pesquisaSalaPanel);
        add(Box.createVerticalGlue());
        
        setBackground(Color.DARK_GRAY);
        setBorder(new LineBorder(Color.BLACK));
    }
    
    @Override
    public void update(Observable o, Object o1) {}
    
}
