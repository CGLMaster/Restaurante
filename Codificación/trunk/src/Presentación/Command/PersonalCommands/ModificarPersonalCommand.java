package Presentaci�n.Command.PersonalCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Personal.TPersonal;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;


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