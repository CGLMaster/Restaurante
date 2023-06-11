package Presentación.Command;

public abstract class CommandFactory {

	private static CommandFactory instancia;

	public static CommandFactory getInstance() {
		if (instancia == null)
			instancia = new CommandFactoryImp();

		return instancia;
	}

	public abstract Command getCommand(int evento);
}