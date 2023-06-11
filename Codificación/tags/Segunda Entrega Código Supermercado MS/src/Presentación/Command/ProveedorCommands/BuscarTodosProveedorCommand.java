package Presentación.Command.ProveedorCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Proveedor.TProveedor;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarTodosProveedorCommand implements Command{
	
	@Override
	public Context executeCommand(Object datos) {
		Context result;
		List<TProveedor> proveedores = FactoriaSA.getInstance().getSAProveedor().buscarTodosProveedor();

		if (proveedores != null) {
			result = new Context(Eventos.CREAR_VPROVEEDOR, proveedores);
		} else {
			result = new Context(Eventos.RES_BUSCAR_TODOS_PROVEEDOR_KO, null);
		}

		return result;
	}
}