package Presentación.Command.IngredientesCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.TIngrediente;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class ModificarIngredientesCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAIngrediente().editarIngrediente((TIngrediente) datos);

		if (res != -1) {
			return new Context(Eventos.RES_MODIFICAR_INGREDIENTE_OK, res);
		} else {
			return new Context(Eventos.RES_MODIFICAR_INGREDIENTE_KO, null);
		}
	}
}