package Presentación.Personal;

import javax.swing.JFrame;

import Presentación.Command.Context;
import Presentación.FactoriaVistas.IGUI;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class VIdentificarPersonal extends JFrame implements IGUI {
	
	private Set<JLabel> jLabel;
	
	private Set<JTextField> jTextField;
	
	private Set<JButton> jButton;
	
	private Set<JPanel> jPanel;
	
	private Set<JComboBox> jComboBox;

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}