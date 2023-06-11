package Integracion.Cliente;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Integración.Cliente.DAOClienteImp;
import Integración.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TClienteNoPremium;
import Negocio.Cliente.TClientePremium;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DAOClienteTests {
	static DAOClienteImp clientes;
	static int idNp, idP;
	static TCliente clienteNp, clienteP;
	
	@BeforeClass
	public static void BeforeClass() {
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			clientes = (DAOClienteImp) FactoriaIntegracion.getInstance().getDAOCliente();
			TClienteNoPremium cNp = new TClienteNoPremium();
			cNp.setDNI("82754281F");
			cNp.setMail("prueba716111@gmail.com");
			cNp.setNombre("Prueba12873");
			clienteNp = cNp;
			idNp = clientes.altaCliente(clienteNp);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			TClientePremium cP =  new TClientePremium();
			cP.setDNI("81726590F");
			cP.setMail("prueba816111@gmail.com");
			cP.setNombre("Prueba912873");
			LocalDate date_cadud = null;
			String dateNums[] = {"2020", "10", "12"};
			int year = Integer.parseInt(dateNums[0]);
			int month = Integer.parseInt(dateNums[1]);
			int day = Integer.parseInt(dateNums[2]);
			date_cadud = LocalDate.of(year, month, day);
			cP.setAntiguedad(date_cadud);
			cP.setTelefono("827162332");
			cP.setDireccion("calle calle calle");
			clienteP = cP;
			idP = clientes.altaCliente(clienteP);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void A_pruebaBuscaBienPremium() {
		TCliente elem = clientes.buscarUnCliente(idP);
		assertNotNull(elem);
		assertEquals(elem.getDNI(), "81726590F");
		assertEquals(elem.getMail(), "prueba816111@gmail.com");
		assertEquals(elem.getNombre(), "Prueba912873");
	}

	@Test
	public void B_pruebaBuscaBienNoPremium() {
		TCliente elem = clientes.buscarUnCliente(idNp);
		assertNotNull(elem);
		assertEquals(elem.getDNI(), "82754281F");
		assertEquals(elem.getMail(), "prueba716111@gmail.com");
		assertEquals(elem.getNombre(), "Prueba12873");
	}
	
	@Test
	public void C_pruebaBuscaMal() {
		assertNull(clientes.buscarUnCliente(-1));
	}
	
	@Test
	public void D_LeerActivo() {
		TCliente cl = clientes.buscarUnCliente(idP);
		assertTrue(cl.getEsActivo());
	}
	
	@Test
	public void E_BajaCliente() {
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			assertEquals(1, clientes.bajaCliente(idNp));
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void F_LeerInactivo() {
			TCliente cl = clientes.buscarUnCliente(idNp);
			assertFalse(cl.getEsActivo());
	}
	
	@Test
	public void G_BuscarTodos() {
		List<TCliente> client = clientes.buscarTodosCliente();
		assertNotNull(client);
	}
	
	@Test
	public void H_BorrarClienteNoPremium(){
		
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			Connection connection = (Connection) t.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM CLIENTE_NO_PREMIUM WHERE ID_SOCIO = " + idNp);
			Statement s2 = connection.createStatement();
			s2.executeUpdate("DELETE FROM CLIENTES WHERE ID_SOCIO = " + idNp);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void I_BorrarClientePremium(){
		
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			Connection connection = (Connection) t.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM CLIENTE_PREMIUM WHERE ID_SOCIO = " + idP);
			Statement s2 = connection.createStatement();
			s2.executeUpdate("DELETE FROM CLIENTES WHERE ID_SOCIO = " + idP);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}