package Negocio.Ingrediente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Version;
import jakarta.persistence.NamedQueries;

import java.util.List;
import Negocio.Plato.Plato;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Ingrediente.Ingrediente.findByid", query = "select obj from Ingrediente obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Ingrediente.Ingrediente.findBynombre", query = "select obj from Ingrediente obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Ingrediente.Ingrediente.findBycalorias", query = "select obj from Ingrediente obj where :calorias = obj.calorias "),
		@NamedQuery(name = "Negocio.Ingrediente.Ingrediente.findByactivo", query = "select obj from Ingrediente obj where :activo = obj.activo "),
		@NamedQuery(name = "Negocio.Ingrediente.Ingrediente.findByplato", query = "select obj from Ingrediente obj where :plato MEMBER OF obj.plato ") })
public class Ingrediente implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String nombre;

	private double calorias;
	
	private boolean activo;
	
	@jakarta.persistence.ManyToMany(mappedBy = "ingredientes")
	private List<Plato> plato;
	
	@Version
    private Long version;
	
	public Ingrediente() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Plato> getPlatos() {
		return plato;
	}
	
	public double getCalorias() {
		return calorias;	
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}
	
	public void transferToEntity(TIngrediente ingrediente) {
		this.setCalorias(ingrediente.getCalorias());
		this.setNombre(ingrediente.getNombre());
		this.setActivo(ingrediente.isActivo());
	}
}