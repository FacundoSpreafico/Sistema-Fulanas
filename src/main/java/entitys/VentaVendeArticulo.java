package entitys;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Embeddable
class VentaVendeArticuloId implements Serializable {

    @Column(name = "idArticulo")
    private int idArticulo;

    @Column(name = "idVenta")
    private int idVenta;

    @Column(name = "talle")
    private int talle;

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getTalle() {
        return talle;
    }

    public void setTalle(int talle) {
        this.talle = talle;
    }

}

@Entity
@Table(name = "venta_vende_articulo")
public class VentaVendeArticulo {

    @EmbeddedId
    private VentaVendeArticuloId id;

    @Column(name = "cantidad")
    private int cantidad;

    
    public int getIdArticulo() {
        return this.id.getIdArticulo();
    }
    public int getIdVenta() {
        return this.id.getIdVenta();
    }
    public int getTalle() {
        return this.id.getTalle();
    }
    public void setidArticulo(int idArticulo) {
        this.id.setIdArticulo(idArticulo);
    }
    public void setidVenta(int idVenta) {
        this.id.setIdVenta(idVenta);
    }
    public void setTalle(int talle) {
        this.id.setTalle(talle);
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
