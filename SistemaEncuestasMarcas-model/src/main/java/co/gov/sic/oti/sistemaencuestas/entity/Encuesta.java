package co.gov.sic.oti.sistemaencuestas.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "encuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encuesta.findAll", query = "SELECT e FROM Encuesta e"),
    @NamedQuery(name = "Encuesta.findByNumeroDocumento", query = "SELECT e FROM Encuesta e WHERE e.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Encuesta.findByEmail", query = "SELECT e FROM Encuesta e WHERE e.email = :email"),
    @NamedQuery(name = "Encuesta.findByComentarios", query = "SELECT e FROM Encuesta e WHERE e.comentarios = :comentarios"),
    @NamedQuery(name = "Encuesta.findByFechaCreacion", query = "SELECT e FROM Encuesta e WHERE e.fechaCreacion = :fechaCreacion")})
public class Encuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinColumn(name = "marca_favorita_pc", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Marca marcaFavoritaPc;
    @JoinColumn(name = "usuario_creacion", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Usuario usuarioCreacion;

    public Encuesta() {
    }

    public Encuesta(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Encuesta(String numeroDocumento, String email) {
        this.numeroDocumento = numeroDocumento;
        this.email = email;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Marca getMarcaFavoritaPc() {
        return marcaFavoritaPc;
    }

    public void setMarcaFavoritaPc(Marca marcaFavoritaPc) {
        this.marcaFavoritaPc = marcaFavoritaPc;
    }

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroDocumento != null ? numeroDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        return !((this.numeroDocumento == null && other.numeroDocumento != null) || (this.numeroDocumento != null && !this.numeroDocumento.equals(other.numeroDocumento)));
    }

    @Override
    public String toString() {
        return "co.gov.sic.oti.sistemaencuestas.entity.Encuesta[ numeroDocumento=" + numeroDocumento + " ]";
    }
    
}
