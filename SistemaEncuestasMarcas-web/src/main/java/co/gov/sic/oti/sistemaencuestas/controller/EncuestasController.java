package co.gov.sic.oti.sistemaencuestas.controller;

import co.gov.sic.oti.sistemaencuestas.ejb.EncuestaFacade;
import co.gov.sic.oti.sistemaencuestas.ejb.MarcaFacade;
import co.gov.sic.oti.sistemaencuestas.entity.Encuesta;
import co.gov.sic.oti.sistemaencuestas.entity.Marca;
import co.gov.sic.oti.sistemaencuestas.entity.Usuario;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * <b>Descripción:</b> Esta clase se encarga de gestionar las encuestas en
 * el sistema
 * <b>Proyecto:</b> Sistema de Encuestas
 *
 */
@ManagedBean
@SessionScoped
public class EncuestasController {
    
    // Atributos
    
    /** Atributo que determina el logger de la clase */
    private final Logger log =  Logger.getLogger(getClass());
    /** Atributo que determina el archivo de propiedades */
    private ResourceBundle resourceBundle;
    /** Atributo que determina el nombre del usuario logueado */
    private String nombreUsuario;
    /** Atributo que determina el username del usuario logueado */
    private String username;
    
    /** Atributo que determina una encuesta a registrar */
    private Encuesta encuestaARegistrar;
    /** Atributo que determina una encuesta a eliminar */
    private Encuesta encuestaAEliminar;
    
    /** Atributo que determina la lista de marcas de PC */
    private List <Marca> marcas;
    /** Atributo que determina la lista de encuestas diligenciadas */
    private List <Encuesta> encuestas;
    
    // EJBs
    
    /** Bean encargado de administrar las encuestas */
    @EJB
    EncuestaFacade encuestaEJB;
    /** Bean encargado de administrar las marcas de PCs */
    @EJB
    MarcaFacade marcaEJB;
    
    
    /**
     * Método de inicialización del action que se ejecuta despues de la 
     * inyección de dependencias
     */
    @PostConstruct
    public void init() {
        
        // Se obtienen los atributos de sesión
        nombreUsuario = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombre");
        username = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        
        // Se obtienen las marcas de PCs
        marcas = marcaEJB.findAll();
        
        // Se cargan los mensajes del archivo de propiedades
        resourceBundle = ResourceBundle.getBundle("messages");  
    }
    
    /**
     * Método encargado de inicializar la pantalla de registrar encuesta
     * @return 
     */
    public String inicializarRegistrar() {
        encuestaARegistrar = new Encuesta();
        return "registrar_encuesta";
    }
    
    /**
     * Método encargado de registrar una encuesta en el sistema
     * @return 
     */
    public String registrarEncuesta() {
        encuestaARegistrar.setFechaCreacion(new Date());
        encuestaARegistrar.setUsuarioCreacion(new Usuario(username));
        encuestaEJB.create(encuestaARegistrar);
        generarMensaje("La encuesta ha sido creada correctamente.", FacesMessage.SEVERITY_INFO, null);
        return "encuestas";
    }
    
    /**
     * Método encargado de inicializar el proceso de eliminar una encuesta
     * @param encuesta
     * @return 
     */
    public String inicializarEliminar(Encuesta encuesta) {
        encuestaAEliminar = encuesta;
        return "consultar_encuestas";
    }
    
    /**
     * Método encargado de eliminar una encuesta 
     */
    public void eliminar() {
        encuestaEJB.remove(encuestaAEliminar);
        generarMensaje("La encuesta ha sido eliminada correctamente.", FacesMessage.SEVERITY_INFO, null);
    }
    
    /**
     * Método encargado de inicializar la pantalla de consultar encuestas
     * @return 
     */
    public String inicializarConsultar() {
        return "consultar_encuestas";
    }
    
    /**
     * Método encargado de cancelar el registro de una encuesta
     * @return 
     */
    public String cancelar() {
        return "encuestas";
    }
    
    /**
     * Método encargado de finalizar la sesión del usuario en la aplicación 
     * @return 
     */
    public String salir() {
        HttpServletRequest httpServletRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        return "/faces/index";
    }
    
    /**
     * Método encargado de mostrar un mensaje al usuario
     * 
     * @param mensaje
     *          El mensaje a mostrar en pantalla
     * @param tipoMensaje
     *          Indica si el mensaje es de advertencia, error o información
     * @param campo 
     *          Campo para el que se genera el mensaje
     */
    public void generarMensaje(String mensaje, FacesMessage.Severity tipoMensaje, String campo) {
        FacesContext.getCurrentInstance().addMessage(campo,new FacesMessage(tipoMensaje,mensaje,null));    
    }
    
    // <------------------------- Getters and Setters ------------------------->
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Encuesta getEncuestaARegistrar() {
        return encuestaARegistrar;
    }

    public void setEncuestaARegistrar(Encuesta encuestaARegistrar) {
        this.encuestaARegistrar = encuestaARegistrar;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public List<Encuesta> getEncuestas() {
        return encuestaEJB.findAll();
    }

    public void setEncuestas(List<Encuesta> encuestas) {
        this.encuestas = encuestas;
    }
    
}
