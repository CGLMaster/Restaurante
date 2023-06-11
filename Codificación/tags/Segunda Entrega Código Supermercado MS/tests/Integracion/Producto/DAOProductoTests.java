package Integracion.Producto;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import Integración.FactoriaIntegracion.FactoriaIntegracion;
import Integración.Producto.DAOProductoImp;
import Negocio.Producto.TProdNoPerecedero;
import Negocio.Producto.TProdPerecedero;
import Negocio.Producto.TProducto;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;



public class DAOProductoTests {
	private static DAOProductoImp daoProductos;
	static int id;

	@BeforeClass
	public static void BeforeClass() {
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			daoProductos = (DAOProductoImp) FactoriaIntegracion.getInstance().getDAOProducto();
			id = daoProductos.altaProducto(new TProdNoPerecedero("PruebaIntProdNoPer", 3, 2, 3, 5, true, "Plastificados"));
			
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			daoProductos = (DAOProductoImp) FactoriaIntegracion.getInstance().getDAOProducto();
			
			LocalDate date_cadud = null;
			String dateNums[] = {"2020", "10", "12"};
			int year = Integer.parseInt(dateNums[0]);
			int month = Integer.parseInt(dateNums[1]);
			int day = Integer.parseInt(dateNums[2]);
			date_cadud = LocalDate.of(year, month, day);
			id = daoProductos.altaProducto(new TProdPerecedero("PruebaIntProdPer", 3, 2, 3, 5, true, date_cadud));
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pruebaBuscaBien() {
		TProducto elem = daoProductos.buscarProducto(10);
		assertNotNull(elem);
		assertTrue(elem.getActivo());
		
		assertEquals(elem.getNombre(), "PruebaIntProdNoPer");
	}

	@Test
	public void pruebaBuscaMal() {
		assertNull(daoProductos.buscarProducto(-1));
	}
	
}