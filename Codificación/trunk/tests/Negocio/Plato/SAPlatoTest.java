package Negocio.Plato;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Ingrediente.Ingrediente;
import Negocio.Ingrediente.SAIngredienteImp;
import Negocio.Ingrediente.TIngrediente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SAPlatoTest {

	public static SAPlatoImp saPlato;
	public static SAIngredienteImp saIngrediente;
	public static int idComida, idBebida, idIngrediente, idBebMala, idComMala;

	@BeforeClass
	public static void BeforeClass() {
		saPlato = new SAPlatoImp();
		saIngrediente = new SAIngredienteImp();
		idIngrediente = saIngrediente.aniadirIngrediente(new TIngrediente("IngredientePruebaPLato", (float) 1.1, true));
		idComMala = saPlato.altaPlato(new TComida("Comida Mala", "Una Comida Mala", 1.1, 1, true, "Pruebas"));
		idBebMala = saPlato.altaPlato(new TBebida("Bebida Mala", "Una Bebida Mala", 1.1, 1, true, 10));
	}

	@Test
	public void aAltaPlatoOKComida() {
		TComida comida = new TComida("TestComidaOK", "Test JUnit", 1.1, 1, true, "Pruebas");
		idComida = saPlato.altaPlato(comida);
		assertNotEquals(-1, idComida);
	}

	@Test
	public void bAltaPlatoComidaYaExistente() {
		TComida comida = new TComida("TestComidaOK", "Test 1", 1.1, 1, true, "Pruebas");
		int aux = saPlato.altaPlato(comida);
		assertEquals(-1, aux);
	}

	@Test
	public void cAltaPlatoOKBebida() {
		TBebida bebida = new TBebida("TestBebidaOK", "Test JUnit", 1.1, 1, true, 100.0);
		idBebida = saPlato.altaPlato(bebida);
		assertNotEquals(-1, idBebida);
	}

	@Test
	public void dAltaPlatoBebidaYaExistente() {
		TBebida bebida = new TBebida("TestBebidaOK", "Test 2", 1.1, 1, true, 100.0);
		int aux = saPlato.altaPlato(bebida);
		assertEquals(-1, aux);
	}

	@Test
	public void eModificarOKComida() {
		TPlato comida = saPlato.buscarPlato(idComida);
		comida.setNombre("TestComidaOKModificada");
		assertEquals(1, saPlato.modificarPlato(comida));
	}

	@Test
	public void fModificarOKBebida() {
		TPlato bebida = saPlato.buscarPlato(idBebida);
		bebida.setNombre("TestBebidaOKModificada");
		assertEquals(1, saPlato.modificarPlato(bebida));
	}

	@Test
	public void gModificarKOComida() {
		TPlato comida = saPlato.buscarPlato(idComida);
		comida.setNombre("Comida Mala");
		assertEquals(-1, saPlato.modificarPlato(comida));
	}

	@Test
	public void hModificarKOBebida() {
		TPlato bebida = saPlato.buscarPlato(idBebida);
		bebida.setNombre("Bebida Mala");
		assertEquals(-1, saPlato.modificarPlato(bebida));
	}

	@Test
	public void iBuscarPlatoOKComida() {
		TPlato elem = saPlato.buscarPlato(idComida);
		assertNotNull(elem);
		assertEquals(elem.getId(), idComida);
	}

	@Test
	public void jBuscarPlatoOKBebida() {
		TPlato elem = saPlato.buscarPlato(idBebida);
		assertNotNull(elem);
		assertEquals(elem.getId(), idBebida);
	}

	@Test
	public void kBuscarPlatoKOC() {
		assertNull(saPlato.buscarPlato(-1));
	}

	@Test
	public void lAniadirIngredienteAComidaOK() {
		int res = saPlato.aniadirIngredienteAPlato(idIngrediente, idComida);
		assertTrue(res >= 0);
	}

	@Test
	public void mAniadirIngredienteAComidaKO() {
		int res = saPlato.aniadirIngredienteAPlato(-1, idComida);
		assertEquals(-1, res);
	}

	@Test
	public void nAniadirIngredienteABebidaOK() {
		int res = saPlato.aniadirIngredienteAPlato(idIngrediente, idBebida);
		assertTrue(res >= 0);
	}

	@Test
	public void oAniadirIngredienteABebidaKO() {
		int res = saPlato.aniadirIngredienteAPlato(-1, idBebida);
		assertEquals(-1, res);
	}

	@Test
	public void pEliminarIngredienteAComidaOK() {
		int res = saPlato.eliminarIngredienteAPlato(idIngrediente, idComida);
		assertTrue(res > 0);
	}

	@Test
	public void qEliminarIngredienteAComidaKO() {
		int res = saPlato.eliminarIngredienteAPlato(idIngrediente, idComida);
		assertEquals(idComida, res);
	}

	@Test
	public void rEliminarIngredienteABebidaOK() {
		int res = saPlato.eliminarIngredienteAPlato(idIngrediente, idBebida);
		assertTrue(res > 0);
	}

	@Test
	public void sEliminarIngredienteABebidaKO() {
		int res = saPlato.eliminarIngredienteAPlato(idIngrediente, idBebida);
		assertEquals(idBebida, res);
	}

	@Test
	public void tBajaPlatoOKComida() {
		int res = saPlato.bajaPlato(idComida);
		assertTrue(res > 0);
	}

	@Test
	public void uBajaPlatoKOComida() {
		int res = saPlato.bajaPlato(idComida);
		assertEquals(-1, res);
	}

	@Test
	public void vBajaPlatoOKBebida() {
		int res = saPlato.bajaPlato(idBebida);
		assertTrue(res > 0);
	}

	@Test
	public void wBajaPlatoKOBebida() {
		int res = saPlato.bajaPlato(idBebida);
		assertEquals(-1, res);
	}

	@org.junit.AfterClass
	public static void AfterClass() {
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Plato plato1 = em.find(Plato.class, idComida);
		Plato plato2 = em.find(Plato.class, idBebida);
		Plato plato3 = em.find(Plato.class, idComMala);
		Plato plato4 = em.find(Plato.class, idBebMala);
		Ingrediente ingre = em.find(Ingrediente.class, idIngrediente);
		em.remove(plato1);
		em.remove(plato2);
		em.remove(plato3);
		em.remove(plato4);
		em.remove(ingre);
		et.commit();
		em.close();
	}
}