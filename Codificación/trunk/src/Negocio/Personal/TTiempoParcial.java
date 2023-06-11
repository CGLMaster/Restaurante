package Negocio.Personal;


public class TTiempoParcial extends TPersonal {
	
	private Double precioHora;
	
	private int numeroHoras;

	
	public TTiempoParcial(int id, String nombre, String dni, int numPedidos, Double sueldo, boolean activo, int idTurno
			,Double precioHora, int numHoras) {
		super(id, nombre, dni, numPedidos, sueldo, activo, idTurno);
		this.precioHora = precioHora;
		this.numeroHoras = numHoras;
	}


	public TTiempoParcial() {
		
	}


	public Double getPrecioHora() {
		return precioHora;
	}

	
	public void setPrecioHora(Double precioHora) {
		this.precioHora = precioHora;
	}

	
	public int getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(int numHoras) {
		this.numeroHoras = numHoras;
	}
}