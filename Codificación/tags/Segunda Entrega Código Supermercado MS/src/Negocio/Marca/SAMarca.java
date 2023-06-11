package Negocio.Marca;

import java.util.List;


public interface SAMarca {
	
	public int altaMarca(TMarca marca);

	
	public TMarca buscarMarca(int id_marca);

	
	public List<TMarca> buscarTodosMarca();

	
	public int modificarMarca(TMarca marca);

	
	public int eliminarMarca(int id_marca);
}