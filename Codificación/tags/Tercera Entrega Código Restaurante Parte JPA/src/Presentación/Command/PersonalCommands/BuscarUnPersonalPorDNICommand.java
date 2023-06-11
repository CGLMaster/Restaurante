package Presentaci�n.Command.PersonalCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Personal.TPersonal;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;


public class BuscarUnPersonalPorDNICommand implements Command {
	
	public Context executeCommand(Object datos) {
		TPersonal res = FactoriaSA.getInstance().getSAPersonal().buscarUnPersonalPorDNI((String) datos);
		if(res != null){
			return new Context(Eventos.BUSCAR_PERSONAL_DNI,res);
		}else {
			return new Context(Eventos.RES_BUSCAR_PERSONAL_DNI_KO,(int) datos);
		}
	}
}