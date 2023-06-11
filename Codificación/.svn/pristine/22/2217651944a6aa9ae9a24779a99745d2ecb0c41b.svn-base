package Negocio.Pedido;

import Negocio.ClienteRest.ClienteRest;
import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Personal.Personal;
import Negocio.Plato.Bebida;
import Negocio.Plato.Comida;
import Negocio.Plato.Plato;
import Negocio.Plato.TBebida;
import Negocio.Plato.TComida;
import Negocio.Plato.TPlato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class SAPedidoImp implements SAPedido {

	public int DevolucionPedido(List<TLineaPedido> lineas) {
		int res = -1;
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		int id_pedido = lineas.get(0).getIdPedido();
		Pedido pedido = em.find(Pedido.class, id_pedido);

		if (pedido != null) {
			double nuevo_precio = pedido.getPrecioTotal();
			res = pedido.getID();
			for (TLineaPedido linea : lineas) {
				LineaPedidoID id_linea = new LineaPedidoID(linea.getIdPedido(), linea.getIdtPlato());
				LineaPedido lineaNueva = em.find(LineaPedido.class, id_linea);
				Plato plato = em.find(Plato.class, linea.getIdtPlato());
				int cantTodev = 0;

				if (lineaNueva != null && plato != null) {
					if (lineaNueva.getCantidad() == linea.getCantidad()) {
						cantTodev = lineaNueva.getCantidad();
						em.remove(lineaNueva);
					} else if (linea.getCantidad() > 0 && linea.getCantidad() < lineaNueva.getCantidad()) {
						cantTodev = linea.getCantidad();
						lineaNueva.setCantidad(lineaNueva.getCantidad() - linea.getCantidad());
					} else {
						res = -1;
					}
					nuevo_precio -= (cantTodev * linea.getPrecio());
					plato.setStock(plato.getStock() + cantTodev);
				} else {
					res = -1;
				}
			}
			pedido.setPrecioTotal(nuevo_precio);
		}

		if (res != -1) {
			try {
				et.commit();
			} catch (Exception e) {
				et.rollback();
			}
		} else {
			et.rollback();
		}

		em.close();

		return res;
	}

	public TComanda abrirPedido(int idPersonal) {
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		Personal p = em.find(Personal.class, idPersonal);

		if (p != null) {
			if (p.getID() == -1 || p.getActivo()) {
				TComanda c = new TComanda();
				c.getPedido().setId_personal(idPersonal);
				return c;

			} else
				return null;
		} else
			return null;
	}

	public int cerrarPedido(TComanda comanda) {
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		int resultado = 1;
		Personal p = em.find(Personal.class, comanda.getPedido().getId_personal(), LockModeType.OPTIMISTIC);
		ClienteRest cl = em.find(ClienteRest.class, comanda.getPedido().getId_cliente(), LockModeType.OPTIMISTIC);
		Pedido resPedido = null;

		if (p != null && cl != null && comanda.getPedido().getPrecioPagado() >= comanda.getPedido().getPrecioTotal()) {
			if (p.getActivo() && cl.getActivo()) {
				resPedido = new Pedido();
				resPedido.setpreciopagado(comanda.getPedido().getPrecioPagado());
				HashMap<Integer, TLineaPedido> mapa = comanda.getMapaLineas();
				resPedido.setCliente(cl);
				resPedido.setPersonal(p);
				resPedido.setDate(LocalDate.now().plusDays(1));
				p.setNumPedidos(p.getNumPedidos() + 1);

				em.persist(resPedido);
				for (Entry<Integer, TLineaPedido> entry : mapa.entrySet()) {
					TLineaPedido aux = entry.getValue();
					Plato plato = em.find(Plato.class, aux.getIdtPlato(), LockModeType.OPTIMISTIC);
					if (plato == null || !plato.getActivo() || plato.getStock() < aux.getCantidad()
							|| aux.getCantidad() <= 0) {
						resultado = -1;
					} else {
						aux.setPrecio(plato.getPrecio());
						LineaPedido p_aux = new LineaPedido();
						p_aux.transferToEntity(aux);
						p_aux.setPedido(resPedido);
						p_aux.setPlato(plato);
						em.persist(p_aux);
						resPedido.getListaLineaPedido().add(p_aux);
						plato.setStock(plato.getStock() - aux.getCantidad());
						resPedido.setPrecioTotal(resPedido.getPrecioTotal() + aux.getCantidad() * plato.getPrecio());
					}
				}

			} else
				resultado = -1;
		} else
			resultado = -1;

		if (resultado == -1) {
			et.rollback();
		} else {
			try {
				et.commit();
				resultado = resPedido.getID();
			} catch (Exception e) {
				et.rollback();
			}
		}
		em.close();
		return resultado;
	}

	public TPedidoConPlatos buscarPedidoPorID(int id) {
		FactoriaEntityManager factoriaEM = FactoriaEntityManager.getInstance();
		EntityManager EM = factoriaEM.generarEntityManager();
		EntityTransaction transaction = EM.getTransaction();

		transaction.begin();

		Pedido pedido = EM.find(Pedido.class, id);
		if (pedido == null)
			return null;

		TPedido Pedido = new TPedido(pedido.getID(), pedido.getCliente().getId(), pedido.getPersonal().getID(),
				pedido.getPrecioTotal(), pedido.getDate());

		HashMap<Integer, TPlato> platos = new HashMap<Integer, TPlato>();
		List<TLineaPedido> lineaPedidos = new ArrayList<>();
		for (LineaPedido p : pedido.getListaLineaPedido()) {
			TPlato tPlato = null;

			if (p.getPlato() instanceof Comida) {
				Comida comida = (Comida) p.getPlato();
				tPlato = new TComida(p.getPlato().getID(), p.getPlato().getNombre(), p.getPlato().getDescripcion(),
						p.getPlato().getPrecio(), p.getPlato().getStock(), p.getPlato().getActivo(),
						comida.getCategoria());

			} else if (p.getPlato() instanceof Bebida) {
				Bebida bebida = (Bebida) p.getPlato();
				tPlato = new TBebida(p.getPlato().getID(), p.getPlato().getNombre(), p.getPlato().getDescripcion(),
						p.getPlato().getPrecio(), p.getPlato().getStock(), p.getPlato().getActivo(),
						bebida.getVolumen());
			}
			platos.put(tPlato.getId(), tPlato);
			TLineaPedido linea = new TLineaPedido(p.getPedido().getID(), p.getPlato().getID(), p.getPrecio(),
					p.getCantidad());
			lineaPedidos.add(linea);
		}
		TPedidoConPlatos TpedidoPlatos = new TPedidoConPlatos(Pedido, platos, lineaPedidos);

		try {
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		EM.close();
		return TpedidoPlatos;
	}

	public List<TPedido> buscarPedidoPorCliente(int idCliente) {
		FactoriaEntityManager factoriaEM = FactoriaEntityManager.getInstance();
		EntityManager EM = factoriaEM.generarEntityManager();

		ClienteRest c = EM.find(ClienteRest.class, idCliente);
		List<TPedido> lista = null;

		if (c != null) {
			lista = new ArrayList<TPedido>();
			for (Pedido pedido : c.getPedidosCliente()) {
				TPedido p = new TPedido(pedido.getID(), pedido.getCliente().getId(), pedido.getPersonal().getID(),
						pedido.getPrecioTotal(), pedido.getDate());
				lista.add(p);
			}
		}
		EM.close();
		return lista;
	}

	public List<TPedido> buscarTodosPedido() {
		FactoriaEntityManager factoriaEM = FactoriaEntityManager.getInstance();
		EntityManager EM = factoriaEM.generarEntityManager();

		TypedQuery<Pedido> query = EM.createQuery("SELECT P FROM Pedido P", Pedido.class);
		List<Pedido> l = query.getResultList();
		List<TPedido> lista = new ArrayList<TPedido>();

		for (Pedido pedido : l) {
			TPedido p = new TPedido(pedido.getID(), pedido.getCliente().getId(), pedido.getPersonal().getID(),
					pedido.getPrecioTotal(), pedido.getDate());
			lista.add(p);
		}
		EM.close();
		return lista;
	}

	public TComanda aniadirPlatoPedido(TComanda comanda, TLineaPedido lineaP) {

		HashMap<Integer, TLineaPedido> mapa = comanda.getMapaLineas();

		if (mapa != null) {
			if (mapa.containsKey(lineaP.getIdtPlato())) {
				TLineaPedido lineaAnt = mapa.get(lineaP.getIdtPlato());

				lineaAnt.setCantidad(lineaP.getCantidad() + lineaAnt.getCantidad());

			} else {
				comanda.getMapaLineas().put(lineaP.getIdtPlato(), lineaP);

			}
		}

		return comanda;
	}

	public TComanda eliminarPlatoPedido(TComanda comanda, TLineaPedido linea) {
		HashMap<Integer, TLineaPedido> mapa = comanda.getMapaLineas();

		if (mapa.containsKey(linea.getIdtPlato())) {
			mapa.remove(linea.getIdtPlato());

		} else
			return null;

		return comanda;
	}

	@Override
	public TComanda validarPedido(TComanda datos) {
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();

		Double total = 0.0;
		int resultado = 1;
		Personal tr = em.find(Personal.class, datos.getPedido().getId_personal());
		if (tr != null) {
			if (datos.getMapaLineas().isEmpty() || !tr.getActivo()) resultado = -1;
			else {
				HashMap<Integer, TLineaPedido> mapa = datos.getMapaLineas();
				for (Entry<Integer, TLineaPedido> entry : mapa.entrySet()) {
					TLineaPedido aux = entry.getValue();
					Plato plato = em.find(Plato.class, aux.getIdtPlato());
					if (plato == null || !plato.getActivo() || plato.getStock() < aux.getCantidad()
							|| aux.getCantidad() <= 0) {
						resultado = -1;
					} else
						total += plato.getPrecio() * aux.getCantidad();

				}
			}
			datos.getPedido().setPrecioTotal(total);
			em.close();

			if (resultado == -1)
				return null;
			else
				return datos;
		}
		else return null;
	}

}