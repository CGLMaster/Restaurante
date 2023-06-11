package Negocio.Pedido;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Personal.SAPersonal;
import Negocio.Personal.TPersonal;
import Negocio.Personal.TTiempoCompleto;
import Negocio.Turno.SATurno;
import Negocio.Turno.TTurno;

public class Prueba_abrir {

	public static void main(String[] args) {
		SAPersonal sa = FactoriaSA.getInstance().getSAPersonal();
		SATurno saturno = FactoriaSA.getInstance().getSATurno();
		
		TTiempoCompleto cperso = new TTiempoCompleto();
		TTurno turno = new TTurno("Mañana");
		turno.setHoraInicio("19:00");
		turno.setHoraFin("20:00");
		turno.setActivo(true);
		
		cperso.setActivo(true);
		cperso.setDni("98765432W");
		cperso.setId_turno(1);
		cperso.setNombre("Pepito");
		cperso.setNumPedidos(0);
		cperso.setNomina(1200.0);
		cperso.setHorasExtras(0);
		
		saturno.altaTurno(turno);
		sa.altaPersonal(cperso);
		

	}

}
