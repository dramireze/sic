package co.gov.sic.oti.sistemaencuestas.ejb;

import co.gov.sic.oti.sistemaencuestas.entity.Marca;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class MarcaFacade extends AbstractFacade<Marca> implements MarcaFacadeLocal {

    @PersistenceContext(unitName = "EncuestasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaFacade() {
        super(Marca.class);
    }
    
    public Marca findByName(String name) {
        return (Marca) em.createNamedQuery("Marca.findByNombre")
            .setParameter("nombre", name).getSingleResult();
    }
    
}
