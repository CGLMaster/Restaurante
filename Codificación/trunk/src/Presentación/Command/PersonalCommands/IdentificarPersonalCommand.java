package Presentación.Command.PersonalCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Personal.TPersonal;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class IdentificarPersonalCommand implements Command {
	
	public Context executeCommand(Object datos) {
		TPersonal res = FactoriaSA.getInstance().getSAPersonal().identificarPersonal((int) datos);
		if(res != null){
			return new Context(Eventos.LOGIN_REST,res);
		}else {
			return new Context(Eventos.RES_IDENTIFICAR_PERSONAL_KO,null);
		}
	}
}