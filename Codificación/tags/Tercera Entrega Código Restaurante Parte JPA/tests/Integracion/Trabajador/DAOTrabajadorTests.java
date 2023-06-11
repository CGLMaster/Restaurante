package Integracion.Trabajador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Integración.Trabajador.DAOTrabajadorImp;
import Negocio.Trabajador.TTrabajador;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DAOTrabajadorTests {
	static DAOTrabajadorImp trabajadores;
	@BeforeClass
	public static void BeforeClass() {
		trabajadores = new DAOTrabajadorImp();
	}
	
	@Test
	public void aBuscarTrabajadorExistente(){							
		
		TTrabajador res = trabajadores.buscarDatosTrabajadorID(4);
		
		assertNotNull(res);
	}
	
	@Test
	public void bBuscarTrabajadorNoExistente(){
		int id = -2001;
		
		TTrabajador res = trabajadores.buscarDatosTrabajadorID(id);
		
		assertNull(res);
	}
	
	@Test
	public void cLeerActivo(){
		TTrabajador res = trabajadores.buscarDatosTrabajadorID(4);
		
		assertTrue(res.getActivo());
	}
	
	
	@Test
	public void dLeerNoActivo(){
		TTrabajador res = trabajadores.buscarDatosTrabajadorID(10);
		
		assertFalse(res.getActivo());		
	}
	
	
	@Test
	public void eBuscarTodasLosTrabajadores(){
		List<TTrabajador> res = trabajadores.buscarTodosTrabajador();
		
		assertNotNull(res);
	}
	@Test
	public void fReactivarSeccion(){
		TransactionManager transaction = TransactionManager.getInstance();
		Transaction t = null;;
		int exito = -1;
		try {
			t = transaction.nuevaTransaccion();
			t.start();
			exito = trabajadores.reactivar(10);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			if(exito == -1)
				try {
					t.rollback();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		assertEquals(1, exito);
	}
}
