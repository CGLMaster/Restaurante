package Presentaci�n.Command.ProveedorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class AltaProveedorCommand implements Command {
public Context executeCommand(Object datos) {
		
		int res = FactoriaSA.getInstance().getSAProveedor().altaProveedor((String) datos);
		Context resContext;
		
		if(res > 0){
			resContext = new Context(Eventos.RES_ALTA_PRODUCTO_OK, res);
		}else{
			resContext = new Context(Eventos.RES_ALTA_PRODUCTO_KO, null);
		}
		
		return resContext;
	}
}
