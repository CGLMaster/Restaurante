package Negocio.Transaction;

public abstract class TransactionFactory {

	private static TransactionFactory instancia = null;

	public static TransactionFactory getInstance() {
		if (instancia == null) {
			instancia = new TransactionFactoryImp();
		}
		return instancia;
	}

	public abstract Transaction generaTransaccion() throws Exception;
}