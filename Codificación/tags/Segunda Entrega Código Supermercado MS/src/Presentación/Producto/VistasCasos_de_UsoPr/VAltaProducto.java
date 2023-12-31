package Presentación.Producto.VistasCasos_de_UsoPr;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Marca.TMarca;
import Negocio.Producto.TProdNoPerecedero;
import Negocio.Producto.TProdPerecedero;
import Negocio.Producto.TProducto;
import Negocio.Seccion.TSeccion;
import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VAltaProducto extends JFrame implements IGUI {

	private ArrayList<JTextField> textFields;
	JComboBox<String> selTipe, selNoPer;
	JTextField date;

	public VAltaProducto() {
		textFields = new ArrayList<JTextField>();
		init_GUI();
	}

	public void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main Panel.
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel buttonPanel = backButtonContainer();

		// PANEL SELECCION PERECEDERO/NO PERECEDERO.
		selTipe = new JComboBox<String>();
		selTipe.setToolTipText("Tipo de producto");
		selTipe.addItem("Perecedero");
		selTipe.addItem("No Perecedero");

		JLabel noPerInf = new JLabel("Tipo:   ");
		noPerInf.setVisible(false);
		selNoPer = new JComboBox<String>();
		selNoPer.setToolTipText("Tipo de envase");
		selNoPer.addItem("Enlatados");
		selNoPer.addItem("Plastificados");
		selNoPer.addItem("Encartonados");
		selNoPer.addItem("Embotellados");
		selNoPer.setVisible(false);

		JLabel dateInf = new JLabel("Fecha de caducidad:   ");
		date = new JTextField(10);
		date.setText("YYYY/MM/DD");
		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.X_AXIS));
		selectionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		selectionPanel.add(selTipe);
		selectionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		selTipe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selTipe.getSelectedItem().equals("No Perecedero")) {
					dateInf.setVisible(false);
					date.setVisible(false);
					noPerInf.setVisible(true);
					selNoPer.setVisible(true);
				} else if (selTipe.getSelectedItem().equals("Perecedero")) {
					dateInf.setVisible(true);
					date.setVisible(true);
					noPerInf.setVisible(false);
					selNoPer.setVisible(false);
				}

			}
		});
		selectionPanel.add(dateInf);
		selectionPanel.add(date);
		selectionPanel.add(noPerInf);
		selectionPanel.add(selNoPer);
		selectionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		selectionPanel.setMaximumSize(new Dimension(440, 30));

		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Stock");
		names.add("Precio");
		names.add("Marca(ID)");
		names.add("Seccion(ID)");

		FormComponent formComponent = new FormComponent(names, "Alta Producto", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TProducto pr;

				try {
					int stock = Integer.parseInt(textFields.get(1).getText());
					float precio = Float.parseFloat(textFields.get(2).getText());

					if(stock < 0 || precio < 0) {
						throw new NumberFormatException();
					}
					if (selTipe.getSelectedItem().equals("Perecedero")) {
						String[] dateNums = date.getText().split("/");
						LocalDate date_cadud = null;

						if (dateNums.length == 3) {
							int year = Integer.parseInt(dateNums[0]);
							int month = Integer.parseInt(dateNums[1]);
							int day = Integer.parseInt(dateNums[2]);
							date_cadud = LocalDate.of(year, month, day);
						}

						pr = new TProdPerecedero(textFields.get(0).getText(), stock, precio,
								Integer.parseInt(textFields.get(3).getText()),
								Integer.parseInt(textFields.get(4).getText()), true, date_cadud);
					} else {
						pr = new TProdNoPerecedero(textFields.get(0).getText(), stock, precio,
								Integer.parseInt(textFields.get(3).getText()),
								Integer.parseInt(textFields.get(4).getText()), true,
								selNoPer.getSelectedItem().toString());
					}

					Controller.getInstance().action(new Context(Eventos.GUARDAR_PRODUCTO, pr));
					dispose();

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"formato invalido: debe introducir valores numericos en:\n marca, seccion ");
				}

			}

		});

		this.add(mainPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(selectionPanel);
		mainPanel.add(formComponent);
		this.pack();
		this.setVisible(true);

	}

	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Productos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
				dispose();
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}

	@Override
	public void actualizar(Context res) {

		if (res.getEvento() == Eventos.RES_ALTA_PRODUCTO_OK) {
			JOptionPane.showMessageDialog(null, "Producto creado correctamente con id: " + res.getDatos());
		} else if (res.getEvento() == Eventos.RES_ALTA_PRODUCTO_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido añadir el producto");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
		dispose();
	}
}