package Presentación.Command.PedidoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TComanda;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

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
