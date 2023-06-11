package Presentación.Producto.VistasCasos_de_UsoPr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import Negocio.Producto.TProdNoPerecedero;
import Negocio.Producto.TProdPerecedero;
import Negocio.Producto.TProducto;
import Presentación.FormComponent;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class VModificarPr extends JFrame implements IGUI {
	private ArrayList<JTextField> textFields;
	private TProducto producto;
	JComboBox<String> selNoPer;
	JTextField tFecha;
	private String[] nList = { "Enlatados", "Encartonados", "Plastificados", "Embotellados" };

	public VModificarPr(TProducto producto) {
		super("Editar Proucto");
		setTitle("Modificar Datos de Producto");
		textFields = new ArrayList<JTextField>();
		this.producto = producto;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	private void init_GUI() {
		this.getContentPane().removeAll();

		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel backButtonContainer = backButtonContainer();

		// SELECTION PANEL
		JPanel selectionPanel = new JPanel();
		selectionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.X_AXIS));
		selectionPanel.setMaximumSize(new Dimension(440, 30));
		JLabel pTipo = new JLabel("Tipo:  ");
		selectionPanel.add(pTipo);
		selectionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		if (producto instanceof TProdNoPerecedero) {
			pTipo.setText(pTipo.getText() + "No perecedero");
			selNoPer = new JComboBox<String>();
			for (String s : nList) {
				selNoPer.addItem(s);
			}
			selNoPer.setSelectedItem(((TProdNoPerecedero) producto).getTipo());
			selectionPanel.add(selNoPer);
			selectionPanel.add(Box.createRigidArea(new Dimension(20, 0)));

		} else {
			pTipo.setText(pTipo.getText() + "Perecedero");
			tFecha = new JTextField(10);
			tFecha.setText(((TProdPerecedero) producto).getFechaDeCaducidad().toString().replaceAll("-", "/"));
			JLabel lfecha = new JLabel("Fecha de caducidad:  ");
			selectionPanel.add(lfecha);
			selectionPanel.add(tFecha);
			selectionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		}

		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Precio");
		names.add("Stock");
		names.add("Marca");
		names.add("Seccion");

		ArrayList<String> values = new ArrayList<String>();
		values.add(producto.getNombre());
		values.add(Double.toString(producto.getPrecio()));
		values.add(Integer.toString(producto.getStock()));
		values.add(Integer.toString(producto.getIDMarca()));
		values.add(Integer.toString(producto.getIDSeccion()));

		FormComponent formComponent = new FormComponent(names, "Editar Producto", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					float precio = Float.parseFloat(textFields.get(1).getText());
					int stock = Integer.parseInt(textFields.get(2).getText());

					if (stock < 0 || precio < 0) {
						throw new NumberFormatException();
					}
					if (textFields.get(0).getText().length() == 0) {
						throw new IllegalArgumentException();
					}
					producto.setNombre(textFields.get(0).getText());
					producto.setPrecio(precio);
					producto.setStock(stock);
					producto.setIDMarca(Integer.parseInt(textFields.get(3).getText()));
					producto.setIDSeccion(Integer.parseInt(textFields.get(4).getText()));
					if (producto instanceof TProdNoPerecedero) {
						((TProdNoPerecedero) producto).setTipo(selNoPer.getSelectedItem().toString());
					} else {
						LocalDate l = null;
						String[] dateNums = tFecha.getText().split("/");
						if (dateNums.length == 3) {
							int year = Integer.parseInt(dateNums[0]);
							int month = Integer.parseInt(dateNums[1]);
							int day = Integer.parseInt(dateNums[2]);
							l = LocalDate.of(year, month, day);
						}
						((TProdPerecedero) producto).setFechaDeCaducidad(l);
					}
					Controller.getInstance().action(new Context(Eventos.UPDATE_PRODUCTO, producto));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"formato invalido: debe introducir valores numericos en:\n marca, seccion, fecha... el stock y el precio deben ser no negativos");
				}
				catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null,"formato invalido: recuerde introducir un nombre");
				}

			}
		});

		formComponent.setValues(values);

		// CONSTUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
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
		if (res.getEvento() == Eventos.RES_MODIFICAR_PRODUCTO_OK) {
			JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_PRODUCTO_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido modificar producto, revise los datos");
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_PRODUCTO_KO && (int) res.getDatos() == -2) {
			JOptionPane.showMessageDialog(null, "No se ha podido modificar producto, revise los datos");
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_PRODUCTO_KO && (int) res.getEvento() == -3) {
			JOptionPane.showMessageDialog(null, "No se ha podido modificar producto, revise la fecha(yyyy/mm/dd)");
		} else if (res.getEvento() == Eventos.RES_MODIFICAR_PRODUCTO_KO && (int) res.getEvento() == -1) {
			JOptionPane.showMessageDialog(null,
					"No se ha podido modificar producto, revise los datos, puede que no existan los ids proporcionados");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
		dispose();

	}
}