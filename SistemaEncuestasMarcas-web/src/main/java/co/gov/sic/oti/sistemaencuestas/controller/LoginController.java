package co.gov.sic.oti.sistemaencuestas.controller;

import co.gov.sic.oti.sistemaencuestas.ejb.UsuarioFacade;
import co.gov.sic.oti.sistemaencuestas.entity.Usuario;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * <b>Descripción:</b> Esta clase se encarga de manejar la autenticación
 * de usuarios
 * <b>Proyecto:</b> Sistema de Encuestas
 *
 */
@ManagedBean
@SessionScoped
public class LoginController {
    
    // Atributos
    
    /** Atributo que determina el username */
    private String username;
    /** Atributo que determina el password */
    private String password;
    /** Atributo que determina si la sesión está activa */
    private Boolean sessionActiva;
    /** Atributo que determina el resourceBundle */
    private final ResourceBundle resourceBundle;
    /** Atributo que determina el resourceBundle */
    
    // EJBs
    
    /** Bean encargado de administrar los usuarios */
    @EJB
    UsuarioFacade usuarioEJB;
    
       
    /**
     * Constructor de la clase
     */
    public LoginController() {
        // Se cargan los mensajes del archivo de propiedades
        resourceBundle = ResourceBundle.getBundle("messages");
    }
    
    /**
     * Método encargado de validar las credenciales del usuario
     * 
     * @return 
     */
    public String validarUsuario() {
        Usuario usuario = usuarioEJB.find(username);
        
        if (usuario != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", usuario.getUsername());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombre", usuario.getNombre());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesionActiva", Boolean.TRUE);
            return "/faces/app/encuestas";
        } else {
            generarMensaje(resourceBundle.getString("sistemaencuestas.usuarioPasswordIncorrectos"), FacesMessage.SEVERITY_ERROR, null);
            return "index";
        }
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

    /**
     * Metodo encargado de retornar el valor del atributo
     * username
     * 
     * @return El username asociado a la clase
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo encargado de modificar el valor del atributo
     * username
     * 
     * @param username
     *            El nuevo username a modificar.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo encargado de retornar el valor del atributo
     * password
     * 
     * @return El password asociado a la clase
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metodo encargado de modificar el valor del atributo
     * password
     * 
     * @param password
     *            El nuevo password a modificar.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Metodo encargado de retornar el valor del atributo
     * sessionActiva
     * 
     * @return El sessionActiva asociado a la clase
     */
    public Boolean getSessionActiva() {
        return sessionActiva;
    }

    /**
     * Metodo encargado de modificar el valor del atributo
     * sessionActiva
     * 
     * @param sessionActiva
     *            El nuevo sessionActiva a modificar.
     */
    public void setSessionActiva(Boolean sessionActiva) {
        this.sessionActiva = sessionActiva;
    }
   
}
