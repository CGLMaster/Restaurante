package Negocio.Transaction;

public abstract class TransactionManager {

	private static TransactionManager instancia = null;

	public abstract Transaction nuevaTransaccion() throws Exception;

	public abstract Transaction getTransaccion() throws Exception;

	public abstract void eliminaTransaccion() throws Exception;

	public static TransactionManager getInstance() {

		if (instancia == null) {
			instancia = new TransactionManagerImp();
		}
		return instancia;
	}
}