package co.gov.sic.oti.sistemaencuestas.ejb;

import co.gov.sic.oti.sistemaencuestas.entity.Encuesta;
import co.gov.sic.oti.sistemaencuestas.entity.Marca;
import co.gov.sic.oti.sistemaencuestas.entity.Usuario;
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
    
    public void crearEncuesta(Encuesta encuesta) {
        em.getTransaction().begin();
        if (!em.contains(encuesta)) {
            Marca marca = em.find(Marca.class, encuesta.getMarcaFavoritaPc().getId());
            encuesta.setMarcaFavoritaPc(marca);
            encuesta.setFechaCreacion(new java.util.Date());
            Usuario usuario = em.find(Usuario.class, encuesta.getUsuarioCreacion().getUsername());
            encuesta.setUsuarioCreacion(usuario);
            em.persist(encuesta);
            em.flush();
        }
        em.getTransaction().commit();
    }
    
}
