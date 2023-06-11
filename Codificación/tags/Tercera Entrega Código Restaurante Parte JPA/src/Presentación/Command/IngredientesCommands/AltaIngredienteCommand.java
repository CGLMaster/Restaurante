package Presentación.Command.IngredientesCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.TIngrediente;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class AltaIngredienteCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAIngrediente().aniadirIngrediente((TIngrediente) datos);
		Context resContext;

		if (res > 0) {
			resContext = new Context(Eventos.RES_ALTA_INGREDIENTE_OK, res);
		} else {
			resContext = new Context(Eventos.RES_ALTA_INGREDIENTE_KO, null);
		}
		
		return resContext;
	}

}