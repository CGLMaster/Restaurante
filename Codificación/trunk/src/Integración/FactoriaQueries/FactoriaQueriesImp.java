package Integraci�n.FactoriaQueries;

import Integraci�n.Queries.ComprasPorRangoFechaPrecio;
import Integraci�n.Queries.ProductosPorRangoPrecio;
import Integraci�n.Queries.Query;

public class FactoriaQueriesImp extends FactoriaQueries {

	public Query getNewQuery(String nombre) {
		switch (nombre) {
		case "ProductosPorRangoPrecio":
			return new ProductosPorRangoPrecio();
		case "ComprasPorRangoFechaPrecio":
			return new ComprasPorRangoFechaPrecio();
		}
		return null;
	}
}