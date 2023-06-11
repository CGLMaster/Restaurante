package Negocio.Compra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Integración.Cliente.DAOCliente;
import Integración.Compra.DAOCompra;
import Integración.Compra.DAOProductoCompra;
import Integración.FactoriaIntegracion.FactoriaIntegracion;
import Integración.FactoriaQueries.FactoriaQueries;
import Integración.Producto.DAOProducto;
import Integración.Queries.Query;
import Integración.Trabajador.DAOTrabajador;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TClienteNoPremium;
import Negocio.Cliente.TClientePremium;
import Negocio.Producto.TProducto;
import Negocio.Trabajador.TTrabajador;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;
import Presentación.Controller.Eventos;

public class SACompraImp implements SACompra {
	
	DAOCompra daoCompra;
	
	public SACompraImp(){
		daoCompra = FactoriaIntegracion.getInstance().getDAOCompra();
	}
	
	public TCarrito abrirCompra(int id_trabajador) {
		TCarrito carrito = new TCarrito();
		carrito.getCompra().setIdTrabajador(id_trabajador);
		carrito.getCompra().setIdCliente(-1);
		return carrito;
	}

	public int cerrarCompra(TCarrito carrito) {
		int idTrabajador = carrito.getCompra().getIdTrabajador();
		int idCliente =carrito.getCompra().getIdCliente();
		TCompra compra = carrito.getCompra();
		
		DAOProductoCompra daoProductoCompra = FactoriaIntegracion.getInstance().getDAOProductoCompra();
		DAOCliente daoClientes = FactoriaIntegracion.getInstance().getDAOCliente();
	    DAOTrabajador daoTrabajadores = FactoriaIntegracion.getInstance().getDAOTrabajador();
	    DAOProducto daoProductos = FactoriaIntegracion.getInstance().getDAOProducto();
	    
	    
		try{
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.nuevaTransaccion();
			transaccion.start();
			
			compra.setFecha(LocalDate.now());
			
		    TCliente cliente = daoClientes.buscarUnCliente(idCliente);
		    if(cliente == null || !cliente.getEsActivo()){
		    	transaccion.rollback();
		    	return -1;
		    }
		    else{
		    	if(cliente instanceof TClienteNoPremium){
		    		TClienteNoPremium aux=(TClienteNoPremium) daoClientes.buscarUnCliente(cliente.getIDSocio());
		    		aux.setNumCompras(aux.getNumCompras() +1 );
		    		daoClientes.modificarCliente(aux);
		    	}
		    }
		    	

		    if(idTrabajador != -1) {
		    	TTrabajador trabajador = daoTrabajadores.buscarDatosTrabajadorID(idTrabajador);
			    if(trabajador == null || !trabajador.getActivo()){ 
			    	transaccion.rollback();
			    	return -1;
			    }
		    }
		    
		    int res = validarCarrito(carrito);
		    
		    if(res != 0){
		    	transaccion.rollback();
		    	return res;
		    }


		    int idCompra = daoCompra.cerrarCompra(compra);
		    
		    List<TLineaDeCompra> lineaDeCompra = carrito.getLineaDeCompra();
		    for(TLineaDeCompra linea : lineaDeCompra){
		    	
		    	linea.setID_Compra(idCompra);

		        daoProductoCompra.aniadirProductoCompra(linea);

		        daoProductos.reducirStock(linea.getIDProducto(), linea.getCantidad());
		    }
		    
		    transaccion.commit();
		    return Eventos.RES_PAGAR_COMPRA_OK;
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}


	}
	

	public int aniadirProducto(TLineaDeCompra lineaDeCompra, TCarrito carrito) {
		
		if(lineaDeCompra.getCantidad() > 0){
			
			boolean producto_ya_esta_en_carrito = false;
			int i_lineaDeCompra = 0;
			
			for(int i = 0; i < carrito.getLineaDeCompra().size(); i++){
				if(carrito.getLineaDeCompra().get(i).getIDProducto() == lineaDeCompra.getIDProducto()){
					producto_ya_esta_en_carrito = true;
					i_lineaDeCompra = i;
				}
			}
			
			if(producto_ya_esta_en_carrito){
				int nuevaCantidad = carrito.getLineaDeCompra().get(i_lineaDeCompra).getCantidad() + lineaDeCompra.getCantidad();
				carrito.getLineaDeCompra().get(i_lineaDeCompra).setCantidad(nuevaCantidad);
			}
			else{
				carrito.getLineaDeCompra().add(lineaDeCompra);
			}
			
			return 1;
		}
		else{
			return -1;
		}
		
	}

	public int eliminarProducto(TLineaDeCompra lineaDeCompra, TCarrito carrito) {
		
			int i_producto = 0;
			boolean producto_esta_en_carrito = false;
			
			for(int i = 0; i < carrito.getLineaDeCompra().size(); i++){
				if(carrito.getLineaDeCompra().get(i).getIDProducto() == lineaDeCompra.getIDProducto()){
					producto_esta_en_carrito = true;
					i_producto = i;
				}
			}
			
			if(producto_esta_en_carrito){
				carrito.getLineaDeCompra().remove(i_producto);
				return 1;
			}
			else{
				return -1;
			}

	}

	public TCompraConProductos buscarPorIDCompra(int id_compra) {
		TCompra compra = daoCompra.buscarCompra(id_compra);
		if(compra==null)
			return null;
		
		List<TLineaDeCompra> lineasDeCompra = FactoriaIntegracion.getInstance().getDAOProductoCompra().leerTodasLineas(id_compra);
		List<TProducto> listaProductos = new ArrayList<TProducto>();
		
		for (TLineaDeCompra lineaDeCompra : lineasDeCompra) {
			listaProductos.add(FactoriaIntegracion.getInstance().getDAOProducto().buscarProducto(lineaDeCompra.getIDProducto()));
		}
		
		return new TCompraConProductos(listaProductos, compra, lineasDeCompra);
	}

	public List<TCompra> buscarTodosCompra() {
		return daoCompra.buscarTodasCompras();
	}

	public int devolverProductos(List<TLineaDeCompra> prods_devolucion) {		
		int id_compra = prods_devolucion.get(0).getID_Compra();
		int res = -1;
		
		try{
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.nuevaTransaccion();
			transaccion.start();
			
			TCompra compra = FactoriaIntegracion.getInstance().getDAOCompra().buscarCompra(id_compra);
			boolean ok = false;
			if(compra != null){
				ok = true;
				float nuevo_precio = compra.getPrecioTotal();
				int i = 0;
				while(ok && i < prods_devolucion.size()){
					TLineaDeCompra toDev = prods_devolucion.get(i);
					TLineaDeCompra preDev = FactoriaIntegracion.getInstance().getDAOProductoCompra().leerLinea(id_compra, toDev.getIDProducto());
					if(preDev != null){
						if(toDev.getCantidad()==preDev.getCantidad()){
							ok = FactoriaIntegracion.getInstance().getDAOProductoCompra().eliminarProductoCompra(id_compra, toDev.getIDProducto()) >= 0;
							nuevo_precio -= preDev.getCantidad()*preDev.getPrecio();
						}else if(toDev.getCantidad() < preDev.getCantidad()){
							nuevo_precio -= toDev.getCantidad()*preDev.getPrecio();
							int nuevaCantida = preDev.getCantidad()-toDev.getCantidad();
							System.out.println(nuevaCantida);
							toDev.setCantidad(nuevaCantida);
							ok = FactoriaIntegracion.getInstance().getDAOProductoCompra().modificarProductoCompra(toDev) >= 0;
						}
					}else{
						ok = false;
					}
					i++;
				}
				if(ok){
					compra.setPrecioTotal(nuevo_precio);
					res = FactoriaIntegracion.getInstance().getDAOCompra().realizarDevolucion(compra);
					if(res > 0){
						transaccion.commit();
					}else{
						ok = false;
					}
				}
			}
			if(!ok){
				transaccion.rollback();
				res = -1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int validarCarrito(TCarrito carrito){
		List<TLineaDeCompra> productosCantidad = carrito.getLineaDeCompra();
		float precioTotal = 0;
		DAOProducto daoProductos = FactoriaIntegracion.getInstance().getDAOProducto();
        DAOCliente daoClientes = FactoriaIntegracion.getInstance().getDAOCliente();
	    
		float multiplicador=1;
		
		if(carrito.getCompra().getIdCliente() != -1){
	    	TCliente cliente = daoClientes.buscarUnCliente(carrito.getCompra().getIdCliente());
	    	if(cliente == null || !cliente.getEsActivo()){
		    	return -1;
		    }
	    	else if(cliente instanceof TClientePremium){
	    		float total =carrito.getCompra().getPrecioTotal();
	    		multiplicador=(float) 0.8;
	    	}
	    }
	    for(TLineaDeCompra productoCantidad : productosCantidad){
	        TProducto producto = daoProductos.buscarProducto(productoCantidad.getIDProducto());

	        if(producto == null || !producto.getActivo()) 
	        	return -1;

	        if(productoCantidad.getCantidad() > producto.getStock()) 
	        	return -1;

	        producto.setPrecio(multiplicador*producto.getPrecio());
	        precioTotal += producto.getPrecio() * productoCantidad.getCantidad();
	        
	        productoCantidad.setPrecio(producto.getPrecio());
	    }
	    
	    carrito.getCompra().setPrecioTotal(precioTotal);
	    
		return 0;
	}

	@Override
	public List<TCompra> buscarComprasPorRangoPrecioFecha(int desde, int hasta, LocalDate desdeFecha,
			LocalDate hastaFecha) {
		try {
			if (desde >= 0 && desde < hasta && comprobacionFechaValida(desdeFecha,hastaFecha)) {
				Query q = FactoriaQueries.getInstance().getNewQuery("ComprasPorRangoFechaPrecio");
				HashMap<String, Object> args = new HashMap<String, Object>();
				args.put("desdePrecio", desde);
				args.put("hastaPrecio", hasta);
				args.put("desdeFecha", desdeFecha);
				args.put("hastaFecha", hastaFecha);
				return (List<TCompra>) q.Execute(args);
			}
			throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean comprobacionFechaValida(LocalDate desdeFecha, LocalDate hastaFecha) {
	int diaDesde = desdeFecha.getDayOfYear();
	int diaHasta = hastaFecha.getDayOfYear();
	return diaDesde > 0 && diaHasta < 366;
	}

	

}