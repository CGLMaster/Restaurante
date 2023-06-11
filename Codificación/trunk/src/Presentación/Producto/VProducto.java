package Presentación.Producto;

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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;



import Negocio.Producto.TProducto;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;


@SuppressWarnings("serial")
public class VProducto extends JFrame implements IGUI {
	
	private List<TProducto> productos;
	
	public VProducto(List<TProducto> productos){
		this.productos = productos;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	private void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(700,500));
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
		JLabel title = new JLabel("Productos");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(350, 0)));
		
		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_pequeño.png")));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(new Color(39, 174, 95));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(280, 0)));

			
		icon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_SUPER, 1));
				dispose();
			}		
		});
		
		// NEW PROVEEDOR BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(39, 174, 95));
		
		JButton newButton = new JButton("Nuevo Producto");
		newButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("producto_icon.jpg")));
		newButton.setToolTipText("Añadir nuevo producto");
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
	
		newButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controller.getInstance().action(new Context((Eventos.ALTA_PRODUCTO)));
				dispose();
				
			}
			
		});
		
		contentContainer.add(headerContainer);
		
		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un producto para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);
			
		//Search Panel
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
					Controller.getInstance().action(new Context(Eventos.BUSCAR_PRODUCTO, id));
					dispose();
					
				}catch(NumberFormatException a){
					JOptionPane.showMessageDialog(null, "Introduzca un valor numerico para el ID");
				}
			}
			
		});
		
		searchPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		JLabel rangoLabel = new JLabel("Buscar por rango de precio: ");
		JLabel desdeLabel = new JLabel("Desde: ");
		JLabel hastaLabel = new JLabel("Hasta: ");
		JSpinner rangoDesde = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,5));
		JSpinner rangoHasta = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,5));
		JButton buscarRango = new JButton("Buscar");
		buscarRango.setBackground(new Color(39, 174, 95));
		buscarRango.setForeground(Color.white);
		buscarRango.setBorderPainted(false);
		rangoDesde.setMaximumSize(new Dimension(70,50));
		rangoDesde.setPreferredSize(new Dimension(70,50));
		rangoHasta.setMaximumSize(new Dimension(70,50));
		rangoHasta.setPreferredSize(new Dimension(70,50));
		searchPanel.add(rangoLabel);
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(desdeLabel);
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(rangoDesde);
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(hastaLabel);
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(rangoHasta);
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(buscarRango);
		buscarRango.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try{
					int desde = (Integer) rangoDesde.getValue();
					int hasta = (Integer) rangoHasta.getValue();
					int[] range = new int[2];
					range[0] = desde;
					range[1] = hasta;
					Controller.getInstance().action(new Context(Eventos.BUSCAR_PRODUCTOS_PROVEEDOR_POR_RANGO_PRECIO,range));
					dispose();
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Valores no validos");
				}
				
			}
			
		});
		
		JButton reseteo = new JButton("Restaurar");
		reseteo.setBackground(new Color(255, 164, 32));
		reseteo.setForeground(Color.white);
		reseteo.setBorderPainted(false);
		reseteo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
				dispose();
			}
			
		});
		
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(reseteo);
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(searchPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		for(TProducto pr : productos){
			contentContainer.add(productoPanel(pr));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel productoPanel(TProducto pr){
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 60));
		JPanel buttonContainer = new JPanel(new FlowLayout());
		
		// NEW LINK BUTTON
		JButton linkButton = new JButton();
		linkButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("link_icon.jpg")));
		linkButton.setToolTipText("Vincularlo con un proveedor");
		linkButton.setPreferredSize(new Dimension(25, 25));
		linkButton.setBackground(new Color(39, 174, 95));
		linkButton.setBorderPainted(false);
		linkButton.setVisible(true);
		
		linkButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_VINCULADOS, pr.getIDProducto()));
				dispose();
			}
			
		});
		
		
		// OTROS BOTONES
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit_icon.png")));
		editButton.setToolTipText("Editar producto");
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash_icon.jpg")));
		deleteButton.setToolTipText("Eliminar producto");
		
		editButton.setBackground(new Color(39, 174, 95));
		editButton.setForeground(Color.white);
		editButton.setBorderPainted(false);
		
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);
		
		deleteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int res= JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere eliminar el producto con id: "+pr.getIDProducto()+" ?");
				if(res == 0){
					Controller.getInstance().action(new Context(Eventos.BAJA_PRODUCTO, pr.getIDProducto()));
				}
			}
			
		});
		
		
		editButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.MODIFICAR_PRODUCTO, pr));
				dispose();
			}
			
		});
		
		buttonContainer.add(linkButton);
		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());
		
		
		//LABEL
		JLabel label = new JLabel("ID: " + pr.getIDProducto() + "  Nombre: " + pr.getNombre());
		label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_PRODUCTO, pr.getIDProducto()));
				dispose();
				
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		label.setToolTipText("Información del Producto");
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
				
		return panel;
	}
	
	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_BAJA_PRODUCTO_OK){
			JOptionPane.showMessageDialog(null, "Se ha dado de baja al producto con id: "+res.getDatos());
		}else if(res.getEvento() == Eventos.RES_BAJA_PRODUCTO_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja el producto");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
		dispose();
		
	}
	

}