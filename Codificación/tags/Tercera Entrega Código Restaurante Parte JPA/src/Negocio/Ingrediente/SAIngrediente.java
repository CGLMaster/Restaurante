package Negocio.Ingrediente;

import java.util.List;

public interface SAIngrediente {

	public int aniadirIngrediente(TIngrediente ingrediente);

	public int eliminarIngrediente(int id);

	public TIngrediente buscarIngrediente(int id);
	
	public List<TIngrediente> buscarTodosIngredientes();

	public int editarIngrediente(TIngrediente ingrediente);
}