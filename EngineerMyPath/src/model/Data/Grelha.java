/* Grelha/CelulaMapa
 *
 * João Gonçalves, João Ferreira
 *
 * 12/12/17
 *
 * Representa o mapa de uma planta, contém também a implementação da classe CelulaMapa que representa uma célula na grelha
 */
package model.Data;

import java.util.*;

public class Grelha {

    private final double[][] mapa;
    
    public Grelha(double[][] mapa) {
        this.mapa = mapa;
    }
    
    public void print(){
        System.out.println("");
        for(int i=0; i<getYSize(); i++){
            for(int j=0; j<getXSize(); j++){
                if(mapa[i][j]==-1)
                    System.out.print(0);
                else
                    System.out.print(1);
            }
            System.out.print("\n");
        }
    }

    public class CelulaMapa implements Nodo {

        private final int x, y;

        public CelulaMapa(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public double getHeuristica(CelulaMapa localizacaoFinal) {
            return Math.abs(x - localizacaoFinal.x) + Math.abs(y - localizacaoFinal.y);
        }

        @Override
        public double getTraversalCost(CelulaMapa localizacaoVizinhanca) {
            double cost = mapa[localizacaoVizinhanca.y][localizacaoVizinhanca.x];
            return 1 + cost;
        }

        @Override
        public Set<CelulaMapa> getVizinhos() {
            Set<CelulaMapa> vizinhos = new HashSet<>();

            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if ((i == x && j == y) || i < 0 || j < 0 || j >= mapa.length
                            || i >= mapa[j].length) {
                        continue;
                    }

                    if (mapa[j][i] < 0)
                        continue;
                    
                    
                    // TODO: create cache instead of recreation
                    vizinhos.add(new CelulaMapa(i, j));
                }
            }

            return vizinhos;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        @Override
        public int hashCode() {
            final int numPrimo = 31;
            int resultado = 1;
            resultado = numPrimo * resultado + getOuterType().hashCode();
            resultado = numPrimo * resultado + x;
            resultado = numPrimo * resultado + y;
            return resultado;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            else if (obj == null)
                return false;
            else if (getClass() != obj.getClass())
                return false;
            
            CelulaMapa other = (CelulaMapa) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            else if (x != other.x)
                return false;
            else if (y != other.y)
                return false;
            
            return true;
        }

        private Grelha getOuterType() {
            return Grelha.this;
        }

    }
    
    public int getXSize(){
        return mapa[0].length;
    }
    
    public int getYSize(){
        return mapa.length;
    }
    
    public List<CelulaMapa> findPath(int xInicial, int yInicial, int xFinal, int yFinal) {
        List<CelulaMapa> l = PathFinding.doAStar(new CelulaMapa(xInicial, yInicial), new CelulaMapa(
                xFinal, yFinal));
        return l;
    }
}
