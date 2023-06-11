package Presentación.Command.CompraCommands;

import Negocio.Compra.TCarrito;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class AbrirCompraCommand implements Command {
	
	public Context executeCommand(Object datos) {
		
		TCarrito carrito = FactoriaSA.getInstance().getSACompra().abrirCompra((int) datos);
		Context resContext;
		
		if(carrito  != null){
			resContext = new Context(Eventos.ABRIR_COMPRA, carrito);
		}else{
			resContext = new Context(Eventos.RES_ABRIR_COMPRA_KO, null);
		}
		
		return resContext;
	}
}