package entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuenta")

public class Cuenta {

	@Id
	@Column(name = "idCliente")
	private int idCliente;
	@Column(name = "deudaTotal")
	private double deudaTotal;
	
	private Cliente cliente;
	
	public double getDeudaTotal() {
		return deudaTotal;
	}
	public void setDeudaTotal(float deudaTotal) {
		this.deudaTotal = deudaTotal;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
