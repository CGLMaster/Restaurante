package Presentación.Command.IngredientesCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

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