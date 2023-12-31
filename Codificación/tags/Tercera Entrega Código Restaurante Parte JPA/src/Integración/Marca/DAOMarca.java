package Integración.Marca;

import Negocio.Marca.TMarca;

import java.util.List;

public interface DAOMarca {

	public int altaMarca(TMarca marca);

	public TMarca buscarMarca(int id);

	public List<TMarca> buscarTodosMarca();

	public int modificarMarca(TMarca marca);

	public int bajaMarca(int id);

	public TMarca buscarMarcaPorNombre(String nombre);

	public int reactivar(int id_marca);
}