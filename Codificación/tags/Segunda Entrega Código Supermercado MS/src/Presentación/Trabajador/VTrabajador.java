package Presentación.Trabajador;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Negocio.Trabajador.TTrabajador;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;



@SuppressWarnings("serial")
public class VTrabajador extends JFrame implements IGUI {

	private List<TTrabajador> trabajadores;

	public VTrabajador(List<TTrabajador> trabajadores) {
		this.trabajadores = trabajadores;
		setTitle("trabajadores");
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

		// NEW TRABAJADOR BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(39, 174, 95));
		
		JButton newButton = new JButton("Nuevo Trabajador");
		newButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		newButton.setToolTipText("AÃ±adir nuevo trabajador");
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
				Controller.getInstance().action(new Context(Eventos.ALTA_TRABAJADOR, null));
				dispose();
			}
		});
		contentContainer.add(headerContainer);
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un trabajador para mostrar mas informacion");
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
					Controller.getInstance().action(new Context(Eventos.BUSCAR_TRABAJADOR_ID, id));
					dispose();
							
				}catch(NumberFormatException a){
					JOptionPane.showMessageDialog(null, "Introduzca un valor numerico para el ID");
				}
			}
					
		});
				
		JLabel searchLabel2 = new JLabel("Buscar por DNI:  ");
		JTextField tSearch2 = new JTextField(10);
		tSearch2.setMaximumSize(new Dimension(90, 20));
		JButton okSearch2 = new JButton("Buscar");
		okSearch2.setBackground(new Color(39, 174, 95));
		okSearch2.setForeground(Color.white);
		okSearch2.setBorderPainted(false);
				
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
					Controller.getInstance().action(new Context(Eventos.BUSCAR_TRABAJADOR_DNI, dni));
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

		for (TTrabajador tr : trabajadores) {
			contentContainer.add(trabajadorPanel(tr));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		this.pack();
		this.setVisible(true);
	}

	private JPanel trabajadorPanel(TTrabajador tr) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 60));

		JPanel buttonContainer = new JPanel(new FlowLayout());
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit_icon.png")));
		editButton.setToolTipText("Editar Trabajador");
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash_icon.jpg")));
		deleteButton.setToolTipText("Eliminar Trabajador");

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
				int res= JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere eliminar al trabajador con id: "+ tr.getIDTrabajador()+" ?");
				if(res == 0){
					Controller.getInstance().action(new Context(Eventos.BAJA_TRABAJADOR,tr.getIDTrabajador()));
				}
			}
		});

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.MODIFICAR_TRABAJADOR, tr));
				dispose();
			}
		});

		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());

		// LABEL
		JLabel label = new JLabel("ID: " + tr.getIDTrabajador() + "   Nombre: " + tr.getNombre() );
		label.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TRABAJADOR_ID, tr.getIDTrabajador()));
				dispose();
			}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}

		});

		label.setToolTipText("InformaciOn del Trabajador");

		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);

		return panel;
	}

	@Override
	public void actualizar(Context res) {
		
		int evento = res.getEvento();
		
		if (evento == Eventos.RES_BAJA_TRABAJADOR_OK) {
			JOptionPane.showMessageDialog(null, "Se ha dado de baja al trabajador con exito");
			Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TRABAJADOR));
			dispose();
		} 
		else if (evento == Eventos.RES_BAJA_TRABAJADOR_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja al trabajador");
		}
		else if(evento == Eventos.RES_BUSCAR_TRABAJADOR_DNI_KO){
			JOptionPane.showMessageDialog(null, "Trabajador no existente, compruebe el DNI");
			Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TRABAJADOR));
		}
		else if(evento == Eventos.RES_BUSCAR_TRABAJADOR_ID_KO){
			JOptionPane.showMessageDialog(null, "Trabajador no existente, compruebe el ID");
			Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TRABAJADOR));
		}	
		
		
		
	}
}