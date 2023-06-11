package Presentación.Command.PedidoCommands;

import java.util.List;
import java.util.Map;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TLineaPedido;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class DevolucionCommand implements Command {
	
	@SuppressWarnings("unchecked")
	public Context executeCommand(Object datos) {
		
		int res = FactoriaSA.getInstance().getSAPedido().DevolucionPedido((List<TLineaPedido>) datos);
		Context resContext;
		
		if(res != -1){
			resContext = new Context(Eventos.RES_DEVOLUCION_PLATOS_OK, res);
		}else{
			resContext = new Context(Eventos.RES_DEVOLUCION_PLATOS_KO);
		}
		
		return resContext;
	}
}