package Negocio.Seccion;

import java.util.List;

public interface SASeccion {

	public int altaSeccion(TSeccion seccion);

	public TSeccion buscarSeccion(int id);

	public List<TSeccion> buscarTodosSeccion();

	public int actualizarSeccion(TSeccion seccion);

	public int eliminarSeccion(int id);

	public int buscarSeccionPorZonaPasillo(String zona, int pasillo);
}