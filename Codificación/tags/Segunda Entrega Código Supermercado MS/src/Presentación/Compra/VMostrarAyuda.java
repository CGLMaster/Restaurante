package Presentaci�n.Compra;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Presentaci�n.Command.Context;
import Presentaci�n.FactoriaVistas.IGUI;

import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class VMostrarAyuda extends JDialog implements ActionListener, IGUI {

	private JPanel generalPanel, panel1, panel2, panel3, panel4;
	private JLabel labelIcon1, labelIcon2, labelIcon3, labelIcon4;
	private JLabel labelText1, labelText2, labelText3, labelText4, consejo;

	public VMostrarAyuda() {
		setTitle("Ayuda al Cliente");
		setResizable(false);
		setSize(new Dimension(600, 500));
		setBackground(new Color(197, 233, 205));
		setLocationRelativeTo(null);
		generalPanel = new JPanel();
		generalPanel.setBackground(new Color(197, 233, 205));

		panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.setBackground(new Color(197, 233, 205));
		labelText1 = new JLabel();
		labelIcon1 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/a�adir_iconhelp.png"))));

		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.setBackground(new Color(197, 233, 205));
		labelText2 = new JLabel();
		labelIcon2 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/trash_iconhelp.jpg"))));

		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.setBackground(new Color(197, 233, 205));
		labelText3 = new JLabel();
		labelIcon3 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/pago_iconhelp.jpg"))));

		panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4.setBackground(new Color(197, 233, 205));
		labelText4 = new JLabel();
		labelIcon4 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/back_iconhelp.png"))));

		consejo = new JLabel();

		setContentPane(generalPanel);
		getContentPane().setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));
		init_GUI();
	}

	public void init_GUI() {

		labelText1.setText(
				"<html>Al pulsar '<i>A�adir</i>', se agregar� el producto deseado al carrito de la compra, <br>aumentando la cantidad correspondiente al precio de la compra total.</html>");
		panel1.add(Box.createRigidArea(new Dimension(10, 80)));
		panel1.add(labelIcon1);
		panel1.add(labelText1);

		labelText2.setText(
				"<html>Al pulsar '<i>Delete</i>', se borrar� el producto del carrito de la compra, <br>restando la cantidad correspondiente al precio de la compra total.</html>");
		panel2.add(Box.createRigidArea(new Dimension(10, 80)));
		panel2.add(labelIcon2);
		panel2.add(labelText2);

		labelText3.setText(
				"<html>Al pulsar '<i>Pagar</i>', se pasar� a efectuar la compra, <br>aseg�rese de tener suficiente dinero.</html>");
		panel3.add(Box.createRigidArea(new Dimension(10, 80)));
		panel3.add(labelIcon3);
		panel3.add(labelText3);

		labelText4.setText("<html>Al pulsar '<i>Atr�s</i>', volver� a la pagina principal de compra.");
		panel4.add(Box.createRigidArea(new Dimension(10, 80)));
		panel4.add(labelIcon4);
		panel4.add(labelText4);

		generalPanel.add(panel1);
		generalPanel.add(panel2);
		generalPanel.add(panel3);
		generalPanel.add(panel4);

		consejo.setText(
				"<html><i>Si lo desea puede solicitar la ayuda de uno de nuestros 'Trabajadores Supermercado'</i>.<html>");
		consejo.setForeground(new Color(201, 60, 32));
		consejo.setAlignmentX(CENTER_ALIGNMENT);
		consejo.setHorizontalAlignment(SwingUtilities.CENTER);
		generalPanel.add(consejo);
	}

	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void actualizar(Context res) {
		
	}
}
