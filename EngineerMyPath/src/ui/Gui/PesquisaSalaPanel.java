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
import javax.swing.JPanel;
import model.*;
import model.States.*;

/**
 *
 * @author João
 */
class PesquisaSalaPanel extends JPanel implements Observer {

    private ObservableApp observableApp;

    private JLabel imgLabel;
    private JButton terminarPesquisaB;

    public PesquisaSalaPanel(ObservableApp oApp) {
        this.observableApp = oApp;
        this.observableApp.addObserver(this);
        
        terminarPesquisaB = new JButton("Terminar Pesquisa");
        imgLabel = new JLabel();
        setupLayout();
        setupListeners();

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
        
        terminarPesquisaB.setAlignmentX(Component.CENTER_ALIGNMENT);
        terminarPesquisaB.setAlignmentY(Component.CENTER_ALIGNMENT);
        terminarPesquisaB.setMaximumSize(new Dimension(120, 30));
        terminarPesquisaB.setMinimumSize(new Dimension(120, 30));
        terminarPesquisaB.setSize(new Dimension(120, 30));  
        terminarPesquisaB.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
        add(terminarPesquisaB);
        
        add(Box.createHorizontalStrut(10));
        add(Box.createHorizontalGlue());
        
        update(observableApp,null);
        validate();
    }
    
    private void setupListeners() {
        terminarPesquisaB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                observableApp.TerminarPesquisa();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        System.out.println("Passei aqui");
        
        if (observableApp.getState() instanceof PesquisaSala) {
            try {
                BufferedImage Icon = ImageIO.read(Resources.getResourceFile(observableApp.getAppData().getPlantaAtual().getPathImagem()));
                System.out.println(observableApp.getAppData().getPlantaAtual().getPathImagem());
                System.out.println(observableApp.getAppData().getPlantaAtual().getNome());
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
        setVisible(observableApp.getState() instanceof PesquisaSala);

    }

}
