package Presentación.Command.ProductoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

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
