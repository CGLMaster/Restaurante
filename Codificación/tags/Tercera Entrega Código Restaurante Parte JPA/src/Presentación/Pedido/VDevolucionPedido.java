package Presentación.Pedido;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Pedido.TLineaPedido;
import Negocio.Pedido.TPedido;
import Negocio.Pedido.TPedidoConPlatos;
import Negocio.Plato.TPlato;

@SuppressWarnings("serial")
public class VDevolucionPedido extends JFrame implements IGUI {
	TPedidoConPlatos pedidoconplatos;
	List<TLineaPedido> listalineas;
	List<TLineaPedido> platsToDev;
	
	public VDevolucionPedido(TPedidoConPlatos tPedidoconplatos){
		setTitle("Devolucion de pedido");
		pedidoconplatos = tPedidoconplatos;
		listalineas = pedidoconplatos.getLineaPedidos();
		platsToDev = new ArrayList<TLineaPedido>();
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
				if (platsToDev.size() > 0) {
					int res = JOptionPane.showConfirmDialog(null, "¿Desea confirmar la devolucion?");

					if (res == 0) {
						
						Controller.getInstance().action(new Context(Eventos.DEVOLUCION_PEDIDO, platsToDev));
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No es posible devolver 0 productos.");
				}
			}

		});

		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setBackground(new Color(128, 0, 0));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));

		// TITLE
		JLabel title = new JLabel("Pedidos");
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
		TPedido pedido = pedidoconplatos.getPedido();

		JLabel idLabel = new JLabel("ID: " + pedido.getID());
		JLabel fecha = new JLabel("Fecha: " + pedido.getFecha());

		JLabel productosTitle = new JLabel("PLATOS");
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
		JLabel help = new JLabel("Haga click en un pedido para mostrar mas informacion");
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
		for (TLineaPedido linea :listalineas) {
			TPlato platoCantidad = pedidoconplatos.getPlatos().get(linea.getIdtPlato());
			int cantidad = linea.getCantidad();
			contentContainer.add(seccionPanel(platoCantidad, cantidad));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		this.pack();
		this.setVisible(true);

	}

	private JPanel seccionPanel(TPlato plato, int cantidad) {
		TLineaPedido platDev = new TLineaPedido();

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 75));

		// INFO PRODUCTO PANEL
		JPanel infoProd = new JPanel();
		JLabel precioTotal = new JLabel(cantidad * plato.getPrecio() + " € ");
		// LABEL
		JLabel label = new JLabel(
				String.valueOf(plato.getId() + ":   " + plato.getNombre() + "   X   " + cantidad));
		infoProd.add(label);
		infoProd.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.WEST);
		infoProd.add(precioTotal);
		infoProd.setBackground(Color.white);

		// DEVOLUCION PANEL
		JPanel devPanel = new JPanel();
		JLabel infoDev = new JLabel();
		infoDev.setText("Usted devuelve 0 platos/s ");
		devPanel.setBackground(Color.white);
		JSpinner prodDev = new JSpinner(new SpinnerNumberModel(cantidad, 0, cantidad, 1));
		prodDev.setMaximumSize(new Dimension(40, 40));
		prodDev.setPreferredSize(new Dimension(40, 40));
		prodDev.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int udsdev = cantidad - (int) prodDev.getValue();
				infoDev.setText("Usted devuelve " + udsdev + " platos/s ");
			}

		});

		JButton okDevbutton = new JButton("Confirmar");
		okDevbutton.setBackground(new Color(128, 174, 95));
		okDevbutton.setForeground(Color.white);
		okDevbutton.setBorderPainted(false);
		okDevbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int uds = cantidad - (int) prodDev.getValue();
				if (uds > 0) {
					if (prodDev.isEnabled()) {
						platDev.setIdPedido(pedidoconplatos.getPedido().getID());
						platDev.setPlato(plato.getId());
						platDev.setCantidad(uds); 
						platDev.setPrecio(plato.getPrecio());
						platsToDev.add(platDev);
						prodDev.setEnabled(false);
						okDevbutton.setBackground(new Color(236, 115, 115));
						okDevbutton.setText("Cancelar");
					} else {
						platsToDev.remove(platDev);
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
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("backRest_icon.png"))));
		backButton.setToolTipText("Volver a la lista de compras");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PEDIDO));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_DEVOLUCION_PLATOS_OK) {
			JOptionPane.showMessageDialog(null, "Devolucion realizada correctamente del pedido con id: " + res.getDatos());
		} else if (res.getEvento() == Eventos.RES_DEVOLUCION_PLATOS_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la devolucion correctamente.");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PEDIDO));
		dispose();
	}
}