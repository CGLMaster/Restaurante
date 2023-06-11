package Negocio.Turno;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Personal.Personal;
import Negocio.Personal.SAPersonalImp;
import Negocio.Personal.TTiempoCompleto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SATurnoTest {
	
	public static SATurnoImp saturno;
	public static SAPersonalImp sapersonal;
	public static int idTurno, idPersonal;
	
	@BeforeClass
	public static void Beforeclass(){
		saturno = new SATurnoImp();
		sapersonal = new SAPersonalImp();
	}
	
	@Test
	public void aAltaTurnoOK(){
		TTurno turno = new TTurno("PruebaTurno", "9:00", "14:00", true);
		idTurno = saturno.altaTurno(turno);
		assertNotEquals(-1, idTurno);
	}
	
	@Test
	public void bAltaTurnoKO(){
		TTurno turno = new TTurno("PruebaTurno");
		int id = saturno.altaTurno(turno);
		assertEquals(-1, id);
	}
	
	@Test
	public void cBuscarTurnoOK(){
		TTurno turno = saturno.buscarUnTurno(idTurno);
		assertNotNull(turno);
		assertEquals(turno.getID(), idTurno);
	}
	
	@Test
	public void dBuscarTurnoKO(){
		assertNull(saturno.buscarUnTurno(-1));
	}
	
	@Test
	public void eModificarTurnoOK(){
	    TTurno turno = saturno.buscarUnTurno(idTurno);
	    turno.setNombre("PruebaTurnoModificado");
	    assertEquals(idTurno, saturno.modificarTurno(turno));
	}
	
	@Test
	public void fModificarTurnoKO(){
		TTurno turno = saturno.buscarUnTurno(4);
	    turno.setNombre("PruebaTurnoModificado");
	    assertEquals(-1, saturno.modificarTurno(turno));
	}
	
	@Test
	public void gBuscarTodosTurnos(){
	    List<TTurno> turno= saturno.buscarTodosTurno();
	    assertTrue(turno.size() > 0);
	}
	
	@Test
	public void hCalcularNominaTurnoOK(){
		TTiempoCompleto personal = new TTiempoCompleto();
		personal.setNombre("PruebaTurno");
		personal.setDni("98773728C");
		personal.setNumPedidos(0);
		personal.setNomina(1000.0);
		personal.setHorasExtras(8);
		personal.setActivo(true);
		personal.setId_turno(idTurno);
		idPersonal = sapersonal.altaPersonal(personal);
		double r = saturno.calcularNominaTurno(idTurno);
		assertTrue(r > 0);
	}
	
	@Test
	public void iCalcularNominaTurnoKO(){
		double r = saturno.calcularNominaTurno(-69);
		assertTrue(r < 0);
	}
	
	@Test
	public void jBajaTurnoOK(){
		int p = sapersonal.bajaPersonal(idPersonal);
		int r = saturno.bajaTurno(idTurno);
		assertEquals(idTurno, r);
	}
	
	@Test
	public void kBajaTurnoKO(){
		int r = saturno.bajaTurno(-69);
		assertEquals(-1, r);
	}
	
	@org.junit.AfterClass
	public static void AfterClass() {
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		Turno turn = em.find(Turno.class, idTurno);
		Personal p = em.find(Personal.class, idPersonal);
		
		em.remove(turn);
		em.remove(p);
		et.commit();
		em.close();
	}
}
