package Presentación.Command.PedidoCommands;

import java.util.HashMap;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TComanda;
import Negocio.Pedido.TLineaPedido;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class AniadirPlatoaPedidoCommand implements Command {
	
	@SuppressWarnings("unchecked")
	public Context executeCommand(Object datos) {
		
		HashMap<String, Object> params = (HashMap<String, Object>) datos;
		
		 TComanda comanda = (TComanda)params.get("comanda");
		 TLineaPedido linea = (TLineaPedido) params.get("linea");
		
		TComanda c = FactoriaSA.getInstance().getSAPedido().aniadirPlatoPedido(comanda,linea);
		Context resContext;
		
		if (c!=null) {
			resContext = new Context(Eventos.ANIADIR_PLATO_PEDIDO_OK, c);
		} else {
			resContext = new Context(Eventos.ANIADIR_PLATO_PEDIDO_KO, null);
		}

		return resContext;
	}
}