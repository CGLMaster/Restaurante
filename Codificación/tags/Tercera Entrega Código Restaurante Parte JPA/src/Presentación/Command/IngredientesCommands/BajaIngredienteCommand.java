package Presentaci�n.Command.IngredientesCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BajaIngredienteCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAIngrediente().eliminarIngrediente((int) datos);
		Context resContext;

		if (res > 0) {
			resContext = new Context(Eventos.RES_BAJA_INGREDIENTE_OK, datos);
		} else {
			resContext = new Context(Eventos.RES_BAJA_INGREDIENTE_KO, datos);
		}

		return resContext;
	}
}