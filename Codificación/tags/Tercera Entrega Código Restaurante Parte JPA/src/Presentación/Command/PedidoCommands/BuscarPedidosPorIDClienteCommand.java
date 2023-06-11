package Presentación.Command.PedidoCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TPedido;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarPedidosPorIDClienteCommand implements Command {
	
	public Context executeCommand(Object datos) {
		Context result;
		List<TPedido> res = (List<TPedido>) FactoriaSA.getInstance().getSAPedido().buscarPedidoPorCliente((int) datos);

		if (res != null) {
			result = new Context(Eventos.CREAR_VPEDIDO, res);
		} else {
			result = new Context(Eventos.RES_BUSCAR_TODOS_PEDIDO_KO, (int) datos);
		}

		return result;
	}
}