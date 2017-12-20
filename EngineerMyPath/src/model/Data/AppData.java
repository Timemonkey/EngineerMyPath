/* AppData
 *
 * João Gonçalves, João Ferreira
 *
 * 20/12/17
 */
package model.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import model.Data.Grelha.CelulaMapa;

public class AppData {

    private Planta plantaGeral;
    private List<List<CelulaMapa>> listaPerc;
    private int percursoAtual;
    private List<Planta> plantasPerc;

    public AppData() {
        leFicheiroPlantaGeral();
        leFicheiro("Deis.txt");
        leFicheiro("Gerais.txt");
        listaPerc = new ArrayList<List<CelulaMapa>>();
        plantasPerc = new ArrayList<Planta>();

        //System.out.println(pesquisaPerc("DEIS","GERAIS"));
        //System.out.println(pesquisaPerc("L2.1,DEIS","G1 117,GERAIS"));
        //System.out.println(pesquisaPerc("L2.1,DEIS","BIBLIOTECA,DEIS"));
        //System.out.println(pesquisaPerc("L2.1,DEIS","GERAIS"));
        System.out.println(pesquisaPerc("GERAIS","L2.1,DEIS"));

        percursoAtual = 0;

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
            //O StringBuilder é para remover um carater que estava a aparecer no inicio do ficheiro
            StringBuilder sb = new StringBuilder(sc.nextLine());
            sb.deleteCharAt(0);
            str = sb.toString();
            plantaGeral = new Campus(str, "images/campus.png");

            while ((str = sc.nextLine()).compareTo("MAPA") != 0) {
                strArr = str.split(":");
                px = Integer.parseInt(strArr[1]);
                py = Integer.parseInt(strArr[2]);
                ed = new Edificio(plantaGeral, strArr[0], px, py);
                plantaGeral.addChild(strArr[0], ed);
                plantaGeral.addPontoAcesso(plantaGeral, ed, px, py);
            }

            int rows = 0;
            int columns = 0;
            while (sc.hasNextLine()) {
                ++rows;
                Scanner colReader = new Scanner(sc.nextLine());
                columns = colReader.next().length();
            }

            mapa = new double[rows][columns];

            sc.close();

            sc = new Scanner(new File("src/Campus.txt"));
            Scanner sc2;
            while ((str = sc.nextLine()).compareTo("MAPA") != 0) {
            }

            for (int i = 0; i < rows; i++) {
                str = sc.nextLine();
                sc2 = new Scanner(str);
                for (int j = 0; j < columns; j++) {
                    mapa[i][j] = Integer.parseInt("" + str.charAt(j));
                }
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

            while ((str = sc.nextLine()).compareTo("MAPA") != 0) {}
            
            int rows = 0;
            int columns = 0;
            
            while (!(str = sc.nextLine()).contains("PISO")) {
                ++rows;
                Scanner colReader = new Scanner(str);
                columns = colReader.next().length();
            }

            mapa = new double[rows][columns];

            sc.close();

            sc = new Scanner(new File("src/" + ficheiro));

            str = sc.nextLine();
            Edificio ed = (Edificio) plantaGeral.getChild(str);

            str = sc.nextLine();
            ed.setNPisos(Integer.parseInt(str));
            Piso p;
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                if (!ed.hasChild(str)) {
                    p = new Piso(ed, str);
                    ed.addChild(str, p);
                } else {
                    p = (Piso) ed.getChild(str);
                }
                str = sc.nextLine();
                p.setPathImagem(str);
                while ((str = sc.nextLine()).compareTo("SALAS") != 0) {
                    strArr = str.split(":");
                    px = Integer.parseInt(strArr[1]);
                    py = Integer.parseInt(strArr[2]);
                    if ((strArr[0].compareTo(plantaGeral.getNome())) != 0) {
                        if (!ed.hasChild(strArr[0])) {
                            Piso p2 = new Piso(ed, strArr[0]);
                            ed.addChild(strArr[0], p2);
                            p.addPontoAcesso(p, p2, px, py);
                        } else {
                            p.addPontoAcesso(p, ed.getChild(strArr[0]), px, py);
                        }
                    } else {
                        p.addPontoAcesso(p, plantaGeral, px, py);
                    }
                }
                while ((str = sc.nextLine()).compareTo("MAPA") != 0) {
                    strArr = str.split(":");
                    px = Integer.parseInt(strArr[1]);
                    py = Integer.parseInt(strArr[2]);
                    p.addChild(strArr[0], new Sala(p, strArr[0], px, py));
                }
                
                Scanner sc2;
                //para ler o mapa
                for (int i = 0; i < rows; i++) {
                    str = sc.nextLine();
                    sc2 = new Scanner(str);
                    for (int j = 0; j < columns; j++) {
                        mapa[i][j] = Integer.parseInt("" + str.charAt(j));
                    }
                }
                p.setMapa(new Grelha(mapa));
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Não foi possível localizar o ficheiro");
        }
    }
    
    private Sala findSalaInEdificio(Edificio ed, String nomeSala) {
        Piso p;
        for (int i = 0; i < ed.getNPisos(); i++) {
            p = (Piso) ed.getChild("PISO " + i);
            if (p.hasChild(nomeSala)) {
                return (Sala) p.getChild(nomeSala);
            }
        }
        return null;
    }

    private int getRelation(PontoDeAcesso pa1, PontoDeAcesso pa2) {
        if (pa1.getPlanta() instanceof Campus) {
            if (pa2.getPlanta() instanceof Campus) {
                return 3; // Edificio -> Edificio
            } else {
                return 4; // Edificio -> Sala 
            }
        } else {
            if (pa2.getPlanta() instanceof Campus) {
                return 5; // Sala -> Edificio 
            }
        }
        //Sala -> Sala
        Piso p1 = (Piso) pa1.getPlanta();
        Piso p2 = (Piso) pa2.getPlanta();
        Edificio e1 = (Edificio) p1.getParent();
        Edificio e2 = (Edificio) p2.getParent();
        if (p1 == p2) {
            return 0; //Mesmo Piso
        } else if (e1 == e2) {
            return 1; //Mesmo Edificio
        }
        return 2; //Edificios Diferentes
    }

    private Edificio getEdificioOfPontoAcesso(PontoDeAcesso pa) {
        if (pa.getPlanta() instanceof Piso) {
            return (Edificio) pa.getPlanta().getParent();
        }
        return (Edificio) pa.getDestino();
    }

    private PontoDeAcesso getSaidaEdificio(PontoDeAcesso pa) {
        Edificio ed = getEdificioOfPontoAcesso(pa);
        Piso p;
        PontoDeAcesso pa2;
        for (int i = 0; i < ed.getNPisos(); i++) {
            p = (Piso) ed.getChild("PISO " + i);
            pa2 = p.getPontoAcessoByDestino(plantaGeral.getNome());
            if (pa2 != null) {
                return pa2;
            }
        }
        return null;
    }
    
    private PontoDeAcesso getSaidaEdificio(Edificio ed) {
        Piso p;
        PontoDeAcesso pa2;
        for (int i = 0; i < ed.getNPisos(); i++) {
            p = (Piso) ed.getChild("PISO " + i);
            pa2 = p.getPontoAcessoByDestino(plantaGeral.getNome());
            if (pa2 != null) {
                return pa2;
            }
        }
        return null;
    }

    //Procura e adiciona à ArrayList de PA's os vários Pontos de Acesso entre os pisos
    private boolean getPontosAcessoInsideEdificio(List<PontoDeAcesso> PA, PontoDeAcesso pa1, PontoDeAcesso pa2) {
        Piso p1 = (Piso) pa1.getPlanta();
        Piso p2 = (Piso) pa2.getPlanta();
        Piso px = p1;
        String str = "PISO ";
        PontoDeAcesso paA, paB = null;
        boolean sentido;
        sentido = px.getFloorNumber() > p2.getFloorNumber(); //down -> true , up -> false
        do {
            if (sentido) {
                //Se estiver num piso superior ao piso de destino
                paA = px.getPontoAcessoByDestino(str + (px.getFloorNumber() - 1));
                if (px != p1) {
                    paB = px.getPontoAcessoByDestino(str + (px.getFloorNumber() + 1));
                }
            } else {
                //Se estiver num piso inferior ao piso de destino
                paA = px.getPontoAcessoByDestino(str + (px.getFloorNumber() + 1));
                if (px != p1) {
                    paB = px.getPontoAcessoByDestino(str + (px.getFloorNumber() - 1));
                }
            }
            if (paB != null) {
                PA.add(paB);
            }
            if (paA != null) {
                if (px.getFloorNumber()==p2.getFloorNumber()){
                    PA.add(pa2);
                    return true;
                }
                PA.add(paA);
                px = (Piso) paA.getDestino();
            } else {
                PA.add(pa2);
                return true;
            }
        } while (true);
    }

    //Adiciona o Ponto de Acesso de um edificio à ArrayList de Pa's
    private boolean getPontoAcessoOutsideEdificio(List<PontoDeAcesso> PA, PontoDeAcesso pa) {
        Edificio ed = getEdificioOfPontoAcesso(pa);
        PontoDeAcesso pa2 = plantaGeral.getPontoAcessoByDestino(ed.getNome());
        if (pa2 == null) {
            return false;
        }
        PA.add(pa2);
        return true;
    }

    private boolean getPontosAcesso(List<PontoDeAcesso> PA, PontoDeAcesso pa1, PontoDeAcesso pa2) {
        PA.add(pa1);
        switch (getRelation(pa1, pa2)) {
            case 0: // Sala -> Sala , Mesmo Piso
                PA.add(pa2);
                return true;
            case 1: // Sala -> Sala , Pisos Diferentes
                return getPontosAcessoInsideEdificio(PA, pa1, pa2);

            case 2: // Sala -> Sala , Edificios Diferentes
                PontoDeAcesso pa3 = getSaidaEdificio(pa1);
                PontoDeAcesso pa4 = getSaidaEdificio(pa2);
                if (pa3 == null || pa4 == null) {
                    return false;
                }
                if (!getPontosAcessoInsideEdificio(PA, pa1, pa3)) {
                    return false;
                }
                if (!getPontoAcessoOutsideEdificio(PA, pa1)) {
                    return false;
                }
                if (!getPontoAcessoOutsideEdificio(PA, pa2)) {
                    return false;
                }
                PA.add(pa4);
                return getPontosAcessoInsideEdificio(PA, pa4, pa2);

            case 3: // Edificio -> Edificio
                PA.add(pa2);
                return true;

            case 4: // Edificio -> Sala 
                PontoDeAcesso pa5 = getSaidaEdificio(pa2);
                if (pa5 == null) {
                    return false;
                }
                if (!getPontoAcessoOutsideEdificio(PA, pa2)) {
                    return false;
                }
                PA.add(pa5);
                return getPontosAcessoInsideEdificio(PA, pa5, pa2);
                
            case 5: // Sala -> Edificio
                PontoDeAcesso pa6 = getSaidaEdificio(pa1);
                if (pa6 == null) {
                    return false;
                }
                if (!getPontosAcessoInsideEdificio(PA, pa1, pa6)) {
                    return false;
                }
                if (!getPontoAcessoOutsideEdificio(PA, pa1)) {
                    return false;
                }
                return getPontoAcessoOutsideEdificio(PA, pa2);

        }
        return false;
    }
    
    public boolean pesquisaMapa(String loc) {
        Planta p = pesquisa(loc);
        if (p == null) {
            return false;
        }
        plantasPerc.add(p.getParent());
        int x = p.getPontoAcessoByIndex(0).getX();
        int y = p.getPontoAcessoByIndex(0).getY();
        listaPerc.add(p.getParent().getMapa().findPath(x, y, x, y));
        percursoAtual=0;
        return true;
    }
    
    //Encontra uma sala num determinado edificio 
    private Planta pesquisaSala(String[] strArr) {
        Edificio ed = (Edificio) plantaGeral.getChild(strArr[1]);
        return findSalaInEdificio(ed, strArr[0]);
    }
    
    //Retorna uma lista de todas as salas com o nome recebido como argumento 
    private List<String> findSala(String str){ 
        List<String> results = new ArrayList<>(); 
        for(String strE : plantaGeral.getAllChild()){
            Edificio ed = (Edificio) plantaGeral.getChild(strE);
            for(String strP : ed.getAllChild()){
                Piso p = (Piso) ed.getChild(strP);
                for(String strS : p.getAllChild()){
                    if(str.equals(strS))
                        results.add(strS + "-" + strE );
                }
            }
        }
        return results;
    } 
    
    //Percebe se o utilizador pesquisou o nome de uma sala, edificio ou sala e edificio
    //Retorna a sala ou edificio encontrado
    private Planta pesquisa(String loc){
        String[] strArr = loc.split("-");
        if (strArr.length==2){
            return pesquisaSala(strArr);
        } else if (strArr.length==1){
            if (plantaGeral.hasChild(strArr[0]))
                return plantaGeral.getChild(strArr[0]);
            else{
                for(String s : findSala(strArr[0])){
                    //Ainda não esta acabado
                    strArr = s.split("-");
                    return pesquisaSala(strArr);
                }
            }
        }
        return null;
    }

    public boolean pesquisaPerc(String loc1, String loc2) { //Não inclui as entradas do campus
        List<PontoDeAcesso> PontosAcesso = new ArrayList<>();
        Planta origem = pesquisa(loc1);
        Planta destino = pesquisa(loc2);
        PontoDeAcesso pa1, pa2;
        if (origem == null || destino == null) {
            return false;
        }
        //Obtem o inicio e fim do percurso
        pa1 = origem.getPontoAcessoByIndex(0);
        pa2 = destino.getPontoAcessoByIndex(0);

        //Obtem uma lista dos Pontos de Acesso por onde o percurso passa
        if (!getPontosAcesso(PontosAcesso, pa1, pa2)) {
            return false;
        }
        if(PontosAcesso.size()%2!=0)
            return false;
        //A cada iteração retira 2 Pontos de Acesso da lista e calcula o percurso. Atualiza variáveis   
        for (int i = 0; i < PontosAcesso.size(); i += 2) {
            pa1 = PontosAcesso.get(i);
            pa2 = PontosAcesso.get(i + 1);
            listaPerc.add(pa1.getPlanta().getMapa().findPath(pa1.getX(), pa1.getY(), pa2.getX(), pa2.getY()));
            plantasPerc.add(pa1.getPlanta());
        }
        percursoAtual = 0;
        return true;
    }

    public List<CelulaMapa> getProximoPercurso() {
        return listaPerc.get(++percursoAtual);
    }

    public List<CelulaMapa> getPercursoAnterior() {
        return listaPerc.get(--percursoAtual);
    }
    
    public Planta getPlantaAtual(){
        return plantasPerc.get(percursoAtual);
    }
    
    public void setProximoPercurso(){
        this.percursoAtual++;
    }
    
    public void setPercursoAnterior(){
        this.percursoAtual--;
    }
}
