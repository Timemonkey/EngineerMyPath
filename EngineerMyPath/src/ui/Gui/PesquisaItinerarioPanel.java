/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.ObservableApp;
import model.States.*;

/**
 *
 * @author João
 */
class PesquisaItinerarioPanel extends JPanel implements Observer {
    
    private ObservableApp observableApp;
    
    private JLabel imgLabel;
    private JButton seguinteTerminarPesquisaB;
    private JButton anteriorPesquisaB;
    
    public PesquisaItinerarioPanel(ObservableApp oApp){
        this.observableApp = oApp;
        this.observableApp.addObserver(this);
        
        seguinteTerminarPesquisaB = new JButton("Seguinte");
        anteriorPesquisaB = new JButton("Anterior");
        imgLabel = new JLabel();
        
        setupLayout();
        //setupListeners();
        
        update(observableApp, null);
    }
    
    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.GRAY);
        //this.setSize(dmnsn);

        add(Box.createHorizontalGlue());
        add(Box.createHorizontalStrut(10));
        add(imgLabel);
        
        add(Box.createHorizontalGlue());
        
        anteriorPesquisaB.setAlignmentX(Component.CENTER_ALIGNMENT);
        anteriorPesquisaB.setAlignmentY(Component.CENTER_ALIGNMENT);
        anteriorPesquisaB.setMaximumSize(new Dimension(120, 30));
        anteriorPesquisaB.setMinimumSize(new Dimension(120, 30));
        anteriorPesquisaB.setSize(new Dimension(120, 30));  
        anteriorPesquisaB.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
        add(anteriorPesquisaB);
        
        add(Box.createHorizontalStrut(10));
        add(Box.createHorizontalGlue());
        
        update(observableApp,null);
        validate();
    }
    
    
    
    @Override
    public void update(Observable o, Object o1) {
        setVisible(observableApp.getState() instanceof PesquisaItinerario);
    }
    
}
