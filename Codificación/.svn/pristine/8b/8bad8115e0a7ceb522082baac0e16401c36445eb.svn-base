package Negocio.Turno;

import java.util.ArrayList;
import java.util.List;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Personal.Personal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;

public class SATurnoImp implements SATurno {

	public int altaTurno(TTurno turno) {
		int res = 0;
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		TypedQuery<Turno> query = em.createNamedQuery("Negocio.Turno.Turno.findBynombre", Turno.class);
		query.setParameter("nombre", turno.getNombre());
		Turno aux = null;

		try {
			aux = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("No existe un turno con el mismo nombre luego se puede crear");
		}

		if (aux == null) {
			Turno nuevo_turno = new Turno();
			nuevo_turno.transferToEntity(turno);
			nuevo_turno.setNumPersonal(0);
			em.persist(nuevo_turno);
			try {			
				transaction.commit();
				res = nuevo_turno.getIdTurno();
			} catch (Exception e) {
				res=-1;
				transaction.rollback();
			}
			
		} else if (!aux.getActivo()) { 
			aux.transferToEntity(turno);
			em.persist(aux);
			try {
				transaction.commit();
				res = aux.getIdTurno();
			} catch (Exception e) {
				res=-1;
				transaction.rollback();
			}
			
		} else {
			transaction.rollback();
			res = -1;
		}
		em.close();

		return res;
	}

	public int bajaTurno(int id_turno) {
		
		int res = 0;
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Turno turno = em.find(Turno.class, id_turno);
		
		if(turno != null && turno.getActivo() && turno.getNumPersonal() == 0){
			em.lock(turno, LockModeType.OPTIMISTIC);
			turno.setActivo(false);
			em.persist(turno);
			try {				
				transaction.commit();
				res = turno.getIdTurno();
			} catch (Exception e) {
				res=-1;
				transaction.rollback();
			}
			
		}
		else{
			transaction.rollback();
			res = -1;
		}
		em.close();

		return res;
	}

	@SuppressWarnings("unused")
	public int modificarTurno(TTurno turno) {
		int res = 0;
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Turno existeYactivo = em.find(Turno.class, turno.getID());
		if (existeYactivo != null && existeYactivo.getActivo()) {
			boolean puede_modificar = false;
			if (!turno.getNombre().equals(existeYactivo.getNombre())) {

				TypedQuery<Turno> query = em.createNamedQuery("Negocio.Turno.Turno.findBynombre", Turno.class);
				query.setParameter("nombre", turno.getNombre());
				Turno turno_aux = null;

				try {
					turno_aux = query.getSingleResult();

				} catch (Exception e) {
					puede_modificar = true;
				}
			}
			else puede_modificar=true;

			if (puede_modificar) {
				existeYactivo.transferToEntity(turno);
				em.persist(existeYactivo);
				try {				
					transaction.commit();
					res = turno.getID();
				} catch (Exception e) {
					res=-1;
					transaction.rollback();
				}
				
			} else {
				transaction.rollback();
				res = -1;
			}
		}
		else{
			transaction.rollback();
			res = -1;
		}
		em.close();
		return res;
	}

	public TTurno buscarUnTurno(int id_turno) {
		FactoriaEntityManager factoriaEM = FactoriaEntityManager.getInstance();
		EntityManager EM = factoriaEM.generarEntityManager();
		EntityTransaction transaction = EM.getTransaction();

		transaction.begin();

		Turno turno = EM.find(Turno.class, id_turno);
		if (turno == null)
			return null;

		TTurno Turno = new TTurno(turno.getIdTurno(), turno.getNombre(), turno.getHoraInicio(), turno.getHoraFin(),
				turno.getActivo());

		try {
			transaction.commit();
		} catch (Exception e) {
			Turno = null;
			transaction.rollback();
		}
		EM.close();
		return Turno;
	}

	public List<TTurno> buscarTodosTurno() {
		FactoriaEntityManager factoriaEM = FactoriaEntityManager.getInstance();
		EntityManager EM = factoriaEM.generarEntityManager();
		TypedQuery<Turno> query = EM.createNamedQuery("Negocio.Turno.Turno.findByactivo", Turno.class);
		query.setParameter("activo", true);

		List<Turno> l = query.getResultList();
		List<TTurno> lista = new ArrayList<TTurno>();

		for (Turno turno : l) {
			TTurno t = new TTurno(turno.getIdTurno(), turno.getNombre(), turno.getHoraInicio(), turno.getHoraFin(),
					turno.getActivo());
			lista.add(t);
		}
		EM.close();
		return lista;
	}

	public Double calcularNominaTurno(int id_turno) {
		double nomina = -1;
		EntityManager em = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Turno turno = em.find(Turno.class, id_turno, LockModeType.OPTIMISTIC);
		if(turno != null && turno.getActivo()){
			nomina = 0;
			for(Personal pers: turno.getListaPersonal()){
				em.lock(pers, LockModeType.OPTIMISTIC);
				nomina += pers.getSueldo(); 
			}
			try{
			transaction.commit();
			}catch(Exception e){
				nomina=-1;
				transaction.rollback();
			}
		}else{
			transaction.rollback();
		}
		
		em.close();
		
		return nomina;
	}
}