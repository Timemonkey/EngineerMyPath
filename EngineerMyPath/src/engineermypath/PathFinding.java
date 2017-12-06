package engineermypath;

import engineermypath.Grelha.*;
import java.util.*;

public class PathFinding {

    /**
     * A Star pathfinding. Note that the heuristic has to be monotonic:      {@code h(x) <=
	 * d(x, y) + h(y)}.
     *
     * @param locOrigem Starting node
     * @param locDestino Goal node
     * @return Shortest path from start to goal, or null if none found
     */
    public static List<CelulaMapa> doAStar(CelulaMapa locOrigem, CelulaMapa locDestino) {
        Set<CelulaMapa> closed = new HashSet<CelulaMapa>();
        Map<CelulaMapa, CelulaMapa> fromMap = new HashMap<CelulaMapa, CelulaMapa>();
        List<CelulaMapa> itinerario = new LinkedList<CelulaMapa>();
        Map<CelulaMapa, Double> gScore = new HashMap<CelulaMapa, Double>();
        final Map<CelulaMapa, Double> fScore = new HashMap<CelulaMapa, Double>();
        PriorityQueue<CelulaMapa> open = new PriorityQueue<CelulaMapa>(11, new Comparator<CelulaMapa>() {

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
                if (closed.contains(vizinho)) {
                    continue;
                }

                double tentG = gScore.get(atual)
                        + atual.getTraversalCost(vizinho);

                boolean contains = open.contains(vizinho);
                if (!contains || tentG < gScore.get(vizinho)) {
                    gScore.put(vizinho, tentG);
                    fScore.put(vizinho, tentG + vizinho.getHeuristica(locDestino));

                    if (contains) {
                        open.remove(vizinho);
                    }

                    open.offer(vizinho);
                    fromMap.put(vizinho, atual);
                }
            }
        }

        return null;
    }

}
