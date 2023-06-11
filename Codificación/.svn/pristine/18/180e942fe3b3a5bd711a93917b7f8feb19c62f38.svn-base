package Presentación.Pedido;

import javax.swing.JFrame;

import Presentación.JPanelConFondo;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Negocio.Pedido.TComanda;
import Negocio.Pedido.TLineaPedido;

@SuppressWarnings("serial")
public class VAbrirPedido extends JFrame implements IGUI {

	TComanda comanda;

	public VAbrirPedido(TComanda comanda) {
		this.comanda = comanda;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		this.getContentPane().removeAll();

		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setOpaque(false);

		// CONTENT CONTAINER
		JPanelConFondo contentContainer = new JPanelConFondo();
		contentContainer.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_rojo.png")).getImage());
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// AÑADIR PLATOS CONTAINER
		JPanel addPlatosContainer = new JPanel(new GridLayout(0, 3, 10, 10));
		addPlatosContainer.setMaximumSize(new Dimension(1000, 30));
		addPlatosContainer.setOpaque(false);

		// ID PLATO INPUT
		JTextField idInput = new JTextField(6);
		idInput.setText("ID de plato");
		idInput.setForeground(Color.GRAY);
		idInput.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				idInput.setText(null);
				idInput.setForeground(Color.BLACK);
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
		;

		// CANTIDAD INPUT
		JTextField cantidadInput = new JTextField(6);
		cantidadInput.setText("Cantidad");
		cantidadInput.setForeground(Color.GRAY);
		cantidadInput.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				cantidadInput.setText(null);
				cantidadInput.setForeground(Color.BLACK);
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

		// AÑADIR BUTTON
		JButton addButton = new JButton("Añadir");
		addButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("aniadirPlato_icon.png"))));
		addButton.setBackground(new Color(255, 127, 80));
		addButton.setForeground(Color.white);
		addButton.setBorder(BorderFactory.createBevelBorder(0));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					HashMap<String, Object> args = new HashMap<String, Object>();

					args.put("linea", new TLineaPedido(Integer.parseInt(idInput.getText()),
							Integer.parseInt(cantidadInput.getText())));
					args.put("comanda", comanda);

					Controller.getInstance().action(new Context(Eventos.ANIADIR_PLATO_PEDIDO, args));
				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Introduzca valor numerico");
				}
			}
		});

		// PLATOS EN PEDIDO CONTAINER
		JPanel gridOutsidePlatosContainer = new JPanel(new GridLayout(0, 1));
		gridOutsidePlatosContainer.setOpaque(false);
		JPanel platosContainer = new JPanel();
		platosContainer.setLayout(new BoxLayout(platosContainer, BoxLayout.Y_AXIS));
		platosContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		platosContainer.setOpaque(false);

		// PANEL DEL FINAL

		JPanel bottomContainer = bottomContainer();

		// CONSTRUIR VISTA
		this.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		scrollFrame.setOpaque(false);
		mainPanel.add(scrollFrame);

		// BACK BUTTON
		contentContainer.add(backButtonContainer());

		contentContainer.add(Box.createRigidArea(new Dimension(0, 15)));

		// AÑADIR PLATOS
		contentContainer.add(addPlatosContainer);
		addPlatosContainer.add(idInput);
		addPlatosContainer.add(cantidadInput);
		addPlatosContainer.add(addButton);

		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// PLATOS EN PEDIDO
		contentContainer.add(gridOutsidePlatosContainer);
		gridOutsidePlatosContainer.add(platosContainer);

		if (comanda.getMapaLineas().isEmpty()) {
			JLabel texto = new JLabel("Pedido vacío", SwingConstants.CENTER);
			texto.setForeground(Color.white);
			platosContainer.setLayout(new GridLayout(0, 1));
			platosContainer.add(texto, SwingConstants.CENTER);
		}

		for (Entry<Integer, TLineaPedido> plato : comanda.getMapaLineas().entrySet()) {

			platosContainer.add(platoPanel(plato.getValue()));
			platosContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(bottomContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		this.pack();
		this.setVisible(true);
	}

	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new BorderLayout());
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		backButtonContainer.setOpaque(false);

		JButton backButton = new JButton();
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("backBlanco_icon.png"))));
		backButton.setContentAreaFilled (false);
		backButton.setBorderPainted(false);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance()
						.action(new Context(Eventos.CREAR_VPRINCIPAL_REST, comanda.getPedido().getId_personal()));
				dispose();
			}
		});

		// TITLE
		JLabel title = new JLabel("Pedido");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);

		backButtonContainer.add(backButton, BorderLayout.WEST);

		return backButtonContainer;
	}

	private JPanel bottomContainer() {
		JPanel bottomContainer = new JPanel();
		bottomContainer.setLayout(new BoxLayout(bottomContainer, BoxLayout.X_AXIS));
		bottomContainer.setMaximumSize(new Dimension(1025, 50));
		bottomContainer.setOpaque(false);

		// PAGAR BUTTON
		JButton pagarButton = new JButton("Pagar");
		pagarButton.setBackground(new Color(255, 127, 80));
		pagarButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("pagoRest_icon.png"))));
		pagarButton.setForeground(Color.white);
		pagarButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (comanda.getMapaLineas().size() > 0) {

					Controller.getInstance().action(new Context(Eventos.VALIDAR_PEDIDO_SIN_CLIENTE, comanda));

				} else {
					JOptionPane.showMessageDialog(null, "Operacion no permitida el pedido esta vacio");
				}

			}
		});

		// AYUDA BUTTON
		JButton botonAyuda = new JButton();
		botonAyuda.setContentAreaFilled(false);
		JDialog vIden = new VMostrarAyudaRest();
		botonAyuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vIden.setVisible(true);
			}
		});

		botonAyuda.setBackground(new Color(237, 237, 237));
		botonAyuda.setBorderPainted(false);
		botonAyuda.setPreferredSize(new Dimension(60, 50));
		botonAyuda.setMinimumSize(new Dimension(40, 50));
		botonAyuda.setMaximumSize(new Dimension(40, 50));
		botonAyuda.setIcon(new ImageIcon((getClass().getClassLoader().getResource("ayuda_icon.png"))));
		botonAyuda.setToolTipText("Necesito ayuda");

		bottomContainer.add(Box.createRigidArea(new Dimension(450, 0)));
		bottomContainer.add(pagarButton);
		bottomContainer.add(Box.createRigidArea(new Dimension(400, 0)));
		bottomContainer.add(botonAyuda);

		return bottomContainer;
	}

	private JPanel platoPanel(TLineaPedido pedido) {

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

		// DELETE BUTTON
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("trashRest_icon.jpg"))));
		deleteButton.setBackground(new Color(128, 0, 0));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				HashMap<String, Object> args = new HashMap<String, Object>();
				args.put("linea", pedido);
				args.put("comanda", comanda);

				Controller.getInstance().action(new Context(Eventos.ELIMINAR_PLATO_PEDIDO, args));
			}

		});

		// LABELS
		JLabel idYCantidad = new JLabel(pedido.getIdtPlato() + " X " + pedido.getCantidad());
		idYCantidad.setFont(new Font(idYCantidad.getFont().getName(), Font.PLAIN, 15));

		// CONSTRUIR PANEL
		panel.add(idYCantidad, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		buttonContainer.add(deleteButton);

		return panel;
	}

	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_ABRIR_PEDIDO_KO){
			JOptionPane.showMessageDialog(null, "Error en la validación de la compra. Revise que los IDs existen, el stock es suficiente y los platos están activos.");
		}
		if(res.getEvento() == Eventos.RES_ABRIR_PEDIDO_OK){
			Controller.getInstance().action(new Context(Eventos.V_CERRAR_PEDIDO, (TComanda) res.getDatos()));
			dispose();
		}
		else if(res.getEvento() == Eventos.STOCK_INSUFICIENTE){
			JOptionPane.showMessageDialog(null, "Algún plato no tiene suficiente stock");
		}
		else if(res.getEvento() == Eventos.ANIADIR_PLATO_PEDIDO_OK){
			comanda=(TComanda) res.getDatos();
			init_GUI();
		}
		else if(res.getEvento() == Eventos.ELIMINAR_PLATO_PEDIDO_OK){
			comanda=(TComanda) res.getDatos();
			init_GUI();
		}
	}
}