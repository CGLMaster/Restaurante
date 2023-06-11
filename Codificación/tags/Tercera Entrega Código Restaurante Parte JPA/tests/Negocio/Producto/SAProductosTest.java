package Negocio.Producto;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SAProductosTest {
	static SAProductoImp productos;
	static int idNp, idP;

	@BeforeClass
	public static void BeforeClass() {
		productos = new SAProductoImp();
	}

	@Test
	public void aCrearProductoOKNoPerecedero() {
		TProdNoPerecedero prodNp = new TProdNoPerecedero("PruebaNoPer", 1, 3, 3, 6, true, "Plastificados");

		idNp = productos.altaProducto(prodNp);
		assertNotEquals(-1, idNp);
	}

	@Test
	public void bCrearYaExistenteNoPerecedero() {
		TProdNoPerecedero prodNp = new TProdNoPerecedero("PruebaNoPer", 1, 3, 3, 6, true, "Plastificados");

		assertEquals(-1, productos.altaProducto(prodNp));
	}

	@Test
	public void aCrearProductoOKPerecedero() {
		TProdPerecedero prodP = new TProdPerecedero("PruebaPer", 1, 3, 3, 6, true, null);

		LocalDate date_cadud = null;
		String dateNums[] = { "2020", "10", "12" };
		int year = Integer.parseInt(dateNums[0]);
		int month = Integer.parseInt(dateNums[1]);
		int day = Integer.parseInt(dateNums[2]);
		date_cadud = LocalDate.of(year, month, day);
		prodP.setFechaDeCaducidad(date_cadud);

		idP = productos.altaProducto(prodP);
		assertNotEquals(-1, idP);
	}

	@Test
	public void bCrearYaExistentePerecedero() {
		TProdPerecedero prodP = new TProdPerecedero("PruebaPer", 1, 3, 3, 6, true, null);

		LocalDate date_cadud = null;
		String dateNums[] = { "2020", "05", "12" };
		int year = Integer.parseInt(dateNums[0]);
		int month = Integer.parseInt(dateNums[1]);
		int day = Integer.parseInt(dateNums[2]);

		date_cadud = LocalDate.of(year, month, day);
		prodP.setFechaDeCaducidad(date_cadud);

		assertEquals(-1, productos.altaProducto(prodP));
	}

	@Test
	public void cLeerActivoNoPerecedero() {
		TProducto prod = productos.buscarProducto(idNp);

		assertTrue(prod.getActivo());
	}

	@Test
	public void cLeerActivoPerecedero() {
		TProducto prod = productos.buscarProducto(idP);

		assertTrue(prod.getActivo());
	}

	@Test
	public void dModificarOKNoPerecedero() {
		TProducto prod = productos.buscarProducto(idNp);
		prod.setNombre("PruebaPerNuevo");
		prod.setIDMarca(3);
		prod.setIDSeccion(6);
		
		assertEquals(1, productos.modificarProducto(prod));
	}

	@Test
	public void dModificarOKPerecedero() {
		TProducto prod = productos.buscarProducto(idP);
		prod.setNombre("PruebaNoPerNuevo");
		prod.setIDMarca(3);
		prod.setIDSeccion(6);
		
		assertEquals(1, productos.modificarProducto(prod));
	}

	@Test
	public void eModificarKONoPerecedero() {
		TProducto prod = productos.buscarProducto(idNp);
		prod.setNombre("Leche 1L"); 
		prod.setIDMarca(3);
		prod.setIDSeccion(5);

		assertEquals(-1, productos.modificarProducto(prod));
	}

	@Test
	public void eModificarKOPerecedero() {
		TProducto prod = productos.buscarProducto(idP);
		prod.setNombre("Galletas");
		prod.setIDMarca(3);
		prod.setIDSeccion(5);
		assertEquals(-1, productos.modificarProducto(prod));
	}

	@Test
	public void fEliminarActivo() {
		TProducto prod = productos.buscarProducto(idNp);

		assertEquals(1, productos.bajaProducto(prod.getIDProducto()));
	}

	@Test
	public void gEliminarNoActivo() {
		TProducto prod = productos.buscarProducto(idNp);

		assertEquals(-1, productos.bajaProducto(prod.getIDProducto()));
	}

	@Test
	public void hLeerInActivo() {
		TProducto prod = productos.buscarProducto(idNp);

		assertFalse(prod.getActivo());
	}
}
