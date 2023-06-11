package Presentación.Command.PlatoCommands;

import java.util.HashMap;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class EliminarIngredienteAPlato implements Command {
	
	@SuppressWarnings("unchecked")
	public Context executeCommand(Object datos) {
		HashMap<String, Object> params = (HashMap<String, Object>) datos;

		int id_ingrediente = (int) params.get("id_ingrediente");
		int id_plato = (int) params.get("id_plato");

		int res = FactoriaSA.getInstance().getSAPlato().eliminarIngredienteAPlato(id_ingrediente, id_plato);
		Context resContext;

		if (res > 0) {
			resContext = new Context(Eventos.RES_ELIMINAR_INGREDIENTE_DE_PLATO_OK, datos);
		} else {
			resContext = new Context(Eventos.RES_ELIMINAR_INGREDIENTE_DE_PLATO_KO, null);
		}

		return resContext;
	}
}