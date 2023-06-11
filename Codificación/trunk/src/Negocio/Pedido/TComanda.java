package Negocio.Pedido;

import java.util.HashMap;

public class TComanda {

	private TPedido tPedido;

	private HashMap<Integer, TLineaPedido> tLineaPedido;
	
	public TComanda(){
		tPedido = new TPedido();
		tLineaPedido = new HashMap<Integer, TLineaPedido>();
	}

	public HashMap<Integer, TLineaPedido> getMapaLineas() {
		return tLineaPedido;

	}
	
	public void setMapaLineas(HashMap<Integer, TLineaPedido> tLineaPedido) {
		this.tLineaPedido=tLineaPedido;
	}

	public TPedido getPedido() {
		return tPedido;
	}

	public void setPedido(TPedido tpedido) {
		this.tPedido=tpedido;
	}
}