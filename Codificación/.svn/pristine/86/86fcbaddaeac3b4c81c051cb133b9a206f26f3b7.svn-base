package Presentación.Command.IngredientesCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.TIngrediente;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarIngredienteCommand implements Command {

	public Context executeCommand(Object datos) {
		TIngrediente ingrediente = FactoriaSA.getInstance().getSAIngrediente().buscarIngrediente((int) datos);
		Context resContext;

		if (ingrediente != null) {
			resContext = new Context(Eventos.BUSCAR_INGREDIENTE, ingrediente);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_INGREDIENTE_KO, datos);
		}

		return resContext;
	}
}