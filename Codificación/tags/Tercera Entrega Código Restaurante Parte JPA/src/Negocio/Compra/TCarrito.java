package Negocio.Compra;

import java.util.ArrayList;
import java.util.List;

public class TCarrito {

	private List<TLineaDeCompra> tLineaDeCompra;

	private TCompra tCompra;

	
	public List<TLineaDeCompra> getLineasDeCompra() {
		return tLineaDeCompra;
	}

	public TCarrito(List<TLineaDeCompra> tLineaDeCompra, TCompra tCompra) {
		super();
		this.tLineaDeCompra = tLineaDeCompra;
		this.tCompra = tCompra;
	}

	public TCarrito() {
		tLineaDeCompra = new ArrayList<TLineaDeCompra>();
		tCompra = new TCompra();
	}

	public List<TLineaDeCompra> getLineaDeCompra() {
		return tLineaDeCompra;
	}

	public void setLineaDeCompra(List<TLineaDeCompra> tLineaDeCompra) {
		this.tLineaDeCompra = tLineaDeCompra;
	}

	public TCompra getCompra() {
		return tCompra;
	}

	public void setCompra(TCompra tCompra) {
		this.tCompra = tCompra;
	}

	public void setPrecioPagado(float precioPagado) {
		tCompra.setPrecioPagado(precioPagado);
	}

	public float getPrecioPagado() {
		return tCompra.getPrecioPagado();
	}

}