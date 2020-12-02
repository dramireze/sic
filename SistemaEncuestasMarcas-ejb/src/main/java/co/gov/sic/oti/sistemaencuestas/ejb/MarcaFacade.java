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
    
    public Marca findById(String id) {
        return (Marca) em.createNamedQuery("Marca.findById")
            .setParameter("id", id).getSingleResult();
    }
    
    public Marca findByName(String nombre) {
        return (Marca) em.createNamedQuery("Marca.findByNombre")
            .setParameter("nombre", nombre).getSingleResult();
    }
    
}
