package Presentaci�n.Command.PersonalCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;


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