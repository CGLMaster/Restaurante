package Negocio.Pedido;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Negocio.ClienteRest.ClienteRest;
import Negocio.ClienteRest.TClienteRest;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.FactoriaNegocio.FactoriaSAImp;
import Negocio.Pedido.SAPedidoImp;
import Negocio.Personal.Personal;
import Negocio.Personal.TPersonal;
import Negocio.Personal.TTiempoCompleto;
import Negocio.Plato.Plato;
import Negocio.Plato.TComida;
import Negocio.Plato.TPlato;
import Negocio.Turno.SATurnoImp;
import Negocio.Turno.TTurno;
import Negocio.Turno.Turno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SAPedidoTest {

	static SAPedidoImp pedidos;
	static int id;
	static TComanda comanda;

	static TPersonal empleado;
	static Personal e_empleado;
	static TClienteRest cliente;
	static ClienteRest e_cliente;
	static TPlato plato;
	static Plato e_plato;
	static Pedido e_pedido;

	/*
	 * Pedido con stock de mas pedido sin stock pedido sin cosas pedido con id
	 * personal inexistente pedido con id cliente inexistente pedido con pasta
	 * insuficiente
	 * 
	 * pedido bien
	 *
	 * hacer devolución mal
	 *
	 * hacer devolución parcial bien
	 *
	 * hacer devolucón total bien
	 * 
	 */

	@BeforeClass
	public static void BeforeClass() {
		pedidos = new SAPedidoImp();
		comanda = new TComanda();

		int id_turno = FactoriaSAImp.getInstance().getSATurno().altaTurno(new TTurno("MarioTurno55999", "00:16", "06:22", true));

		empleado = new TTiempoCompleto("MarioPrueba", "95598879H", 0, 0.0, true, id_turno, 15.3, 2);
		empleado.setID(FactoriaSAImp.getInstance().getSAPersonal().altaPersonal(empleado));
		// e_empleado.transferToEntity(empleado);
		// e_empleado.setId(empleado.getID());

		cliente = new TClienteRest("Mario", "mariiioo", "95599979W", true);
		cliente.setID(FactoriaSAImp.getInstance().getSAClienteRest().altaClienteRest(cliente));
		// e_cliente.transfertoEntity(cliente);
		// e_cliente.setId(cliente.getID());

		plato = new TComida("ComMariovFinal1277", "ComMario", 3.2, 200, true, "ComMario");
		plato.setId(FactoriaSAImp.getInstance().getSAPlato().altaPlato(plato));
		// e_plato.transferToEntity(plato);
		// e_plato.setID(plato.getId());

		comanda.getPedido().setId_personal(empleado.getID());
		comanda.getPedido().setId_cliente(cliente.getID());
		comanda.getPedido().setPrecioPagado(5000.0);
	}

	@Test
	public void A_crearPedidoSTOCK_DEMAS() {
		TLineaPedido lp = new TLineaPedido(plato.getId(), 9999);
		comanda.getMapaLineas().put(plato.getId(), lp);

		assertEquals(-1, pedidos.cerrarPedido(comanda));
		comanda.getMapaLineas().remove(plato.getId());
		lp.setCantidad(150);
		comanda.getMapaLineas().put(plato.getId(), lp);
	}

	@Test
	public void B_crearPedido_CON_STOCK_CERO() {
		comanda.getMapaLineas().remove(plato.getId());
		TLineaPedido lp = new TLineaPedido(plato.getId(), 0);
		comanda.getMapaLineas().put(plato.getId(), lp);

		assertEquals(-1, pedidos.cerrarPedido(comanda));
		comanda.getMapaLineas().remove(plato.getId());
		lp.setCantidad(150);
		comanda.getMapaLineas().put(plato.getId(), lp);

	}

	@Test
	public void C_crearPedido_SIN_COSAS() {
		comanda.getMapaLineas().remove(plato.getId());


		assertNull(pedidos.validarPedido(comanda));

		TLineaPedido lp = new TLineaPedido(plato.getId(), 150);
		comanda.getMapaLineas().put(plato.getId(), lp);
	}

	@Test
	public void D_crearPedido_ID_PERSONAL_MAL() {
		comanda.getPedido().setId_personal(-9789);

		assertEquals(-1, pedidos.cerrarPedido(comanda));

		comanda.getPedido().setId_personal(empleado.getID());
	}

	@Test
	public void E_crearPedido_SIN_DINERO() {
		comanda.getPedido().setPrecioPagado(0.0);


		assertNotNull(pedidos.validarPedido(comanda));
		assertEquals(-1, pedidos.cerrarPedido(comanda));

		comanda.getPedido().setPrecioPagado(5000.0);
	}

	@Test
	public void F_crearPedido_BIEN() {

		TLineaPedido lp = new TLineaPedido(plato.getId(), 200);
		comanda.getMapaLineas().put(plato.getId(), lp);
		
		comanda = pedidos.validarPedido(comanda);
		assertNotNull(comanda);
		int id_pedido = pedidos.cerrarPedido(comanda);
		assertNotEquals(-1, id_pedido);
		comanda.getPedido().setID(id_pedido);
		
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();

		Plato aux_plato = em.find(Plato.class, plato.getId());
		assertEquals(aux_plato.getStock(), plato.getStock() - lp.getCantidad());
		
		em.close();

	}

	@Test
	public void G_crearPedido_DEVOLUCION_PARCIAL() {

		TLineaPedido lp = new TLineaPedido(plato.getId(), 199);
		lp.setIdPedido(comanda.getPedido().getID());
		lp.setPrecio(plato.getPrecio());
		List<TLineaPedido> devolver = new ArrayList<TLineaPedido>();
		devolver.add(lp);

		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		// Buscar antes.
		Plato aux_plato = em.find(Plato.class, plato.getId());
		assertNotEquals(-1, pedidos.DevolucionPedido(devolver));
		
		em.close();
		em = FactoriaEntityManager.getInstance().generarEntityManager();
		// Tiene el stock de antes.
		Plato aux_plato_despues = em.find(Plato.class, plato.getId());
		assertEquals(aux_plato_despues.getStock(), aux_plato.getStock() + 199);

	}

	@Test
	public void H_crearPedido_DEVOLUCION_TOTAL() {
		
		TLineaPedido lp = new TLineaPedido(plato.getId(), 1);
		lp.setIdPedido(comanda.getPedido().getID());
		lp.setPrecio(plato.getPrecio());
		List<TLineaPedido> devolver = new ArrayList<TLineaPedido>();
		devolver.add(lp);

		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();

		// Buscar antes.
		Plato aux_plato = em.find(Plato.class, plato.getId());
		assertNotEquals(-1, pedidos.DevolucionPedido(devolver));
		
		em.close();
		em = FactoriaEntityManager.getInstance().generarEntityManager();
		// Tiene el stock de antes.
		Plato aux_plato_despues = em.find(Plato.class, plato.getId());
		assertEquals(aux_plato_despues.getStock(), aux_plato.getStock() + 1);

	}
	
	@AfterClass
	public static void AfterClass() {
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		e_pedido = em.find(Pedido.class, comanda.getPedido().getID());
		em.remove(e_pedido);
		e_cliente = em.find(ClienteRest.class, cliente.getID());
		em.remove(e_cliente);
		
		Turno t = em.find(Turno.class, empleado.getIDTurno());
		e_empleado = em.find(Personal.class, empleado.getID());
		em.remove(e_empleado);
		e_plato = em.find(Plato.class, plato.getId());
		em.remove(e_plato);
		em.remove(t);
		tr.commit();

		em.close();

	}
	
}
