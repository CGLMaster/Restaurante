package Negocio.Pedido;

import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import Negocio.Plato.Plato;


@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Pedido.LineaPedido.findByid", query = "select obj from LineaPedido obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Pedido.LineaPedido.findByprecio", query = "select obj from LineaPedido obj where :precio = obj.precio "),
		@NamedQuery(name = "Negocio.Pedido.LineaPedido.findBycantidad", query = "select obj from LineaPedido obj where :cantidad = obj.cantidad "),
		@NamedQuery(name = "Negocio.Pedido.LineaPedido.findBypedido", query = "select obj from LineaPedido obj where :pedido = obj.pedido "),
		@NamedQuery(name = "Negocio.Pedido.LineaPedido.findByplato", query = "select obj from LineaPedido obj where :plato = obj.plato ") })
public class LineaPedido implements Serializable {
	private static final long serialVersionUID = 0;
	
	@EmbeddedId
	private LineaPedidoID id;

	public void setId(LineaPedidoID id) {
		this.id = id;
	}

	public LineaPedidoID getId() {
		return id;
	}

	private Double precio;
	
	private int cantidad;
	
	@ManyToOne
	@MapsId("pedido_id") private Pedido pedido;
	
	@ManyToOne
	@MapsId("plato_id") private Plato plato;

	
	public void transferToEntity(TLineaPedido lineaPedido) {
		this.cantidad = lineaPedido.getCantidad();
		this.precio = lineaPedido.getPrecio();
	}
	
	public double getPrecio(){
		return precio;
	}
	
	public void setPrecio(double precio){
		this.precio = precio;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public int getCantidad() {
		return cantidad;
	}
	
	public void setPedido(Pedido pedido){
		this.pedido = pedido;
	}
	
	public Pedido getPedido(){
		return this.pedido;
	}
	
	public void setPlato(Plato plato){
		this.plato = plato;
	}
	
	public Plato getPlato(){
		return this.plato;
	}
	
	
}