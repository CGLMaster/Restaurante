package Presentaci�n.Command.ProductoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BajaProductoCommand implements Command{

	@Override
	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAProducto().bajaProducto((int) datos);
		Context resContext;
		
		if(res >= 0){
			resContext = new Context(Eventos.RES_BAJA_PRODUCTO_OK, res);
		}else{
			resContext = new Context(Eventos.RES_BAJA_PRODUCTO_KO, res);
		}
		
		return resContext;
	}

}
