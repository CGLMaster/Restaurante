package Presentaci�n.Command.PlatoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Plato.TPlatoConIngredientes;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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
