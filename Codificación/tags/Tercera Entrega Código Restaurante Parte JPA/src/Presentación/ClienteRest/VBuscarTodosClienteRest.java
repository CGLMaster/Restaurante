package Presentación.ClienteRest;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.ClienteRest.TClienteRest;


@SuppressWarnings("serial")
public class VBuscarTodosClienteRest extends JFrame implements IGUI{
	private List<TClienteRest> clientes;

	public VBuscarTodosClienteRest(List<TClienteRest> clientes) {
		setTitle("Cliente Restaurante");
		this.clientes = clientes;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		this.add(mainPanel);

		// CONTENT CONTAINER

		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBackground(new Color(128, 0, 0));

		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		contentContainer.setAutoscrolls(true);

		mainPanel.add(scrollFrame);

		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setBackground(Color.white);
		headerContainer.add(Box.createRigidArea(new Dimension(60, 0)));

		// TITLE
		JLabel title = new JLabel("Clientes Restaurantes");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(220, 0)));

		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_restaurante_pequeño.png"))));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(Color.white);
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(270, 0)));

		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_REST, 1));
				dispose();
			}
		});

		// NEW MARCA BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		
		JButton newButton = new JButton("Nuevo Cliente");
		newButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("user_icon.png"))));
		newButton.setToolTipText("Añadir nuevo cliente");
		newButton.setBackground(new Color(128, 0, 0));
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
				Controller.getInstance().action(new Context(Eventos.VALTA_CLIENTE_REST));
				dispose();

			}

		});

		// CONSTRUIR VISTA
		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un cliente para mostrar mas informacion");
		help.setForeground(Color.white);
		helpPanel.setOpaque(false);
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);


		// SEARCH PANEL
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.setMaximumSize(new Dimension(1000, 20));
		searchPanel.setOpaque(false);
		
		JLabel searchLabel = new JLabel("Buscar por ID:  ");
		searchLabel.setForeground(Color.white);
		JTextField tSearch = new JTextField(10);
		tSearch.setMaximumSize(new Dimension(70, 20));
		JButton okSearch = new JButton("Buscar");
		okSearch.setBackground(new Color(128, 0, 0));
		okSearch.setBorder(BorderFactory.createBevelBorder(0));
		okSearch.setForeground(Color.white);

		searchPanel.add(searchLabel);
		searchPanel.add(tSearch);
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(okSearch);

		okSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id;

				try {
					id = Integer.parseInt(tSearch.getText());
					Controller.getInstance().action(new Context(Eventos.BUSCAR_CLIENTE_REST, id));
					dispose();

				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Introduzca un valor numerico para el ID");
				}
			}

		});


		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(searchPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		for (TClienteRest cliente : clientes) {
			contentContainer.add(marcaPanel(cliente));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		this.pack();
		this.setVisible(true);
	}

	private JPanel marcaPanel(TClienteRest cliente) {

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 100));

		// BUTTON CONTAINER
		JPanel buttonContainer = new JPanel();
		buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
		buttonContainer.setOpaque(false);
		buttonContainer.setAlignmentY(CENTER_ALIGNMENT);
		buttonContainer.setSize(buttonContainer.getPreferredSize());

		// EDIT BUTTON
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("edit_icon.png"))));
		editButton.setBackground(new Color(48, 124, 45));
		editButton.setForeground(Color.white);
		editButton.setBorderPainted(false);

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.VMODIFICAR_CLIENTE_REST, cliente));
				dispose();
			}
		});

		// DELETE BUTTON
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("trashRest_icon.jpg"))));
		deleteButton.setBackground(new Color(128, 0, 0));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int res= JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere eliminar el cliente con id: "+cliente.getID()+" ?");
				if(res == 0){
					Controller.getInstance().action(new Context(Eventos.BAJA_CLIENTE_REST,cliente.getID()));
					dispose();
				}


			}

		});

		// LABEL
		JLabel label = new JLabel("ID: " + cliente.getID() + "  Nombre: " + cliente.getNombre());
	    label.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_CLIENTE_REST, cliente.getID()));
				dispose();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {

			}


			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}


		});

		// CONSTRUIR PANEL
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		buttonContainer.add(editButton);
		buttonContainer.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonContainer.add(deleteButton);

		return panel;
	}

	@Override
	public void actualizar(Context res) {
       int evento = res.getEvento();
		
		if (evento == Eventos.RES_BAJA_CLIENTE_REST_OK) {
			JOptionPane.showMessageDialog(null, "Se ha dado de baja al cliente con exito");
			
		} 
		else if (evento == Eventos.RES_BAJA_CLIENTE_REST_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja al cliente");
		}
		
		else if(evento == Eventos.RES_BUSCAR_CLIENTE_REST_KO){
			JOptionPane.showMessageDialog(null, "Cliente no existente, compruebe el ID");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE_REST));
		dispose();
		
	}
}