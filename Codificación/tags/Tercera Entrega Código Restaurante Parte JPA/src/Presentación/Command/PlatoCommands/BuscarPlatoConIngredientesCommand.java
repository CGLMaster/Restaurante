package Presentación.Command.PlatoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Plato.TPlatoConIngredientes;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarPlatoConIngredientesCommand implements Command {

	public Context executeCommand(Object datos) {
		TPlatoConIngredientes platoConIngredientes = FactoriaSA.getInstance().getSAPlato().mostrarIngredientesPlato((int) datos);
		Context resContext;

		if (platoConIngredientes != null) {
			resContext = new Context(Eventos.BUSCAR_PLATO_CON_INGREDIENTES, platoConIngredientes);
		} else {
			resContext = new Context(Eventos.RES_BUSCAR_PLATO_KO);
		}

		return resContext;
	}
}
