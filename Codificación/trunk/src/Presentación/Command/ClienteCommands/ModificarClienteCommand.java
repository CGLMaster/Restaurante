package Presentación.Command.ClienteCommands;

import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class ModificarClienteCommand implements Command {

	public Context executeCommand(Object datos) {
		if (FactoriaSA.getInstance().getSACliente().modificarCliente((TCliente) datos) != -1) {
			return new Context(Eventos.RES_MODIFICAR_CLIENTE_OK, null);
		} else {
			return new Context(Eventos.RES_MODIFICAR_CLIENTE_KO, null);
		}
	}
}