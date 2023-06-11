package Presentaci�n.Command.PedidoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TComanda;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class CerrarPedidoCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAPedido().cerrarPedido((TComanda) datos);
		Context resContext;

		if (res >= 0) {
			resContext = new Context(Eventos.RES_PAGAR_PEDIDO_OK, (TComanda) datos);
		} else {
			resContext = new Context(Eventos.RES_PAGAR_PEDIDO_KO, null);
		}

		return resContext;
	}
}