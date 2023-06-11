package Negocio.Marca;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SAMarcasTest {
	static SAMarcaImp marcas;
	static int id;
	
	@BeforeClass
	public static void BeforeClass() {
		marcas = new SAMarcaImp();
	}
	
	@Test
	public void A_crearMarcaOK() {
		TMarca marca = new TMarca("PruebaMarcaNeg");

		id = marcas.altaMarca(marca);
		assertNotEquals(-1, id);
	}

	@Test
	public void B_CrearYaExistente() {
		TMarca marca = new TMarca("PruebaMarcaNeg");

		assertEquals(-1, marcas.altaMarca(marca));
		
	}

	@Test
	public void C_LeerActivo() {
		TMarca marca = marcas.buscarMarca(id);

		assertTrue(marca.getActivo());
	}

	@Test
	public void D_EliminarActivo() {
		assertEquals(1, marcas.eliminarMarca(id));
	}

	@Test
	public void E_EliminarNoActivo() {
		assertEquals(-1, marcas.eliminarMarca(id));
	}

	@Test
	public void F_LeerInActivo() {
		TMarca marca = marcas.buscarMarca(id);

		assertFalse(marca.getActivo());
	}
	
	@Test
	public void G_pruebaModificarBien() {
		TMarca elem = marcas.buscarMarca(id);
		elem.setNombre("PruebaModifinom");
		elem.setWeb("PruebaModifiweb");
		assertNotEquals(-1, marcas.modificarMarca(elem));
	}

	@Test
	public void H_pruebaModificarMal() {
		TMarca elem = marcas.buscarMarca(id);
		elem.setNombre("Hacendado");
		elem.setWeb("hola");
		assertNotEquals(1, marcas.modificarMarca(elem));
	}
	
	@Test
	public void I_pruebaBuscaBien() {
		TMarca elem = marcas.buscarMarca(id);
		assertNotNull(elem);
		
		assertEquals(elem.getID(), id);
		assertEquals(elem.getNombre(), "PruebaModifinom"); 
		
	}

	@Test
	public void J_pruebaBuscaMal() {
		assertNull(marcas.buscarMarca(69));
	}
}
