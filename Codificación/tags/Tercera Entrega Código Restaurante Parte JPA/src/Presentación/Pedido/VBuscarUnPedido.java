package Presentación.Pedido;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Negocio.Pedido.TLineaPedido;
import Negocio.Pedido.TPedido;
import Negocio.Pedido.TPedidoConPlatos;
import Negocio.Plato.TPlato;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VBuscarUnPedido extends JFrame implements IGUI {
	
	private TPedidoConPlatos pedidoConPlatos;
	private HashMap<Integer,TPlato> platos;

	public VBuscarUnPedido(TPedidoConPlatos tPedidoConPlatos) {
		this.pedidoConPlatos = tPedidoConPlatos;
		platos = tPedidoConPlatos.getPlatos();
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	private void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		// BACK BUTTON
		JPanel backButtonContainer = backButtonContainer();
		
		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setBackground(new Color(127,0,0));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));

		// TITULO
		JLabel titleLabel = new JLabel("Pedido");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setForeground(new Color(128, 0, 0));
		titleLabel.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(titleLabel);

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);
		
		//INFO CONTAINER
		JPanel infoContainer = new JPanel();
		infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));
		infoContainer.setAlignmentX(CENTER_ALIGNMENT);
		infoContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		infoContainer.setBackground(Color.white);


		// INFO
		TPedido pedido = this.pedidoConPlatos.getPedido();
		
		JLabel idLabel = new JLabel("ID: " + pedido.getID());
		JLabel clienteLabel = new JLabel("ID Cliente: " + pedido.getId_cliente());
		JLabel empleadoLabel = new JLabel("ID Empleado: " + pedido.getId_personal());
		JLabel precioLabel = new JLabel("Precio Total: " + pedido.getPrecioTotal() + " \u20AC");
		JLabel fechaLabel = new JLabel("Fecha: " + pedido.getFecha());
		
		JLabel platosTitle = new JLabel("Platos");
		platosTitle.setFont(new Font("sans-serif", 1, 20));
		platosTitle.setForeground(new Color(128, 0, 0));
		platosTitle.setAlignmentX(CENTER_ALIGNMENT);

		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		
		contentContainer.add(infoContainer);
		infoContainer.add(idLabel);
		infoContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		infoContainer.add(clienteLabel);
		infoContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		infoContainer.add(empleadoLabel);
		infoContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		infoContainer.add(precioLabel);
		infoContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		infoContainer.add(fechaLabel);
		infoContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		contentContainer.add(platosTitle);

		contentContainer.add(Box.createRigidArea(new Dimension(0, 5)));
		
		// Platos
				for (int i = 0; i < platos.size(); i++) {

					TLineaPedido lineaPedido = pedidoConPlatos.getLineaPedidos().get(i);
					TPlato plato = platos.get(lineaPedido.getIdtPlato());
					contentContainer.add(platosPanel(plato, lineaPedido));
					contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
				}

		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);

		this.pack();
		this.setVisible(true);
	}
	private JPanel platosPanel(TPlato plato, TLineaPedido linea) {

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 75));

		//INFO PRODUCTO PANEL
		JPanel infoProd = new JPanel();
		JLabel precioTotal = new JLabel(linea.getCantidad() * linea.getPrecio() + " \u20AC ");
		// LABEL
		JLabel label = new JLabel(String
				.valueOf(plato.getId() + ":   " + plato.getNombre() + "   X   " + linea.getCantidad()));
		infoProd.add(label);
		infoProd.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.WEST);
		infoProd.add(precioTotal);
		infoProd.setBackground(Color.white);
		infoProd.setAlignmentY(CENTER_ALIGNMENT);

		// CONSTRUIR PANEL
		panel.add(infoProd, BorderLayout.WEST);

		return panel;
	}
	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("backRest_icon.png"))));
		backButton.setToolTipText("Volver a Pedidos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PEDIDO, 0));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}
	@Override
	public void actualizar(Context res) {
		
	}
}