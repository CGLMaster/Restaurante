package Presentaci�n.Cliente;

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
import Negocio.Cliente.TCliente;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Controller;
import Presentaci�n.Controller.Eventos;
import Presentaci�n.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VCliente extends JFrame implements IGUI {
	private List<TCliente> clientes;

	public VCliente(List<TCliente> clientes) {
		this.clientes = clientes;
		setTitle("Clientes");
		init_GUI();
	}
	
	private void init_GUI() {
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,500));
		this.add(mainPanel);
		
		
		// CONTENT CONTAINER
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
		JLabel title = new JLabel("Clientes");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(360, 0)));
		
		//ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_IS_peque�o.png"))));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(new Color(39, 174, 95));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(300, 0)));

			
		icon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL,1)); 		
				dispose();
			}		
		});
		
		//NEW CLIENTE BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(39, 174, 95));
		
		JButton newClienteButton = new JButton("Nuevo Cliente");
		newClienteButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("user_icon.png"))));
		newClienteButton.setToolTipText("A�adir nuevo cliente");
		newClienteButton.setBackground(new Color(39, 174, 95));
		newClienteButton.setForeground(Color.white);
		newClienteButton.setAlignmentX(CENTER_ALIGNMENT);
		newClienteButton.setAlignmentY(CENTER_ALIGNMENT);
		newClienteButton.setBorder(BorderFactory.createBevelBorder(0));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		buttonPanel.add(newClienteButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		headerContainer.add(buttonPanel);
		headerContainer.add(Box.createRigidArea(new Dimension(20, 0)));
	
		newClienteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.VALTA_CLIENTE));
				dispose();
			}
			
		});
		
		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un cliente para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);
		
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
				int id;
				
				try{
					id = Integer.parseInt(tSearch.getText());
					Controller.getInstance().action(new Context(Eventos.BUSCAR_CLIENTE,id));
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
		
		for(TCliente cliente : clientes){
			contentContainer.add(clientePanel(cliente));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel clientePanel(TCliente cliente){
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 60));
		

		JPanel buttonContainer = new JPanel(new FlowLayout());
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("edit_icon.png"))));
		editButton.setToolTipText("Editar Cliente");
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("trash_icon.jpg"))));
		deleteButton.setToolTipText("Eliminar Cliente");
		
		editButton.setBackground(new Color(39, 174, 95));
		editButton.setForeground(Color.white);
		editButton.setBorderPainted(false);
		
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);
		
		deleteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int res= JOptionPane.showConfirmDialog(null, "�Esta seguro que quiere eliminar el cliente con id: "+ cliente.getIDSocio() +" ?");
				if(res == 0){
					Controller.getInstance().action(new Context(Eventos.BAJA_CLIENTE, cliente.getIDSocio()));
					dispose();
				}
			}
			
		});
		
		
		editButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controller.getInstance().action(new Context(Eventos.VMODIFICAR_CLIENTE,cliente));
				dispose();
			}
			
		});
		
		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());
		
		
		//LABEL
		JLabel label = new JLabel("ID: " + cliente.getIDSocio() + "  Nombre: " + cliente.getNombre());
		label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_CLIENTE,cliente.getIDSocio()));
				dispose();
				
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		label.setToolTipText("Informacion del Cliente");
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		
		return panel;
	}
	

	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_BAJA_CLIENTE_OK){
			JOptionPane.showMessageDialog(null, "Se ha dado de baja el cliente con �xito");
		}
		else if(res.getEvento() == Eventos.RES_BAJA_CLIENTE_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja el cliente");
		}else if(res.getEvento() == Eventos.RES_BUSCAR_TODOS_CLIENTE_KO){
			JOptionPane.showMessageDialog(null, "No hay pagina previa");
		}else if(res.getEvento() == Eventos.RES_BUSCAR_CLIENTE_KO){
			JOptionPane.showMessageDialog(null, "Cliente no existente, compruebe el ID");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE));
		dispose();
		
	}
}