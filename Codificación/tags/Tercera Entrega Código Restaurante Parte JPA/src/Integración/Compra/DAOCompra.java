package Integración.Compra;

import Negocio.Compra.TCompra;

import java.util.List;

public interface DAOCompra {

	public int cerrarCompra(TCompra compra);

	public int realizarDevolucion(TCompra compra);

	public TCompra buscarCompra(int id_Compra);

	public List<TCompra> buscarTodasCompras();

}