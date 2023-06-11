package Presentaci�n.Command.PlatoCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Plato.TPlato;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BuscarTodosPlatoCommand implements Command {
	
	public Context executeCommand(Object datos) {
		List<TPlato> platos = FactoriaSA.getInstance().getSAPlato().buscarTodosPlato();
		Context result;
		
		if (platos != null) {
			result = new Context(Eventos.CREAR_VPLATO, platos);
		} else {
			result = new Context(Eventos.RES_BUSCAR_TODOS_PLATO_KO, null);
		}

		return result;
	}
}