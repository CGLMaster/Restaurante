package Presentación.Command.PersonalCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Personal.TPersonal;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class ModificarPersonalCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAPersonal().modificarPersonal((TPersonal)datos);
		if(res != -1){
			return new Context(Eventos.RES_MODIFICAR_PERSONAL_OK,res);
		}else {
			return new Context(Eventos.RES_MODIFICAR_PERSONAL_KO,null);
		}
	}
}