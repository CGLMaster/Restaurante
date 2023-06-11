package Negocio.FactoriaEntityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class FactoriaEntityManagerImp extends FactoriaEntityManager {
	EntityManagerFactory factory;
	
	public FactoriaEntityManagerImp(){
		factory = Persistence.createEntityManagerFactory("CodificacionSuperRest");
	}
	
	public EntityManager generarEntityManager() {
		return factory.createEntityManager();
	}
}