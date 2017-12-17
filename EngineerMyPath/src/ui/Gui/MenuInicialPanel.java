/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.ObservableApp;
import model.States.*;

/**
 *
 * @author João
 */
class MenuInicialPanel extends JPanel implements Observer {
    
    private ObservableApp observableApp;
    
    private static final String IconPath = "images/LOGO.png";
        
    private JLabel iconLabel;
    private JTextField locOrigemT, locDestinoT; 
    private JButton pesquisaB;
    
    public MenuInicialPanel(ObservableApp oApp) {
        this.observableApp = oApp;
        this.observableApp.addObserver(this);
        
        locOrigemT = new JTextField("Localização de Origem");
        locDestinoT = new JTextField("Localização de Destino");
        pesquisaB = new JButton("Pesquisar");
        
        setupLayout();
        setupListeners();
    }
    
    private void setupLayout(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.GRAY);
        
        add(Box.createVerticalGlue());
        add(Box.createVerticalStrut(10));
        
        try{
            BufferedImage Icon = ImageIO.read(Resources.getResourceFile(IconPath));
            iconLabel = new JLabel(new ImageIcon(Icon.getScaledInstance(766, 195, Image.SCALE_FAST)));
            iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            iconLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            add(iconLabel);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        
        add(Box.createVerticalStrut(10));
        add(Box.createVerticalGlue());
        
        locOrigemT.setAlignmentX(Component.CENTER_ALIGNMENT);
        locOrigemT.setAlignmentY(Component.CENTER_ALIGNMENT);
        locOrigemT.setMaximumSize(new Dimension(200, 25));
        locOrigemT.setMinimumSize(new Dimension(200, 25));
        locOrigemT.setSize(new Dimension(200, 25));        
        add(locOrigemT);
        
        add(Box.createVerticalStrut(10));
        add(Box.createVerticalGlue());
        
        locDestinoT.setAlignmentX(Component.CENTER_ALIGNMENT);
        locDestinoT.setAlignmentY(Component.CENTER_ALIGNMENT);
        locDestinoT.setMaximumSize(new Dimension(200, 25));
        locDestinoT.setMinimumSize(new Dimension(200, 25));
        locDestinoT.setSize(new Dimension(200, 25));        
        add(locDestinoT);
        
        add(Box.createVerticalStrut(20));
        add(Box.createVerticalGlue());
        
        pesquisaB.setAlignmentX(Component.CENTER_ALIGNMENT);
        pesquisaB.setAlignmentY(Component.CENTER_ALIGNMENT);
        pesquisaB.setMaximumSize(new Dimension(120, 30));
        pesquisaB.setMinimumSize(new Dimension(120, 30));
        pesquisaB.setSize(new Dimension(120, 30));        
        add(pesquisaB);
        
        add(Box.createVerticalGlue());
        
        validate();
    }
    
    private void setupListeners() {
        
        locDestinoT.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
                if(locDestinoT.getText().length() > 3)
                    locOrigemT.setVisible(true);
                else if(locDestinoT.getText().length() < 4)
                    locOrigemT.setVisible(false);
            }

            @Override
            public void keyPressed(KeyEvent ke) {}

            @Override
            public void keyReleased(KeyEvent ke) {}
        });
        
        pesquisaB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //verificar se ambas as caixas estão preenchidas, ou só uma
            }
        });
    }
    
    @Override
    public void update(Observable o, Object o1) {
        setVisible(observableApp.getState() instanceof MenuInicial);
    }
    
}
