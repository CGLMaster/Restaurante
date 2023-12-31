package Presentación.FactoriaVistas;

import java.util.List;


import Negocio.Cliente.TCliente;
import Negocio.Compra.TCarrito;
import Negocio.Compra.TCompra;
import Negocio.Compra.TCompraConProductos;
import Negocio.Marca.TMarca;
import Negocio.Producto.TDistribuye;
import Negocio.Producto.TProducto;
import Negocio.Proveedor.TProveedor;
import Negocio.Seccion.TSeccion;
import Presentación.VPrincipal;
import Presentación.Cliente.VAltaCliente;
import Presentación.Cliente.VBajaCliente;
import Presentación.Cliente.VBuscarUnCliente;
import Presentación.Cliente.VCliente;
import Presentación.Cliente.VModificarCliente;
import Presentación.Command.Context;
import Presentación.Compra.VAbrirCompra;
import Presentación.Compra.VBuscarCompra;
import Presentación.Compra.VBuscarTodosCompra;
import Presentación.Compra.VCerrarCompra;
import Presentación.Compra.VDevolucionCompra;
import Presentación.Compra.VValidacionCompra;
import Presentación.Controller.Eventos;
import Presentación.Marca.VMarca;
import Presentación.Marca.VistasCasos_de_Uso.VBuscarMarca;
import Presentación.Marca.VistasCasos_de_Uso.VCrearMarca;
import Presentación.Marca.VistasCasos_de_Uso.VModificarMarca;
import Presentación.Producto.VProducto;
import Presentación.Producto.VistasCasos_de_UsoPr.VAltaProducto;
import Presentación.Producto.VistasCasos_de_UsoPr.VBuscarProducto;
import Presentación.Producto.VistasCasos_de_UsoPr.VModificarPr;
import Presentación.Producto.VistasCasos_de_UsoPr.VMostrarVinculados;
import Presentación.Producto.VistasCasos_de_UsoPr.VvincularPrProv;
import Presentación.Proveedor.VProveedor;
import Presentación.Proveedor.VAltaProveedor;
import Presentación.Proveedor.VBuscarProveedor;
import Presentación.Proveedor.VModificarProveedor;
import Presentación.Seccion.VAltaSeccion;
import Presentación.Seccion.VBajaSeccion;
import Presentación.Seccion.VBuscarSeccion;
import Presentación.Seccion.VModificarSeccion;
import Presentación.Seccion.VSeccion;
import Presentación.Trabajador.VAltaTrabajador;
import Presentación.Trabajador.VTrabajador;
import Presentación.Trabajador.VBuscarTrabajador;
import Presentación.Trabajador.VModificarTrabajador;
import Negocio.Trabajador.TTrabajador;

public class FactoriaVistasImp extends FactoriaVistas {
	IGUI currentIGUI;

	@Override
	public IGUI generarVista(Context contexto) {
		switch (contexto.getEvento()) {
		// VPrincipal
		case Eventos.LOGIN:
			currentIGUI = new VPrincipal( (TTrabajador) contexto.getDatos());
			return currentIGUI;
		
		case Eventos.CREAR_VPRINCIPAL:
			currentIGUI = new VPrincipal();
			return currentIGUI;

		// VISTAS MARCA
		case Eventos.CREAR_VMARCA:
			currentIGUI = new VMarca((List<TMarca>) contexto.getDatos());
			return currentIGUI;

		case Eventos.ALTA_MARCA:
			currentIGUI = new VCrearMarca();
			return currentIGUI;

		case Eventos.CREAR_VMODIFICAR_MARCA:
			currentIGUI = new VModificarMarca((TMarca) contexto.getDatos());
			System.out.println("FACTORIA VISTAS");
			return currentIGUI;

		case Eventos.BUSCAR_MARCA: 
			currentIGUI = new VBuscarMarca((TMarca) contexto.getDatos());
			return currentIGUI;
		
			
		case Eventos.CREAR_VCLIENTE:
			currentIGUI = new VCliente((List<TCliente>) contexto.getDatos());
			return currentIGUI;
			
		  case Eventos.VALTA_CLIENTE:
			  currentIGUI = new VAltaCliente();
			  return currentIGUI;
		 
		  case Eventos.VBAJA_CLIENTE: 
			  currentIGUI = new VBajaCliente((TCliente) contexto.getDatos());
				return currentIGUI;
		  
		  case Eventos.VMODIFICAR_CLIENTE: 
			  currentIGUI = new VModificarCliente((TCliente) contexto.getDatos());
			  return currentIGUI;
		  
		  case Eventos.VBUSCAR_CLIENTE: 
			currentIGUI = new VBuscarUnCliente((TCliente) contexto.getDatos());
			return currentIGUI;
		 
		// VISTAS TRABAJADOR

		case Eventos.CREAR_VTRABAJADOR:
			currentIGUI = new VTrabajador((List<TTrabajador>) contexto.getDatos());
			return currentIGUI; 

		case Eventos.ALTA_TRABAJADOR:
			currentIGUI = new VAltaTrabajador();
			return currentIGUI; 
			
		case Eventos.BUSCAR_TRABAJADOR_ID: 
			currentIGUI = new VBuscarTrabajador((TTrabajador) contexto.getDatos());
			return currentIGUI; 
			
		case Eventos.BUSCAR_TRABAJADOR_DNI: 
			currentIGUI = new VBuscarTrabajador((TTrabajador) contexto.getDatos());
			return currentIGUI;
			
		case Eventos.MODIFICAR_TRABAJADOR: 
			currentIGUI = new VModificarTrabajador((TTrabajador) contexto.getDatos());
			return currentIGUI;
			
		// VISTAS PROVEEDOR

		case Eventos.CREAR_VPROVEEDOR:
			currentIGUI = new VProveedor((List<TProveedor>) contexto.getDatos());
			return currentIGUI; 
			
		case Eventos.MODIFICAR_PROVEEDOR: 
			currentIGUI = new VModificarProveedor((TProveedor) contexto.getDatos());
			return currentIGUI;
			
		case Eventos.BUSCAR_PROVEEDOR:
			currentIGUI = new VBuscarProveedor((TProveedor) contexto.getDatos());
			return currentIGUI;
			
		case Eventos.ALTA_PROVEEDOR:
			currentIGUI = new VAltaProveedor();
			return currentIGUI;

		//VISTAS PRODUCTO
			
		case Eventos.ALTA_PRODUCTO: 
			currentIGUI = new VAltaProducto();
			return currentIGUI;
			
		case Eventos.MODIFICAR_PRODUCTO:  
			currentIGUI = new VModificarPr((TProducto) contexto.getDatos());
			return currentIGUI;
			
		case Eventos.CREAR_VPRODUCTO:  
			currentIGUI = new VProducto((List<TProducto>) contexto.getDatos());
			return currentIGUI;
			
		case Eventos.BUSCAR_PRODUCTO: 
			currentIGUI = new VBuscarProducto((TProducto) contexto.getDatos());
			return currentIGUI;
		case Eventos.VINCULAR_PRODUCTO_PROVEEDOR: 
			currentIGUI = new VvincularPrProv((int) contexto.getDatos());
			return currentIGUI;
		case Eventos.MOSTRAR_VINCULADOS: 
			currentIGUI = new VMostrarVinculados((List<TDistribuye>) contexto.getDatos());
			return currentIGUI;
		 
	 	//VISTAS COMPRA
		  
		case Eventos.CREAR_VCOMPRA:
			currentIGUI = new VBuscarTodosCompra((List<TCompra>) contexto.getDatos());
			return currentIGUI;
		
		case Eventos.BUSCAR_COMPRA_ID:
			currentIGUI = new VBuscarCompra((TCompraConProductos) contexto.getDatos());
			return currentIGUI;
			
		case Eventos.ABRIR_COMPRA: 
			currentIGUI = new VAbrirCompra((TCarrito) contexto.getDatos());
			return currentIGUI;
			
		case Eventos.CREAR_V_DEVOLUCION: 
			currentIGUI = new VDevolucionCompra((TCompraConProductos) contexto.getDatos());
			return currentIGUI;
			
		  case Eventos.CREAR_V_PAGAR_COMPRA:
			  currentIGUI = new VCerrarCompra((TCarrito) contexto.getDatos());
			  return currentIGUI;
			  
		  case Eventos.VALIDAR_ID_CLIENTE:
			  currentIGUI = new VValidacionCompra((TCarrito) contexto.getDatos());
			  return currentIGUI;
		
		  //VISTAS SECCION
		  
		  case Eventos.VALTA_SECCION: 
			  currentIGUI = new VAltaSeccion();
				return currentIGUI;
		 
		  case Eventos.VBAJA_SECCION: 
			  currentIGUI = new VBajaSeccion((TSeccion) contexto.getDatos());
				return currentIGUI;
		  
		  case Eventos.VMODIFICAR_SECCION: 
			  currentIGUI = new VModificarSeccion((TSeccion) contexto.getDatos());
			  return currentIGUI;
		  
		  case Eventos.VBUSCAR_SECCION:
			  currentIGUI = new VBuscarSeccion((TSeccion) contexto.getDatos());
				return currentIGUI;
		  
		  case Eventos.CREAR_VSECCION:
			  currentIGUI = new VSeccion((List<TSeccion>) contexto.getDatos());
				return currentIGUI;
		  
		}
		return null;
	}

	@Override
	public IGUI getCurrentView() {
		return currentIGUI;
	}
}