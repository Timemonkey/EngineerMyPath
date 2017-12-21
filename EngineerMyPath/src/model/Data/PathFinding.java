/* PathFinding
 *
 * João Gonçalves, João Ferreira
 *
 * 12/12/17
 *
 * Classe que implementa o algoritmo A* 
 */
package model.Data;

import model.Data.Grelha.*;
import java.util.*;

public class PathFinding {

    public static List<CelulaMapa> doAStar(CelulaMapa locOrigem, CelulaMapa locDestino) {
        Set<CelulaMapa> closed = new HashSet<>();
        Map<CelulaMapa, CelulaMapa> fromMap = new HashMap<>();
        List<CelulaMapa> itinerario = new LinkedList<>();
        Map<CelulaMapa, Double> gScore = new HashMap<>();
        final Map<CelulaMapa, Double> fScore = new HashMap<>();
        PriorityQueue<CelulaMapa> open = new PriorityQueue<>(11, new Comparator<CelulaMapa>() {
            @Override
            public int compare(CelulaMapa celulaA, CelulaMapa celulaB) {
                return Double.compare(fScore.get(celulaA), fScore.get(celulaB));
            }
        });

        gScore.put(locOrigem, 0.0);
        fScore.put(locOrigem, locOrigem.getHeuristica(locDestino));
        open.offer(locOrigem);
        while (!open.isEmpty()) {
            CelulaMapa atual = open.poll();
            if (atual.equals(locDestino)) {
                while (atual != null) {
                    itinerario.add(0, atual);
                    atual = fromMap.get(atual);
                }
                return itinerario;
            }

            closed.add(atual);
            for (CelulaMapa vizinho : atual.getVizinhos()) {
                if (closed.contains(vizinho))
                    continue;
                
                
                double tentG = gScore.get(atual)
                        + atual.getTraversalCost(vizinho);

                boolean contains = open.contains(vizinho);
                if (!contains || tentG < gScore.get(vizinho)) {
                    gScore.put(vizinho, tentG);
                    fScore.put(vizinho, tentG + vizinho.getHeuristica(locDestino));

                    if (contains)
                        open.remove(vizinho);

                    open.offer(vizinho);
                    fromMap.put(vizinho, atual);
                }
            }
        }

        return null;
    }

}
