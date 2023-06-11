package Presentaci�n.Command.PedidoCommands;

import java.util.List;
import java.util.Map;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pedido.TLineaPedido;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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