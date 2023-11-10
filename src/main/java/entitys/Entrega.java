package entitys;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "entrega")


public class Entrega {

	@Id
	@Column(name = "idCliente")
	private int idCliente;
	@Column(name = "importeEntrega")
	private double importeEntrega;
	@Column(name = "fechaEntrega")
	private Date fechaEntrega;
	private Cliente cliente;
	
	public double getImporteEntrega() {
		return importeEntrega;
	}
	public void setImporteEntrega(float importeEntrega) {
		this.importeEntrega = importeEntrega;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
