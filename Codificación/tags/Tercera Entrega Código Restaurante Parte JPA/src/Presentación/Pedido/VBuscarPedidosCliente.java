package Presentación.Pedido;

import javax.swing.JFrame;

import Presentación.Command.Context;
import Presentación.FactoriaVistas.IGUI;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VBuscarPedidosCliente extends JFrame implements IGUI {
	
	private Set<JButton> jButton;

	private Set<JPanel> jPanel;
	
	private Set<JLabel> jLabel;
	
	private Set<JTextField> jTextField;

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}