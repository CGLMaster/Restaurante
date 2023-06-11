package Negocio.Turno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;

import java.util.List;
import Negocio.Personal.Personal;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.persistence.NamedQueries;

@NamedQueries({
		@NamedQuery(name = "Negocio.Turno.Turno.findByid", query = "select obj from Turno obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Turno.Turno.findBypersonal", query = "select obj from Turno obj where :personal MEMBER OF obj.personal "),
		@NamedQuery(name = "Negocio.Turno.Turno.findByhora_inicio", query = "select obj from Turno obj where :hora_inicio = obj.hora_inicio "),
		@NamedQuery(name = "Negocio.Turno.Turno.findByhora_fin", query = "select obj from Turno obj where :hora_fin = obj.hora_fin "),
		@NamedQuery(name = "Negocio.Turno.Turno.findBynombre", query = "select obj from Turno obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Turno.Turno.findByactivo", query = "select obj from Turno obj where :activo = obj.activo ") })

@Entity
public class Turno implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	public Turno() {
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	@OneToMany(mappedBy = "turno")
	private List<Personal> personal;
	
	@Version
	private Long version;
	private String hora_inicio;
	private String hora_fin;
	
	@Column(unique = true)
	private String nombre;
	
	private Boolean activo;
	private int contadorPersonal;
	

	public int getIdTurno() {
		return id;
	}

	public void setIdTurno(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHoraInicio() {
		return hora_inicio;
	}

	public void setHoraInicio(String hora) {
		this.hora_inicio = hora;
	}

	public String getHoraFin() {
		return hora_fin;
	}

	public void setHoraFin(String hora) {
		this.hora_fin = hora;
	}
	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getActivo() {
		return activo;
	}
	public int getNumPersonal(){
		return contadorPersonal;
		
	}
	public void setNumPersonal(int numPersonal){
		this.contadorPersonal = numPersonal;
	}

	public List<Personal> getListaPersonal() {
		return personal;
	}
	
	public void transferToEntity(TTurno turno){
		this.hora_fin = turno.getHoraFin();
		this.hora_inicio =turno.getHoraInicio();
		this.nombre = turno.getNombre();
		this.activo = turno.isActivo();
	}
	
}