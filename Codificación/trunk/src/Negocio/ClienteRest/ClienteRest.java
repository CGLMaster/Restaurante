package Negocio.ClienteRest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;

import java.util.List;
import Negocio.Pedido.Pedido;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.persistence.NamedQueries;


@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.ClienteRest.ClienteRest.findByid", query = "select obj from ClienteRest obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.ClienteRest.ClienteRest.findBypedido", query = "select obj from ClienteRest obj where :pedido MEMBER OF obj.pedido "),
		@NamedQuery(name = "Negocio.ClienteRest.ClienteRest.findBynombre", query = "select obj from ClienteRest obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.ClienteRest.ClienteRest.findByapellidos", query = "select obj from ClienteRest obj where :apellidos = obj.apellidos "),
		@NamedQuery(name = "Negocio.ClienteRest.ClienteRest.findBydni", query = "select obj from ClienteRest obj where :dni = obj.dni "),
		@NamedQuery(name = "Negocio.ClienteRest.ClienteRest.findByactivo", query = "select obj from ClienteRest obj where :activo = obj.activo ") })
public class ClienteRest implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@OneToMany(mappedBy = "clienteRest")
	private List<Pedido> pedido;
	
	private String nombre;
	
	private String apellidos;
	
	@Column(unique = true)
	private String dni;
	
	private boolean activo;
	
	@Version
	private Long version;
	
	public ClienteRest() {
		
	}

	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getApellidos() {
		return apellidos;
	}

	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	
	public String getDni() {
		return dni;
	}

	
	public void setDni(String dni) {
        this.dni= dni;
	}

	
	public boolean getActivo() {
		return activo;
	}

	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public List<Pedido> getPedidosCliente(){
		return this.pedido; 
	}

	
	public void transfertoEntity(TClienteRest cliente) {
		this.setDni(cliente.getDni());
		this.setNombre(cliente.getNombre());
		this.setApellidos(cliente.getApellidos());
		this.setActivo(cliente.getActivo());
	}
	
	
}