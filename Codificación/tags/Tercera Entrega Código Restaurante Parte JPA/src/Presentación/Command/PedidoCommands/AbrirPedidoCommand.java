package Presentación.Command.PedidoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TComanda;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class AbrirPedidoCommand implements Command {
	public Context executeCommand(Object datos) {
		TComanda c= FactoriaSA.getInstance().getSAPedido().abrirPedido((int) datos);
		Context resContext;

		if (c != null) {
			resContext = new Context(Eventos.CREAR_VCARRITO, c);
		} else {
			resContext = new Context(Eventos.CREAR_CARRITO_KO, null);
		}

		return resContext;
	}
}