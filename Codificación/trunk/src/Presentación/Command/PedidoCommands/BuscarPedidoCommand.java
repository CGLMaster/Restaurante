package Presentación.Command.PedidoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TPedidoConPlatos;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarPedidoCommand implements Command {
	
	public Context executeCommand(Object datos) {
		TPedidoConPlatos res = FactoriaSA.getInstance().getSAPedido().buscarPedidoPorID((int) datos);
		Context resContext;

		if (res != null) {
			resContext = new Context(Eventos.BUSCAR_PEDIDO, res);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_PEDIDO_KO, datos);
		}

		return resContext;
	}
}