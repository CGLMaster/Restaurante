package Negocio.Turno;

import java.util.List;

public interface SATurno {

	public int altaTurno(TTurno turno);

	public int bajaTurno(int id_turno);

	public int modificarTurno(TTurno turno);

	public TTurno buscarUnTurno(int id_turno);

	public List<TTurno> buscarTodosTurno();
	
	public Double calcularNominaTurno(int id_turno);
}