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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
    private JButton seguinteTerminarB;
    private JButton anteriorB;
    
    public PesquisaItinerarioPanel(ObservableApp oApp){
        this.observableApp = oApp;
        this.observableApp.addObserver(this);
        
        seguinteTerminarB = new JButton("Seguinte");
        anteriorB = new JButton("Anterior");
        imgLabel = new MapaLabel(observableApp);
        
        setupLayout();
        setupListeners();
        
        update(observableApp, null);
    }
    
    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.GRAY);
                
        add(Box.createHorizontalGlue());
        add(Box.createHorizontalStrut(10));
        
        anteriorB.setAlignmentX(Component.CENTER_ALIGNMENT);
        anteriorB.setAlignmentY(Component.CENTER_ALIGNMENT);
        anteriorB.setMaximumSize(new Dimension(100, 30));
        anteriorB.setMinimumSize(new Dimension(100, 30));
        anteriorB.setSize(new Dimension(100, 30));  
        anteriorB.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
        add(anteriorB);
        anteriorB.setEnabled(false);
        
        add(Box.createHorizontalGlue());

        add(imgLabel);
        
        add(Box.createHorizontalGlue());
        
        seguinteTerminarB.setAlignmentX(Component.CENTER_ALIGNMENT);
        seguinteTerminarB.setAlignmentY(Component.CENTER_ALIGNMENT);
        seguinteTerminarB.setMaximumSize(new Dimension(100, 30));
        seguinteTerminarB.setMinimumSize(new Dimension(100, 30));
        seguinteTerminarB.setSize(new Dimension(100, 30));  
        seguinteTerminarB.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
        add(seguinteTerminarB);
        
        add(Box.createHorizontalStrut(10));
        add(Box.createHorizontalGlue());
        
        update(observableApp,null);
        validate();
    }
    
    private void setupListeners(){
        anteriorB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if(observableApp.getAppData().getPercursoAtual() > 0)
                    observableApp.Anterior();                
            }
        });
        
        seguinteTerminarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if(observableApp.getAppData().getPercursoAtual() < observableApp.getAppData().getNumPercursos() - 1)
                    observableApp.Seguinte();                
            }
        });
        
        seguinteTerminarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if(observableApp.getAppData().getPercursoAtual() < observableApp.getAppData().getNumPercursos() - 1)
                    observableApp.Seguinte(); 
                else
                    observableApp.TerminarPesquisa();
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (observableApp.getState() instanceof PesquisaItinerario) {
            try {
                BufferedImage Icon = ImageIO.read(Resources.getResourceFile(observableApp.getAppData().getPlantaAtual().getPathImagem()));
                imgLabel.setIcon(new ImageIcon(Icon.getScaledInstance(1000, 500, Image.SCALE_FAST)));
                imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                imgLabel.setAlignmentY(Component.CENTER_ALIGNMENT);


            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    
    
    @Override
    public void update(Observable o, Object o1) {
        repaint();
        if(observableApp.getAppData().getPercursoAtual() > 0)
            anteriorB.setEnabled(true);
        if(observableApp.getAppData().getPercursoAtual() == observableApp.getAppData().getNumPercursos() - 1)
            seguinteTerminarB.setText("Terminar Pesquisa");
        if(observableApp.getAppData().getPercursoAtual() < observableApp.getAppData().getNumPercursos() - 1)
            seguinteTerminarB.setText("Seguinte");
        setVisible(observableApp.getState() instanceof PesquisaItinerario);
    }
    
}
