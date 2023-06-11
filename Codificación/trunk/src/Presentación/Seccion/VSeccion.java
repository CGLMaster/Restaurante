package Presentaci�n.Seccion;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import Negocio.Seccion.TSeccion;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Controller;
import Presentaci�n.Controller.Eventos;
import Presentaci�n.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VSeccion extends JFrame implements IGUI {
	private List<TSeccion> secciones;

	public VSeccion(List<TSeccion> secciones) {
		this.secciones = secciones;
		setTitle("Secciones");
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		contentContainer.setAutoscrolls(true);
		
		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setBackground(new Color(39, 174, 95));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));

		// TITLE
		JLabel title = new JLabel("Secciones");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(350, 0)));

		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_peque�o.png")));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(new Color(39, 174, 95));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(300, 0)));

		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_SUPER, 1));
				dispose();
			}
		});

		// NEW SECCION BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(39, 174, 95));

		JButton newSeccionButton = new JButton("Nueva seccion");
		newSeccionButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		newSeccionButton.setToolTipText("A�adir nueva Secci�n");
		newSeccionButton.setBorder(BorderFactory.createBevelBorder(0));
		newSeccionButton.setBackground(new Color(39, 174, 95));
		newSeccionButton.setForeground(Color.white);
		newSeccionButton.setAlignmentX(CENTER_ALIGNMENT);
		newSeccionButton.setAlignmentY(CENTER_ALIGNMENT);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		buttonPanel.add(newSeccionButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		headerContainer.add(buttonPanel);
		headerContainer.add(Box.createRigidArea(new Dimension(20, 0)));

		newSeccionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.VALTA_SECCION));
				dispose();
			}

		});
		
		// CONSTRUIR VISTA
		this.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);
		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en una seccion para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);

		// SEARCH PANEL
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
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

		okSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id;

				try {
					id = Integer.parseInt(tSearch.getText());
					Controller.getInstance().action(new Context(Eventos.BUSCAR_SECCION, id));
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

		for (TSeccion seccion : secciones) {
			contentContainer.add(seccionPanel(seccion));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		this.pack();
		this.setVisible(true);
	}

	private JPanel seccionPanel(TSeccion seccion) {

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 75));

		// BUTTON CONTAINER
		JPanel buttonContainer = new JPanel(new FlowLayout());
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());

		// EDIT BUTTON
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit_icon.png")));
		editButton.setToolTipText("Editar Secci�n");
		editButton.setBackground(new Color(39, 174, 95));
		editButton.setForeground(Color.white);
		editButton.setBorderPainted(false);

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.VMODIFICAR_SECCION, seccion));
				dispose();
			}
		});


		// DELETE BUTTON
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash_icon.jpg")));
		deleteButton.setToolTipText("Eliminar Secci�n");
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int res= JOptionPane.showConfirmDialog(null, "�Esta seguro que quiere eliminar la seccion con id: "+ seccion.getID_Seccion() +" ?");
				if(res == 0){
					Controller.getInstance().action(new Context(Eventos.BAJA_SECCION, seccion.getID_Seccion()));
				}
			}

		});


		// LABEL
		JLabel label = new JLabel("ID: " + seccion.getID_Seccion() + "  Zona: " + seccion.getZona());
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_SECCION, seccion.getID_Seccion()));
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

		label.setToolTipText("Informaci�n de la Secci�n");

		// CONSTRUIR PANEL
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);

		return panel;
	}

	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_BAJA_SECCION_OK) {
			JOptionPane.showMessageDialog(null, "Se ha dado de baja la secci�n con �xito");
		} else if(res.getEvento() == Eventos.RES_BAJA_SECCION_KO) {
			JOptionPane.showMessageDialog(null,
					"No se ha podido dar de baja la secci�n, seguramente todavia tenga productos");
		} else if(res.getEvento() == Eventos.RES_BUSCAR_TODOS_SECCION_KO) {
			JOptionPane.showMessageDialog(null, "No hay pagina previa");
		} else if(res.getEvento() == Eventos.RES_BUSCAR_SECCION_KO){
			JOptionPane.showMessageDialog(null, "Secci�n no existente, compruebe el ID");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_SECCION));
		dispose();
	}
}