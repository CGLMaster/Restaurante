package Negocio.ClienteRest;

import java.util.ArrayList;
import java.util.List;

import Negocio.FactoriaEntityManager.FactoriaEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;



public class SAClienteRestImp implements SAClienteRest {

	public int altaClienteRest(TClienteRest cliente) {
		int id = -1;
		if(cliente.getNombre().trim().length() == 0){
			return -1;
		}
		if(!validarDNI(cliente.getDni())){
			return -1;
		}
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		TypedQuery<ClienteRest> q = em.createNamedQuery("Negocio.ClienteRest.ClienteRest.findBydni", ClienteRest.class);
		q.setParameter("dni", cliente.getDni());
		ClienteRest c2 = null;
		try {
			c2 = q.getSingleResult();
		} catch (Exception e) {
			System.out.println("No existe un cliente con el mismo dni luego se puede crear");
		}
		if(c2 == null){
			ClienteRest c = new ClienteRest();
			c.transfertoEntity(cliente);
			em.persist(c);
			try{
			  et.commit();
			  id = c.getId();
			}catch(Exception e){
			  et.rollback();
			}

			
		}else if(c2 != null &&!c2.getActivo()){
			c2.transfertoEntity(cliente);
			em.persist(c2);
			try{
			et.commit();
			id = c2.getId();
			}catch(Exception e){
			et.rollback();
			}
			
			
		}else {
			et.rollback();
		}
		em.close();
		return id;
		
	}

	
	public int bajaClienteRest(int cliente) {
		int id = -1;
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		ClienteRest c2 = em.find(ClienteRest.class, cliente);
		if(c2 != null && c2.getActivo()){
			c2.setActivo(false);
			em.persist(c2);
			try{
			et.commit();
			id =1;
			}catch(Exception e){
			et.rollback();
			}
		}else if(c2 == null || !c2.getActivo()){
			et.rollback();
		}else {
			et.rollback();
		}
		em.close();
		return id;
	}

	
	public int modificarClienteRest(TClienteRest cliente) {
		int exito = -1;
		
		if(cliente.getNombre().trim().length() == 0){
			return -1;
		}
		if(!validarDNI(cliente.getDni())){
			return -1;
		}
		
		FactoriaEntityManager factory = FactoriaEntityManager.getInstance();
		EntityManager manager = factory.generarEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin(); 
		ClienteRest c2 = manager.find(ClienteRest.class, cliente.getID());
		@SuppressWarnings("rawtypes")
		TypedQuery q = manager.createNamedQuery("Negocio.ClienteRest.ClienteRest.findBydni", ClienteRest.class);
		q.setParameter("dni", cliente.getDni());
		ClienteRest c = null;
		try {
			c = (ClienteRest) q.getSingleResult();
		} catch (Exception e) {}
		
		if(c2!=null && (c == null || cliente.getDni().equals(c2.getDni()))){
			c2.transfertoEntity(cliente);;
			manager.persist(c2);
			try{
			transaction.commit();
			exito = 1;
			}catch(Exception e){
			transaction.rollback();
			}
		}else{
			transaction.rollback();
		}		
		
		return exito;
	}

	
	public TClienteRest buscarUnClienteRest(int id) {
		TClienteRest cliente = null;
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		ClienteRest clienteR = em.find(ClienteRest.class, id);
		if (clienteR != null) {
			cliente = new TClienteRest(id, clienteR.getNombre(), clienteR.getApellidos(), clienteR.getDni(), clienteR.getActivo());
		}
		em.close();
		return cliente;
	}
	
	public List<TClienteRest> buscarTodosClienteRest() {
		FactoriaEntityManager emf = FactoriaEntityManager.getInstance();
		EntityManager em = emf.generarEntityManager();
		TypedQuery<ClienteRest> q = em.createNamedQuery("Negocio.ClienteRest.ClienteRest.findByactivo", ClienteRest.class);
		q.setParameter("activo", true);
		List<ClienteRest> clientes = q.getResultList();
		List<TClienteRest> tclientes = new ArrayList<TClienteRest>();
		for(ClienteRest  clienteR  : clientes){
			TClienteRest c =  new TClienteRest(clienteR.getId(), clienteR.getNombre(), clienteR.getApellidos(), clienteR.getDni(),clienteR.getActivo());
			tclientes.add(c);
		}
		em.close();
		return tclientes;
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