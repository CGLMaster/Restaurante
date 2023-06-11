package Presentación.Command.ProductoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TDistribuye;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

public class DesvincularProductoProvCommand implements Command{

	@Override
	public Context executeCommand(Object datos) {
		int res = FactoriaSA.getInstance().getSAProducto().desvincularProveedor((TDistribuye) datos);
		Context resContext = null;
		
		if(res > 0){
			resContext = new Context(Eventos.RES_DESVINCULAR_PRODUCTO_PROVEEDOR_OK);
		}else{
			resContext = new Context(Eventos.RES_DESVINCULAR_PRODUCTO_PROVEEDOR_KO);
		}
		
		return resContext;
	}

}
