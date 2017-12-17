/* AppData
 *
 * João Gonçalves, João Ferreira
 *
 * 17/12/17
 */
package model.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.*;

import model.Data.Grelha.CelulaMapa;

public class AppData extends Observable implements Pesquisa {

    private Planta plantaGeral;
    private List<List<CelulaMapa>> listaPerc;
    private int numPercursos=0;
    private List<Planta> plantasPerc;

    public AppData() {
        leFicheiroPlantaGeral();
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

    public void leFicheiroPlantaGeral() {
        String str, strArr[];
        Edificio ed;
        int px, py;
        double mapa[][];

        try {
            Scanner sc = new Scanner(new File("src/Campus.txt"));
            str = sc.nextLine();
            plantaGeral = new Campus(str, "imagens/Campus.png");

            while ((str = sc.nextLine()).compareTo("MAPA") != 0) {
                strArr = str.split("\t");
                px = Integer.parseInt(strArr[1]);
                py = Integer.parseInt(strArr[2]);
                ed = new Edificio(plantaGeral,strArr[0], px, py);
                plantaGeral.addChild(strArr[0],ed);
                plantaGeral.addPontoAcesso(plantaGeral, ed, px, py);
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
            while ((str = sc.nextLine()).compareTo("MAPA") != 0){}

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

            while ((str = sc.nextLine()).compareTo("MAPA") != 0){}

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
            Piso p;
            //Não está acabada, falta pôr as imagens
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                if(!ed.hasChild(str)){
                    p = new Piso(ed,str);
                    ed.addChild(str, p);
                }
                else
                    p = (Piso) ed.getChild(str);
                while ((str = sc.nextLine()).compareTo("SALAS") != 0) {
                    strArr = str.split("\t");
                    px = Integer.parseInt(strArr[1]);
                    py = Integer.parseInt(strArr[2]);
                    if ((strArr[0].compareTo(plantaGeral.getNome())) != 0){
                        if(!ed.hasChild(strArr[0])){
                            Piso p2 = new Piso(ed,strArr[0]);
                            ed.addChild(strArr[0], p2);
                            p.addPontoAcesso(p, p2, px, py);
                        }
                        else
                            p.addPontoAcesso(p, ed.getChild(strArr[0]), px, py);
                    }
                    else
                        p.addPontoAcesso(p, plantaGeral, px, py);
                }
                while ((str = sc.nextLine()).compareTo("MAPA") != 0) {
                    strArr = str.split("\t");
                    px = Integer.parseInt(strArr[1]);
                    py = Integer.parseInt(strArr[2]);
                    p.addChild(strArr[0], new Sala(p,strArr[0], px, py));
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
    
    private int getRelation(PontoDeAcesso pa1, PontoDeAcesso pa2){
        if(pa1.getPlanta() instanceof Campus){
            if (pa2.getPlanta() instanceof Campus)
                return 3; // Edificio -> Edificio
            else
                return 4; // Edificio -> Sala 
        } else {
            if (pa2.getPlanta() instanceof Campus)
                return 5; // Sala -> Edificio 
        }
        //Sala -> Sala
        Piso p1 = (Piso) pa1.getPlanta();
        Piso p2 = (Piso) pa2.getPlanta();
        Edificio e1 = (Edificio) p1.getParent();
        Edificio e2 = (Edificio) p2.getParent();
        if(p1==p2)
            return 0; //Mesmo Piso
        else if(e1==e2)
            return 1; //Mesmo Edificio
        return 2; //Edificios Diferentes
    }
    
    private boolean getPontosAcessoInsideEdificio(List<PontoDeAcesso> PA,PontoDeAcesso pa1, PontoDeAcesso pa2){
        Piso p2 = (Piso) pa2.getPlanta();
        Piso px = (Piso) pa1.getPlanta();
        String str = "PISO ";
        PontoDeAcesso pa3, pa4;
        do{
            pa3 = px.getPontoAcessoByDestino(str + (px.getFloorNumber()-1));
            pa4 = px.getPontoAcessoByDestino(str + (px.getFloorNumber()+1));
            if (pa3==null || pa4==null)
                return false;
            if (px.getFloorNumber()>p2.getFloorNumber()){
                PA.add(pa4);
                PA.add(pa3);
                px = (Piso) pa3.getDestino();
            } else {
                PA.add(pa3);
                PA.add(pa4);
                px = (Piso) pa4.getDestino();
            }
        }while(px.getFloorNumber()!=p2.getFloorNumber());
        return true;
    }
    
    private Edificio getEdificioOfPontoAcesso(PontoDeAcesso pa){
        if (pa.getPlanta() instanceof Piso)
            return (Edificio) pa.getPlanta().getParent();
        return (Edificio) pa.getDestino();
    }
    
    private PontoDeAcesso getSaidaEdificio(PontoDeAcesso pa){
        Edificio ed = getEdificioOfPontoAcesso(pa);
        Piso p;
        PontoDeAcesso pa2;
        for (int i = 0; i < ed.getNPisos(); i++){
            p = (Piso) ed.getChild("P" + i);
            pa2 = p.getPontoAcessoByDestino(plantaGeral.getNome());
            if (pa2!=null)
                return pa2;
        }
        return null;
    }
    
    private boolean getPontoAcessoOutsideEdificio(List<PontoDeAcesso> PA, PontoDeAcesso pa){
        Edificio ed = getEdificioOfPontoAcesso(pa);
        PontoDeAcesso pa2 = plantaGeral.getPontoAcessoByDestino(ed.getNome());
        if (pa2==null)
            return false;
        PA.add(pa2);
        return true;
    }
    
    private boolean getPontosAcesso(List<PontoDeAcesso> PA,PontoDeAcesso pa1, PontoDeAcesso pa2){ //DÁ PARA SER BASTANTE CONSOLIDADA
        switch(getRelation(pa1,pa2)){
            case 0: // Sala -> Sala , Mesmo Piso
                return true;
            case 1: // Sala -> Sala , Pisos Diferentes
                return getPontosAcessoInsideEdificio(PA,pa1,pa2);
                
            case 2: // Sala -> Sala , Edificios Diferentes
                PontoDeAcesso pa3 = getSaidaEdificio(pa1);
                PontoDeAcesso pa4 = getSaidaEdificio(pa2);
                if (pa3==null || pa4==null)
                    return false;
                if (!getPontosAcessoInsideEdificio(PA,pa1,pa3))
                    return false;
                if (!getPontoAcessoOutsideEdificio(PA,pa1))
                    return false;
                if (!getPontoAcessoOutsideEdificio(PA,pa2))
                    return false;
                return getPontosAcessoInsideEdificio(PA,pa4,pa2);
                        
            case 3: // Edificio -> Edificio
                if (!getPontoAcessoOutsideEdificio(PA,pa1))
                    return false;
                return getPontoAcessoOutsideEdificio(PA,pa2);
                
            case 4: // Edificio -> Sala 
                PontoDeAcesso pa5 = getSaidaEdificio(pa2);
                if (pa5==null)
                    return false;
                if (!getPontoAcessoOutsideEdificio(PA,pa1))
                    return false;
                if (!getPontoAcessoOutsideEdificio(PA,pa2))
                    return false;
                return getPontosAcessoInsideEdificio(PA,pa5,pa2);
            case 5: // Sala -> Edificio
                PontoDeAcesso pa6 = getSaidaEdificio(pa1);
                if (pa6==null)
                    return false;
                if (!getPontosAcessoInsideEdificio(PA,pa1,pa6))
                    return false;
                if (!getPontoAcessoOutsideEdificio(PA,pa1))
                    return false;
                return getPontoAcessoOutsideEdificio(PA,pa2);
    
        }
        return false;
    }

    @Override
    public List<CelulaMapa> getProximoPercurso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CelulaMapa> pesquisaPerc(String loc1, String loc2) { //Não inclui as entradas do campus
        List <PontoDeAcesso> PontosAcesso = new ArrayList<>();
        Sala origem = (Sala) pesquisaMapa(loc1);
        Sala destino = (Sala) pesquisaMapa(loc2);
        PontoDeAcesso pa1,pa2;
        if (origem == null || destino == null)
            return null;
        PontosAcesso.add(origem.getPontoAcessoByIndex(0));
        getPontosAcesso(PontosAcesso,origem.getPontoAcessoByIndex(0),destino.getPontoAcessoByIndex(0)); 
        PontosAcesso.add(destino.getPontoAcessoByIndex(0));
        for(int i=0;i<PontosAcesso.size();i+=2){
            pa1 = PontosAcesso.get(i);
            pa2 = PontosAcesso.get(i+1);
            listaPerc.add(pa1.getPlanta().getMapa().findPath(pa1.getX(),pa1.getY(),pa2.getX(),pa2.getY()));
            plantasPerc.add(pa1.getPlanta());
            numPercursos++;
        }
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
