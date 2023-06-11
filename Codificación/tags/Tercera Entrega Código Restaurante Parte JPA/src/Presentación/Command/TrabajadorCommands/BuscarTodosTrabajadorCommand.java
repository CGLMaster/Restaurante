package Presentaci�n.Command.TrabajadorCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Trabajador.TTrabajador;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BuscarTodosTrabajadorCommand implements Command {

	@Override
	public Context executeCommand(Object datos) {
		Context result;
		List<TTrabajador> trabajadores = FactoriaSA.getInstance().getSATrabajador().buscarTodosTrabajadores();

		if (trabajadores != null) {
			result = new Context(Eventos.CREAR_VTRABAJADOR, trabajadores);
		} else {
			result = new Context(Eventos.RES_BUSCAR_TODOS_TRABAJADOR_KO, null);
		}

		return result;
	}
}