package Presentación.Proveedor;

import javax.swing.JFrame;

import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.Proveedor.TProveedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VProveedor extends JFrame implements IGUI {

	private List<TProveedor> proveedores;

	public VProveedor(List<TProveedor> proveedores) {
		this.proveedores = proveedores;
		setTitle("proveedores");
		init_GUI();
	}
	
	private void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		this.add(mainPanel);

		// SCROLL CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);

		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		contentContainer.setAutoscrolls(true);

		mainPanel.add(scrollFrame);

		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setBackground(new Color(39, 174, 95));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));

		// TITLE
		JLabel title = new JLabel("Trabajadores");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(330, 0)));

		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_pequeño.png")));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(new Color(39, 174, 95));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(270, 0)));

		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL, 1));
				dispose();
			}
		});

		// NEW PROVEEDOR BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(39, 174, 95));
		
		JButton newButton = new JButton("Nuevo proveedor");
		newButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		newButton.setToolTipText("Añadir nuevo proveedor");
		newButton.setBackground(new Color(39, 174, 95));
		newButton.setForeground(Color.white);
		newButton.setAlignmentX(CENTER_ALIGNMENT);
		newButton.setAlignmentY(CENTER_ALIGNMENT);
		newButton.setBorder(BorderFactory.createBevelBorder(0));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		buttonPanel.add(newButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		headerContainer.add(buttonPanel);
		headerContainer.add(Box.createRigidArea(new Dimension(20, 0)));

		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.ALTA_PROVEEDOR, null));
				dispose();
			}
		});
		contentContainer.add(headerContainer);
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un proveedor para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);
		
		helpPanel.add(Box.createRigidArea(new Dimension(350, 0)));
		
		
		//SEARCH PANEL
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel,BoxLayout.X_AXIS));
		searchPanel.setMaximumSize(new Dimension(1000, 20));
		JLabel searchLabel = new JLabel("Buscar por ID:  ");
		JTextField tSearch = new JTextField(10);
		tSearch.setMaximumSize(new Dimension(70, 20));
		JButton okSearch = new JButton("Buscar");
		okSearch.setBackground(new Color(39, 174, 95));
		okSearch.setForeground(Color.white);
		okSearch.setBorderPainted(false);
				
		searchPanel.add(searchLabel);
		searchPanel.add(tSearch);
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(okSearch);
				
		okSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
						
				try{
					int id = Integer.parseInt(tSearch.getText());
					Controller.getInstance().action(new Context(Eventos.BUSCAR_PROVEEDOR, id));
					dispose();
							
				}catch(NumberFormatException a){
					JOptionPane.showMessageDialog(null, "Introduzca un valor numerico para el ID");
				}
			}
					
		});
				
				
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(searchPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		for (TProveedor proveedor : proveedores) {
			contentContainer.add(proveedorPanel(proveedor));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		this.pack();
		this.setVisible(true);
	}

	private JPanel proveedorPanel(TProveedor proveedor) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 60));

		JPanel buttonContainer = new JPanel(new FlowLayout());
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit_icon.png")));
		editButton.setToolTipText("Editar Proveedor");
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash_icon.jpg")));
		deleteButton.setToolTipText("Eliminar Proveedor");

		editButton.setBackground(new Color(39, 174, 95));
		editButton.setForeground(Color.white);
		editButton.setBorderPainted(false);

		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);

		deleteButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				int res= JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere eliminar al proveedor con id: "+ proveedor.getID()+" ?");
				if(res == 0){
					Controller.getInstance().action(new Context(Eventos.BAJA_PROVEEDOR,proveedor.getID()));
				}
			}
		});

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.MODIFICAR_PROVEEDOR, proveedor));
				dispose();
			}
		});

		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());

		// LABEL
		JLabel label = new JLabel("ID: " + proveedor.getID() + "   Nombre: " + proveedor.getNombre() );
		label.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_PROVEEDOR, proveedor.getID()));
				dispose();
			}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}

		});

		label.setToolTipText("Información del Proveedor");

		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);

		return panel;
	}
	

	@Override
	public void actualizar(Context res) {
		
		int evento = res.getEvento();
		
		if (evento == Eventos.RES_BAJA_PROVEEDOR_OK) {
			JOptionPane.showMessageDialog(null, "Se ha dado de baja al proveedor con exito");
			Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PROVEEDOR));
			dispose();
		} 
		else if (evento == Eventos.RES_BAJA_PROVEEDOR_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja al proveedor");
		}
		else if(evento == Eventos.RES_BUSCAR_PROVEEDOR_KO){
			JOptionPane.showMessageDialog(null, "Proveedor no existente, compruebe el ID");
			Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PROVEEDOR));
		}
		
	}
}