package Negocio.Plato;

import java.util.ArrayList;
import java.util.List;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Ingrediente.Ingrediente;
import Negocio.Ingrediente.TIngrediente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class SAPlatoImp implements SAPlato {

	public int altaPlato(TPlato plato) {
		int res = 0;

		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		TypedQuery<Plato> query = em.createNamedQuery("Negocio.Plato.Plato.findBynombre", Plato.class);
		query.setParameter("nombre", plato.getNombre());
		Plato platoAux = null;

		try {
			platoAux = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("No existe un plato con el mismo nombre luego se puede crear");
		}

		Plato newPlato = null;

		if (platoAux == null) {
			if (plato instanceof TComida) {
				Comida comidaAux = new Comida();
				comidaAux.transferToEntity(plato);
				newPlato = comidaAux;

			} else if (plato instanceof TBebida) {
				Bebida bebidaAux = new Bebida();
				bebidaAux.transferToEntity(plato);
				newPlato = bebidaAux;
			}

			em.persist(newPlato);
			
			try{
				transaction.commit();
				res = newPlato.getID();
			}
			catch(Exception e){
				transaction.rollback();
			}
		} else if (!platoAux.getActivo()) {
			platoAux.transferToEntity(plato);
			em.persist(platoAux);
			
			try{
				transaction.commit();
				res = platoAux.getID();
			}
			catch(Exception e){
				transaction.rollback();
			}
		} else {
			transaction.rollback();
			res = -1;
		}

		em.close();
		return res;
	}

	public TPlato buscarPlato(int id_Plato) {
		FactoriaEntityManager factoriaEntityManager = FactoriaEntityManager.getInstance();
		EntityManager entityManager = factoriaEntityManager.generarEntityManager();

		Plato plato = entityManager.find(Plato.class, id_Plato);

		if (plato == null) {
			return null;
		}

		TPlato tPlato = entityToTransfer(plato);

		entityManager.close();
		return tPlato;
	}

	public List<TPlato> buscarTodosPlato() {
		FactoriaEntityManager factoriaEntityManager = FactoriaEntityManager.getInstance();
		EntityManager entityManager = factoriaEntityManager.generarEntityManager();

		TypedQuery<Plato> query = entityManager.createQuery("SELECT P FROM Plato P WHERE P.activo = 1", Plato.class);
		List<Plato> platos = query.getResultList();

		List<TPlato> tPlatos = new ArrayList<TPlato>();
		for (Plato plato : platos) {

			TPlato tPlato = entityToTransfer(plato);
			tPlatos.add(tPlato);
		}

		entityManager.close();
		return tPlatos;
	}

	public int modificarPlato(TPlato plato) {
		int res = -1;

		EntityManager eManager = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction transaction = eManager.getTransaction();

		transaction.begin();
		Plato pl = eManager.find(Plato.class, plato.getId());
		Plato platoAux = null;

		if (pl != null) {
			if (!pl.getNombre().equals(plato.getNombre())) {
				try {
					platoAux = eManager
							.createQuery("SELECT P FROM Plato P WHERE P.nombre = '" + plato.getNombre() + "'",
									Plato.class)
							.getSingleResult();
				} catch (NoResultException e) {
					platoAux = null;
				}
				if (platoAux != null){
					return res;
					
				}

			}
			
			pl.transferToEntity(plato);
			eManager.persist(pl);
			 try{
				transaction.commit();
				res = 1;
			 }
			 catch(Exception e){
				transaction.rollback();
			 }
			
		} else {
			transaction.rollback();
		
		}

		eManager.close();
		return res;
	}

	public int bajaPlato(int id_plato) {
		int res = -1;

		EntityManager eManager = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction transaction = eManager.getTransaction();

		transaction.begin();

		Plato pl = eManager.find(Plato.class, id_plato);

		if (pl != null && pl.getActivo()) {
			pl.setActivo(false);
			eManager.persist(pl);
			try{
				transaction.commit();
				res = 1;
			}
			catch(Exception e){
				transaction.rollback();
			}
		} else {
			transaction.rollback();
		}
		eManager.close();

		return res;
	}

	public int aniadirIngredienteAPlato(int id_ingrediente, int id_plato) {
		int res = 0;

		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();

		Ingrediente ingrediente = em.find(Ingrediente.class, id_ingrediente, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		Plato plato = em.find(Plato.class, id_plato);

		if (ingrediente != null && plato != null && ingrediente.isActivo() && plato.getActivo()) {
			List<Ingrediente> ingredientesList = plato.getIngredientes();

			if (!ingredientesList.contains(ingrediente)) {
				ingredientesList.add(ingrediente);
				
			}

			try{
				transaccion.commit();
				res = 1;
			}
			catch(Exception e){
				transaccion.rollback();
				res =-1;
			}
			
		} else {
			transaccion.rollback();
			res = -1;
		}

		em.close();

		return res;
	}

	public int eliminarIngredienteAPlato(int id_ingrediente, int id_plato) {
		int i = 0;
		boolean encontrado = false;

		FactoriaEntityManager factoriaEntityManager = FactoriaEntityManager.getInstance();
		EntityManager entityManager = factoriaEntityManager.generarEntityManager();

		EntityTransaction transaccion = entityManager.getTransaction();
		transaccion.begin();

		Plato p = entityManager.find(Plato.class, id_plato);

		if (p == null) {
			transaccion.rollback();
			entityManager.close();
			return -1;
		}

		while (i < p.getIngredientes().size() && !encontrado) {
			if (p.getIngredientes().get(i).getId() == id_ingrediente) {
				p.getIngredientes().remove(i);
				encontrado = true;
				try{
					transaccion.commit();
				}
				catch(Exception e){
					transaccion.rollback();
					entityManager.close();
					return -1;
					
					
				}
			}
			i++;
		}

		entityManager.close();
		return id_plato;
	}

	public TPlatoConIngredientes mostrarIngredientesPlato(int id_plato) {
		FactoriaEntityManager factoriaEntityManager = FactoriaEntityManager.getInstance();
		EntityManager entityManager = factoriaEntityManager.generarEntityManager();
		TPlatoConIngredientes tPlatoConIngredientes = null;

		Plato plato = entityManager.find(Plato.class, id_plato);

		if (plato != null) {
			List<Ingrediente> ingredientes = plato.getIngredientes();

			List<TIngrediente> tIngredientes = new ArrayList<TIngrediente>();
			for (Ingrediente ingrediente : ingredientes) {

				TIngrediente tIngrediente = new TIngrediente(ingrediente.getId(), ingrediente.getNombre(),
						ingrediente.getCalorias(), ingrediente.isActivo());
				tIngredientes.add(tIngrediente);
			}

			TPlato tPlato = entityToTransfer(plato);

			tPlatoConIngredientes = new TPlatoConIngredientes(tPlato, tIngredientes);

		}

		entityManager.close();

		return tPlatoConIngredientes;
	}
	
	private TPlato entityToTransfer(Plato plato){
		TPlato tPlato = null;

		if (plato instanceof Comida) {
			Comida comida = (Comida) plato;
			tPlato = new TComida(plato.getID(), plato.getNombre(), plato.getDescripcion(), plato.getPrecio(),
					plato.getStock(), plato.getActivo(), comida.getCategoria());
		} else if (plato instanceof Bebida) {
			Bebida bebida = (Bebida) plato;
			tPlato = new TBebida(plato.getID(), plato.getNombre(), plato.getDescripcion(), plato.getPrecio(),
					plato.getStock(), plato.getActivo(), bebida.getVolumen());
		}
		return tPlato;
	}
	
	
}