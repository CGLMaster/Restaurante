package Negocio.Cliente;

import java.util.List;

import Integración.Cliente.DAOCliente;
import Integración.FactoriaIntegracion.FactoriaIntegracion;
import Negocio.Transaction.Transaction;
import Negocio.Transaction.TransactionManager;

public class SAClienteImp implements SACliente {

	public int altaCliente(TCliente cliente) {
		if (cliente.getNombre().trim().length() == 0) {
			return -1;
		}
		if (!validarDNI(cliente.getDNI())) {
			return -1;
		}
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			DAOCliente daoCliente = f.getDAOCliente();
			int id = daoCliente.buscarUnClientePorDni(cliente.getDNI());
			TCliente c = daoCliente.buscarUnCliente(id);
			
			if (id == -1) {
				exito = daoCliente.altaCliente(cliente);
				t.commit();
			} else if (id != -1 && !c.getEsActivo()) {
				cliente.setID_socio(id);
				daoCliente.reactivar(cliente.getIDSocio());
				exito = daoCliente.modificarCliente(cliente);
				t.commit();
			} else if (c != null && c.getEsActivo()) {
				t.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exito;
	}

	public int bajaCliente(int id_trabajador) {
		int exito = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			DAOCliente daoCliente = f.getDAOCliente();
			TCliente c = daoCliente.buscarUnCliente(id_trabajador);
			if (c != null && c.getEsActivo()) {
				exito = daoCliente.bajaCliente(id_trabajador);
				t.commit();
			} else if (c != null && !c.getEsActivo())
				t.rollback();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public int modificarCliente(TCliente cliente) {
		int exito = -1;
		
		if (cliente.getNombre().trim().length() == 0) {
			return -1;
		}

		if (!validarDNI(cliente.getDNI())) {
			return -1;
		}
		try {
			Transaction transaccion;
			transaccion = TransactionManager.getInstance().nuevaTransaccion();
			transaccion.start();

			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			DAOCliente daoCliente = f.getDAOCliente();
			
			int id = daoCliente.buscarUnClientePorDni(cliente.getDNI());
			TCliente c = daoCliente.buscarUnCliente(id);
			if(id == -1 || c.getIDSocio() == cliente.getIDSocio()){
				exito = daoCliente.modificarCliente(cliente);
				transaccion.commit();
			}
			else {
				transaccion.rollback();
				exito = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exito = -1;
		}
		return exito;
	}

	public TCliente buscarUnoCliente(int id) {
		return FactoriaIntegracion.getInstance().getDAOCliente().buscarUnCliente(id);
	}

	public List<TCliente> buscarTodosCliente() {
		return FactoriaIntegracion.getInstance().getDAOCliente().buscarTodosCliente(); 
	}

	@Override
	public int buscarUnoClientePorDni(String dni) {
		int id = -1;
		try {
			TransactionManager transaction = TransactionManager.getInstance();
			Transaction t = transaction.nuevaTransaccion();
			t.start();

			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			DAOCliente daoCliente = f.getDAOCliente();
			id = daoCliente.buscarUnClientePorDni(dni);

			if (id != -1) {
				t.commit();
			} else
				t.rollback();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
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