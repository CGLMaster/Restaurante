package Negocio.Proveedor;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SAProveedorTest {
	static SAProveedorImp proveedores;
	static int idProveedor;

	@BeforeClass
	public static void BeforeClass() {
		proveedores = new SAProveedorImp();
	}

	@Test
	public void a_CrearProveedorOK() {
		TProveedor proveedor = new TProveedor("ProveedorTest");
		idProveedor = proveedores.altaProveedor(proveedor.getNombre());

		assertNotEquals(-1, idProveedor);
	}

	@Test
	public void b_CrearProveedorExistente() {
		TProveedor proveedor = new TProveedor("ProveedorTest");

		assertEquals(-1, proveedores.altaProveedor(proveedor.getNombre()));
	}

	@Test
	public void c_LeerActivo() {
		TProveedor proveedor = proveedores.buscarProveedor(idProveedor);

		assertTrue(proveedor.getActivo());
	}

	@Test
	public void d_EliminarActivo() {
		assertEquals(1, proveedores.eliminarProveedor(idProveedor));
	}

	@Test
	public void e_EliminarNoActivo() {
		assertEquals(-1, proveedores.eliminarProveedor(idProveedor));
	}

	@Test
	public void f_LeerInactivo() {
		TProveedor proveedor = proveedores.buscarProveedor(idProveedor);

		assertFalse(proveedor.getActivo());
	}

	@Test
	public void g_EliminarVinculado() {
		assertEquals(-1, proveedores.eliminarProveedor(1));
	}
}
