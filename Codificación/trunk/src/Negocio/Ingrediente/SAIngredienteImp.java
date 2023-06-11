package Negocio.Ingrediente;

import java.util.ArrayList;
import java.util.List;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class SAIngredienteImp implements SAIngrediente {

	public int aniadirIngrediente(TIngrediente ingrediente) {
		int res = 0;
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Query q = em.createNamedQuery("Negocio.Ingrediente.Ingrediente.findBynombre");
		q.setParameter("nombre", ingrediente.getNombre());
		Ingrediente ing = null;

		try {
			ing = (Ingrediente) q.getSingleResult();
		} catch (Exception e) {
			System.out.println("No existe un ingrediente con el mismo nombre luego se puede crear");
		}

		if (ing == null) {
			Ingrediente i = new Ingrediente();
			i.transferToEntity(ingrediente);
			em.persist(i);
			try {
				transaction.commit();
				res = i.getId();
			} catch (Exception e) {
				transaction.rollback();
				res = -1;
			}

		} else if (ing != null && !ing.isActivo()) {
			ing.transferToEntity(ingrediente);
			try {
				transaction.commit();
				res = ing.getId();
			} catch (Exception e) {
				transaction.rollback();
				res = -1;
			}

		} else {
			transaction.rollback();
			res = -1;
		}

		em.close();
		return res;
	}

	public int eliminarIngrediente(int id) {
		int res = -1;

		FactoriaEntityManager emfactory = FactoriaEntityManager.getInstance();
		EntityManager em = emfactory.generarEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Ingrediente ingred = em.find(Ingrediente.class, id);

		if (ingred != null && ingred.isActivo() && ingred.getPlatos().size() == 0) {
			em.lock(ingred, LockModeType.OPTIMISTIC);
			ingred.setActivo(false);
			em.persist(ingred);
			try {
				et.commit();
				res = 1;
			} catch (Exception e) {
				et.rollback();
				res = -1;
			}

		} else {
			et.rollback();
		}

		em.close();
		return res;
	}

	public TIngrediente buscarIngrediente(int id) {
		FactoriaEntityManager factoriaEntityManager = FactoriaEntityManager.getInstance();
		EntityManager entityManager = factoriaEntityManager.generarEntityManager();

		EntityTransaction transaccion = entityManager.getTransaction();
		transaccion.begin();

		Ingrediente ingrediente = entityManager.find(Ingrediente.class, id);

		if (ingrediente == null) {
			return null;
		}

		TIngrediente tIngrediente = new TIngrediente();

		tIngrediente.setID(ingrediente.getId());
		tIngrediente.setNombre(ingrediente.getNombre());
		tIngrediente.setCalorias(ingrediente.getCalorias());
		tIngrediente.setActivo(ingrediente.isActivo());

		try {
			transaccion.commit();
		} catch (Exception e) {
			tIngrediente = null;
			transaccion.rollback();
		}
		entityManager.close();

		return tIngrediente;
	}

	public List<TIngrediente> buscarTodosIngredientes() {
		FactoriaEntityManager factoriaEntityManager = FactoriaEntityManager.getInstance();

		EntityManager entityManager = factoriaEntityManager.generarEntityManager();

		TypedQuery<Ingrediente> query = entityManager.createQuery("SELECT I FROM Ingrediente I WHERE I.activo = 1",
				Ingrediente.class);
		List<Ingrediente> ingredientes = query.getResultList();

		List<TIngrediente> tIngredientes = new ArrayList<TIngrediente>();
		for (Ingrediente ingrediente : ingredientes) {
			TIngrediente c = new TIngrediente(ingrediente.getId(), ingrediente.getNombre(), ingrediente.getCalorias(),
					ingrediente.isActivo());
			tIngredientes.add(c);
		}

		entityManager.close();
		return tIngredientes;
	}

	public int editarIngrediente(TIngrediente ingredienteEditado) {
		int exito = -1;

		if (ingredienteEditado.getNombre().trim().length() == 0) {
			return -1;
		}

		FactoriaEntityManager factory = FactoriaEntityManager.getInstance();
		EntityManager em = factory.generarEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Ingrediente ingredienteOriginal = em.find(Ingrediente.class, ingredienteEditado.getID());

		if (ingredienteOriginal == null) {
			transaction.rollback();
			return -1;
		}

		if (!ingredienteOriginal.getNombre().equals(ingredienteEditado.getNombre())) {

			TypedQuery<Ingrediente> queryBuscarPorNombre = em
					.createNamedQuery("Negocio.Ingrediente.Ingrediente.findBynombre", Ingrediente.class);
			queryBuscarPorNombre.setParameter("nombre", ingredienteEditado.getNombre());
			Ingrediente ingredienteBuscadoPorNombre = null;

			try {
				ingredienteBuscadoPorNombre = queryBuscarPorNombre.getSingleResult();
			} catch (Exception e) {

			}

			if (ingredienteBuscadoPorNombre != null) {
				transaction.rollback();
				return -1;
			}
		}

		ingredienteOriginal.transferToEntity(ingredienteEditado);
		em.persist(ingredienteOriginal);
		try {
			transaction.commit();
			exito = ingredienteOriginal.getId();
		} catch (Exception e) {
			exito = -1;
			transaction.rollback();
		}
		em.close();

		return exito;
	}
}