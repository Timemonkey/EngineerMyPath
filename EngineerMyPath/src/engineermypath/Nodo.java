/* Interface Nodo
 *
 * João Gonçalves
 *
 * 08/12/17
 *
 * Interface que representa uma célula no mapa
 */
package engineermypath;

import engineermypath.Grelha.*;
import java.util.*;

public interface Nodo {

    double getHeuristica(CelulaMapa goal);

    double getTraversalCost(CelulaMapa neighbour);

    Set<CelulaMapa> getVizinhos();
}
