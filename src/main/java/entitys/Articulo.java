package entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;

@Embeddable
class ArticuloId implements Serializable {

    @Column(name = "idArticulo")
    private int codArticulo;

    @Column(name = "talle")
    private int talle;

    public ArticuloId (int codArticulo, int talle) {
    	this.codArticulo = codArticulo;
    	this.talle = talle;	
    }
    
    public ArticuloId() {
    	
    }
    
    public int getIdArticulo() {
    	return this.codArticulo;
    }
    
    public int getTalle() {
    	return this.talle;
    }
    public void setIdArticulo(int idArticulo) {
    	this.codArticulo = idArticulo;
    }
    
    public void setTalle(int talle) {
        this.talle = talle;
    }
}

@Entity
@Table(name = "articulo")


public class Articulo {

public Articulo() {}
    @EmbeddedId
    private ArticuloId id;

    @Column(name = "stock")
    private int stock;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private double precio;
    
    @Column(name = "disponible")
    private boolean disponible = false;

    // Constructor
    public Articulo(int codArticulo, int talle, int stock, String descripcion, double precio) {
        this.id = new ArticuloId(codArticulo,talle);
        this.stock = stock;
        this.descripcion = descripcion;
        this.precio = precio;
        if (stock>0) {
        	this.disponible = true;
        }
    }
    
    public Articulo(int codArticulo, int talle) {
        this.id = new ArticuloId(codArticulo,talle);
    }

    public void setId(int idArticulo) {
        this.id.setIdArticulo(idArticulo);
    }
    
    public int getId() {
    	return this.id.getIdArticulo();
    }
    
    public void setTalle(int talle) {
        this.id.setTalle(talle);
    }
    
    public int getTalle() {
    	return this.id.getTalle();
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
