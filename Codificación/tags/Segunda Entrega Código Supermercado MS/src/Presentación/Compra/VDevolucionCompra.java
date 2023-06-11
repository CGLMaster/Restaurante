package Presentaci�n.Compra;

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
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Compra.TCompra;
import Negocio.Compra.TCompraConProductos;
import Negocio.Compra.TLineaDeCompra;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Controller;
import Presentaci�n.Controller.Eventos;
import Presentaci�n.FactoriaVistas.IGUI;

import Negocio.Producto.TProducto;

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
import java.util.ArrayList;
import java.util.List;

public class VDevolucionCompra extends JFrame implements IGUI {

	private TCompraConProductos compraConProductos;
	private List<TProducto> productos;
	private List<TLineaDeCompra> prodsToDev;

	public VDevolucionCompra(TCompraConProductos tCompraConProductos) {
		this.compraConProductos = tCompraConProductos;
		this.productos = tCompraConProductos.getProductos();
		prodsToDev = new ArrayList<TLineaDeCompra>();
		init_GUI();
	}

	public void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);

		// BACKBUTTON CONTAINER
		JPanel backButtonContainer = backButtonContainer();

		// BUTTON DEVOLVER
		JButton devButton = new JButton("Confirmar Devolucion");
		devButton.setBackground(new Color(255, 164, 32));
		devButton.setForeground(Color.white);
		devButton.setBorderPainted(false);
		devButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(prodsToDev.size() > 0){
					int res = JOptionPane.showConfirmDialog(null, "�Desea confirmar la devolucion?");
					
					if(res == 0){
						Controller.getInstance().action(new Context(Eventos.DEVOLUCION_COMPRA, prodsToDev));
						dispose();
					}
				}else{
					JOptionPane.showMessageDialog(null, "No es posible devolver 0 productos.");
				}
			}

		});

		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setBackground(new Color(39, 174, 95));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));

		// TITLE
		JLabel title = new JLabel("Compras");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(370, 0)));
		headerContainer.add(devButton);

		// CONTENT CONTAINER
		JPanel infoContainer = new JPanel();
		infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));
		infoContainer.setAlignmentX(CENTER_ALIGNMENT);
		infoContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		infoContainer.setBackground(Color.white);

		// INFO
		TCompra compra = compraConProductos.getCompra();

		JLabel idLabel = new JLabel("ID: " + compra.getIDCompra());
		JLabel fecha = new JLabel("Fecha: " + compra.getFecha());

		JLabel productosTitle = new JLabel("PRODUCTOS");
		productosTitle.setAlignmentX(CENTER_ALIGNMENT);

		// CONSTRUIR VISTA
		this.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);

		contentContainer.add(headerContainer);

		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(backButtonContainer);

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en una compra para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);

		// CompraTotal info
		contentContainer.add(infoContainer);
		infoContainer.add(idLabel);
		infoContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		infoContainer.add(fecha);
		infoContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		contentContainer.add(productosTitle);

		contentContainer.add(Box.createRigidArea(new Dimension(0, 5)));

		// PRODUCTOS
		for (int i = 0; i < productos.size(); i++) {

			TLineaDeCompra productoCantidad = compraConProductos.getLineasDeCompra().get(i);
			int cantidad = productoCantidad.getCantidad();
			TProducto producto = productos.get(i);
			contentContainer.add(seccionPanel(producto, cantidad));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		this.pack();
		this.setVisible(true);

	}

	private JPanel seccionPanel(TProducto producto, int cantidad) {
		TLineaDeCompra devProd = new TLineaDeCompra();
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 75));

		// INFO PRODUCTO PANEL
		JPanel infoProd = new JPanel();
		JLabel precioTotal = new JLabel(cantidad * producto.getPrecio() + " � ");
		// LABEL
		JLabel label = new JLabel(
				String.valueOf(producto.getIDProducto() + ":   " + producto.getNombre() + "   X   " + cantidad));
		infoProd.add(label);
		infoProd.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.WEST);
		infoProd.add(precioTotal);
		infoProd.setBackground(Color.white);

		// DEVOLUCION PANEL
		JPanel devPanel = new JPanel();
		JLabel infoDev = new JLabel();
		infoDev.setText("Usted devuelve 0 producto/s ");
		devPanel.setBackground(Color.white);
		JSpinner prodDev = new JSpinner(new SpinnerNumberModel(cantidad, 0, cantidad, 1));
		prodDev.setMaximumSize(new Dimension(40, 40));
		prodDev.setPreferredSize(new Dimension(40, 40));
		prodDev.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				int udsdev = cantidad - (int) prodDev.getValue();
				infoDev.setText("Usted devuelve "+udsdev + " producto/s ");
			}
			
		});
		
		JButton okDevbutton = new JButton("Confirmar");
		okDevbutton.setBackground(new Color(39, 174, 95));
		okDevbutton.setForeground(Color.white);
		okDevbutton.setBorderPainted(false);
		okDevbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int uds = cantidad - (int) prodDev.getValue();
				if(uds > 0){
					if(prodDev.isEnabled()){
						devProd.setID_Compra(compraConProductos.getCompra().getIDCompra());
						devProd.setIDProducto(producto.getIDProducto());
						devProd.setCantidad(uds); 
						prodsToDev.add(devProd);
						prodDev.setEnabled(false);
						okDevbutton.setBackground(new Color(236, 115, 115));
						okDevbutton.setText("Cancelar");
					}else{
						prodsToDev.remove(devProd);
						prodDev.setEnabled(true);
						okDevbutton.setBackground(new Color(39, 174, 95));
						okDevbutton.setText("Confirmar");
						
					}
					
				}
			}
			
		});
		
		devPanel.add(infoDev);
		devPanel.add(prodDev);
		devPanel.add(okDevbutton);

		// CONSTRUIR PANEL
		panel.add(infoProd, BorderLayout.CENTER);
		panel.add(devPanel, BorderLayout.EAST);

		return panel;
	}

	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setToolTipText("Volver a la lista de compras");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_COMPRA));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_DEVOLUCION_PRODUCTO_OK){
			JOptionPane.showMessageDialog(null, "Devolucion realizada correctamente.");
		}else if(res.getEvento() == Eventos.RES_DEVOLUCION_PRODUCTO_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la devolucion correctamente.");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_COMPRA));
		dispose();

	}
}