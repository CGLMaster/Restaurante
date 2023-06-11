package Negocio.Personal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import Negocio.Turno.Turno;
import jakarta.persistence.ManyToOne;

import java.util.List;
import Negocio.Pedido.Pedido;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Inheritance;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Personal.Personal.findByid", query = "select obj from Personal obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Personal.Personal.findBynombre", query = "select obj from Personal obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Personal.Personal.findBydni", query = "select obj from Personal obj where :dni = obj.dni "),
		@NamedQuery(name = "Negocio.Personal.Personal.findBynumPedidos", query = "select obj from Personal obj where :numPedidos = obj.numPedidos "),
		@NamedQuery(name = "Negocio.Personal.Personal.findByactivo", query = "select obj from Personal obj where :activo = obj.activo "),
		@NamedQuery(name = "Negocio.Personal.Personal.findByturno", query = "select obj from Personal obj where :turno = obj.turno "),
		@NamedQuery(name = "Negocio.Personal.Personal.findBypedido", query = "select obj from Personal obj where :pedido MEMBER OF obj.pedido ") })
public abstract class Personal implements Serializable {

	private static final long serialVersionUID = 0;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private String nombre;
	
	@Column(unique = true)
	private String dni;
	
	private int numPedidos;
	
	private boolean activo;
	
	@Version
	private Long version;
	
	@ManyToOne
	private Turno turno;
	
	@OneToMany(mappedBy = "personal")
	private List<Pedido> pedido;

	
	public Personal() {
		
	}
	
	public int getID() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDni(){
		return dni;
	}
	public void setDni(String dni){
		this.dni = dni;
	}


	public int getNumPedidos() {
		return numPedidos;
	}

	
	public void setNumPedidos(int numPedidos) {
		this.numPedidos = numPedidos;
	}

	
	public abstract Double getSueldo();

	
	public boolean getActivo() {
		return activo;
	}

	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Turno getTurno(){
		return turno;
	}
	public void setTurno(Turno t){
		this.turno = t;
	}

	public void transferToEntity(TPersonal personal) {
		this.setNombre(personal.getNombre());
		this.setDni(personal.getDni());
		this.setNumPedidos(personal.getNumPedidos());
		this.setActivo(personal.getActivo());
	}

	public void setId(int id2) {
		this.id=id2;
		
	}



}