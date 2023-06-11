package Negocio.Proveedor;

import java.util.List;
import Integración.FactoriaIntegracion.FactoriaIntegracion;
import Integración.Proveedor.DAOProveedor;
import Negocio.Producto.TDistribuye;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

public class SAProveedorImp implements SAProveedor {

	DAOProveedor daoProveedor;

	public SAProveedorImp() {
		daoProveedor = FactoriaIntegracion.getInstance().getDAOProveedor();
	}

	public int altaProveedor(String nombre) {
		if (nombre.trim().length() == 0) {
			return -1;
		}

		try {
			TransactionManager transactionManager = TransactionManager.getInstance();
			Transaction transaccion = transactionManager.nuevaTransaccion();
			transaccion.start();

			int res = -1;
			TProveedor proveedor = daoProveedor.buscarProveedorPorNombre(nombre);

			if (proveedor == null) {
				res = daoProveedor.altaProveedor(nombre);
			} else if (!proveedor.getActivo()) {
				res = daoProveedor.reactivar(proveedor.getID());
			}

			if (res == -1)
				transaccion.rollback();
			else
				transaccion.commit();

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public TProveedor buscarProveedor(int id) {
		return daoProveedor.buscarProveedor(id);
	}

	public List<TProveedor> buscarTodosProveedor() {
		return daoProveedor.buscarTodosProveedor();
	}

	public int modificarProveedor(TProveedor proveedorDatosNuevos) {
		if (proveedorDatosNuevos.getNombre().trim().length() == 0) {
			return -1;
		}

		try {
			Transaction transaccion;
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();

			int res = -1;

			TProveedor proveedorAModificar = daoProveedor.buscarProveedor(proveedorDatosNuevos.getID());

			if (proveedorAModificar == null) {
				transaccion.rollback();
				return -1;
			}

			TProveedor proveedorConMismoNombre = daoProveedor
					.buscarProveedorPorNombre(proveedorDatosNuevos.getNombre());
			if (proveedorConMismoNombre != null) {
				transaccion.rollback();
				return -1;
			}

			res = daoProveedor.modificarProveedor(proveedorDatosNuevos);

			if (res == -1)
				transaccion.rollback();
			else
				transaccion.commit();

			return res;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int eliminarProveedor(int id) {
		try {

			Transaction transaccion;
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();

			int res = -1;

			TProveedor proveedor = daoProveedor.buscarProveedor(id);

			if (proveedor != null && proveedor.getActivo() == true) {
				List<TDistribuye> productosVinculados = FactoriaIntegracion.getInstance().getDAODistribuye()
						.buscarTodosVinculosProveedor(proveedor.getID());
				if (productosVinculados.size() == 0)
					res = daoProveedor.eliminarProveedor(id);
			}

			if (res == -1)
				transaccion.rollback();
			else
				transaccion.commit();

			return res;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}