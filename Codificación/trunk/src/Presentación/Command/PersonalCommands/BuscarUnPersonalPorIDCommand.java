package Presentaci�n.Command.PersonalCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Personal.TPersonal;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;


public class BuscarUnPersonalPorIDCommand implements Command {
	
	public Context executeCommand(Object datos) {
		TPersonal res = FactoriaSA.getInstance().getSAPersonal().buscarUnPersonalPorID((int) datos);
		if(res != null){
			return new Context(Eventos.BUSCAR_PERSONAL,res);
		}else {
			return new Context(Eventos.RES_BUSCAR_PERSONAL_KO,null);
		}
	}
}