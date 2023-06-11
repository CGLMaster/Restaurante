package Negocio.Pedido;

import java.util.List;
import java.util.Map;

public interface SAPedido {

	public int DevolucionPedido(List<TLineaPedido> lineas);

	public TComanda abrirPedido(int idPersonal);

	public int cerrarPedido(TComanda comanda);

	public TPedidoConPlatos buscarPedidoPorID(int id);

	public List<TPedido> buscarPedidoPorCliente(int idCliente);

	public List<TPedido> buscarTodosPedido();

	public TComanda aniadirPlatoPedido(TComanda comanda, TLineaPedido lineaP);

	public TComanda eliminarPlatoPedido(TComanda comanda,TLineaPedido linea);

	public TComanda validarPedido(TComanda datos);

}