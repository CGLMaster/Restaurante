package Negocio.Cliente;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SAClientesTest {
	static SAClienteImp clientes;
	static int idNp, idP;
	
	@BeforeClass
	public static void BeforeClass() {
		clientes = new SAClienteImp();
	}
	
	@Test
	public void aCrearClienteOKNoPremium(){
		TClienteNoPremium clienteNp = new TClienteNoPremium();
		clienteNp.setDNI("12345679A");
		clienteNp.setMail("prueba6@gmail.com");
		clienteNp.setNombre("Prueba");
		
		idNp = clientes.altaCliente(clienteNp);
		assertNotEquals(-1, idNp);
	}
	
	@Test
	public void bCrearYaExistenteNoPremium(){
		TClienteNoPremium clienteNp = new TClienteNoPremium();
		clienteNp.setDNI("12345679A");
		clienteNp.setMail("prueba6@gmail.com");
		clienteNp.setNombre("Prueba2");
		
		assertEquals(-1, clientes.altaCliente(clienteNp));
	}
	
	@Test
	public void aCrearClienteOKPremium(){
		TClientePremium clienteP = new TClientePremium();
		clienteP.setDNI("12345679C");
		clienteP.setMail("prueba99@gmail.com");
		clienteP.setNombre("Prueba");
		LocalDate date_cadud = null;
		String dateNums[] = {"2020", "10", "12"};
		int year = Integer.parseInt(dateNums[0]);
		int month = Integer.parseInt(dateNums[1]);
		int day = Integer.parseInt(dateNums[2]);
		date_cadud = LocalDate.of(year, month, day);
		clienteP.setAntiguedad(date_cadud);
		clienteP.setDireccion("Gran via");
		clienteP.setTelefono("12345678");
		
		idP = clientes.altaCliente(clienteP);
		assertNotEquals(-1, idP);
	}
	
	@Test
	public void bCrearYaExistentePremium(){
		TClientePremium clienteP = new TClientePremium();
		clienteP.setDNI("12345679C");
		clienteP.setMail("prueba99@gmai");
		clienteP.setNombre("Prue");
		LocalDate date_cadud = null;
		String dateNums[] = {"2020", "05", "12"};
		int year = Integer.parseInt(dateNums[0]);
		int month = Integer.parseInt(dateNums[1]);
		int day = Integer.parseInt(dateNums[2]);
		date_cadud = LocalDate.of(year, month, day);
		clienteP.setAntiguedad(date_cadud);
		clienteP.setDireccion("Gran via 2");
		clienteP.setTelefono("12345");
		
		assertEquals(-1, clientes.altaCliente(clienteP));
	}
	
	@Test
	public void cLeerActivoNoPremium(){
		TCliente cl = clientes.buscarUnoCliente(idNp);
		
		assertTrue(cl.getEsActivo());
	}
	
	@Test
	public void cLeerActivoPremium(){
		TCliente cl = clientes.buscarUnoCliente(idP);
		
		assertTrue(cl.getEsActivo());
	}
	
	@Test
	public void dModificarOKNoPremium(){
		TCliente cliente = clientes.buscarUnoCliente(idNp);
		cliente.setMail("nuevoMail@ucm.com");
		
		assertEquals(1, clientes.modificarCliente(cliente));
	}
	
	@Test
	public void dModificarOKPremium(){
		TClientePremium cliente = (TClientePremium)clientes.buscarUnoCliente(idP);
		cliente.setTelefono("98756");
		cliente.setDNI("75387418T");
		assertEquals(1, clientes.modificarCliente(cliente));
	}
	
	@Test
	public void eModificarKONoPremium(){
		TCliente cliente = clientes.buscarUnoCliente(idNp);
		cliente.setDNI("12345678W"); 
		
		assertEquals(-1, clientes.modificarCliente(cliente));
	}
	
	@Test
	public void eModificarKOPremium(){
		TCliente cliente = clientes.buscarUnoCliente(idP);
		cliente.setDNI("1234");
		assertEquals(-1, clientes.modificarCliente(cliente));
	}
	
	@Test
	public void fEliminarActivo(){
		assertEquals(1, clientes.bajaCliente(idNp));
	}
	
	@Test
	public void gEliminarNoActivo(){
		assertEquals(-1, clientes.bajaCliente(idNp)); 
	}
		
	@Test
	public void hLeerInActivo(){
		TCliente cl = clientes.buscarUnoCliente(idNp);
		
		assertFalse(cl.getEsActivo());
	}
}
