package co.gov.sic.oti.sistemaencuestas.ejb;

import co.gov.sic.oti.sistemaencuestas.entity.Marca;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MarcaFacadeLocal {

    void create(Marca marca);

    void edit(Marca marca);

    void remove(Marca marca);

    Marca find(Object id);

    List<Marca> findAll();

    List<Marca> findRange(int[] range);

    int count();
    
}
