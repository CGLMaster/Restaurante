package Presentaci�n.Command.PersonalCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Personal.TPersonal;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BuscarTodosPersonalCommand implements Command {
	
	public Context executeCommand(Object datos) {
		 List<TPersonal> res = FactoriaSA.getInstance().getSAPersonal().buscarTodosPersonal();
			if(res != null){
				return new Context(Eventos.CREAR_VPERSONAL,res);
			}else {
				return new Context(Eventos.RES_BUSCAR_TODOS_PERSONAL_KO,null);
			}
	}
}