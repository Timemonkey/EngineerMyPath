/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import model.ObservableApp;

/**
 *
 * @author João
 */
public class AppFrame extends JFrame implements Observer {
    
    private ObservableApp observableApp;
    private AppPanel appPanel;
    
    public AppFrame(ObservableApp oApp){
        super("Engineer My Path");
        
        observableApp = oApp;
        observableApp.addObserver(this);
        
        Container cp = getContentPane();
        
        appPanel = new AppPanel(observableApp);
        cp.add(appPanel, BorderLayout.CENTER);
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        validate();
    }
    
    @Override
    public void update(Observable o, Object o1) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        repaint();
    }
    
}
