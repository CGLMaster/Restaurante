package Presentaci�n.Command.ProveedorCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Proveedor.TProveedor;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class ModificarProveedorCommand implements Command{

	@Override
	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAProveedor().modificarProveedor((TProveedor) datos);

		if (res != -1) {
			return new Context(Eventos.RES_MODIFICAR_PROVEEDOR_OK, null);
		} else {
			return new Context(Eventos.RES_MODIFICAR_PROVEEDOR_KO, null);
		}
	}

}
