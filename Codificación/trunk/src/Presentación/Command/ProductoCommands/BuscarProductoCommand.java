package Presentación.Command.ProductoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TProducto;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;


public class BuscarProductoCommand implements Command {
	
	public Context executeCommand(Object datos) {
		TProducto res = FactoriaSA.getInstance().getSAProducto().buscarProducto((int) datos);
		Context resContext;
		if(res != null) resContext = new Context(Eventos.BUSCAR_PRODUCTO, res);
		else resContext = new Context(Eventos.RES_BUSCAR_PRODUCTO_KO);
		return resContext;
	}
}