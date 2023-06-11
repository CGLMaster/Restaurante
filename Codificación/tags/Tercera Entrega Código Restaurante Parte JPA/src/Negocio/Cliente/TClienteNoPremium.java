package Negocio.Cliente;

public class TClienteNoPremium extends TCliente {

	public TClienteNoPremium(String nombre, String DNI, String mail, int ID_socio, boolean esActivo) {
		super(nombre, DNI, mail, ID_socio, esActivo);
	}

	public TClienteNoPremium(String nombre, String DNI, String mail, int ID_socio, boolean esActivo, int nCompras) {
		super(nombre, DNI, mail, ID_socio, esActivo);
		this.nCompras = nCompras;
	}

	public TClienteNoPremium(int ID_socio) {
		super(ID_socio);
	}

	public TClienteNoPremium() {
	}

	private int nCompras;

	public void setNumCompras(int nCompras) {
		this.nCompras = nCompras;
	}

	public int getNumCompras() {
		return nCompras;
	}
}