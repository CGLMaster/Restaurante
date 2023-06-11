package Integraci�n.FactoriaQueries;

import Integraci�n.Queries.Query;

public abstract class FactoriaQueries {

	private static FactoriaQueries instancia;

	public static FactoriaQueries getInstance() {
		if (instancia == null)
			instancia = new FactoriaQueriesImp();

		return instancia;
	}

	public abstract Query getNewQuery(String nombre);
}