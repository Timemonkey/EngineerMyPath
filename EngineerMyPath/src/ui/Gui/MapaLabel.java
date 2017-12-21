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
import model.States.PesquisaItinerario;
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

        update(observableApp, null);
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
                
                String str;
                
                try {
                    str = observableApp.getAppData().getPlantaAtual().getParent().getNome();
                } catch(NullPointerException e){
                    str = "Campus";
                }
                
                int x = 0;
                int y = 0;

                if (str.compareToIgnoreCase("Deis") == 0) {
                    x = observableApp.getAppData().getPercurso().get(0).getX() * (1000 / observableApp.getAppData().getPlantaAtual().getMapa().getXSize()) + 25;
                    y = observableApp.getAppData().getPercurso().get(0).getY() * (500 / observableApp.getAppData().getPlantaAtual().getMapa().getYSize()) + 13;
                } else if(str.compareToIgnoreCase("Gerais") == 0) {
                    x = observableApp.getAppData().getPercurso().get(0).getX() * (1000 / observableApp.getAppData().getPlantaAtual().getMapa().getXSize()) + 20;
                    y = observableApp.getAppData().getPercurso().get(0).getY() * (500 / observableApp.getAppData().getPlantaAtual().getMapa().getYSize()) + 5;
                    System.out.println(x + ";" + y);
                }

                g.setColor(Color.BLACK);
                g.fillOval(x, y, 15, 15);

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        if (observableApp.getState() instanceof PesquisaItinerario) {
            try {
                BufferedImage Icon = ImageIO.read(Resources.getResourceFile(observableApp.getAppData().getPlantaAtual().getPathImagem()));
                setIcon(new ImageIcon(Icon.getScaledInstance(1000, 500, Image.SCALE_FAST)));
                setAlignmentX(Component.CENTER_ALIGNMENT);
                setAlignmentY(Component.CENTER_ALIGNMENT);

                String str = observableApp.getAppData().getPlantaAtual().getParent().getNome();
                
                try {
                    str = observableApp.getAppData().getPlantaAtual().getParent().getNome();
                } catch(NullPointerException e){
                    str = "Campus";
                }
                
                int x = 0;
                int y = 0;
                
                for (int i = 0; i < observableApp.getAppData().getPercurso().size(); i++) {
                    if (str.compareToIgnoreCase("Deis") == 0) {
                        x = observableApp.getAppData().getPercurso().get(i).getX() * (1000 / observableApp.getAppData().getPlantaAtual().getMapa().getXSize()) + 25;
                        y = observableApp.getAppData().getPercurso().get(i).getY() * (500 / observableApp.getAppData().getPlantaAtual().getMapa().getYSize()) + 13;
                    } else if (str.compareToIgnoreCase("Gerais") == 0) {
                        x = observableApp.getAppData().getPercurso().get(i).getX() * (1000 / observableApp.getAppData().getPlantaAtual().getMapa().getXSize()) + 20;
                        y = observableApp.getAppData().getPercurso().get(i).getY() * (500 / observableApp.getAppData().getPlantaAtual().getMapa().getYSize()) + 5;
                    }
                    g.setColor(Color.BLACK);
                    g.fillOval(x, y, 15, 15);
                }

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        repaint();
        if(observableApp.getState() instanceof PesquisaSala)
            setVisible(true);
        if(observableApp.getState() instanceof PesquisaItinerario)
            setVisible(true);
    }

}
