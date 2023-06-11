package Negocio.Personal;

import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Personal.TiempoCompletoP.findBynomina", query = "select obj from TiempoCompletoP obj where :nomina = obj.nomina "),
		@NamedQuery(name = "Negocio.Personal.TiempoCompletoP.findByhorasExtras", query = "select obj from TiempoCompletoP obj where :horasExtras = obj.horasExtras ") })
public class TiempoCompletoP extends Personal implements Serializable {
	
	private static final long serialVersionUID = 0;

	
	public TiempoCompletoP() {
		
	}

	
	private Double nomina;
	
	private int horasExtras;

	
	public Double getNomina() {
		return nomina;
	}

	public void setNomina(Double nomina) {
		this.nomina = nomina;
	}

	
	public int getHorasExtra() {
		return horasExtras; 
	}

	public void setHorasExtra(int numHoras) {
		this.horasExtras = numHoras;
	}
	
	public Double getSueldo() {
		return nomina + (horasExtras * 15);
	}
	public void transferToEntity(TPersonal personal) {
		super.transferToEntity(personal);
		TTiempoCompleto p = (TTiempoCompleto) personal;
		this.setNomina(p.getNomina());
		this.setHorasExtra(p.getHorasExtras());
	}
}