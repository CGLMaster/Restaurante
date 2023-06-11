package Presentaci�n.Command.PedidoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TComanda;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class ValidarPedidoCommand implements Command {
	public Context executeCommand(Object datos) {

		TComanda res = FactoriaSA.getInstance().getSAPedido().validarPedido((TComanda) datos);
		Context resContext;

		if (res != null) {
			resContext = new Context(Eventos.RES_ABRIR_PEDIDO_OK, res);
		} else {
			resContext = new Context(Eventos.RES_ABRIR_PEDIDO_KO, null);
		}

		return resContext;
	}
}
