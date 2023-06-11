package Presentación.Command.ProductoCommands;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TDistribuye;
import Presentación.Command.Command;
import Presentación.Command.Context;
import Presentación.Controller.Eventos;

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
