
package Presentación.Command.TurnoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class calcularNominaTurnoCommand implements Command {
	
	public Context executeCommand(Object datos) {
		
		double res = FactoriaSA.getInstance().getSATurno().calcularNominaTurno((int) datos);
		Context resContext;
		
		if(res !=-1 ){
			resContext = new Context(Eventos.RES_CALCULAR_NOMINA_OK, res);
		}else{
			resContext = new Context(Eventos.RES_CALCULAR_NOMINA_KO);
		}
		
		return resContext;
	}
}