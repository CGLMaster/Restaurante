package Negocio.Personal;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.ClienteRest.ClienteRest;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;
import Negocio.Turno.SATurnoImp;
import Negocio.Turno.TTurno;
import Negocio.Turno.Turno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SAPersonalTest {

	static SAPersonalImp personal;
	static SATurnoImp turno;
	static int idTC, idTP, idTurno;
	
	@BeforeClass
	public static void BeforeClass() {
		personal = new SAPersonalImp();
		turno = new SATurnoImp();
		
	}
	
	@Test
	public void aCrearPersonalOKTiempoCompleto(){
		TTiempoCompleto personalTC = new TTiempoCompleto();
		personalTC.setNombre("Gabri Torres");
		personalTC.setDni("98775525W");
		personalTC.setNumPedidos(0);
		personalTC.setNomina(1000.0);
		personalTC.setHorasExtras(8);
		personalTC.setActivo(true);
		TTurno t = new TTurno("Manana1");
		t.setHoraInicio("19:00");
		t.setHoraFin("20:00");
		idTurno = turno.altaTurno(t);
		personalTC.setId_turno(idTurno);
		idTC = personal.altaPersonal(personalTC);
		assertNotEquals(-1, idTC);
	}
	
	@Test
	public void bCrearYaExistenteTiempoCompleto(){
		TTiempoCompleto personalTC = new TTiempoCompleto();
		personalTC.setNombre("Jose");
		personalTC.setDni("98775525W");
		personalTC.setNumPedidos(0);
		personalTC.setId_turno(idTurno);
		personalTC.setNomina(500.0);
		personalTC.setHorasExtras(5);
		personalTC.setActivo(true);

		int aux  = personal.altaPersonal(personalTC);
		assertEquals(-1, aux);
	}
	
	@Test
	public void cCrearPersonalOKTiempoParcial(){
		TTiempoParcial personalTP = new TTiempoParcial();
		personalTP.setNombre("Jose");
		personalTP.setDni("98775526W");
		personalTP.setNumPedidos(0);
		personalTP.setId_turno(idTurno);
		personalTP.setPrecioHora(13.5);
		personalTP.setNumeroHoras(40);
		personalTP.setActivo(true);

		idTP = personal.altaPersonal(personalTP);
		assertNotEquals(-1, idTP);
	}
	
	@Test
	public void dCrearYaExistenteTiempoParcial(){
		TTiempoParcial personalTP = new TTiempoParcial();
		personalTP.setNombre("Jose");
		personalTP.setDni("98775526W");
		personalTP.setNumPedidos(0);
		personalTP.setId_turno(idTurno);
		personalTP.setPrecioHora(11.5);
		personalTP.setNumeroHoras(55);
		personalTP.setActivo(true);

		int aux = personal.altaPersonal(personalTP);
		assertEquals(-1, aux);
	}
	
	@Test
	public void eLeerActivoTiempoCompleto(){
		TPersonal per = personal.buscarUnPersonalPorID(idTC);
		
		assertTrue(per.getActivo());
	}
	
	@Test
	public void fLeerActivoTiempoParcial(){
		TPersonal per = personal.buscarUnPersonalPorID(idTP);
		
		assertTrue(per.getActivo());
	}
	
	@Test
	public void gModificarOKTiempoCompleto(){
		TPersonal per = personal.buscarUnPersonalPorID(idTC);
		per.setDni("76529822R");
		
		assertEquals(1, personal.modificarPersonal(per));
	}
	
	@Test
	public void hModificarOKTiempoParcial(){
		TPersonal per = personal.buscarUnPersonalPorID(idTP);
		per.setNombre("Gabriel Torres Martin");
		assertEquals(1, personal.modificarPersonal(per));
	}
	
	@Test
	public void iModificarKOTiempoCompleto(){
		TPersonal per = personal.buscarUnPersonalPorID(idTC);
		per.setDni("98775526W"); 
		
		assertEquals(-1, personal.modificarPersonal(per));
	}
	
	@Test
	public void jModificarKOTiempoParcial(){
		TPersonal per = personal.buscarUnPersonalPorID(idTP);
		per.setDni("123A"); 
		
		assertEquals(-1, personal.modificarPersonal(per));
	}
	
	@Test
	public void kBuscarTodos(){
		List<TPersonal> listaPersonal = personal.buscarTodosPersonal();
		assertNotNull(listaPersonal);
	}
	
	@Test
	public void lEliminarActivo(){
		assertEquals(1, personal.bajaPersonal(idTP));
	}
	
	@Test
	public void mEliminarNoActivo(){
		assertEquals(-1, personal.bajaPersonal(idTP)); 
	}
		
	@Test
	public void nLeerInActivo(){
		TPersonal per = personal.buscarUnPersonalPorID(idTP);
		
		assertFalse(per.getActivo());
	}
	
	@org.junit.AfterClass
	public static void AfterClass(){
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Personal p = em.find(Personal.class, idTC);
		Personal p2 = em.find(Personal.class, idTP);
		Turno t = em.find(Turno.class, idTurno);
		em.remove(t);
		em.remove(p);		
		em.remove(p2);
		et.commit();
		em.close();
	}
}
