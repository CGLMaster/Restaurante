package Presentación.Command.PedidoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TPedidoConPlatos;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarPedidoParaDevolucionCommand implements Command{

	@Override
	public Context executeCommand(Object datos) {
		TPedidoConPlatos pedido = FactoriaSA.getInstance().getSAPedido().buscarPedidoPorID((int) datos);
		Context resContext;

		if (pedido != null) {
			resContext = new Context(Eventos.CREAR_V_DEVOLUCION_PEDIDO, pedido);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_PEDIDO_KO, null);
		}

		return resContext;
	}

}
