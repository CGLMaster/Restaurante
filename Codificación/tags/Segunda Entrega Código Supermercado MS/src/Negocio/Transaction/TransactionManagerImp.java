package Negocio.Transaction;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImp extends TransactionManager {
	
	private ConcurrentHashMap<Thread, Transaction> concurrentHashMap;
	
	public TransactionManagerImp(){
		concurrentHashMap = new ConcurrentHashMap<Thread, Transaction>();
	}
	
	public Transaction nuevaTransaccion() throws Exception{
		if(concurrentHashMap.get(Thread.currentThread()) == null){
			Transaction t = TransactionFactory.getInstance().generaTransaccion();
			concurrentHashMap.put(Thread.currentThread(), t);
			return t;
		}
		
		throw new Exception();
	}

	
	public Transaction getTransaccion() throws Exception{
		if(concurrentHashMap.get(Thread.currentThread()) != null){
			return concurrentHashMap.get(Thread.currentThread());
		}
		throw new Exception();
	}

	public void eliminaTransaccion() throws Exception{
		if(concurrentHashMap.get(Thread.currentThread()) != null){
			concurrentHashMap.remove(Thread.currentThread());
		}
		else throw new Exception();
	}
}