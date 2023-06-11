package Presentaci�n.Command.CompraCommands;

import Negocio.Compra.TCarrito;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;


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