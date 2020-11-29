package co.gov.sic.oti.sistemaencuestas.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <b>Descripción:<b> Clase encargada de filtrar las peticiones para verificar
 * que el usuario tiene sesión activa en la aplicación
 * <b>Proyecto:</b> Sistema de Encuestas
 *
 */
@WebFilter(filterName="SecurityFilter", urlPatterns="/faces/app/*")
public class SecurityFilter implements Filter {
    
    /** Atributo que determina el filterConfig */
    private FilterConfig filterConfig = null;

    /**
     * Método que se dispara al realizar una petición
     * 
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        try {
            HttpServletRequest rq = (HttpServletRequest) request;
            HttpServletResponse rp = (HttpServletResponse) response;
            HttpSession session = rq.getSession();
            Boolean sesionActiva = (Boolean)session.getAttribute("sesionActiva");
            
            // Se verifica que el usuario tenga sesión activa
            if (sesionActiva != null && Boolean.TRUE.equals(sesionActiva)) {
               chain.doFilter(request, response); 
            } else {
                // Se invalida la sesión y redirecciona a la página de login
                session.invalidate();
                rp.sendRedirect(rq.getContextPath() + "/faces/index.xhtml");
            }  
        } catch (Throwable t) {
        }  
    }
    
    /**
     * Método que es llamado cuando el filtro es destruido por el contenedor
     */
    @Override
    public void destroy() {        
    }

    /**
     * Método que es llamado cuando el filtro es inicializado en el contenedor
     * @param filterConfig 
     */
    @Override
    public void init(FilterConfig filterConfig) {     
        this.filterConfig = filterConfig;
    }
    
    /**
     * Método encargado de proporcionar información referente a la clase
     * @return 
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SecurityFilter()");
        }
        StringBuilder sb = new StringBuilder("SecurityFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    // <------------------------- Getters and Setters ------------------------->
    
    /**
     * Metodo encargado de retornar el valor del atributo
     * filterConfig
     * 
     * @return El filterConfig asociado a la clase
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Metodo encargado de modificar el valor del atributo
     * filterConfig
     * 
     * @param filterConfig
     *            El nuevo filterConfig a modificar.
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    
}
