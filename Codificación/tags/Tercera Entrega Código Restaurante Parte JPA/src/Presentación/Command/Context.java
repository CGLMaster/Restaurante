package Presentación.Command;

public class Context {

	private int evento;

	private Object datos;

	public Context(int evento, Object datos) {
		this.datos = datos;
		this.evento = evento;
	}

	public Context(int evento) {
		this.evento = evento;
	}

	public Object getDatos() {

		return datos;
	}

	public int getEvento() {
		return evento;
	}

	public void CONTEXT(int evento, Object datos) {

	}

	public void CONTEXT(int evento) {

	}

}