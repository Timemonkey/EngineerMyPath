/* Interface Pesquisa
 *
 * João Gonçalves
 *
 * 14/12/17
 */
package model;

import java.util.List;
import model.Grelha.CelulaMapa;

public interface Pesquisa {
    
    public List<CelulaMapa> getPercursoAnterior();
    public List<CelulaMapa> getProximoPercurso();
    public List<CelulaMapa> pesquisaPerc(String loc1, String loc2);
    public Planta pesquisaMapa(String loc);
    
}
