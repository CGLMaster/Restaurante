package Negocio.Transaction;

public class TransactionFactoryImp extends TransactionFactory {

	public Transaction generaTransaccion() throws Exception {
		return new TransactionMySQL();
	}
}