package Presentación.Command.MarcaCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Marca.TMarca;
import Presentación.Command.Command;
import Presentación.Command.Context;

import Presentación.Controller.Eventos;

public class BuscarTodosMarcaCommand implements Command {
	public Context executeCommand(Object datos) {
		Context result;
		List<TMarca> res = FactoriaSA.getInstance().getSAMarca().buscarTodosMarca();

		if (res != null) {
			result = new Context(Eventos.CREAR_VMARCA, res);
		} else {
			result = new Context(Eventos.RES_BUSCAR_TODOS_MARCA_KO, null);
		}

		return result;
	}

}