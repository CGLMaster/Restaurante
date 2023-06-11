package Negocio.Plato;

import java.util.List;

public interface SAPlato {

	public int altaPlato(TPlato plato);

	public TPlato buscarPlato(int id_Plato);

	public List<TPlato> buscarTodosPlato();

	public int modificarPlato(TPlato plato);

	public int bajaPlato(int id_plato);

	public int aniadirIngredienteAPlato(int id_ingrediente, int idPlato);

	public int eliminarIngredienteAPlato(int id_ingrediente, int id_plato);

	public TPlatoConIngredientes mostrarIngredientesPlato(int id_plato);
}