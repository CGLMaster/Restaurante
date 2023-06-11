package Presentación.Personal;

import javax.swing.JFrame;

import Presentación.Command.Context;
import Presentación.FactoriaVistas.IGUI;
import java.util.Set;
import javax.swing.JTextField;

import Negocio.Personal.TPersonal;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class VBuscarUnoDNIPersonal extends JFrame implements IGUI {
	
	
	public VBuscarUnoDNIPersonal(TPersonal personal) {
		
	}
	
	private Set<JTextField> jTextField;
	
	private Set<JPanel> jPanel;
	
	private Set<JLabel> jLabel;

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}