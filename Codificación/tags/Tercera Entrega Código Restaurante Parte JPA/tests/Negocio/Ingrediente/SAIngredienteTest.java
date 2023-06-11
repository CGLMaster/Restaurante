package Negocio.Ingrediente;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SAIngredienteTest {

	static SAIngrediente sa;
	static int idAniadido;
	static int idAniadido2;

	@BeforeClass
	public static void BeforeClass() {
		sa = new SAIngredienteImp();
	}

	@Test
	public void A_aniadirConNombreNoExistente() {
		TIngrediente ingredienteNuevo = new TIngrediente("PruebaIngrediente1", 100, true);

		idAniadido = sa.aniadirIngrediente(ingredienteNuevo);

		EntityManager entityManager = FactoriaEntityManager.getInstance().generarEntityManager();

		Ingrediente ingredienteBuscado = entityManager.find(Ingrediente.class, idAniadido);

		assertEquals("PruebaIngrediente1", ingredienteBuscado.getNombre());
		entityManager.close();
	}

	@Test
	public void B_aniadirConNombreQueYaExiste() {
		TIngrediente ingredienteNuevo = new TIngrediente("PruebaIngrediente1", 100, true);
		int res = sa.aniadirIngrediente(ingredienteNuevo);

		assertEquals(-1, res);
	}

	@Test
	public void B_buscarTodos() {
		List<TIngrediente> ingredientes = sa.buscarTodosIngredientes();

		assertTrue(ingredientes.size() > 0);
	}

	@Test
	public void C_buscarNoExistente() {
		TIngrediente tIngrediente = sa.buscarIngrediente(123456);

		assertNull(tIngrediente);
	}

	@Test
	public void C_buscarExistente() {
		TIngrediente tIngrediente = sa.buscarIngrediente(idAniadido);
		assertEquals("PruebaIngrediente1", tIngrediente.getNombre());
	}

	@Test
	public void D_editarExistenteConNombreNoExistente() {
		TIngrediente ingredienteEditado = new TIngrediente(idAniadido, "PruebaIngrediente2", 200, true);
		sa.editarIngrediente(ingredienteEditado);

		EntityManager entityManager = FactoriaEntityManager.getInstance().generarEntityManager();

		Ingrediente ingredienteBuscado = entityManager.find(Ingrediente.class, idAniadido);

		assertEquals("PruebaIngrediente2", ingredienteBuscado.getNombre());
		entityManager.close();
	}

	@Test
	public void D_editarNoExistente() {
		TIngrediente ingredienteEditado = new TIngrediente(123456, "PruebaIngrediente2", 200, true);
		int res = sa.editarIngrediente(ingredienteEditado);

		assertEquals(-1, res);
	}

	@Test
	public void D_editarExistenteConNombreQueYaExiste() {

		TIngrediente tNuevoIngrediente = new TIngrediente("PruebaIngrediente3", 200, true);
		idAniadido2 = sa.aniadirIngrediente(tNuevoIngrediente);

		// Intentamos editar un ingrediente con ese mismo nombre
		TIngrediente ingredienteEditado = new TIngrediente(idAniadido, "PruebaIngrediente3", 200, true);
		int res = sa.editarIngrediente(ingredienteEditado);

		assertEquals(-1, res);
		
	}

	@Test
	public void E_eliminarNoExistente() {
		int res = sa.eliminarIngrediente(123456);

		assertEquals(-1, res);
	}

	@Test
	public void E_eliminarExistente() {
		sa.eliminarIngrediente(idAniadido);

		EntityManager entityManager = FactoriaEntityManager.getInstance().generarEntityManager();

		Ingrediente ingredienteBuscado = entityManager.find(Ingrediente.class, idAniadido);

		assertEquals(false, ingredienteBuscado.isActivo());
		entityManager.close();
	}

	@org.junit.AfterClass
	public static void AfterClass() {
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Ingrediente ingrediente = em.find(Ingrediente.class, idAniadido);
		Ingrediente i2 = em.find(Ingrediente.class, idAniadido2);

		em.remove(ingrediente);
		em.remove(i2);
		et.commit();
		em.close();

	}

}
