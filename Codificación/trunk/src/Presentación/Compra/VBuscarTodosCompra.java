package Presentaci�n.Compra;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
import Presentaci�n.Command.Context;
import Presentaci�n.Controller.Controller;
import Presentaci�n.Controller.Eventos;
import Presentaci�n.FactoriaVistas.IGUI;
import Negocio.Compra.TCompra;

@SuppressWarnings("serial")
public class VBuscarTodosCompra extends JFrame implements IGUI {

	private List<TCompra> compras;

	public VBuscarTodosCompra(List<TCompra> compra) {
		this.compras = compra;
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
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);

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
		headerContainer.add(Box.createRigidArea(new Dimension(350, 0)));

		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_peque�o.png")));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(new Color(39, 174, 95));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(300, 0)));

		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_SUPER, 1));
				dispose();

			}
		});

		// CONSTRUIR VISTA
		this.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);
		contentContainer.add(headerContainer);

		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en una compra para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);

		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		// Search Panel
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		searchPanel.setMaximumSize(new Dimension(2000, 20));
		JLabel searchLabel = new JLabel("Buscar por ID:  ");
		JTextField tSearch = new JTextField(10);
		tSearch.setMaximumSize(new Dimension(70, 20));
		JButton okSearch = new JButton("Buscar");
		okSearch.setBackground(new Color(39, 174, 95));
		okSearch.setForeground(Color.white);
		okSearch.setBorderPainted(false);
		
		searchPanel.add(Box.createRigidArea(new Dimension(85, 0)));
		searchPanel.add(searchLabel);
		searchPanel.add(tSearch);
		searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		searchPanel.add(okSearch);

		okSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id;
				try {
					id = Integer.parseInt(tSearch.getText());
					Controller.getInstance().action(new Context(Eventos.BUSCAR_COMPRA_ID, id));
					dispose();

				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Introduzca un valor numerico para el ID");
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
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_COMPRA));
				dispose();
			}
			
		});
		searchPanel.add(Box.createRigidArea(new Dimension(75, 0)));
		searchPanel.add(new JLabel("Por favor respete los guiones / en la b�squeda por fecha"));
		searchPanel.add(Box.createRigidArea(new Dimension(225, 0)));
		searchPanel.add(reseteo);
		
		
		
		JPanel buscarRangoPanel = new JPanel();
		buscarRangoPanel.setLayout(new BoxLayout(buscarRangoPanel, BoxLayout.X_AXIS));
		buscarRangoPanel.setMaximumSize(new Dimension(2500, 20));
		
		JLabel rangoLabel = new JLabel("Buscar por precio y fecha: ");
		JLabel desdePrecioLabel = new JLabel("Desde (Precio) : ");
		JLabel hastaPrecioLabel = new JLabel("Hasta (Precio) : ");
		JLabel desdeFechaLabel = new JLabel("Desde (Fecha) : ");
		JLabel hastaFechaLabel = new JLabel("Hasta (Fecha) : ");
		JSpinner rangoDesdePrecio = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,5));
		JSpinner rangoHastaPrecio = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,5));
		JTextField rangoDesdeFecha = new JTextField(10);
		JTextField rangoHastaFecha = new JTextField(10);
		rangoDesdeFecha.setText("yyyy/mm/dd");
		rangoHastaFecha.setText("yyyy/mm/dd");
		
		
		rangoDesdeFecha.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {

            	rangoDesdeFecha.setText(null);
            	rangoDesdeFecha.setForeground(Color.BLACK);
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}

        });
		
		rangoHastaFecha.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {

            	rangoHastaFecha.setText(null);
            	rangoHastaFecha.setForeground(Color.BLACK);
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}

        });
		
		rangoDesdeFecha.setMaximumSize(new Dimension(60,20));
		rangoDesdeFecha.setPreferredSize(new Dimension(60,20));
		rangoHastaFecha.setMaximumSize(new Dimension(60,20));
		rangoHastaFecha.setPreferredSize(new Dimension(60,20));
		JButton buscarRango = new JButton("Buscar");
		buscarRango.setBackground(new Color(39, 174, 95));
		buscarRango.setForeground(Color.white);
		buscarRango.setBorderPainted(false);
		rangoDesdePrecio.setMaximumSize(new Dimension(60,20));
		rangoDesdePrecio.setPreferredSize(new Dimension(60,20));
		rangoHastaPrecio.setMaximumSize(new Dimension(60,20));
		rangoHastaPrecio.setPreferredSize(new Dimension(60,20));
		rangoDesdeFecha.setMaximumSize(new Dimension(70,20));
		rangoDesdeFecha.setPreferredSize(new Dimension(70,20));
		rangoHastaFecha.setMaximumSize(new Dimension(70,20));
		rangoHastaFecha.setPreferredSize(new Dimension(70,20));
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(85, 0)));
		buscarRangoPanel.add(rangoLabel);
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buscarRangoPanel.add(desdePrecioLabel);
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buscarRangoPanel.add(rangoDesdePrecio);
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buscarRangoPanel.add(hastaPrecioLabel);
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buscarRangoPanel.add(rangoHastaPrecio);
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buscarRangoPanel.add(desdeFechaLabel);
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buscarRangoPanel.add(rangoDesdeFecha);
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buscarRangoPanel.add(hastaFechaLabel);
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buscarRangoPanel.add(rangoHastaFecha);
		buscarRangoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		buscarRangoPanel.add(buscarRango);
		buscarRango.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try{
					int desde = (Integer) rangoDesdePrecio.getValue();
					int hasta = (Integer) rangoHastaPrecio.getValue();
					String[] dateNumsDesde = rangoDesdeFecha.getText().split("/");
					LocalDate dateDesde = null;

					if (dateNumsDesde.length == 3) {
						int year = Integer.parseInt(dateNumsDesde[0]);
						int month = Integer.parseInt(dateNumsDesde[1]);
						int day = Integer.parseInt(dateNumsDesde[2]);
						dateDesde = LocalDate.of(year, month, day);
					}
					String[] dateNumsHasta = rangoHastaFecha.getText().split("/");
					LocalDate dateHasta = null;

					if (dateNumsHasta.length == 3) {
						
						int yearH = Integer.parseInt(dateNumsHasta[0]);
						int monthH = Integer.parseInt(dateNumsHasta[1]);
						int dayH = Integer.parseInt(dateNumsHasta[2]);
						dateHasta = LocalDate.of(yearH, monthH, dayH);
					}
					if(dateDesde.isAfter(dateHasta)) throw new Exception();
					
					HashMap<String, Object> args = new HashMap<String, Object>();
					args.put("desdePrecio", desde);
					args.put("hastaPrecio", hasta);
					args.put("desdeFecha", dateDesde);
					args.put("hastaFecha", dateHasta);
					Controller.getInstance().action(new Context(Eventos.BUSCAR_COMPRA_POR_RANGO_PRECIO_FECHA,args));
					dispose();
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Valores no validos");
					rangoDesdeFecha.setText("yyyy/mm/dd");
					rangoHastaFecha.setText("yyyy/mm/dd");

				}
				
			}
		
		});
		
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(searchPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(buscarRangoPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

		for (TCompra compra : compras) {
			contentContainer.add(seccionPanel(compra));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		this.pack();
		this.setVisible(true);
	}

	private JPanel seccionPanel(TCompra compra) {

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 75));

		// BUTTON CONTAINER
		JPanel buttonContainer = new JPanel(new FlowLayout());
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());

		// DELETE BUTTON
		JButton deleteButton = new JButton("Devolucion");
		deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash_icon.jpg")));
		deleteButton.setToolTipText("Devolucion de Compra");
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null,
						"�Esta seguro que quiere realizar una devolucion de la compra con id: " + compra.getIDCompra() + " ?");
				if (res == 0) {
					Controller.getInstance().action(new Context(Eventos.CREAR_V_DEVOLUCION, compra.getIDCompra()));
					dispose();
				}
			}

		});

		// LABEL
		JLabel label = new JLabel("ID: " + compra.getIDCompra() + "  " + compra.getFecha());
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_COMPRA_ID, compra.getIDCompra()));
				dispose();
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

		label.setToolTipText("Informaci�n de la Compra");

		// CONSTRUIR PANEL
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		buttonContainer.add(deleteButton);

		return panel;
	}

	@Override
	public void actualizar(Context res) {

	}
}