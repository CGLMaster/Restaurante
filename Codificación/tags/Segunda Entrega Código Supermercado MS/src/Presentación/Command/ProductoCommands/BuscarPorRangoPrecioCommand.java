package Presentaci�n.Command.ProductoCommands;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TProducto;
import Presentaci�n.Command.Command;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Eventos;

public class BuscarPorRangoPrecioCommand implements Command{

	@Override
	public Context executeCommand(Object datos) {
		int[] rango = (int[]) datos;
		
		List<TProducto> prods = FactoriaSA.getInstance().getSAProducto().buscarProductosPorRangoPrecio(rango[0], rango[1]);
		Context contextRes = null;
		
		if(prods != null){
			contextRes = new Context(Eventos.CREAR_VPRODUCTO, prods);
		}else{
			contextRes = new Context(Eventos.RES_BUSCAR_PRODUCTOS_PROVEEDOR_POR_RANGO_PRECIO_KO);
		}
		
		return contextRes;
	}

}
