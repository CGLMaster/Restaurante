package Negocio.Seccion;

import java.util.List;

import Integración.FactoriaIntegracion.FactoriaIntegracion;
import Integración.Seccion.DAOSeccion;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

public class SASeccionImp implements SASeccion {

	public int altaSeccion(TSeccion seccion) {
		int exito = -1;
		try {
			if (seccion.getPasillo() < 0) {
				throw new NumberFormatException("Pasillo negativo");
			}
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			DAOSeccion daoSeccion = f.getDAOSeccion();
			int id = daoSeccion.buscarSeccionPorZonaPasillo(seccion.getZona(), seccion.getPasillo());
			TSeccion s = daoSeccion.buscarSeccion(id);
			if (s == null) {
				exito = daoSeccion.altaSeccion(seccion);
				t.commit();
			} else if (s != null && !s.getActivo()) {
				seccion.setId_seccion(id);
				daoSeccion.reactivar(seccion.getID_Seccion());
				exito = daoSeccion.modificarSeccion(seccion);
				t.commit();

			} else if (s != null && s.getActivo()) {
				t.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exito;
	}

	public TSeccion buscarSeccion(int id) {
		return FactoriaIntegracion.getInstance().getDAOSeccion().buscarSeccion(id);
	}

	public List<TSeccion> buscarTodosSeccion() {
		return FactoriaIntegracion.getInstance().getDAOSeccion().buscarTodosSeccion();
	}

	public int buscarSeccionPorZonaPasillo(String zona, int pasillo) {
		return FactoriaIntegracion.getInstance().getDAOSeccion().buscarSeccionPorZonaPasillo(zona, pasillo);
	}

	public int actualizarSeccion(TSeccion seccion) {
		int exito = -1;
		try {
			if (seccion.getPasillo() < 0) {
				throw new NumberFormatException("Pasillo negativo");
			}
			Transaction transaccion;
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();
			
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			DAOSeccion daoSeccion = f.getDAOSeccion();

			int id = daoSeccion.buscarSeccionPorZonaPasillo(seccion.getZona(), seccion.getPasillo());

			if (id == -1) {
				exito = daoSeccion.modificarSeccion(seccion);
				transaccion.commit();
			} else {
				transaccion.rollback();
				exito = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			exito = -1;
		}
		return exito;
	}

	public int eliminarSeccion(int id) {
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			DAOSeccion daoSeccion = f.getDAOSeccion();
			TSeccion c = daoSeccion.buscarSeccion(id);
			if (c != null && c.getActivo() && c.getCont_productos() == 0) {
				exito = daoSeccion.bajaSeccion(id);
				t.commit();
			} else if (c != null && !c.getActivo()) {
				t.rollback();
			} else {
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}
}