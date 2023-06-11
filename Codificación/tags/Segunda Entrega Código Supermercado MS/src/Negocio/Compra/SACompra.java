package Negocio.Compra;

import java.time.LocalDate;
import java.util.List;

public interface SACompra {

	public TCarrito abrirCompra(int id_Trabajador);

	public int cerrarCompra(TCarrito carrito);

	public int aniadirProducto(TLineaDeCompra lineaDeCompra, TCarrito carrito);

	public int eliminarProducto(TLineaDeCompra id_producto, TCarrito carrito);

	public TCompraConProductos buscarPorIDCompra(int id_compra);

	public List<TCompra> buscarTodosCompra();
	
	public List<TCompra> buscarComprasPorRangoPrecioFecha(int desde, int hasta, LocalDate desdeFecha, LocalDate hastaFecha);

	public int devolverProductos(List<TLineaDeCompra> prods_devolucion);
	
	public int validarCarrito(TCarrito carrito);
}