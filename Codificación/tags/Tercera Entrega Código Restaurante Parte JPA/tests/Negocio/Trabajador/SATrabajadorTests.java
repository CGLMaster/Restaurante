package Negocio.Trabajador;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.FactoriaNegocio.FactoriaSA;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SATrabajadorTests {
	static SATrabajador saTrabajador;

	@BeforeClass
	public static void BeforeClass() {
		saTrabajador = FactoriaSA.getInstance().getSATrabajador();
	}

	@Test
	public void A_altaTrabajadorBien() {
		TTrabajador trabajador = new TTrabajador("TestAltaOK", "testAltaOK@test.ok", "00000000X");
		assertTrue(saTrabajador.altaTrabajador(trabajador) >= 0);
	}

	@Test
	public void B_altaMal_DNI() {
		TTrabajador trabajador = new TTrabajador("TestAltaKO_DNI", "testAltaKO@test.ko", "badUse");
		assertEquals(-1, saTrabajador.altaTrabajador(trabajador));
	}

	@Test
	public void C_altaMal_Nombre() {
		TTrabajador trabajador = new TTrabajador(" ", "testAltaKO@test.ko", "00000000Y");
		assertEquals(-1, saTrabajador.altaTrabajador(trabajador));
	}

	@Test
	public void D_altaMal_ExistenteActivo() {
		TTrabajador trabajador = new TTrabajador("TestAltaOK", "testAltaOK@test.ok", "00000000X");
		assertEquals(-1, saTrabajador.altaTrabajador(trabajador));
	}

	@Test
	public void E_eliminarBien() {
		assertTrue(saTrabajador.eliminarTrabajador(23) >= 0);
	}

	@Test
	public void F_eliminarMal() {
		assertEquals(-1, saTrabajador.eliminarTrabajador(23));
	}

	@Test
	public void G_modificarBien() {
		TTrabajador trabajadorMod = new TTrabajador("TestModOK", "testModOK@test.ok", "10101010Y");
		trabajadorMod.setId(23);
		assertTrue(saTrabajador.modificarDatosTrabajador(trabajadorMod) >= 0);
	}

	@Test
	public void H_modificarMal_Nombre() {
		TTrabajador revertirTrab = new TTrabajador("TestAltaOK", "testAltaOK@test.ok", "00000000X");
		revertirTrab.setId(23);
		saTrabajador.modificarDatosTrabajador(revertirTrab);

		TTrabajador trabajadorMod = new TTrabajador("  ", "testModKO@test.ko", "99999999Z");
		trabajadorMod.setId(23);
		assertEquals(-1, saTrabajador.modificarDatosTrabajador(trabajadorMod));
	}
	
	@Test
	public void I_modificarMal_DNI() {
		TTrabajador trabajadorMod = new TTrabajador("TestModKO", "testModKO@test.ko", "");
		trabajadorMod.setId(23);
		assertEquals(-1, saTrabajador.modificarDatosTrabajador(trabajadorMod));
	}
	
	@Test
	public void J_modificarMal_NoExiste() {
		TTrabajador trabajadorMod = new TTrabajador("TestModKO", "testModKO@test.ko", "99999999Z");
		trabajadorMod.setId((int) 1E6);
		assertEquals(-1, saTrabajador.modificarDatosTrabajador(trabajadorMod));
	}
}
