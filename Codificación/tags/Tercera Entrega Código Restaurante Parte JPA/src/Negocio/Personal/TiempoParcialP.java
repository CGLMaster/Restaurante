package Negocio.Personal;

import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;


@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Personal.TiempoParcialP.findByprecioHora", query = "select obj from TiempoParcialP obj where :precioHora = obj.precioHora "),
		@NamedQuery(name = "Negocio.Personal.TiempoParcialP.findBynumeroHoras", query = "select obj from TiempoParcialP obj where :numeroHoras = obj.numeroHoras ") })
public class TiempoParcialP extends Personal implements Serializable {
	
	private static final long serialVersionUID = 0;

	
	public TiempoParcialP() {
	}

	
	private Double precioHora;
	
	private int numeroHoras;

	
	public Double getPrecioHora() {
		return precioHora;
	}

	
	public void setPrecioHora(Double precioHora) {
		this.precioHora = precioHora;
	}

	
	public int getNumeroHoras() {
		return numeroHoras;
	}

	
	public void setNumeroHoras(int numHoras) {
		this.numeroHoras = numHoras;
	}
	public Double getSueldo() {
		return precioHora * numeroHoras;
	}
	
	public void transferToEntity(TPersonal personal) {
		super.transferToEntity(personal);
		TTiempoParcial p = (TTiempoParcial) personal;
		this.setNumeroHoras(p.getNumeroHoras());
		this.setPrecioHora(p.getPrecioHora());
	}
}