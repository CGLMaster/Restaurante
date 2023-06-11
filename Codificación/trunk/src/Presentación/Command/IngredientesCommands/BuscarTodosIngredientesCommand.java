package Presentación.Command.IngredientesCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.TIngrediente;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class BuscarTodosIngredientesCommand implements Command {

	public Context executeCommand(Object datos) {
		Context result;
		List<TIngrediente> ingredientes = FactoriaSA.getInstance().getSAIngrediente().buscarTodosIngredientes();

		if (ingredientes != null) {
			result = new Context(Eventos.CREAR_VINGREDIENTE, ingredientes);
		} else {
			result = new Context(Eventos.RES_BUSCAR_TODOS_INGREDIENTE_KO, null);
		}

		return result;
	}
}