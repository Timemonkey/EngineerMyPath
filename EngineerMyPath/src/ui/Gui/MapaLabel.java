/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.ObservableApp;
import model.States.PesquisaSala;

/**
 *
 * @author João
 */
public class MapaLabel extends JLabel implements Observer {
    
    private ObservableApp observableApp;
    
    public MapaLabel(ObservableApp oApp) {
        this.observableApp = oApp;
        this.observableApp.addObserver(this);
        
        update(observableApp,null);
        validate();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (observableApp.getState() instanceof PesquisaSala) {
            try {
                BufferedImage Icon = ImageIO.read(Resources.getResourceFile(observableApp.getAppData().getPlantaAtual().getPathImagem()));
                setIcon(new ImageIcon(Icon.getScaledInstance(1000, 500, Image.SCALE_FAST)));
                setAlignmentX(Component.CENTER_ALIGNMENT);
                setAlignmentY(Component.CENTER_ALIGNMENT);
                
                int x = observableApp.getAppData().getPercurso().get(0).getX() * (1000/observableApp.getAppData().getPlantaAtual().getMapa().getXSize());
                int y = observableApp.getAppData().getPercurso().get(0).getY() * (500/observableApp.getAppData().getPlantaAtual().getMapa().getYSize());
                
                g.setColor(Color.ORANGE);
                g.fillOval(x, y, 15, 15);

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
