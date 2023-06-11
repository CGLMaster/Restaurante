package Negocio.FactoriaEntityManager;

import jakarta.persistence.EntityManager;


public abstract class FactoriaEntityManager {

	private static FactoriaEntityManager instance;

	
	public static FactoriaEntityManager getInstance() {
		if(instance == null) 
			instance = new FactoriaEntityManagerImp();
		return instance;
	}

	public abstract EntityManager generarEntityManager();
}