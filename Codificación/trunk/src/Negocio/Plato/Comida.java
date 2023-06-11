package Negocio.Plato;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;

import java.io.Serializable;
import jakarta.persistence.NamedQuery;

@NamedQueries({
		@NamedQuery(name = "Negocio.Plato.Comida.findBycategoria", query = "select obj from Comida obj where :categoria = obj.categoria ") })

@Entity
public class Comida extends Plato implements Serializable {

	private static final long serialVersionUID = 0;

	public Comida() {}

	private String categoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void transferToEntity(TPlato plato) {
		super.transferToEntity(plato);
		TComida comidaAux = (TComida) plato;
		this.categoria = comidaAux.getCategoria();
	}
}