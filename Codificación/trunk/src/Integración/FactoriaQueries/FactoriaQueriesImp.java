package Integración.FactoriaQueries;

import Integración.Queries.ComprasPorRangoFechaPrecio;
import Integración.Queries.ProductosPorRangoPrecio;
import Integración.Queries.Query;

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