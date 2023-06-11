package Negocio.Pedido;

import Negocio.Plato.TPlato;
import java.util.HashMap;
import java.util.List;

public class TPedidoConPlatos {

	private TPedido tPedido;

	private HashMap<Integer, TPlato> platos;
	
	private List<TLineaPedido> tLineaPedido;

	public HashMap<Integer, TPlato> getPlatos() {
		return platos;
	}

	public TPedidoConPlatos(TPedido tPedido, HashMap<Integer, TPlato> platos, List<TLineaPedido> tLineaPedido) {
		super();
		this.tPedido = tPedido;
		this.platos = platos;
		this.tLineaPedido = tLineaPedido;
	}

	public TPedido getPedido() {
		return tPedido;
	}

	public List<TLineaPedido> getLineaPedidos() {
		return tLineaPedido;
	}

	public void setTPedido(TPedido pedido) {
		this.tPedido =pedido;
	}

	public void setPlatos(HashMap<Integer, TPlato> mapa) {
		this.platos =mapa;
	}

	public void setLineaPedidos(List<TLineaPedido> listaLineaPedidos) {
		this.tLineaPedido = listaLineaPedidos;
	}

	public void aniadirLineaPedidos(TLineaPedido lineapedido) {
		this.tLineaPedido.add(lineapedido);
	}

	public void aniadirPlato(TPlato plato) {
		this.platos.put(plato.getId(), plato);
	}
}