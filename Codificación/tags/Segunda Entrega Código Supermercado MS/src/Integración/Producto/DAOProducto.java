package Integración.Producto;

import java.util.List;
import Negocio.Producto.TProducto;


public interface DAOProducto {
	
	public int altaProducto(TProducto p);

	public int bajaProducto(int prod);

	public int modificarProducto(TProducto prod);

	public TProducto buscarProducto(int id);

	public List<TProducto> buscarTodosProducto();
	
	public int reactivar(int id_producto);

	public TProducto buscarProductoPorMarcayNombre(String nombre_prod, int id_marca); 
	
	public int reducirStock(int ID_Producto, int cantidad);
}