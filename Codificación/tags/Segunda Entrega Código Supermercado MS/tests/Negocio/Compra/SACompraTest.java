package Negocio.Compra;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SACompraTest {

	static SACompraImp compra;
	static int idClienteP, idTrabajador, idClienteNP;
	static TCarrito c;

	@BeforeClass
	public static void BeforeClass() {
		compra = new SACompraImp();
		idTrabajador=21;
		idClienteNP=65;
		idClienteP=69;
	}
	
	@Test
	public void aAbrirCompraOK(){
		c=compra.abrirCompra(idTrabajador);
		assertNotEquals(null, c);
		assertEquals(-1, c.getCompra().getIdCliente());
	}
	
	@Test
	public void bAniadirCompra(){
		assertEquals(1, compra.aniadirProducto(new TLineaDeCompra(9, 3), c));
		assertEquals(1, compra.aniadirProducto(new TLineaDeCompra(9, 3), c));
		assertEquals(-1, compra.aniadirProducto(new TLineaDeCompra(9, -3), c));
	}
	
	@Test
	public void cValidarCompra(){
		c.getLineaDeCompra().add(new TLineaDeCompra(10, 3));
		assertEquals(0, compra.validarCarrito(c));
		
		c.getLineaDeCompra().add(new TLineaDeCompra(896, 3));
		assertEquals(-1, compra.validarCarrito(c));
		
		c.getLineaDeCompra().clear();
		c.getLineaDeCompra().add(new TLineaDeCompra(10, 3));
		assertEquals(0, compra.validarCarrito(c));
		
		float total = c.getCompra().getPrecioTotal();
		c.getCompra().setIdCliente(idClienteNP);
		compra.validarCarrito(c);
		float nuevo= c.getCompra().getPrecioPagado();
		assertEquals(total, nuevo);
		
		c.getCompra().setIdCliente(idClienteP);
		compra.validarCarrito(c);
		nuevo= c.getCompra().getPrecioPagado();
		assertEquals(total*0.8, nuevo);
	}
	
	@Test
	public void dEliminarProducto(){
		c.getLineaDeCompra().add(new TLineaDeCompra(9, 3));
		assertEquals(1, compra.eliminarProducto(new TLineaDeCompra(9, 3), c));
		assertEquals(-1, compra.eliminarProducto(new TLineaDeCompra(9, 3), c));
	}
	
	@Test
	public void eCerrarCompra(){
		c.getLineaDeCompra().add(new TLineaDeCompra(9, 3));
		c.getCompra().setIdCliente(3445);			
		assertEquals(-1, compra.cerrarCompra(c));
		
		c.getCompra().setIdCliente(idClienteNP);		
		c.getCompra().setIdTrabajador(3445);
		assertEquals(-1, compra.cerrarCompra(c));
		
		c.getCompra().setIdTrabajador(21);				
		c.getCompra().setPrecioPagado(999);
		assertEquals(1, compra.cerrarCompra(c));
	}
	
	@Test
	public void fdevolucionCompra(){ 
		TCompraConProductos t = compra.buscarPorIDCompra(c.getCompra().getIDCompra());
		
		TLineaDeCompra linea = t.getLineasDeCompra().get(0);
		
		TLineaDeCompra lineaToDev = new TLineaDeCompra(linea.getIDProducto(),linea.getID_Compra(),linea.getPrecio(),linea.getCantidad()-1);
		
		List<TLineaDeCompra> lineas = new ArrayList<TLineaDeCompra>();
		lineas.add(lineaToDev);
		
		compra.devolverProductos(lineas);
		
		TCompraConProductos tDev = compra.buscarPorIDCompra(c.getCompra().getIDCompra());
		
		assertEquals((t.getCompra().getPrecioTotal()-lineaToDev.getPrecio()*lineaToDev.getCantidad()), tDev.getCompra().getPrecioTotal());
		
	}
	
}
