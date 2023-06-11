package Integracion.Seccion;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Integración.Seccion.DAOSeccionImp;
import Negocio.Seccion.TSeccion;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DAOSeccionTests {
	   static DAOSeccionImp secciones;
	   static int id;
		
		@BeforeClass
		public static void BeforeClass() {
			secciones = new DAOSeccionImp();
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t;
			try {
				t = transaction.nuevaTransaccion();
				t.start();
				TSeccion s = new TSeccion(9,"Prueba");
				id = secciones.altaSeccion(s);
				t.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test
		public void aBuscarMarcaExistente(){							
			TSeccion res = secciones.buscarSeccion(id);
			
			assertNotNull(res);
		}
		
		@Test
		public void bBuscarMarcaNoExistente(){
			
			TSeccion res = secciones.buscarSeccion(62);
			
			assertNull(res);
		}
		
		@Test
		public void cLeerActivo(){
			TSeccion res = secciones.buscarSeccion(id);
			
			assertTrue(res.getActivo());
		}
		@Test
		public void dEliminarSeccion(){
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t;
			try {
				t = transaction.nuevaTransaccion();
				t.start();
				secciones.bajaSeccion(id);
				t.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		@Test
		public void eLeerNoActivo(){
			
			TSeccion res = secciones.buscarSeccion(id);
			
			assertFalse(res.getActivo());		
		}
		@Test
		public void fReactivarSeccion(){
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = null;;
			int exito = -1;
			try {
				t = transaction.nuevaTransaccion();
				t.start();
				exito = secciones.reactivar(2);
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
		
		
		@Test
		public void gBuscarTodasLassecciones(){
			List<TSeccion> res = secciones.buscarTodosSeccion();
			
			assertNotNull(res);
		}
		
		@Test 
		public void hBorrarSeccionBBDD(){
			TransactionManager transactionManager = TransactionManager.getInstance();
			try{
			Transaction transaccion2 = transactionManager.nuevaTransaccion();
			transaccion2.start();
			Connection connection = (Connection) transaccion2.getResource();
			Statement s = connection.createStatement();
			s.executeUpdate("DELETE FROM SECCION WHERE ID_SECCION=" + id);
			transaccion2.commit();
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}
		

}