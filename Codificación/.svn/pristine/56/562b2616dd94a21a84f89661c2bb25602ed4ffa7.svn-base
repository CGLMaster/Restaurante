package Presentación.Command.ProveedorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Proveedor.TProveedor;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarProveedorCommand implements Command {

	@Override
	public Context executeCommand(Object datos) {
		TProveedor proveedor = FactoriaSA.getInstance().getSAProveedor().buscarProveedor((int) datos);
		Context resContext;
		
		if (proveedor != null) {
			resContext = new Context(Eventos.BUSCAR_PROVEEDOR, proveedor);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_PROVEEDOR_KO);
		}
		
		return resContext;
	}

}
