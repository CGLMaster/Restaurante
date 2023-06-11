package Negocio.Producto;

import java.util.List;


public interface SAProducto {
	
	public int altaProducto(TProducto prod);

	
	public int bajaProducto(int prod);

	
	public int modificarProducto(TProducto prod);

	
	public TProducto buscarProducto(int id);

	
	public List<TProducto> buscarTodosProducto();

	
	public int vincularProveedor(TDistribuye distribuye);

	
	public int desvincularProveedor(TDistribuye distribuye);
	
	
	public List<TDistribuye> buscarVinculadosAProd(int id_producto);
	

	public List<TProducto> buscarProductosPorRangoPrecio(int desde, int hasta);
}