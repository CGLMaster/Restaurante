package Presentaci�n.Command;

import Presentaci�n.Command.ClienteCommands.*;
import Presentaci�n.Command.ClienteRestCommand.AltaClienteRestCommand;
import Presentaci�n.Command.ClienteRestCommand.BuscarTodosClienteRestCommand;
import Presentaci�n.Command.ClienteRestCommand.BuscarUnClienteRestCommand;
import Presentaci�n.Command.ClienteRestCommand.EliminarClienteRestCommand;
import Presentaci�n.Command.ClienteRestCommand.ModificarClienteRestCommand;
import Presentaci�n.Command.CompraCommands.*;
import Presentaci�n.Command.IngredientesCommands.AltaIngredienteCommand;
import Presentaci�n.Command.IngredientesCommands.BajaIngredienteCommand;
import Presentaci�n.Command.IngredientesCommands.BuscarIngredienteCommand;
import Presentaci�n.Command.IngredientesCommands.BuscarTodosIngredientesCommand;
import Presentaci�n.Command.IngredientesCommands.ModificarIngredientesCommand;
import Presentaci�n.Command.MarcaCommands.*;
import Presentaci�n.Command.PedidoCommands.AbrirPedidoCommand;
import Presentaci�n.Command.PedidoCommands.AniadirPlatoaPedidoCommand;
import Presentaci�n.Command.PedidoCommands.BuscarPedidoCommand;
import Presentaci�n.Command.PedidoCommands.BuscarPedidoParaDevolucionCommand;
import Presentaci�n.Command.PedidoCommands.BuscarPedidosPorIDClienteCommand;
import Presentaci�n.Command.PedidoCommands.BuscarTodosPedidoCommand;
import Presentaci�n.Command.PedidoCommands.CerrarPedidoCommand;
import Presentaci�n.Command.PedidoCommands.DevolucionCommand;
import Presentaci�n.Command.PedidoCommands.EliminarPlatodePedidoCommand;
import Presentaci�n.Command.PedidoCommands.ValidarPedidoCommand;
import Presentaci�n.Command.PersonalCommands.AltaPersonalCommand;
import Presentaci�n.Command.PersonalCommands.BajaPersonalCommand;
import Presentaci�n.Command.PersonalCommands.BuscarTodosPersonalCommand;
import Presentaci�n.Command.PersonalCommands.BuscarUnPersonalPorDNICommand;
import Presentaci�n.Command.PersonalCommands.BuscarUnPersonalPorIDCommand;
import Presentaci�n.Command.PersonalCommands.IdentificarPersonalCommand;
import Presentaci�n.Command.PersonalCommands.ModificarPersonalCommand;
import Presentaci�n.Command.PlatoCommands.AltaPlatoCommand;
import Presentaci�n.Command.PlatoCommands.AniadirIngredienteAPlato;
import Presentaci�n.Command.PlatoCommands.BajaPlatoCommand;
import Presentaci�n.Command.PlatoCommands.BuscarPlatoCommand;
import Presentaci�n.Command.PlatoCommands.BuscarPlatoConIngredientesCommand;
import Presentaci�n.Command.PlatoCommands.BuscarTodosPlatoCommand;
import Presentaci�n.Command.PlatoCommands.EliminarIngredienteAPlato;
import Presentaci�n.Command.PlatoCommands.ModificarPlatoCommand;
import Presentaci�n.Command.ProductoCommands.*;
import Presentaci�n.Command.ProveedorCommands.*;
import Presentaci�n.Command.SeccionCommands.*;
import Presentaci�n.Command.TrabajadorCommands.*;
import Presentaci�n.Command.TurnoCommands.AltaTurnoCommand;
import Presentaci�n.Command.TurnoCommands.BajaTurnoCommand;
import Presentaci�n.Command.TurnoCommands.BuscarTodosTurnoCommand;
import Presentaci�n.Command.TurnoCommands.BuscarTurnoCommand;
import Presentaci�n.Command.TurnoCommands.ModificarTurnoCommand;
import Presentaci�n.Command.TurnoCommands.calcularNominaTurnoCommand;
import Presentaci�n.Controller.Eventos;

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

		// COMANDOS PRODUCTO
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

		// COMANDOS SECCION

		case Eventos.ALTA_SECCION:
			return new AltaSeccionCommand();

		case Eventos.BAJA_SECCION:
			return new BajaSeccionCommand();

		case Eventos.MODIFICAR_SECCION:
			return new ModificarSeccionCommand();

		case Eventos.BUSCAR_SECCION:
			return new BuscarSeccionCommand();

		case Eventos.BUSCAR_TODOS_SECCION:
			return new BuscarTodosSeccionCommand();

		// COMANDOS COMPRA

		case Eventos.ABRIR_COMPRA:
			return new AbrirCompraCommand();

		case Eventos.ANIADIR_PRODUCTO_COMPRA:
			return new AniadirProductoCommand();

		case Eventos.ELIMINAR_PRODUCTO_COMPRA:
			return new EliminarProductoCompraCommand();

		case Eventos.CERRAR_COMPRA:
			return new CerrarCompraCommand();

		case Eventos.BUSCAR_TODOS_COMPRA:
			return new BuscarTodosCompraCommand();

		case Eventos.BUSCAR_COMPRA_ID:
			return new BuscarCompraCommand();

		case Eventos.CREAR_V_DEVOLUCION:
			return new BuscarCompraParaDevolucionCommand();

		case Eventos.VALIDAR_COMPRA_SIN_CLIENTE:
			return new ValidarCompraCommand();

		case Eventos.VALIDAR_COMPRA:
			return new ValidarIDClienteCompraCommand();

		case Eventos.DEVOLUCION_COMPRA:
			return new DevolucionCompraCommand();

		case Eventos.BUSCAR_COMPRA_POR_RANGO_PRECIO_FECHA:
			return new BuscarComprasPorRangoPrecioFechaCommand();

		// COMANDOS TURNO

		case Eventos.BUSCAR_TODOS_TURNO:
			return new BuscarTodosTurnoCommand();

		case Eventos.BUSCAR_TURNO:
			return new BuscarTurnoCommand();

		case Eventos.GUARDAR_TURNO:
			return new AltaTurnoCommand();

		case Eventos.UPDATE_TURNO:
			return new ModificarTurnoCommand();

		case Eventos.BAJA_TURNO:
			return new BajaTurnoCommand();
			
		case Eventos.CALCULAR_NOMINA_TURNO:
			return new calcularNominaTurnoCommand();

		// COMANDOS INGREDIENTES

		case Eventos.BUSCAR_TODOS_INGREDIENTE:
			return new BuscarTodosIngredientesCommand();

		case Eventos.BUSCAR_INGREDIENTE:
			return new BuscarIngredienteCommand();

		case Eventos.GUARDAR_INGREDIENTE:
			return new AltaIngredienteCommand();

		case Eventos.UPDATE_INGREDIENTE:
			return new ModificarIngredientesCommand();

		case Eventos.BAJA_INGREDIENTE:
			return new BajaIngredienteCommand();

		// COMANDOS PLATOS

		case Eventos.BUSCAR_TODOS_PLATO:
			return new BuscarTodosPlatoCommand();

		case Eventos.BUSCAR_PLATO:
			return new BuscarPlatoCommand();

		case Eventos.GUARDAR_PLATO:
			return new AltaPlatoCommand();

		case Eventos.BAJA_PLATO:
			return new BajaPlatoCommand();
			
		case Eventos.UPDATE_PLATO:
			return new ModificarPlatoCommand();

		case Eventos.BUSCAR_PLATO_CON_INGREDIENTES:
			return new BuscarPlatoConIngredientesCommand();

		case Eventos.ELIMINAR_INGREDIENTE_DE_PLATO:
			return new EliminarIngredienteAPlato();
			
		case Eventos.ANIADIR_INGREDIENTE_A_PLATO:
			return new AniadirIngredienteAPlato();

		// COMANDOS CLIENTES_REST

		case Eventos.BUSCAR_TODOS_CLIENTE_REST:
			return new BuscarTodosClienteRestCommand();

		case Eventos.BUSCAR_CLIENTE_REST:
			return new BuscarUnClienteRestCommand();

		case Eventos.ALTA_CLIENTE_REST:
			return new AltaClienteRestCommand();

		case Eventos.MODIFICAR_CLIENTE_REST:
			return new ModificarClienteRestCommand();

		case Eventos.BAJA_CLIENTE_REST:
			return new EliminarClienteRestCommand();
			
		// COMANDOS CLIENTES_REST

		case Eventos.BUSCAR_TODOS_PERSONAL:
			return new BuscarTodosPersonalCommand();

		case Eventos.BUSCAR_PERSONAL:
			return new BuscarUnPersonalPorIDCommand();
				
		case Eventos.BUSCAR_PERSONAL_DNI:
			return new BuscarUnPersonalPorDNICommand();
				
		case Eventos.IDENTIFICAR_PERSONAL:
			return new IdentificarPersonalCommand();

		case Eventos.ALTA_PERSONAL:
			return new AltaPersonalCommand();

		case Eventos.MODIFICAR_PERSONAL:
			return new ModificarPersonalCommand();

		case Eventos.BAJA_PERSONAL:
			return new BajaPersonalCommand();

		// COMANDOS PEDIDO

		case Eventos.DEVOLUCION_PEDIDO:
			return new DevolucionCommand();

		case Eventos.BUSCAR_TODOS_PEDIDO:
			return new BuscarTodosPedidoCommand();
			
		case Eventos.BUSCAR_PEDIDOS_POR_CLIENTE:
			return new BuscarPedidosPorIDClienteCommand();

		case Eventos.BUSCAR_PEDIDO:
			return new BuscarPedidoCommand();
			
		case Eventos.CREAR_CARRITO:
			return new AbrirPedidoCommand();
			
		case Eventos.VALIDAR_PEDIDO_SIN_CLIENTE:
			return new ValidarPedidoCommand();
			
		case Eventos.ANIADIR_PLATO_PEDIDO:
			return new AniadirPlatoaPedidoCommand();
			
		case Eventos.ELIMINAR_PLATO_PEDIDO:
			return new EliminarPlatodePedidoCommand();
			
		case Eventos.CERRAR_PEDIDO:
			return new CerrarPedidoCommand();
			
		case Eventos.CREAR_V_DEVOLUCION_PEDIDO:
			return new BuscarPedidoParaDevolucionCommand();

			
		}
		
		return null;
	}
}