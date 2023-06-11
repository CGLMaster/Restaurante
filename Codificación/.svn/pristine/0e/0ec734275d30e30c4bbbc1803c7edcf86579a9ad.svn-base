package Negocio.Personal;

import java.util.ArrayList;
import java.util.List;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import Negocio.Turno.Turno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;


public class SAPersonalImp implements SAPersonal {
	
	public int altaPersonal(TPersonal personal) {
		int id = -1;
		if(!validarDNI(personal.getDni())){
			return -1;
		}
		EntityManager em  = FactoriaEntityManager.getInstance().generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		TypedQuery<Personal> q = em.createNamedQuery("Negocio.Personal.Personal.findBydni",Personal.class);
		q.setParameter("dni", personal.getDni());
		Personal p2 = null;
		Personal p = null;
		try {
			p2 = q.getSingleResult();
		} catch (Exception e) {}
		Turno t = em.find(Turno.class,personal.getIDTurno(),LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if(t != null && t.getActivo()){
		 if(p2 == null){                              
			if(personal instanceof TTiempoCompleto){
				TiempoCompletoP aux = new TiempoCompletoP();
				aux.transferToEntity(personal);
				aux.setTurno(t);
				t.setNumPersonal(t.getNumPersonal() + 1);
				p = aux;
			}else {
				TiempoParcialP aux = new TiempoParcialP();
				aux.transferToEntity(personal);
				aux.setTurno(t);
				t.setNumPersonal(t.getNumPersonal() + 1);
				p = aux;
			}
			
			em.persist(p);
			try{
				et.commit();
				id = p.getID();
			}catch(Exception e){
				et.rollback();
			}
		  }
		  else if(p2 != null && !p2.getActivo()){
			int numPedidos = p2.getNumPedidos();
			p2.transferToEntity(personal);
			p2.setNumPedidos(numPedidos);
			if(t != p2.getTurno()){
				p2.setTurno(t);
				t.setNumPersonal(t.getNumPersonal() + 1);
			}
			em.persist(p2);
			try{
				et.commit();
				id = p2.getID();
			}catch(Exception e){
				et.rollback();
			}
		  }
		  else {
			  et.rollback();
		  }
		}
		else
			et.rollback();
		
		em.close();
		return id;
		
	}

	
	public int bajaPersonal(int id) {
		int salida = -1;
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Personal p2 = em.find(Personal.class, id);
		if(p2 != null && p2.getActivo()){
			p2.setActivo(false);
			Turno t = em.find(Turno.class, p2.getTurno().getIdTurno(),LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			t.setNumPersonal(t.getNumPersonal() - 1);
			try{
				et.commit();
				salida = 1;
			}catch(Exception e){
				et.rollback();
			}
		}else if(p2 == null || !p2.getActivo()){
			et.rollback();
		}else {
			et.rollback();
		}
		em.close();
		return salida;
	}

	public int modificarPersonal(TPersonal personal) {
		int exito = -1;
		if(!validarDNI(personal.getDni())){
			return -1;
		}
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin(); 
		Personal c2 = em.find(Personal.class, personal.getIDPersonal());
		TypedQuery<Personal> q = em.createNamedQuery("Negocio.Personal.Personal.findBydni", Personal.class);
		q.setParameter("dni", personal.getDni());
		Personal c = null;
		try {
			c = q.getSingleResult();
		} catch (Exception e) {}
		
		if(c2!=null &&(c == null || personal.getDni().equals(c2.getDni()))){
			Turno t = em.find(Turno.class,personal.getIDTurno());
			if(t != null && t.getActivo()){
				c2.transferToEntity(personal);
				if (t != c2.getTurno()){
					t.setNumPersonal(t.getNumPersonal() + 1);
					c2.getTurno().setNumPersonal(c2.getTurno().getNumPersonal() - 1);
					c2.setTurno(t);
					
				}			
				try{
					et.commit();
					exito = 1;
				}catch(Exception e){
					et.rollback();
				}
			}
			else
				et.rollback();
		}else{
			et.rollback();
		}		
		
		return exito;
	}

	
	public TPersonal buscarUnPersonalPorID(int id) {
		TPersonal p = null;
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		Personal personalR = em.find(Personal.class, id);
		
		if (personalR == null) {
			return null;
		}
		else {
			p =entityToTransfer(personalR);
		}
		em.close();
		return p;
	}

	
	public TPersonal buscarUnPersonalPorDNI(String dni) {
		TPersonal p = null;
		Personal personalR = null;
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		TypedQuery<Personal> query = em.createNamedQuery("Negocio.Personal.Personal.findBydni", Personal.class);
		query.setParameter("dni", dni);
		
		try {
			personalR = query.getSingleResult();
		}
		catch (Exception e) {
			return null;
		}
		p =entityToTransfer(personalR);
		em.close();
		return p;
	}

	
	public List<TPersonal> buscarTodosPersonal() {
		List<Personal> personalLista = new ArrayList<Personal>();
		TPersonal personal = null;
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		TypedQuery<Personal> query = em.createNamedQuery("Negocio.Personal.Personal.findByactivo", Personal.class);
		query.setParameter("activo",true);
		personalLista = query.getResultList();
		
		List<TPersonal> tPersonalLista = new ArrayList<TPersonal>();
		for (Personal personalR : personalLista) {
			personal =entityToTransfer(personalR);
			tPersonalLista.add(personal);
		}
		em.close();
		return tPersonalLista;
	}

	public TPersonal identificarPersonal(int id) {
		TPersonal personal = null;
		FactoriaEntityManager factory = FactoriaEntityManager.getInstance();
		EntityManager manager = factory.generarEntityManager();
		Personal personalR = manager.find(Personal.class, id);
		
		try{
			if(personalR != null && personalR.getActivo()){
				personal =entityToTransfer(personalR);
			}
		}catch(Exception e){
			return null;
		}	
		manager.close();
		return personal;
		}
	
	private TPersonal entityToTransfer(Personal personalR){
		TPersonal personal =null;
		if (personalR instanceof TiempoCompletoP) {
			 personal = new TTiempoCompleto(personalR.getID(), personalR.getNombre(), personalR.getDni(), 
					personalR.getNumPedidos(), personalR.getSueldo(), personalR.getActivo(), personalR.getTurno().getIdTurno(),
					((TiempoCompletoP) personalR).getNomina(), ((TiempoCompletoP) personalR).getHorasExtra());
			}
			else if (personalR instanceof TiempoParcialP) {
				personal = new TTiempoParcial(personalR.getID(), personalR.getNombre(), personalR.getDni(),
						personalR.getNumPedidos(), personalR.getSueldo(), personalR.getActivo(), personalR.getTurno().getIdTurno()
						,((TiempoParcialP) personalR).getPrecioHora(), ((TiempoParcialP) personalR).getNumeroHoras());
			}
		return personal;
	}
	
	public boolean validarDNI(String DNI) {
		boolean ok = false;

		if (DNI.length() == 9) {
			ok = true;
			String nums = DNI.substring(0, 8);
			try {
				Integer.parseInt(nums);
			} catch (NumberFormatException e) {
				ok = false;
			}

			if (ok) {
				nums = DNI.substring(8);
				try {
					Integer.parseInt(nums);
					ok = false;
				} catch (NumberFormatException e) {
				}
			}
		}

		return ok;
	}
}