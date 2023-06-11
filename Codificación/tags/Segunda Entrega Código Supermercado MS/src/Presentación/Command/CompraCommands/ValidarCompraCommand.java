package Presentaci�n.Command.CompraCommands;

import Negocio.Compra.TCarrito;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class ValidarCompraCommand implements Command{

	public Context executeCommand(Object datos) {
		
		int res = FactoriaSA.getInstance().getSACompra().validarCarrito((TCarrito) datos);
		Context resContext;
		
		if(res >= 0){
			resContext = new Context(Eventos.RES_ABRIR_COMPRA_OK, (TCarrito) datos);
		}else{
			resContext = new Context(Eventos.RES_ABRIR_COMPRA_KO, null);
		}
		
		return resContext;
	}
	
}
