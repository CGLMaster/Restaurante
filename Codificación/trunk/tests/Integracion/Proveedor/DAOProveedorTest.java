package Integracion.Proveedor;


import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Integración.Proveedor.DAOProveedorImp;
import Negocio.Proveedor.TProveedor;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DAOProveedorTest {
	
	@BeforeClass
	public static void BeforeClass(){
		
		try {
			
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.nuevaTransaccion();
			transaccion.start();
			Connection connection = (Connection) transaccion.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("INSERT INTO PROVEEDOR(NOMBRE, ACTIVO) VALUES('" + "ProveedorParaTests" + "', " + +1 + ")");
			transaccion.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	


	@Test
	public void a_altaProveedorDevuelveId() {
		
		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.nuevaTransaccion();
			transaccion.start();
			DAOProveedorImp dao = new DAOProveedorImp();
			
			int id = dao.altaProveedor("asasdsaddf");
			transaccion.commit();
			
			
			assertTrue(id != -1);
			
			
			TProveedor proveedor = dao.buscarProveedor(id);
			assertEquals("asasdsaddf", proveedor.getNombre());
			
			System.out.println(proveedor.getNombre());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void b_modificarProveedor() {
		
		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.nuevaTransaccion();
			transaccion.start();
			DAOProveedorImp dao = new DAOProveedorImp();
			
			int id = dao.buscarProveedorPorNombre("asasdsaddf").getID();
			
			TProveedor proveedorDatosNuevos = new TProveedor(id, "Holaasdasdads");
			
			int res = dao.modificarProveedor(proveedorDatosNuevos);
			transaccion.commit();
			
			assertTrue(res != -1);
			
			TProveedor proveedor = dao.buscarProveedor(id);
			assertEquals("Holaasdasdads", proveedor.getNombre());
			
			System.out.println(proveedor.getNombre());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void c_bajaProveedor() {
		
		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.nuevaTransaccion();
			transaccion.start();
			DAOProveedorImp dao = new DAOProveedorImp();
			
			int id = dao.buscarProveedorPorNombre("Holaasdasdads").getID();
			
			int res = dao.eliminarProveedor(id);
			transaccion.commit();
			
			assertTrue(res != -1);
			
			TProveedor proveedor = dao.buscarProveedor(id);
			assertTrue(proveedor.getActivo() == false);
			
			System.out.println(proveedor.getNombre());
			
			
			Transaction transaccion2 = transactionManager.nuevaTransaccion();
			transaccion2.start();
			Connection connection = (Connection) transaccion2.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM PROVEEDOR WHERE ID_PROVEEDOR=" + id);
			transaccion2.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void buscarExistente() {
		
		DAOProveedorImp dao = new DAOProveedorImp();
		
		TProveedor proveedor = dao.buscarProveedor(1);

		assertNotNull(proveedor);
	}
	
	@Test
	public void buscarNoExistente_devuelveNull() {
		
		DAOProveedorImp dao = new DAOProveedorImp();
		
		TProveedor proveedor = dao.buscarProveedor(45655);

		assertNull(proveedor);
	}
	
	@Test
	public void buscarPorNombre() {
		
		DAOProveedorImp dao = new DAOProveedorImp();
		
		TProveedor proveedor = dao.buscarProveedorPorNombre("ProveedorParaTests");

		assertNotNull(proveedor);
	}
	
	@Test
	public void buscarPorNombreNoExistente_devuelveNull() {
		
		DAOProveedorImp dao = new DAOProveedorImp();
		
		TProveedor proveedor = dao.buscarProveedorPorNombre("qwqweqwerrttssdffsd");

		assertNull(proveedor);
	}
	
	@Test
	public void buscarTodos() {
		
		DAOProveedorImp dao = new DAOProveedorImp();
		
		List<TProveedor> proveedores = dao.buscarTodosProveedor();

		assertNotNull(proveedores);
		assertTrue(proveedores.size() != 0);
	}
	
	
	@AfterClass
	public static void AfterClass(){
		
		try {

			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.nuevaTransaccion();
			transaccion.start();
			Connection connection = (Connection) transaccion.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM PROVEEDOR WHERE NOMBRE = 'ProveedorParaTests'");
			transaccion.commit();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
