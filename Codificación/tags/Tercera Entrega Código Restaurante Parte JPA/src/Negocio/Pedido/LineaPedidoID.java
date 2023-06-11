package Negocio.Pedido;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.PrePersist;

import java.util.UUID;

@Embeddable
public class LineaPedidoID implements Serializable {
	private static final long serialVersionUID = 0;
	
	public LineaPedidoID(int id_pedido, int id_plato){
		this.pedido_id=id_pedido;
		this.plato_id = id_plato;
	}
	

	public LineaPedidoID() {
	}

	private int pedido_id;
	
	private int plato_id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineaPedidoID other = (LineaPedidoID) obj;
		if (pedido_id != other.pedido_id)
			return false;
		if (plato_id != other.plato_id)
			return false;
		return true;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pedido_id;
		result = prime * result + plato_id;
		return result;
	}
	
	
	 
}