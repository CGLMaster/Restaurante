package Presentación.Ingrediente;

import javax.swing.JFrame;

import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import Negocio.Ingrediente.TIngrediente;

@SuppressWarnings("serial")
public class VBuscarIngrediente extends JFrame implements IGUI {

	private TIngrediente ingrediente;
	
	public VBuscarIngrediente(TIngrediente ingrediente) {
		this.ingrediente = ingrediente;
		init_GUI();
		this.setLocationRelativeTo(null);
	}
	
	public void init_GUI() {
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		// BACK BUTTON
		JPanel backButtonContainer = backButtonContainer();
		
		// TITULO
		JLabel titleLabel = new JLabel("Ingrediente");
		titleLabel.setFont(new Font("sans-serif", 1, 20));
		titleLabel.setForeground(new Color(128, 0, 0));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);

		// INFO
		JLabel idLabel = new JLabel("ID: " + ingrediente.getID());
		JLabel nombreLabel = new JLabel("Nombre: " + ingrediente.getNombre());
		JLabel caloriasLabel = new JLabel("Calorias: " + ingrediente.getCalorias());
		JLabel activoLabel = new JLabel();
		if(ingrediente.isActivo()){
			activoLabel.setText("Activo");
			activoLabel.setForeground(Color.green);
		}else{
			activoLabel.setText("Inactivo");
			activoLabel.setForeground(Color.red);
		}
		
		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		contentContainer.add(idLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(nombreLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(caloriasLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(activoLabel);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel backButtonContainer() {
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("backRest_icon.png")));
		backButton.setToolTipText("Volver a Ingredientes");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_INGREDIENTE, 0));
				dispose();
			}
			
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {}
}