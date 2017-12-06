package engineermypath;

import java.util.*;

public class EngineerMyPath {

    
    public static void main(String[] args) {
        double[][] mat = { { 0, 1, 2 }, { 3, 3, 2 }, { 0, -1, 0 } };
        Grelha grelha = new Grelha(mat);      
        System.out.println(grelha.findPath(0, 0, 2, 2));
        
        System.out.println("Sò para Teste");
    }
    
}
