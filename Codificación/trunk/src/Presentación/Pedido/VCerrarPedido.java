package Presentación.Pedido;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.Pedido.TComanda;
import Presentación.JPanelConFondo;
import Presentación.Command.Context;
import Presentación.Compra.VMostrarAyudaSuper;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

public class VCerrarPedido extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	TComanda comanda;
	
	public VCerrarPedido(TComanda comanda) {
		this.comanda=comanda;
		init_GUI();
		this.setLocationRelativeTo(null);
	}
	
	
	public void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));

		// CONTENT CONTAINER
		JPanelConFondo contentContainer = new JPanelConFondo();
		contentContainer.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_rojo.png")).getImage());
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setAutoscrolls(true);
		
		// TOTAL CONTAINER
		JPanel totalContainer = new JPanel();
		totalContainer.setOpaque(false);
		totalContainer.setLayout(new BoxLayout(totalContainer, BoxLayout.Y_AXIS));
		totalContainer.setAlignmentX(CENTER_ALIGNMENT);

		DecimalFormat df = new DecimalFormat("#.##");
		JLabel totalLabel = new JLabel("TOTAL: " + df.format(comanda.getPedido().getPrecioTotal()) + " €");
		totalLabel.setForeground(Color.white);
		totalLabel.setFont(new Font(totalLabel.getFont().getName(), Font.PLAIN, 30));
		
		
		// INFORMACION DE PAGO CONTAINER
		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setOpaque(false);
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));

		// HELP
		JPanel helpPanel = new JPanel();
		helpPanel.setOpaque(false);
		JLabel help = new JLabel("Seleccione una forma de pago");
		help.setForeground(Color.white);
		helpPanel.setMaximumSize(new Dimension(1000, 30));
		helpPanel.add(help);

		// METODOS DE PAGO BUTTONS CONTAINER
		JPanel buttonsContainer = new JPanel(new FlowLayout());
		buttonsContainer.setOpaque(false);
		buttonsContainer.setMaximumSize(new Dimension(1000, 40));

		JButton efectivoButton = new JButton("Efectivo");
		efectivoButton.setBackground(Color.white);
		efectivoButton.setForeground(new Color(128, 0, 0));
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
		tarjetaButton.setBackground(Color.white);
		tarjetaButton.setForeground(new Color(128, 0, 0));
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
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		scrollFrame.setOpaque(false);
		mainPanel.add(scrollFrame);
		this.add(mainPanel);

		// BACK BUTTON
		contentContainer.add(backButtonContainer());

		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		contentContainer.add(Box.createRigidArea(new Dimension(0, 25)));
		
		//TOTAL
		contentContainer.add(totalContainer);
		totalContainer.add(totalLabel);
		
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
	
	
	private JPanel crearEfectivoInformacionDePago() {

		ArrayList<JTextField> textFields = new ArrayList<JTextField>();

		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setOpaque(false);
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));
		informacionPagoContainer.setMaximumSize(new Dimension(400, 300));

		JButton submitButton = new JButton("Pagar");
		submitButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("help/pagoRestHelp_icon.png"))));
		submitButton.setMaximumSize(new Dimension(1000, 40));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setBackground(new Color(255, 127, 80));
		submitButton.setForeground(Color.white);
		submitButton.setBorder(BorderFactory.createBevelBorder(0));

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					comanda.getPedido().setPrecioPagado(Double.parseDouble(textFields.get(0).getText()));
					int id_aux=Integer.parseInt(textFields.get(1).getText());
					comanda.getPedido().setId_cliente(id_aux);
					if (comanda.getPedido().getPrecioPagado() < comanda.getPedido().getPrecioTotal())
						throw new Exception();
					Controller.getInstance().action(new Context(Eventos.CERRAR_PEDIDO, comanda));
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "La cantidad en efectivo y el ID del consumidor deberán tener valor numérico");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error. El precio de la compra es superior al precio abonado.");
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
	
	
	private JPanel crearTarjetaInformacionDePago() {

		ArrayList<JTextField> textFields = new ArrayList<JTextField>();

		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setOpaque(false);
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));
		informacionPagoContainer.setMaximumSize(new Dimension(400, 300));
		informacionPagoContainer.setAlignmentX(CENTER_ALIGNMENT);

		JLabel imgLabel = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("creditCards.png"))));
		imgLabel.setAlignmentX(CENTER_ALIGNMENT);

		JPanel expirationDateAndCVVContainer = new JPanel(new GridLayout(1, 2, 5, 5));
		expirationDateAndCVVContainer.setOpaque(false);
		expirationDateAndCVVContainer.setMaximumSize(new Dimension(1000, 60));

		JButton submitButton = new JButton("Pagar");
		submitButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("creditCard_icon.png"))));
		submitButton.setMaximumSize(new Dimension(1000, 40));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setBackground(new Color(255, 127, 80));
		submitButton.setForeground(Color.white);
		submitButton.setBorder(BorderFactory.createBevelBorder(0));

		JButton boton = new JButton();
		boton.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog vIden = new VMostrarAyudaSuper();
			}
		});

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					comanda.getPedido().setId_cliente(Integer.parseInt(textFields.get(3).getText()));
					comanda.getPedido().setPrecioPagado(comanda.getPedido().getPrecioTotal());
					Controller.getInstance().action(new Context(Eventos.CERRAR_PEDIDO, comanda));
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "ID debe ser un número entero");
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
	
	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setOpaque(false);
		backButtonContainer.setMaximumSize(new Dimension(1000, 70));

		JButton backButton = new JButton();
		backButton.setContentAreaFilled (false);
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("backBlanco_icon.png"))));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance().action(new Context(Eventos.CREAR_VCARRITO, comanda));//
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
		label.setForeground(Color.white);
		label.setAlignmentX(CENTER_ALIGNMENT);

		JTextField input = new JTextField(6);
		input.setMaximumSize(new Dimension(1000, 30));
		input.setPreferredSize(new Dimension(400, 30));
		textFields.add(input);

		container.add(label);
		container.add(input);

		return container;
	}
	
	
	@Override
	public void actualizar(Context res) {
		if (res.getEvento() == Eventos.RES_PAGAR_PEDIDO_KO) {
			JOptionPane.showMessageDialog(null, "El pedido no se ha podido realizar");
		} else if (res.getEvento() == Eventos.RES_PAGAR_PEDIDO_OK) {
			pago();
			JOptionPane.showMessageDialog(null, "Pago realizado con exito. ¡Gracias por su compra!");
			Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_REST, comanda.getPedido().getId_personal()));
			dispose();
		} else if (res.getEvento() == Eventos.RES_BUSCAR_CLIENTE_KO) {
			JOptionPane.showMessageDialog(null, "El cliente no existe");
		} else if (res.getEvento() == Eventos.RES_BUSCAR_TRABAJADOR_ID_KO) {
			JOptionPane.showMessageDialog(null, "El trabajador no existe");
		} else if (res.getEvento() == Eventos.RES_BUSCAR_PRODUCTO_KO) {
			JOptionPane.showMessageDialog(null, "Algún producto de la lista no existe");
		} else if (res.getEvento() == Eventos.STOCK_INSUFICIENTE) {
			JOptionPane.showMessageDialog(null, "Algún producto no tiene suficiente stock");
		} else if (res.getEvento() == Eventos.PRECIO_PAGADO_INSUFICIENTE) {
			JOptionPane.showMessageDialog(null, "El precio pagado es menor al precio total de la compra");
		}
	}
	
	public void pago() {
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanelConFondo mainPanel = new JPanelConFondo();
		mainPanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_pizarra.png")).getImage());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setBackground(new Color(180, 0, 0));
		this.add(mainPanel);

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);
		contentContainer.setOpaque(false);

		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setBackground(new Color(128, 0, 0));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));

		// ICONO DE MENU
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_restaurante_pequeño_blanco.png"))));
		icon.setBackground(new Color(128, 0, 0));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);

		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Pago realizado con éxito");
		help.setForeground(new Color(128, 0, 0));
		help.setFont(new Font("sans-serif", 1, 17));
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.setOpaque(false);
		helpPanel.add(help);

		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		// DESPEDIDA 1
		JPanel goodbye = new JPanel();
		goodbye.setMaximumSize(new Dimension(1000, 350));
		goodbye.setOpaque(false);
		goodbye.setLayout(new BoxLayout(goodbye, BoxLayout.Y_AXIS));
		// MENSAJE
		JLabel mensaje = new JLabel("¡Gracias por su pedido!");
		mensaje.setFont(new Font("sans-serif", 1, 25));
		mensaje.setForeground(new Color(128, 0, 0));
		mensaje.setOpaque(false);
		mensaje.setAlignmentX(CENTER_ALIGNMENT);
		// GIF
		JLabel gif = new JLabel();
		gif.setIcon(new ImageIcon((getClass().getClassLoader().getResource("despedida.gif"))));
		gif.setOpaque(false);
		gif.setAlignmentX(CENTER_ALIGNMENT);

		goodbye.add(mensaje);
		goodbye.add(Box.createRigidArea(new Dimension(0, 10)));
		goodbye.add(gif);

		contentContainer.add(goodbye);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));

		// DESPEDIDA 2
		JPanel byePanel = new JPanel();
		byePanel.setOpaque(false);
		JLabel bye = new JLabel("¡VUELVA PRONTO!");
		bye.setForeground(new Color(128, 0, 0));
		bye.setFont(new Font("sans-serif", 1, 25));
		byePanel.setMaximumSize(new Dimension(1000, 60));
		byePanel.add(bye);

		contentContainer.add(byePanel);
		mainPanel.add(contentContainer);

		this.pack();
		this.setVisible(true);
	}

}
