package Presentación.Command.ClienteCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BajaClienteCommand implements Command {

	public Context executeCommand(Object datos) {
		int respuesta = FactoriaSA.getInstance().getSACliente().bajaCliente((int) datos);

		if (respuesta > 0) {
			return new Context(Eventos.RES_BAJA_CLIENTE_OK, respuesta);
		} else {
			return new Context(Eventos.RES_BAJA_CLIENTE_KO, respuesta);
		}

	}
}