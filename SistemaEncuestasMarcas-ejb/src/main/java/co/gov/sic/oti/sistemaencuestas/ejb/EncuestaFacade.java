package co.gov.sic.oti.sistemaencuestas.ejb;

import co.gov.sic.oti.sistemaencuestas.entity.Encuesta;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class EncuestaFacade extends AbstractFacade<Encuesta> implements EncuestaFacadeLocal {

    @PersistenceContext(unitName = "EncuestasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaFacade() {
        super(Encuesta.class);
    }
    
}
