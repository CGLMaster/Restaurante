package Presentación.Command.ProductoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TProducto;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class ModificarProductoCommand implements Command {
	
	public Context executeCommand(Object datos) {
		if(FactoriaSA.getInstance().getSAProducto().modificarProducto((TProducto) datos) != -1){
			return new Context(Eventos.RES_MODIFICAR_PRODUCTO_OK, null);
		}
		else{
			return new Context(Eventos.RES_MODIFICAR_PRODUCTO_KO, null);
		}
	}
}
