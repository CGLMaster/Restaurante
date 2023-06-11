package Negocio.Plato;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;

import java.util.List;
import Negocio.Ingrediente.Ingrediente;
import jakarta.persistence.ManyToMany;
import Negocio.Pedido.LineaPedido;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Inheritance;

@Inheritance(strategy = InheritanceType.JOINED)

@NamedQueries({
		@NamedQuery(name = "Negocio.Plato.Plato.findByid", query = "select obj from Plato obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Plato.Plato.findBynombre", query = "select obj from Plato obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Plato.Plato.findBydescripcion", query = "select obj from Plato obj where :descripcion = obj.descripcion "),
		@NamedQuery(name = "Negocio.Plato.Plato.findByprecio", query = "select obj from Plato obj where :precio = obj.precio "),
		@NamedQuery(name = "Negocio.Plato.Plato.findBystock", query = "select obj from Plato obj where :stock = obj.stock "),
		@NamedQuery(name = "Negocio.Plato.Plato.findByingrediente", query = "select obj from Plato obj where :ingredientes MEMBER OF obj.ingredientes "),
		@NamedQuery(name = "Negocio.Plato.Plato.findBylineaPedido", query = "select obj from Plato obj where :lineaPedido MEMBER OF obj.lineaPedido "),
		@NamedQuery(name = "Negocio.Plato.Plato.findByactivo", query = "select obj from Plato obj where :activo = obj.activo ") })

@Entity
public abstract class Plato implements Serializable {

	private static final long serialVersionUID = 0;

	public Plato() {}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	@ManyToMany
	private List<Ingrediente> ingredientes;

	@OneToMany(mappedBy = "plato")
	private List<LineaPedido> lineaPedido;

	@Version
	private Long version;
	
	@Column(unique = true)
	private String nombre;

	private String descripcion;

	private double precio;

	private int stock;

	private boolean activo;

	public int getID() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public int getStock() {
		return stock;
	}

	public void setID(int id_plato) {
		this.id = id_plato;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean bool) {
		this.activo = bool;
	}

	public void transferToEntity(TPlato plato) {
		this.activo = plato.getActivo();
		this.descripcion = plato.getDescripcion();
		this.nombre = plato.getNombre();
		this.precio = plato.getPrecio();
		this.stock = plato.getStock();
	}
}