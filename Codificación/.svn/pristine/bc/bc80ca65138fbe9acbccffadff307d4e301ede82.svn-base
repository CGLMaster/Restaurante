package Negocio.Cliente;

import java.time.LocalDate;

public class TClientePremium extends TCliente {

	public TClientePremium(String nombre, String DNI, String mail, int ID_socio, boolean esActivo) {
		super(nombre, DNI, mail, ID_socio, esActivo);
	}

	public TClientePremium(String nombre, String DNI, String mail, int ID_socio, boolean esActivo, LocalDate antiguedad,
			String telefono, String direccion) {
		super(nombre, DNI, mail, ID_socio, esActivo);
		this.antiguedad = antiguedad;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public TClientePremium(int ID_socio) {
		super(ID_socio);
	}

	public TClientePremium() {
		super();
	}

	private LocalDate antiguedad;

	private String telefono;

	private String direccion;

	public LocalDate getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(LocalDate antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}