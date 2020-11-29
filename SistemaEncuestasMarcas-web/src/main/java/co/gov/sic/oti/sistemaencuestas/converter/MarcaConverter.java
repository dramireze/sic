package co.gov.sic.oti.sistemaencuestas.converter;

import co.gov.sic.oti.sistemaencuestas.ejb.MarcaFacade;
import co.gov.sic.oti.sistemaencuestas.entity.Marca;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

@FacesConverter("marcaConverter")
public class MarcaConverter implements Converter {
    
    private final Logger log =  Logger.getLogger(getClass());
    @EJB
    MarcaFacade marcaEJB;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String arg2) {
        try {
            String marca = arg2;
            return marcaEJB.findByName(marca);
        }
        catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
        try {
            String marca = obj.toString();
            return marcaEJB.findByName(marca).getNombre();
        }
        catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return null;
    }
    
}