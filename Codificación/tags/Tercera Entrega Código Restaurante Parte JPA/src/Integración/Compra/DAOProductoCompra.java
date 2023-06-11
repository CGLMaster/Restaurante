package Integración.Compra;

import Negocio.Compra.TLineaDeCompra;

import java.util.List;

public interface DAOProductoCompra {

	
	

	public int aniadirProductoCompra(TLineaDeCompra lineaCompra);

	public int modificarProductoCompra(TLineaDeCompra lineaCompra);

	public int eliminarProductoCompra(int idCompra, int idProducto);

	public TLineaDeCompra leerLinea(int id_Compra, int id_Producto);

	public List<TLineaDeCompra> leerTodasLineas(int idCompra);
}