/* Modelo
 *
 * João Gonçalves
 *
 * 14/12/17
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.*;

import model.Grelha.CelulaMapa;

public class Modelo extends Observable implements Pesquisa {

    private Planta plantaGeral;
    private List<List<CelulaMapa>> listaPerc;
    private int numPercursos;
    private List<Planta> plantasPerc;

    public Modelo() {
        leFicheiroCampus();
        leFicheiro("src/Deis.txt");
        listaPerc = new ArrayList<List<CelulaMapa>>();
        plantasPerc = new ArrayList<Planta>();
        numPercursos = 0;
    }

    public Planta getPlantaGeral() {
        return plantaGeral;
    }

    public void setPlantaGeral(Planta p) {
        this.plantaGeral = p;
    }

    public void leFicheiroCampus() {
        String str, strArr[];
        int px, py;
        double mapa[][];

        try {
            Scanner sc = new Scanner(new File("src/Campus.txt"));
            str = sc.nextLine();
            sc.nextLine();
            plantaGeral = new Campus(str, "imagens/Campus.png");

            while ((str = sc.nextLine()).compareTo("MAPA") != 0) {
                strArr = str.split("\t");
                px = Integer.parseInt(strArr[1]);
                py = Integer.parseInt(strArr[2]);
                plantaGeral.addChild(strArr[0], new Edificio(strArr[0], px, py));
            }

            int rows = 0;
            int columns = 0;
            while (sc.hasNextLine()) {
                ++rows;
                Scanner colReader = new Scanner(sc.nextLine());
                while (colReader.hasNextInt()) {
                    ++columns;
                }
            }

            mapa = new double[rows][columns];

            sc.close();

            sc = new Scanner(new File("src/Campus.txt"));
            while ((str = sc.nextLine()).compareTo("MAPA") != 0);

            int i = 0;
            while (sc.hasNextLine()) {
                int j = 0;
                while (sc.hasNextInt()) {
                    mapa[i][j] = sc.nextInt();
                    j++;
                }
                i++;
            }

            plantaGeral.setMapa(new Grelha(mapa));
            sc.close();
        } catch (Exception e) {
            System.out.println("Não foi possível localizar o ficheiro");
        }

    }

    public void leFicheiro(String ficheiro) {
        String str, strArr[];
        int px, py;
        double mapa[][];

        try {
            Scanner sc = new Scanner(new File("src/" + ficheiro));

            while ((str = sc.nextLine()).compareTo("MAPA") != 0);

            int rows = 0;
            int columns = 0;
            while (sc.hasNextLine()) {
                ++rows;
                Scanner colReader = new Scanner(sc.nextLine());
                while (colReader.hasNextInt()) {
                    ++columns;
                }
            }

            mapa = new double[rows][columns];

            sc.close();

            sc = new Scanner(new File("src/" + ficheiro));

            str = sc.nextLine();
            Edificio ed = (Edificio) plantaGeral.getChild(str);
            
            str = sc.nextLine();
            ed.setNPisos(Integer.parseInt(str));

            //Não está acabada, falta pôr as imagens
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                Piso p = new Piso(str);
                ed.addChild(str, p);
                while ((str = sc.nextLine()).compareTo("MAPA") != 0) {
                    strArr = str.split("\t");
                    px = Integer.parseInt(strArr[1]);
                    py = Integer.parseInt(strArr[2]);
                    p.addChild(strArr[0], new Sala(strArr[0], px, py));
                }

                //para ler o mapa
                int i = 0;
                while (sc.hasNextLine()) {
                    int j = 0;
                    while (sc.hasNextInt()) {
                        mapa[i][j] = sc.nextInt();
                        j++;
                    }
                    i++;
                }
                p.setMapa(new Grelha(mapa));
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Não foi possível localizar o ficheiro");
            return;
        }
    }
    
    private Sala findSalaInEdificio(Edificio ed, String nomeSala){
        Piso p;
        for (int i = 0; i < ed.getNPisos(); i++){
            p = (Piso) ed.getChild("P" + i);
            if (p.hasChild(nomeSala))
                return (Sala) p.getChild(nomeSala);
        }
        return null;
    }

    @Override
    public List<CelulaMapa> getProximoPercurso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CelulaMapa> pesquisaPerc(String loc1, String loc2) {
        List<double[]> PontosItinerario = new LinkedList<>();
        Sala origem = (Sala) pesquisaMapa(loc1);
        Sala destino = (Sala) pesquisaMapa (loc2);
        if (origem == null || destino == null)
            return null;
        //PREENCHER LISTA LIGADA COM TODOS OS PONTOS NECESSARIOS PARA CALCULAR O PERCURSO
        //CALCULAR OS VARIOS PERCURSOS
        return null;
    }

    @Override
    public Planta pesquisaMapa(String loc) {
        String [] strArr = loc.split(",");
        Edificio ed = (Edificio) plantaGeral.getChild(strArr[1]);
        return findSalaInEdificio(ed,strArr[0]);
    }

    @Override
    public List<CelulaMapa> getPercursoAnterior() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
