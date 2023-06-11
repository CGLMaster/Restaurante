package Presentación.Command.ClienteCommands;

import Negocio.Cliente.TCliente;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class AltaClienteCommand implements Command {

	public Context executeCommand(Object datos) {
		int respuesta = FactoriaSA.getInstance().getSACliente().altaCliente((TCliente) datos);

		if (respuesta > 0) {
			return new Context(Eventos.RES_ALTA_CLIENTE_OK, respuesta);
		} else {
			return new Context(Eventos.RES_ALTA_CLIENTE_KO, respuesta);
		}

	}
}