package Negocio.Marca;

import java.util.List;
import Integración.FactoriaIntegracion.FactoriaIntegracion;
import Integración.Marca.DAOMarca;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

public class SAMarcaImp implements SAMarca {

	public SAMarcaImp() {

	}

	public int altaMarca(TMarca marca) {
		int res = -1;
		try {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOMarca daomarca = FactoriaIntegracion.getInstance().getDAOMarca();
			TMarca marcaBusc = daomarca.buscarMarcaPorNombre(marca.getNombre());

			if (marcaBusc == null) {
				res = daomarca.altaMarca(marca);
				t.commit();
			} else if (!marcaBusc.getActivo()) {
				daomarca.reactivar(marcaBusc.getID());
				marca.setID(marcaBusc.getID());
				daomarca.modificarMarca(marca);
				res = marca.getID();
				t.commit();
			} else {
				t.rollback();
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		return res;
	}

	public TMarca buscarMarca(int id_marca) {
		return FactoriaIntegracion.getInstance().getDAOMarca().buscarMarca(id_marca);
	}

	public List<TMarca> buscarTodosMarca() {
		return FactoriaIntegracion.getInstance().getDAOMarca().buscarTodosMarca();
	}

	public int modificarMarca(TMarca marca) {
		try {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOMarca dao = FactoriaIntegracion.getInstance().getDAOMarca();
			TMarca m = dao.buscarMarca(marca.getID());
			if (m != null) {
				TMarca aux = null;
				if (!marca.getNombre().equals(m.getNombre())) {
					aux = dao.buscarMarcaPorNombre(marca.getNombre());
				}
				if (aux == null) {
					int res = dao.modificarMarca(marca);
					if (res == 1)
						t.commit();
					else {
						t.rollback();
						throw new Exception();
					}
				} else {
					t.rollback();
					throw new Exception();
				}
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public int eliminarMarca(int id_marca) {
		try {
			Transaction t = TransactionManager.getInstance().nuevaTransaccion();
			t.start();
			DAOMarca dao = FactoriaIntegracion.getInstance().getDAOMarca();
			TMarca aux = dao.buscarMarca(id_marca);
			if (aux != null && aux.getActivo() && aux.getContProductos() == 0) {
				int res = dao.bajaMarca(id_marca);
				if (res == 1)
					t.commit();
				else {
					t.rollback();
					throw new Exception();
				}
			} else {
				t.rollback();
				throw new Exception();
			}

			return 1;

		} catch (Exception e) {
			return -1;
		}
	}
}