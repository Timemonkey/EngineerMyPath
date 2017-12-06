package engineermypath;

import java.util.*;

/**
 *
 * @author João
 */
public class Grelha {

    private final double[][] mapa;

    public class CelulaMapa implements Nodo {

        private final int x, y;

        public CelulaMapa(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getHeuristica(CelulaMapa localizacaoFinal) {
            return Math.abs(x - localizacaoFinal.x) + Math.abs(y - localizacaoFinal.y);
        }

        public double getTraversalCost(CelulaMapa localizacaoVizinhanca) {
            return 1 + mapa[localizacaoVizinhanca.y][localizacaoVizinhanca.x];
        }

        public Set<CelulaMapa> getVizinhos() {
            Set<CelulaMapa> vizinhos = new HashSet<CelulaMapa>();

            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if ((i == x && j == y) || i < 0 || j < 0 || j >= mapa.length
                            || i >= mapa[j].length) {
                        continue;
                    }

                    if (mapa[j][i] < 0) {
                        continue;
                    }

                    // TODO: create cache instead of recreation
                    vizinhos.add(new CelulaMapa(i, j));
                }
            }

            return vizinhos;
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
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            CelulaMapa other = (CelulaMapa) obj;
            if (!getOuterType().equals(other.getOuterType())) {
                return false;
            }
            if (x != other.x) {
                return false;
            }
            if (y != other.y) {
                return false;
            }
            return true;
        }

        private Grelha getOuterType() {
            return Grelha.this;
        }

    }

    public Grelha(double[][] mapa) {
        this.mapa = mapa;
    }

    public List<CelulaMapa> findPath(int xInicial, int yInicial, int xFinal, int yFinal) {
        return new PathFinding().doAStar(new CelulaMapa(xInicial, yInicial), new CelulaMapa(
                xFinal, yFinal));
    }
}
