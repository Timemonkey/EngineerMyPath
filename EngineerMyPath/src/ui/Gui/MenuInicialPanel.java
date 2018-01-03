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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        
        update(observableApp,null);
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
        
        add(Box.createVerticalStrut(50));
        //add(Box.createVerticalGlue());
        
        locOrigemT.setAlignmentX(Component.CENTER_ALIGNMENT);
        locOrigemT.setAlignmentY(Component.CENTER_ALIGNMENT);
        locOrigemT.setMaximumSize(new Dimension(200, 30));
        locOrigemT.setMinimumSize(new Dimension(200, 30));
        locOrigemT.setSize(new Dimension(200, 30));
        locOrigemT.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));        
        add(locOrigemT);

        
        add(Box.createVerticalStrut(30));
        
        locDestinoT.setAlignmentX(Component.CENTER_ALIGNMENT);
        locDestinoT.setAlignmentY(Component.CENTER_ALIGNMENT);
        locDestinoT.setMaximumSize(new Dimension(200, 30));
        locDestinoT.setMinimumSize(new Dimension(200, 30));
        locDestinoT.setSize(new Dimension(200, 30)); 
        locDestinoT.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
        add(locDestinoT);
        
        add(Box.createVerticalStrut(20));
        add(Box.createVerticalGlue());
        
        pesquisaB.setAlignmentX(Component.CENTER_ALIGNMENT);
        pesquisaB.setAlignmentY(Component.CENTER_ALIGNMENT);
        pesquisaB.setMaximumSize(new Dimension(120, 50));
        pesquisaB.setMinimumSize(new Dimension(120, 50));
        pesquisaB.setSize(new Dimension(120, 50));  
        pesquisaB.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        add(pesquisaB);
        
        add(Box.createVerticalGlue());
        
        locDestinoT.requestFocus();
        locOrigemT.setVisible(false);
        validate();
    }
    
    private void setupListeners() {
        locDestinoT.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                if(locDestinoT.getText().length() < 4 || locDestinoT.getText().compareTo("Localização de Destino") == 0){
                    locOrigemT.setText("Localização de Origem");
                    locOrigemT.setVisible(false);
                    validate();
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if(locDestinoT.getText().length() < 4 || locDestinoT.getText().compareTo("Localização de Destino") == 0){
                    locOrigemT.setText("Localização de Origem");
                    locOrigemT.setVisible(false);
                    validate();
                }
            }
        });
        
        locDestinoT.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
                if(locDestinoT.getText().length() >= 3 && locDestinoT.getText().compareTo("Localização de Destino") != 0){
                    locOrigemT.setVisible(true);
                    validate();
                }
                /*else if(locDestinoT.getText().length() < 3 || locDestinoT.getText().compareTo("Localização de Destino") == 0){
                    locOrigemT.setVisible(false);
                    validate();
                }*/
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if(locDestinoT.getText().length() < 4 || locDestinoT.getText().compareTo("Localização de Destino") == 0){
                    locOrigemT.setText("Localização de Origem");
                    locOrigemT.setVisible(false);
                    validate();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if(locDestinoT.getText().length() < 4 || locDestinoT.getText().compareTo("Localização de Destino") == 0){
                    locOrigemT.setText("Localização de Origem");
                    locOrigemT.setVisible(false);
                    validate();
                }
            }
        });
        
        locDestinoT.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                locDestinoT.setText("");
            }
        });
        
        locOrigemT.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                locOrigemT.setText("");
            }
        });
        
        pesquisaB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                String strOrigem = locOrigemT.getText().toUpperCase();
                String strDestino = locDestinoT.getText().toUpperCase();


                
                if(strOrigem.compareToIgnoreCase("Localização de Origem") != 0){
                    if(observableApp.existeLoc(strOrigem) && observableApp.existeLoc(strDestino) && strOrigem.compareTo(strDestino) != 0){
                        observableApp.getAppData().reset();
                        locOrigemT.setVisible(false);
                        locOrigemT.setText("Localização de Origem");
                        locDestinoT.setText("Localização de Destino");
                        observableApp.PesquisaItinerario(strOrigem, strDestino);
                    } else{
                        observableApp.getAppData().reset();
                        if(strOrigem.compareTo(strDestino) == 0)
                            JOptionPane.showMessageDialog(null, "As localizações introduzidas são iguais!\nSe desejar um itinerário, as localizações têm de ser difrentes.");
                        else if(!observableApp.existeLoc(strDestino))
                            JOptionPane.showMessageDialog(null, "A localização de destino não se encontra no sistema!\nFormato: L1.4-DEIS");
                        else if(!observableApp.existeLoc(strOrigem))
                            JOptionPane.showMessageDialog(null, "A localização de origem não se encontra no sistema!\nFormato: L1.4-DEIS");
                        locOrigemT.setText("Localização de Origem");
                        locDestinoT.setText("Localização de Destino");
                    }
                } else {
                    if(observableApp.existeLoc(strDestino)){
                        observableApp.getAppData().reset();
                        locOrigemT.setVisible(false);
                        locOrigemT.setText("Localização de Origem");
                        locDestinoT.setText("Localização de Destino");
                        observableApp.PesquisaSala(strDestino);
                    } else{
                        observableApp.getAppData().reset();
                        if(strDestino.length() < 3)
                            JOptionPane.showMessageDialog(null, "Por favor introduza uma localização de destino válida!\nFormato: L1.4-DEIS");
                        else if(!observableApp.existeLoc(strDestino))                        
                            JOptionPane.showMessageDialog(null, "As localização introduzida não se encontra no sistema!\nFormato: L1.4-DEIS");
                        locOrigemT.setText("Localização de Origem");
                        locDestinoT.setText("Localização de Destino");
                    }
                }
            }
        });
    }
    
    @Override
    public void update(Observable o, Object o1) {
        setVisible(observableApp.getState() instanceof MenuInicial);
    }
    
}
