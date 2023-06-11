package Presentaci�n.Command.PedidoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TComanda;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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