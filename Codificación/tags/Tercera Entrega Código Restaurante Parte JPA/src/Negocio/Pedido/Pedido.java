package Negocio.Pedido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import Negocio.ClienteRest.ClienteRest;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import java.time.LocalDate;
import Negocio.Personal.Personal;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Pedido.Pedido.findByid", query = "select obj from Pedido obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Pedido.Pedido.findByclienteRest", query = "select obj from Pedido obj where :clienteRest = obj.clienteRest "),
		@NamedQuery(name = "Negocio.Pedido.Pedido.findByfecha", query = "select obj from Pedido obj where :fecha = obj.fecha "),
		@NamedQuery(name = "Negocio.Pedido.Pedido.findByprecioTotal", query = "select obj from Pedido obj where :precioTotal = obj.precioTotal "),
		@NamedQuery(name = "Negocio.Pedido.Pedido.findBypersonal", query = "select obj from Pedido obj where :personal = obj.personal "),
		@NamedQuery(name = "Negocio.Pedido.Pedido.findBylineaPedido", query = "select obj from Pedido obj where :lineaPedido MEMBER OF obj.lineaPedido ") })
public class Pedido implements Serializable {
	private static final long serialVersionUID = 0;
	
	@Version
	private Long version;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@ManyToOne
	private ClienteRest clienteRest;
	
	private LocalDate fecha;
	
	private double precioTotal;
	
	private double preciopagado;
	
	@ManyToOne
	private Personal personal;
	
	@OneToMany(mappedBy = "pedido")
	private List<LineaPedido> lineaPedido;
	
	
	public Pedido(){
		this.lineaPedido=new ArrayList<LineaPedido>();
	}

	public List<LineaPedido> getListaLineaPedido() {
		return lineaPedido;
	}	

	public int getID() {
		return id;
	}

	public void setID(int id_pedido) {
		this.id = id_pedido;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public LocalDate getDate() {
		return fecha;
	}

	public void setDate(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public ClienteRest getCliente(){
		return this.clienteRest;
	}
	
	public void setCliente(ClienteRest cliente){
		this.clienteRest = cliente;
	}
	
	public Personal getPersonal(){
		return this.personal;
	}
	
	public void setPersonal(Personal pers){
		this.personal = pers;
	}
	
	public void transferToEntity(TPedido pedido) {
		precioTotal = pedido.getPrecioTotal();
		fecha = pedido.getFecha();
		preciopagado=pedido.getPrecioPagado();
	}
	
	public double getpreciopagado(){
		return preciopagado;
	}
	
	public void setpreciopagado(double preciopagado){
		this.preciopagado=preciopagado;
	}
	
}