package Negocio.ClienteRest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.ClienteRest.SAClienteRestImp;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SAClienteRestTest {
	static SAClienteRestImp clientes;
	static int id;
	static int idModifica;

	@BeforeClass
	public static void BeforeClass() {
		clientes = new SAClienteRestImp();
	}

	@Test
	public void A_crearClienteRestOK() {
		TClienteRest ClienteRest = new TClienteRest("Jose Luis" , "Bartha" , "45384057Z",true);

		id = clientes.altaClienteRest(ClienteRest);
		assertNotEquals(-1, id);
	}

	@Test
	public void B_CrearYaExistente() {

		TClienteRest ClienteRest = new TClienteRest("Jose Luis" , "Bartha" , "45384057Z",true);

		assertEquals(-1, clientes.altaClienteRest(ClienteRest));
	}

	@Test
	public void C_LeerActivo() {
		TClienteRest ClienteRest = clientes.buscarUnClienteRest(id);

		assertTrue(ClienteRest.getActivo());
	}

	@Test
	public void D_EliminarActivo() {
		assertEquals(1, clientes.bajaClienteRest(id));
	}

	@Test
	public void E_EliminarNoActivo() {
		assertEquals(-1, clientes.bajaClienteRest(id));
	}

	@Test
	public void F_LeerInActivo() {
		TClienteRest ClienteRest = clientes.buscarUnClienteRest(id);

		assertFalse(ClienteRest.getActivo());
	}
	
	@Test
	public void G_pruebaModificarBien() {
		TClienteRest elem = clientes.buscarUnClienteRest(id);
		elem.setNombre("Rodolfo");
		elem.setApellidos("Ramoncin");
		elem.setDni("45334521Z");
		assertNotEquals(-1, clientes.modificarClienteRest(elem));
	}

	@Test
	public void H_pruebaModificarMal() {
		TClienteRest elem = new TClienteRest("Josefas", "Mesas","12345678F",true);
		idModifica = clientes.altaClienteRest(elem);
		TClienteRest elem2 = new TClienteRest();
		elem2.setID(id);
		elem2.setNombre("Mariloli");
		elem2.setApellidos("Rodolfox");
		elem2.setDni("12345678F"); //Falla por dni repetido
		assertEquals(-1, clientes.modificarClienteRest(elem));
	}
	
	@Test
	public void I_pruebaBuscaBien() {
		TClienteRest elem = clientes.buscarUnClienteRest(id);
		assertNotNull(elem);
		assertEquals(elem.getID(), id);
		assertEquals(elem.getNombre(), "Rodolfo"); 
		assertEquals(elem.getApellidos(), "Ramoncin");
		assertEquals(elem.getDni(), "45334521Z"); 
		
	}

	@Test
	public void J_pruebaBuscaMal() {
		assertNull(clientes.buscarUnClienteRest(-1));
	}
	
	@org.junit.AfterClass
	public static void AfterClass(){
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		ClienteRest c = em.find(ClienteRest.class, id);
		ClienteRest c2 = em.find(ClienteRest.class, idModifica);
		em.remove(c);
		em.remove(c2);
		et.commit();
		em.close();
		
	}
	
}
