package Negocio.Personal;


public class TTiempoCompleto extends TPersonal {
	
	private Double nomina;
	
	private int horasExtras;

	
	public TTiempoCompleto(int id, String nombre, String dni, int numPedidos, Double sueldo, Boolean activo, int idTurno,
			Double nomina, int horasExtras) {
		super(id, nombre, dni, numPedidos, sueldo, activo, idTurno);
		this.nomina = nomina;
		this.horasExtras = horasExtras;
	}
	
	public TTiempoCompleto(String nombre, String dni, int numPedidos, Double sueldo, Boolean activo, int idTurno, Double nomina, int horasExtras) {
		super(nombre, dni, numPedidos, sueldo, activo, idTurno);
		this.nomina = nomina;
		this.horasExtras = horasExtras;
	}


	public TTiempoCompleto() {
		super();
	}


	public Double getNomina() {
		return nomina;
	}

	
	public void setNomina(Double nomina) {
		this.nomina = nomina;
	}

	public int getHorasExtras() {
		return horasExtras;
	}

	
	public void setHorasExtras(int horas) {
		this.horasExtras = horas;
	}
	
}