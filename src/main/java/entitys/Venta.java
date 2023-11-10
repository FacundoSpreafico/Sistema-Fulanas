package entitys;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "venta")


public class Venta {
	   @Id
	   @Column(name = "idVenta")
	   private int codVenta;
	   @Column(name = "importe")
       private float importe;
	   @Column(name = "fechaHoraVenta")
	   private Time fechaHoraVenta;
	   
	   private List<Map<Articulo,Integer>> articulosCantidad = new ArrayList<>();  
	   
	   
	public int getCodVenta() {
		return codVenta;
	}
	public void setCodVenta(int codVenta) {
		this.codVenta = codVenta;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public List<Map<Articulo,Integer>> getArticulosCantidad() {
		return articulosCantidad;
	}
	public void setArticulosCantidad(List<Map<Articulo,Integer>> articulosCantidad) {
		this.articulosCantidad = articulosCantidad;
	}


}

