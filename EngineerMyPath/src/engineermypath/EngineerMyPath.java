package engineermypath;

import java.util.*;

public class EngineerMyPath {

    
    public static void main(String[] args) {
        double[][] map = { { 0, 1, 2 }, { 3, 3, 2 }, { 0, -1, 0 } };
        Grelha mapa = new Grelha(map, false);      
        System.out.println(mapa.findPath(0, 0, 2, 2));
        
        System.out.println("Teste");
    }
    
}
