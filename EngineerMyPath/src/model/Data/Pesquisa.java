/* Interface Pesquisa
 *
 * João Gonçalves
 *
 * 14/12/17
 */
package model.Data;

import java.util.List;
import model.Data.Grelha.CelulaMapa;

public interface Pesquisa {
    
    public List<CelulaMapa> getPercursoAnterior();
    public List<CelulaMapa> getProximoPercurso();
    public List<CelulaMapa> pesquisaPerc(String loc1, String loc2);
    public Planta pesquisaMapa(String loc);
    
}
