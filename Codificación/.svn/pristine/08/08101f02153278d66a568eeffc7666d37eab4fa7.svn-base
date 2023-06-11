package Negocio.Seccion;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SASeccionTests {
	static SASeccionImp seciones;
	static int id;

	@BeforeClass
	public static void BeforeClass() {
		seciones = new SASeccionImp();
	}

	@Test
	public void A_crearSeccionOK() {
		TSeccion seccion = new TSeccion(2, "PruebaSeccionEdu");

		id = seciones.altaSeccion(seccion);
		assertNotEquals(-1, id);
	}

	@Test
	public void B_CrearYaExistente() {
		TSeccion seccion = new TSeccion(2, "PruebaSeccionEdu");

		assertEquals(-1, seciones.altaSeccion(seccion));
	}

	@Test
	public void C_LeerActivo() {
		TSeccion seccion = seciones.buscarSeccion(id);

		assertTrue(seccion.getActivo());
	}

	@Test
	public void D_EliminarActivo() {
		assertEquals(1, seciones.eliminarSeccion(id));
	}

	@Test
	public void E_EliminarNoActivo() {
		assertEquals(-1, seciones.eliminarSeccion(id));
	}

	@Test
	public void F_LeerInActivo() {
		TSeccion seccion = seciones.buscarSeccion(id);

		assertFalse(seccion.getActivo());
	}
	
	@Test
	public void G_pruebaModificarBien() {
		TSeccion elem = seciones.buscarSeccion(id);
		elem.setZona("PruebaModifi");
		elem.setPasillo(4);
		assertNotEquals(-1, seciones.actualizarSeccion(elem));
	}

	@Test
	public void H_pruebaModificarMal() {
		TSeccion elem = seciones.buscarSeccion(id);
		elem.setZona("hola");
		elem.setPasillo(1);
		assertNotEquals(1, seciones.actualizarSeccion(elem));
	}
	
	@Test
	public void I_pruebaBuscaBien() {
		TSeccion elem = seciones.buscarSeccion(id);
		assertNotNull(elem);
		assertEquals(elem.getID_Seccion(), id);
		assertEquals(elem.getPasillo(), 4); 
		assertEquals(elem.getZona(), "PruebaModifi");
	}

	@Test
	public void J_pruebaBuscaMal() {
		assertNull(seciones.buscarSeccion(69));
	}
}

