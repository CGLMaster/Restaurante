package Presentación.Command;

import Presentación.Command.ClienteCommands.*;
import Presentación.Command.CompraCommands.*;
import Presentación.Command.MarcaCommands.*;
import Presentación.Command.ProductoCommands.*;
import Presentación.Command.ProveedorCommands.*;
import Presentación.Command.SeccionCommands.*;
import Presentación.Command.TrabajadorCommands.*;
import Presentación.Controller.Eventos;

public class CommandFactoryImp extends CommandFactory {

	public Command getCommand(int evento) {
		switch (evento) {

		// COMANDOS MARCA

		case Eventos.GUARDAR_MARCA:
			return new AltaMarcaCommand();

		case Eventos.BAJA_MARCA:
			return new BajaMarcaCommand();

		case Eventos.UPDATE_MARCA:
			return new ModificarMarcaCommand();

		case Eventos.BUSCAR_MARCA:
			return new BuscarMarcaCommand();

		case Eventos.BUSCAR_TODOS_MARCA:
			return new BuscarTodosMarcaCommand();

		// COMANDOS CLIENTE

		case Eventos.ALTA_CLIENTE:
			return new AltaClienteCommand();

		case Eventos.BAJA_CLIENTE:
			return new BajaClienteCommand();

		case Eventos.MODIFICAR_CLIENTE:
			return new ModificarClienteCommand();

		case Eventos.BUSCAR_CLIENTE:
			return new BuscarUnClienteCommand();

		case Eventos.BUSCAR_TODOS_CLIENTE:
			return new BuscarTodosClienteCommand();

			
		// COMANDOS TRABAJADOR

		case Eventos.GUARDAR_TRABAJADOR:
			return new AltaTrabajadorCommand();

		case Eventos.BAJA_TRABAJADOR:
			return new BajaTrabajadorCommand();

		case Eventos.UPDATE_TRABAJADOR:
			return new ModificarTrabajadorCommand();

		case Eventos.BUSCAR_TRABAJADOR_ID:
			return new BuscarTrabajadorPorIDCommand();
			
		case Eventos.BUSCAR_TRABAJADOR_DNI:
			return new BuscarTrabajadorPorDNICommand();

		case Eventos.BUSCAR_TODOS_TRABAJADOR:
			return new BuscarTodosTrabajadorCommand();
			
		case Eventos.IDENTIFICAR_TRABAJADOR:
			return new IdentificarTrabajadorCommand();
			
			
		//COMANDOS PRODUCTO 
		case Eventos.GUARDAR_PRODUCTO: 
			return new AltaProductoCommand();
		case Eventos.UPDATE_PRODUCTO: 
			return new ModificarProductoCommand();
		case Eventos.BUSCAR_TODOS_PRODUCTO: 
			return new BuscarTodosProductoCommand();
		case Eventos.BUSCAR_PRODUCTO: 
			return new BuscarProductoCommand();
		case Eventos.BAJA_PRODUCTO: 
			return new BajaProductoCommand();
		case Eventos.BUSCAR_VINCULADOS: 
			return new MostrarVinculadosCommand();
		case Eventos.GUARDAR_VINCULAR: 
			return new GuardarVincularCommand();
		case Eventos.DESVINCULAR_PRODUCTO_PROVEEDOR: 
			return new DesvincularProductoProvCommand();
		case Eventos.BUSCAR_PRODUCTOS_PROVEEDOR_POR_RANGO_PRECIO: 
			return new BuscarPorRangoPrecioCommand();
			
		
		// COMANDOS PROVEEDOR
		
		case Eventos.GUARDAR_PROVEEDOR: 
			return new AltaProveedorCommand();
		  
		case Eventos.UPDATE_PROVEEDOR: 
			return new ModificarProveedorCommand();
		  
		case Eventos.BUSCAR_TODOS_PROVEEDOR:
			return new BuscarTodosProveedorCommand();
			
		case Eventos.BAJA_PROVEEDOR:
			return new BajaProveedorCommand();
			
		case Eventos.BUSCAR_PROVEEDOR:
			return new BuscarProveedorCommand();
		 
			
		 //COMANDOS SECCION
		
		  case Eventos.ALTA_SECCION: return new AltaSeccionCommand();
		  
		  case Eventos.BAJA_SECCION: return new BajaSeccionCommand();
		  
		  case Eventos.MODIFICAR_SECCION: return new ModificarSeccionCommand();
		  
		  
		  case Eventos.BUSCAR_SECCION: return new BuscarSeccionCommand();
		  
		  case Eventos.BUSCAR_TODOS_SECCION: return new
		  BuscarTodosSeccionCommand();
		 
		  //COMANDOS COMPRA
		  
		  case Eventos.ABRIR_COMPRA: return new AbrirCompraCommand();
		  
		  case Eventos.ANIADIR_PRODUCTO_COMPRA: return new AniadirProductoCommand();
		  
		  case Eventos.ELIMINAR_PRODUCTO_COMPRA: return new EliminarProductoCompraCommand();
		  
		  case Eventos.CERRAR_COMPRA: return new CerrarCompraCommand();
		  
		  case Eventos.BUSCAR_TODOS_COMPRA: return new BuscarTodosCompraCommand();
		  
		  case Eventos.BUSCAR_COMPRA_ID: return new BuscarCompraCommand();
		  
		  case Eventos.CREAR_V_DEVOLUCION: return new BuscarCompraParaDevolucionCommand();
		  
		  case Eventos.VALIDAR_COMPRA_SIN_CLIENTE: return new ValidarCompraCommand();
		  
		  case Eventos.VALIDAR_COMPRA: return new ValidarIDClienteCompraCommand();
		  
		  case Eventos.DEVOLUCION_COMPRA: return new DevolucionCompraCommand(); 
		  
		  case Eventos.BUSCAR_COMPRA_POR_RANGO_PRECIO_FECHA: return new BuscarComprasPorRangoPrecioFechaCommand();
		  

		}
		return null;
	}
}