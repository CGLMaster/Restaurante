package Presentaci�n.Command.ProductoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TDistribuye;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

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
