package Presentaci�n.Compra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

import Negocio.Compra.TCarrito;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Controller;
import Presentaci�n.Controller.Eventos;
import Presentaci�n.FactoriaVistas.IGUI;

public class VValidacionCompra extends JFrame implements IGUI {

	TCarrito carrito;

	public VValidacionCompra(TCarrito carrito) {
		this.carrito = carrito;
		init_GUI();
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_ABRIR_COMPRA_OK) {
			Controller.getInstance().action(new Context(Eventos.CREAR_V_PAGAR_COMPRA, (TCarrito) res.getDatos()));
			dispose();
		} else if (res.getEvento() == Eventos.RES_ABRIR_COMPRA_KO) {
			JOptionPane.showMessageDialog(null, "El ID introducido no es v�lido. Aseg�rese de que el cliente existe y est� activo");
		}

	}

	public void pago() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setBackground(new Color(197, 233, 205));
		this.add(mainPanel);

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);
		contentContainer.setBackground(new Color(197, 233, 205));

		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setBackground(new Color(39, 174, 95));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));

		// ICONO DE MENU
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_IS_peque�o.png"))));
		icon.setBackground(new Color(39, 174, 95));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);

		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Pago realizado con �xito");
		help.setForeground(new Color(57, 119, 70));
		help.setFont(new Font("sans-serif", 1, 17));
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.setBackground(new Color(197, 233, 205));
		helpPanel.add(help);

		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		// DESPEDIDA 1
		JPanel goodbye = new JPanel();
		goodbye.setMaximumSize(new Dimension(1000, 350));
		goodbye.setBackground(new Color(197, 233, 205));
		goodbye.setLayout(new BoxLayout(goodbye, BoxLayout.Y_AXIS));
		// MENSAJE
		JLabel mensaje = new JLabel("�Gracias por comprar en Supermercado!");
		mensaje.setFont(new Font("sans-serif", 1, 25));
		mensaje.setForeground(new Color(57, 119, 70));
		mensaje.setBackground(new Color(197, 233, 205));
		mensaje.setAlignmentX(CENTER_ALIGNMENT);
		// GIF
		JLabel gif = new JLabel();
		gif.setIcon(new ImageIcon((getClass().getClassLoader().getResource("despedida.gif"))));
		gif.setBackground(new Color(197, 233, 205));
		gif.setAlignmentX(CENTER_ALIGNMENT);

		goodbye.add(mensaje);
		goodbye.add(Box.createRigidArea(new Dimension(0, 10)));
		goodbye.add(gif);

		contentContainer.add(goodbye);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		// DESPEDIDA 2
		JPanel byePanel = new JPanel();
		byePanel.setBackground(new Color(197, 233, 205));
		JLabel bye = new JLabel("�VUELVA PRONTO!");
		bye.setForeground(new Color(57, 119, 70));
		bye.setFont(new Font("sans-serif", 1, 25));
		byePanel.setMaximumSize(new Dimension(1000, 60));
		byePanel.add(bye);

		contentContainer.add(byePanel);
		mainPanel.add(contentContainer);

		this.pack();
		this.setVisible(true);
	}

	public void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setAutoscrolls(true);

		// TOTAL CONTAINER
		JPanel totalContainer = new JPanel();
		totalContainer.setLayout(new BoxLayout(totalContainer, BoxLayout.Y_AXIS));
		totalContainer.setAlignmentX(CENTER_ALIGNMENT);

		DecimalFormat df = new DecimalFormat("#.##");
		JLabel totalLabel = new JLabel("TOTAL: " + df.format(carrito.getCompra().getPrecioTotal()) + " � (Sin descuento aplicado)");

		totalLabel.setFont(new Font(totalLabel.getFont().getName(), Font.PLAIN, 30));

		// INFORMACION DE PAGO CONTAINER
		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));

		informacionPagoContainer.add(crearEfectivoInformacionDePago());
		


		// Construir vista
		this.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);

		// BACK BUTTON
		contentContainer.add(backButtonContainer());

		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		// TOTAL
		contentContainer.add(totalContainer);
		totalContainer.add(totalLabel);

		contentContainer.add(Box.createRigidArea(new Dimension(0, 25)));

		// HELP

		contentContainer.add(Box.createRigidArea(new Dimension(0, 40)));

		// INFORMACION DE PAGO
		contentContainer.add(informacionPagoContainer);

		this.pack();
		this.setVisible(true);

	}

	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 70));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.ABRIR_COMPRA, carrito.getCompra().getIdTrabajador()));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	private JPanel createFormField(String title, ArrayList<JTextField> textFields) {

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setOpaque(false);
		container.setAlignmentX(CENTER_ALIGNMENT);

		JLabel label = new JLabel(title);
		label.setAlignmentX(CENTER_ALIGNMENT);

		JTextField input = new JTextField(6);
		input.setMaximumSize(new Dimension(1000, 30));
		input.setPreferredSize(new Dimension(400, 30));
		textFields.add(input);

		container.add(label);
		container.add(input);

		return container;
	}

	private JPanel crearEfectivoInformacionDePago() {

		ArrayList<JTextField> textFields = new ArrayList<JTextField>();

		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));
		informacionPagoContainer.setMaximumSize(new Dimension(400, 300));

		JButton submitButton = new JButton("Validar");
		submitButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("pago_icon.jpg"))));
		submitButton.setMaximumSize(new Dimension(1000, 40));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setBackground(new Color(39, 174, 95));
		submitButton.setForeground(Color.white);
		submitButton.setBorderPainted(false);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					carrito.getCompra().setIdCliente(Integer.parseInt(textFields.get(0).getText()));
					Controller.getInstance().action(new Context(Eventos.VALIDAR_COMPRA, carrito));
					dispose();
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "El ID debe ser un valor num�rico");
				}

			}
		});

		informacionPagoContainer.add(createFormField("ID de Cliente", textFields));

		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		informacionPagoContainer.add(submitButton);

		return informacionPagoContainer;
	}
}
