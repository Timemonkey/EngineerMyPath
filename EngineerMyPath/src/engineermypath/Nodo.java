package engineermypath;

import engineermypath.Grelha.*;
import java.util.*;

public interface Nodo {

    double getHeuristica(CelulaMapa goal);

    double getTraversalCost(CelulaMapa neighbour);

    Set<CelulaMapa> getVizinhos();
}
