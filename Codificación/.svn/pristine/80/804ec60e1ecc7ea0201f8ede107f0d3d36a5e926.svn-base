package Presentación.Command.ProveedorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BajaProveedorCommand implements Command {
	
	@Override
	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAProveedor().eliminarProveedor((int) datos);
		Context resContext;
		
		if (res > 0) {
			resContext = new Context(Eventos.RES_BAJA_PROVEEDOR_OK, datos);
		} else {
			resContext = new Context(Eventos.RES_BAJA_PROVEEDOR_KO, null);
		}
		
		return resContext;
	}
}