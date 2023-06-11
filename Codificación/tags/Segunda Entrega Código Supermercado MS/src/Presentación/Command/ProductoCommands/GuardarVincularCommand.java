package Presentaci�n.Command.ProductoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TDistribuye;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class GuardarVincularCommand implements Command{

	@Override
	public Context executeCommand(Object datos) {
		
		int res = FactoriaSA.getInstance().getSAProducto().vincularProveedor((TDistribuye) datos);
		Context ContextRes = null;
		
		if(res > 0){
			ContextRes = new Context(Eventos.RES_VINCULAR_PRODUCTO_PROVEEDOR_OK);
		}else{
			ContextRes = new Context(Eventos.RES_VINCULAR_PRODUCTO_PROVEEDOR_KO);
		}

		return ContextRes;
	}
	

}
