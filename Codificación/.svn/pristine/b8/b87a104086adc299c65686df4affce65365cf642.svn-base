package Presentación.Pedido;

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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.Pedido.TPedido;

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

@SuppressWarnings("serial")
public class VBuscarTodosPedido extends JFrame implements IGUI{
	private List<TPedido> pedidos;

	public VBuscarTodosPedido(List<TPedido> pedidos) {
		setTitle("Pedidos");
		this.pedidos = pedidos;
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
		JLabel title = new JLabel("Pedidos");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(340, 0)));

		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_restaurante_pequeño.png"))));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(Color.white);
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(307, 0)));

		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_REST, 1));
				dispose();
			}
		});

		

		// CONSTRUIR VISTA
		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un pedido para mostrar mas informacion");
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
					Controller.getInstance().action(new Context(Eventos.BUSCAR_PEDIDO, id));
					dispose();

				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Introduzca un valor numerico para el ID");
				}
			}

		});
		
		JLabel searchLabel2 = new JLabel("Introduzca el ID del cliente:  ");
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
					int id = Integer.parseInt(tSearch2.getText());
					Controller.getInstance().action(new Context(Eventos.BUSCAR_PEDIDOS_POR_CLIENTE, id));
					dispose();
									
				}catch(NumberFormatException a){
					JOptionPane.showMessageDialog(null, "Introduzca un valor numerico para el id");
				}
			}
							
		});


		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(searchPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		for (TPedido pedido : pedidos) {
			contentContainer.add(pedidoPanel(pedido));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		this.pack();
		this.setVisible(true);
	}

	private JPanel pedidoPanel(TPedido pedido) {

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

	

		// DEVOLUCION BUTTON
		JButton deleteButton = new JButton("Devolucion");
		deleteButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("trashRest_icon.jpg"))));
		deleteButton.setBackground(new Color(128, 0, 0));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int res= JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere realizar una devolucion del el pedido con id: "+pedido.getID()+" ?");
				if(res == 0){
					Controller.getInstance().action(new Context(Eventos.CREAR_V_DEVOLUCION_PEDIDO, pedido.getID()));
					dispose();
				}


			}

		});

		// LABEL
		JLabel label = new JLabel("ID: " + pedido.getID() + " Fecha: " +  pedido.getFecha());
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_PEDIDO, pedido.getID()));
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
		buttonContainer.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonContainer.add(deleteButton);

		return panel;
	}

	@Override
	public void actualizar(Context res) {
		
		 if(res.getEvento() == Eventos.RES_BUSCAR_PEDIDO_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido encontrar el pedido con id: "+res.getDatos());
		} else if(res.getEvento() == Eventos.RES_BUSCAR_TODOS_PEDIDO_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido encontrar los pedidos del cliente con id: "+res.getDatos());
		} 
		
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PEDIDO));
		dispose();
	}
}