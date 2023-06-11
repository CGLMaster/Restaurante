package Negocio.Compra;

import java.util.List;
import Negocio.Producto.TProducto;


public class TCompraConProductos {

	private List<TProducto> productos;
	private TCompra compra;
	private List<TLineaDeCompra> lineasDeCompra;
	
	
	public TCompraConProductos(List<TProducto> productos, TCompra compra, List<TLineaDeCompra> lineasDeCompra){
		this.productos = productos;
		this.compra = compra;
		this.lineasDeCompra = lineasDeCompra;
	}
	

	public List<TProducto> getProductos() {
		return productos;
	}

	public void setProductos(List<TProducto> productos) {
		this.productos = productos;
	}

	public TCompra getCompra() {
		return compra;
	}

	public void setCompra(TCompra compra) {
		this.compra = compra;
	}

	public List<TLineaDeCompra> getLineasDeCompra() {
		return lineasDeCompra;
	}

	public void setLineasDeCompra(List<TLineaDeCompra> lineasDeCompra) {
		this.lineasDeCompra = lineasDeCompra;
	}


}