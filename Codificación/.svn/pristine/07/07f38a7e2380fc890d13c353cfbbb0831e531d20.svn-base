package Negocio.Plato;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;

import java.io.Serializable;
import jakarta.persistence.NamedQuery;

@NamedQueries({
		@NamedQuery(name = "Negocio.Plato.Bebida.findByvolumen", query = "select obj from Bebida obj where :volumen = obj.volumen ") })

@Entity
public class Bebida extends Plato implements Serializable {

	private static final long serialVersionUID = 0;

	public Bebida() {}

	private double volumen;

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public void transferToEntity(TPlato plato) {
		super.transferToEntity(plato);
		TBebida bebidaAux = (TBebida) plato;
		this.volumen = bebidaAux.getVolumen();
	}
}