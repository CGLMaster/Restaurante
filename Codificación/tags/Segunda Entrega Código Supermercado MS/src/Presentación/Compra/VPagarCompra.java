package Presentaci�n.Compra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import Negocio.Compra.TCarrito;
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Controller;
import Presentaci�n.Controller.Eventos;
import Presentaci�n.FactoriaVistas.IGUI;

public class VPagarCompra extends JFrame implements IGUI {

	private TCarrito compraTotal;//

	public VPagarCompra(TCarrito compra) {
		compraTotal = compra;
		init_GUI();
	}

	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_PAGAR_COMPRA_KO) {
			JOptionPane.showMessageDialog(null, "La compra no se ha podido realizar");
		} else if (res.getEvento() == Eventos.RES_PAGAR_COMPRA_OK) {
			pago();
			JOptionPane.showMessageDialog(null, "Pago realizado con exito. �Gracias por su compra!");
			Controller.getInstance()
					.action(new Context(Eventos.CREAR_VPRINCIPAL, compraTotal.getCompra().getIdTrabajador()));//
			dispose();
		} else if (res.getEvento() == Eventos.RES_BUSCAR_CLIENTE_KO) {
			JOptionPane.showMessageDialog(null, "El cliente no existe");
		} else if (res.getEvento() == Eventos.RES_BUSCAR_TRABAJADOR_DNI_KO) {
			JOptionPane.showMessageDialog(null, "El trabajador no existe");
		} else if (res.getEvento() == Eventos.RES_BUSCAR_PRODUCTO_KO) {
			JOptionPane.showMessageDialog(null, "Alg�n producto de la lista no existe");
		} else if (res.getEvento() == Eventos.STOCK_INSUFICIENTE) {
			JOptionPane.showMessageDialog(null, "Alg�n producto no tiene suficiente stock");
		} else if (res.getEvento() == Eventos.PRECIO_PAGADO_INSUFICIENTE) {
			JOptionPane.showMessageDialog(null, "El precio pagado es menor al precio total de la compra");
		}
	}

	public void pago() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		this.getContentPane().removeAll();

		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setAutoscrolls(true);

		// INFORMACION DE PAGO CONTAINER
		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Seleccione una forma de pago");
		helpPanel.setMaximumSize(new Dimension(1000, 30));
		helpPanel.add(help);

		// METODOS DE PAGO BUTTONS CONTAINER
		JPanel buttonsContainer = new JPanel(new FlowLayout());
		buttonsContainer.setMaximumSize(new Dimension(1000, 40));

		JButton efectivoButton = new JButton("Efectivo");
		efectivoButton.setBackground(new Color(39, 174, 95));
		efectivoButton.setForeground(Color.white);
		efectivoButton.setFont(new Font("sans-serif", 1, 15));
		efectivoButton.setPreferredSize(new Dimension(100, 30));
		efectivoButton.setBorder(BorderFactory.createBevelBorder(0));

		efectivoButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				informacionPagoContainer.removeAll();
				informacionPagoContainer.revalidate();
				informacionPagoContainer.repaint();

				informacionPagoContainer.add(crearEfectivoInformacionDePago());
			}
		});

		JButton tarjetaButton = new JButton("Tarjeta");
		tarjetaButton.setBackground(new Color(39, 174, 95));
		tarjetaButton.setForeground(Color.white);
		tarjetaButton.setFont(new Font("sans-serif", 1, 15));
		tarjetaButton.setPreferredSize(new Dimension(100, 30));
		tarjetaButton.setBorder(BorderFactory.createBevelBorder(0));

		tarjetaButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				informacionPagoContainer.removeAll();
				informacionPagoContainer.revalidate();
				informacionPagoContainer.repaint();

				informacionPagoContainer.add(crearTarjetaInformacionDePago());
			}
		});

		// CONSTRUIR VISTA
		this.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);

		// BACK BUTTON
		contentContainer.add(backButtonContainer());

		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		contentContainer.add(Box.createRigidArea(new Dimension(0, 25)));

		// HELP
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// METODOS DE PAGO BUTTONS
		contentContainer.add(buttonsContainer);
		buttonsContainer.add(efectivoButton);
		buttonsContainer.add(tarjetaButton);

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

				Controller.getInstance().action(new Context(Eventos.CREAR_VCOMPRA, compraTotal));//
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

	private JPanel crearTarjetaInformacionDePago() {

		ArrayList<JTextField> textFields = new ArrayList<JTextField>();

		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));
		informacionPagoContainer.setMaximumSize(new Dimension(400, 300));
		informacionPagoContainer.setAlignmentX(CENTER_ALIGNMENT);

		JLabel imgLabel = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("creditCards.png"))));
		imgLabel.setAlignmentX(CENTER_ALIGNMENT);

		JPanel expirationDateAndCVVContainer = new JPanel(new GridLayout(1, 2, 5, 5));
		expirationDateAndCVVContainer.setMaximumSize(new Dimension(1000, 60));

		JButton submitButton = new JButton("Pagar");
		submitButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("pago_icon.jpg"))));
		submitButton.setMaximumSize(new Dimension(1000, 40));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setBackground(new Color(39, 174, 95));
		submitButton.setForeground(Color.white);
		submitButton.setBorderPainted(false);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					compraTotal.getCompra().setIdCliente(Integer.parseInt(textFields.get(3).getText()));
					compraTotal.getCompra().setPrecioPagado(-1);//

					Controller.getInstance().action(new Context(Eventos.CREAR_V_PAGAR_COMPRA, compraTotal));//
					dispose();
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "ID debe ser un n�mero entero");
					exception.printStackTrace();
				}

			}
		});

		informacionPagoContainer.add(imgLabel);

		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		informacionPagoContainer.add(createFormField("Card number", textFields));

		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		informacionPagoContainer.add(expirationDateAndCVVContainer);
		expirationDateAndCVVContainer.add(createFormField("Expiration Date", textFields));
		expirationDateAndCVVContainer.add(createFormField("CVV", textFields));

		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		informacionPagoContainer.add(createFormField("ID de Cliente", textFields));

		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		informacionPagoContainer.add(submitButton);

		return informacionPagoContainer;
	}

	private JPanel crearEfectivoInformacionDePago() {

		ArrayList<JTextField> textFields = new ArrayList<JTextField>();

		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));
		informacionPagoContainer.setMaximumSize(new Dimension(400, 300));

		JButton submitButton = new JButton("Pagar");
		submitButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("pago_icon.jpg"))));
		submitButton.setMaximumSize(new Dimension(1000, 40));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setBackground(new Color(39, 174, 95));
		submitButton.setForeground(Color.white);
		submitButton.setBorderPainted(false);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					compraTotal.getCompra().setPrecioPagado(Float.parseFloat(textFields.get(0).getText()));

					compraTotal.getCompra().setIdCliente(Integer.parseInt(textFields.get(1).getText()));

					Controller.getInstance().action(new Context(Eventos.CREAR_V_PAGAR_COMPRA, compraTotal));//
					dispose();
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "ID y cantidad en efectivo deben ser n�meros");
				}

			}
		});

		informacionPagoContainer.add(createFormField("Cantidad en efectivo a pagar", textFields));

		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		informacionPagoContainer.add(createFormField("ID de Cliente", textFields));

		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		informacionPagoContainer.add(submitButton);

		return informacionPagoContainer;
	}

}