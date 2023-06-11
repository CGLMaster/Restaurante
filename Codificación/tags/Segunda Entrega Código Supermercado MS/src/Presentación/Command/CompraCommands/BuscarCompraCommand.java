package Presentación.Command.CompraCommands;

import Negocio.Compra.TCompraConProductos;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class BuscarCompraCommand implements Command {
	
	public Context executeCommand(Object datos) {
		
		TCompraConProductos compra = FactoriaSA.getInstance().getSACompra().buscarPorIDCompra((int) datos);
		Context resContext;
		
		if(compra  != null){
			resContext = new Context(Eventos.BUSCAR_COMPRA_ID, compra);
		}else{
			resContext = new Context(Eventos.RES_BUSCAR_COMPRA_ID_KO, null);
		}
		
		return resContext;
	}
}