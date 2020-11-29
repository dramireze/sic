package co.gov.sic.oti.sistemaencuestas.ejb;

import co.gov.sic.oti.sistemaencuestas.entity.Encuesta;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EncuestaFacadeLocal {

    void create(Encuesta encuesta);

    void edit(Encuesta encuesta);

    void remove(Encuesta encuesta);

    Encuesta find(Object id);

    List<Encuesta> findAll();

    List<Encuesta> findRange(int[] range);

    int count();
    
}
