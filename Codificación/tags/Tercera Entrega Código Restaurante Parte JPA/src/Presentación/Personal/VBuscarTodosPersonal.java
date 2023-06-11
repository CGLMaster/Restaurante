package Presentación.Personal;


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
import java.awt.FlowLayout;
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

import Negocio.Personal.TPersonal;



@SuppressWarnings("serial")
public class VBuscarTodosPersonal extends JFrame implements IGUI{
	private List<TPersonal> personal;

	public VBuscarTodosPersonal(List<TPersonal> personal) {
		setTitle("Personal Restaurante");
		this.personal = personal;
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
		headerContainer.add(Box.createRigidArea(new Dimension(65, 0)));

		// TITLE
		JLabel title = new JLabel("Personal Restaurante");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(200, 0)));

		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_restaurante_pequeño.png"))));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(Color.white);
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(280, 0)));

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
		
		JButton newButton = new JButton("Nuevo Personal");
		newButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("user_icon.png"))));
		newButton.setToolTipText("Añadir nuevo personal");
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
				Controller.getInstance().action(new Context(Eventos.VALTA_PERSONAL));
				dispose();

			}

		});

		// CONSTRUIR VISTA
		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un personal para mostrar mas informacion");
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
					Controller.getInstance().action(new Context(Eventos.BUSCAR_PERSONAL, id));
					dispose();

				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Introduzca un valor numerico para el ID");
				}
			}

		});
		
		JLabel searchLabel2 = new JLabel("Buscar por DNI:  ");
		searchLabel2.setForeground(Color.white);
		JTextField tSearch2 = new JTextField(10);
		tSearch2.setMaximumSize(new Dimension(70, 20));
		JButton okSearch2 = new JButton("Buscar");
		okSearch2.setBackground(new Color(128, 0, 0));
		okSearch2.setBorder(BorderFactory.createBevelBorder(0));
		okSearch2.setForeground(Color.white);
		

		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(searchLabel2);
		searchPanel.add(tSearch2);
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(okSearch2);
						
		okSearch2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
								
				try{
					String dni = tSearch2.getText();
					Controller.getInstance().action(new Context(Eventos.BUSCAR_PERSONAL_DNI, dni));
					dispose();
									
				}catch(NumberFormatException a){
					JOptionPane.showMessageDialog(null, "Introduzca un valor numerico para el DNI");
				}
			}
							
		});


		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(searchPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		for (TPersonal per : personal) {
			contentContainer.add(marcaPanel(per));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		this.pack();
		this.setVisible(true);
	}

	private JPanel marcaPanel(TPersonal per) {

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 100));

		// BUTTON CONTAINER
		JPanel buttonContainer = new JPanel(new FlowLayout());
		buttonContainer.setOpaque(false);
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
				Controller.getInstance().action(new Context(Eventos.VMODIFICAR_PERSONAL, per));
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
				
				int res= JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere eliminar el empleado con id: "+per.getIDPersonal()+" ?");
				if(res == 0){
					Controller.getInstance().action(new Context(Eventos.BAJA_PERSONAL,per.getIDPersonal()));
					dispose();
				}


			}

		});

		// LABEL
		JLabel label = new JLabel("ID: " + per.getIDPersonal() + "  Nombre: " + per.getNombre());
	    label.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_PERSONAL, per.getIDPersonal()));
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
		buttonContainer.add(deleteButton);

		return panel;
	}

	@Override
	public void actualizar(Context res) {
       int evento = res.getEvento();
		
		if (evento == Eventos.RES_BAJA_PERSONAL_OK) {
			JOptionPane.showMessageDialog(null, "Se ha dado de baja al empleado con exito");
		} 
		else if (evento == Eventos.RES_BAJA_PERSONAL_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja al empleado");
		}
		
		else if(evento == Eventos.RES_BUSCAR_PERSONAL_KO){
			JOptionPane.showMessageDialog(null, "Empleado no existente, compruebe el ID");
		}
		else if(evento == Eventos.RES_BUSCAR_PERSONAL_DNI_KO){
			JOptionPane.showMessageDialog(null, "Empleado no existente, compruebe el DNI");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PERSONAL));
		dispose();
	}
}