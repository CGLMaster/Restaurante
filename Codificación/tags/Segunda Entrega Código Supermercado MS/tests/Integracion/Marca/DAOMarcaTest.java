package Integracion.Marca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.Marca.TMarca;

import Integración.Marca.DAOMarcaImp;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DAOMarcaTest {
    static DAOMarcaImp marcas;
	
	@BeforeClass
	public static void BeforeClass() {
		marcas = new DAOMarcaImp();
	}
	
	@Test
	public void aBuscarMarcaExistente(){							
		
		TMarca res = marcas.buscarMarca(3);
		
		assertNotNull(res);
	}
	
	@Test
	public void bBuscarMarcaNoExistente(){
		int id = -2001;
		
		TMarca res = marcas.buscarMarca(id);
		
		assertNull(res);
	}
	
	@Test
	public void cLeerActivo(){
		TMarca res = marcas.buscarMarca(3);
		
		assertTrue(res.getActivo());
	}
	
	
	@Test
	public void dLeerNoActivo(){
		TMarca res = marcas.buscarMarca(6);
		
		assertFalse(res.getActivo());		
	}
	
	
	@Test
	public void eBuscarTodasLasMarcas(){
		List<TMarca> res = marcas.buscarTodosMarca();
		
		assertNotNull(res);
	}
	
}
