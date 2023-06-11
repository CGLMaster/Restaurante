package Presentación.Command.PersonalCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class BajaPersonalCommand implements Command {

	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAPersonal().bajaPersonal((int) datos);
		if(res != -1){
			return new Context(Eventos.RES_BAJA_PERSONAL_OK,res);
		}else {
			return new Context(Eventos.RES_BAJA_PERSONAL_KO,null);
		}
	}
}