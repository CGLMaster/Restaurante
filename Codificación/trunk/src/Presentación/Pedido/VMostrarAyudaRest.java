package Presentación.Pedido;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Presentación.Command.Context;
import Presentación.FactoriaVistas.IGUI;

import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VMostrarAyudaRest extends JDialog implements ActionListener, IGUI {

	private JPanel generalPanel, panel1, panel2, panel3, panel4;
	private JLabel labelIcon1, labelIcon2, labelIcon3, labelIcon4;
	private JLabel labelText1, labelText2, labelText3, labelText4, consejo;

	public VMostrarAyudaRest() {
		setTitle("Ayuda al Cliente");
		setResizable(false);
		setSize(new Dimension(600, 500));
		setBackground(new Color(232,92,104));
		setLocationRelativeTo(null);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);
		
		generalPanel = new JPanel();
		generalPanel.setBackground(new Color(232,92,104));

		panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.setBackground(new Color(235,114,115));
		labelText1 = new JLabel();
		labelIcon1 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/aniadirPlatoHelp_icon.png"))));

		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.setBackground(new Color(238,163,164));
		labelText2 = new JLabel();
		labelIcon2 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/trashRestHelp_icon.png"))));

		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.setBackground(new Color(235,114,115));
		labelText3 = new JLabel();
		labelIcon3 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/pagoRestHelp_icon.png"))));

		panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4.setBackground(new Color(238,163,164));
		labelText4 = new JLabel();
		labelIcon4 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/backRestHelp_icon.png"))));

		consejo = new JLabel();

		setContentPane(generalPanel);
		getContentPane().setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));
		init_GUI();
	}
	
	public void init_GUI() {

		labelText1.setText(	"<html>Al pulsar '<i>Añadir Plato</i>', se agregará el plato deseado al pedido, <br>aumentando la cantidad correspondiente al precio del pedido total.</html>");
		panel1.add(Box.createRigidArea(new Dimension(10, 80)));
		panel1.add(labelIcon1);
		panel1.add(labelText1);

		labelText2.setText("<html>Al pulsar '<i>Borrar Plato</i>', se borrará el plato del pedido, <br>restando la cantidad correspondiente al precio del pedido total.</html>");
		panel2.add(Box.createRigidArea(new Dimension(10, 80)));
		panel2.add(labelIcon2);
		panel2.add(labelText2);
		
		labelText3.setText("<html>Al pulsar '<i>Realizar Pedido</i>', se pasará a efectuar el pedido, <br>asegúrese de tener suficiente dinero.</html>");
		panel3.add(Box.createRigidArea(new Dimension(10, 80)));
		panel3.add(labelIcon3);
		panel3.add(labelText3);

		labelText4.setText("<html>Al pulsar '<i>Atrás</i>', volverá a la pagina principal de pedido de platos.");
		panel4.add(Box.createRigidArea(new Dimension(10, 80)));
		panel4.add(labelIcon4);
		panel4.add(labelText4);

		generalPanel.add(panel1);
		generalPanel.add(panel2);
		generalPanel.add(panel3);
		generalPanel.add(panel4);

		consejo.setText("<html><i>·  Si lo desea puede solicitar la ayuda de uno de nuestros 'Trabajadores Restaurante'  ·</i><html>");
		consejo.setForeground(Color.WHITE);
		consejo.setAlignmentX(CENTER_ALIGNMENT);
		consejo.setHorizontalAlignment(SwingUtilities.CENTER);
		generalPanel.add(consejo);
	}

	public void actionPerformed(ActionEvent e) {}

	@Override
	public void actualizar(Context res) {}
}
