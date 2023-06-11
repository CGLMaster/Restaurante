package Presentación;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Negocio.Trabajador.TTrabajador;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;


public class VPrincipalSuper extends JFrame implements IGUI {

	private JMenuBar topMenu;
	private static TTrabajador logged;

	public VPrincipalSuper() {
		super();
		initGUI();
		this.setLocationRelativeTo(null);
	}
	
	public VPrincipalSuper(TTrabajador loggedTrabajador) {
		super();
		logged =  loggedTrabajador;
		initGUI();
		this.setLocationRelativeTo(null);
	}

	public void initGUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400,100);
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		JPanel mainpanel = new JPanel(new BorderLayout());
		JPanel topPanel = creaTopPanel();
		JPanel botPanel = creaBotPanel();
		JPanel midPanel = creaMidPanel();
		JPanel westPanel = crearPubli("video1_def.gif");
		westPanel.setBorder(BorderFactory.createTitledBorder("Publicidad"));
		JPanel eastPanel = crearPubli("video2_def.gif");
		eastPanel.setBorder(BorderFactory.createTitledBorder("Publicidad"));

		topMenu = crearTopMenu();
		if (logged == null) {
			topMenu.setVisible(false);
		}
		this.setJMenuBar(topMenu);

		this.setPreferredSize(new Dimension(1150, 750));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainpanel);
		mainpanel.add(topPanel, BorderLayout.NORTH);
		mainpanel.add(botPanel, BorderLayout.SOUTH);
		mainpanel.add(midPanel, BorderLayout.CENTER);
		mainpanel.add(westPanel,BorderLayout.WEST);
		mainpanel.add(eastPanel,BorderLayout.EAST);

		this.pack();
		this.setResizable(true);
		this.setVisible(true);
	}

	private JMenuBar crearTopMenu() {
		JMenuBar menu = new JMenuBar();

		JMenu personasPersonal = new JMenu("Socios y Personal");
		personasPersonal.setMnemonic(KeyEvent.VK_S);

		JMenu productosSeccion = new JMenu("Productos y Seccion");
		productosSeccion.setMnemonic(KeyEvent.VK_P);

		JMenu marcasProveedores = new JMenu("Marcas y Proveedores");
		marcasProveedores.setMnemonic(KeyEvent.VK_M);
		
		JMenu comprasMenu = new JMenu("Compras");

		JMenuItem Clientes = new JMenuItem("Clientes");
		Clientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE));
				dispose(); 

			}

		});
		Clientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

		JMenuItem Trabajadores = new JMenuItem("Trabajadores");
		Trabajadores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TRABAJADOR));
				dispose();
			}

		});
		Trabajadores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));

		personasPersonal.add(Clientes);
		personasPersonal.add(Trabajadores);

		JMenuItem Productos = new JMenuItem("Productos");
		Productos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
				dispose();
			}

		});
		Productos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

		JMenuItem Seccion = new JMenuItem("Seccion");
		Seccion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_SECCION));
				dispose();

			}

		});
		Seccion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		productosSeccion.add(Productos);
		productosSeccion.add(Seccion);

		JMenuItem Marcas = new JMenuItem("Marcas");
		Marcas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_MARCA));
				dispose();
			}

		});
		Marcas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));

		JMenuItem Proveedores = new JMenuItem("Proveedores");
		Proveedores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PROVEEDOR));
				dispose();
			}

		});
		Proveedores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		
		JMenuItem compras = new JMenuItem("Compras");
		compras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_COMPRA));
				dispose();
			}

		});
		compras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		
		comprasMenu.add(compras);

		marcasProveedores.add(Marcas);
		marcasProveedores.add(Proveedores);

		menu.add(personasPersonal);
		menu.add(productosSeccion);
		menu.add(marcasProveedores);
		menu.add(comprasMenu);

		return menu;
	}

	public JPanel creaTopPanel() {
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(39, 174, 95));
		topPanel.setPreferredSize(new Dimension(200, 60));
		topPanel.setMinimumSize(new Dimension(200, 60));
		topPanel.setMaximumSize(new Dimension(200, 60));

		JButton logIn = new JButton();
		logIn.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					int id = Integer.parseInt(JOptionPane.showInputDialog("Introduza el id de trabajador para loggearse"));
					Controller.getInstance().action(new Context(Eventos.IDENTIFICAR_TRABAJADOR, id));
					dispose();
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "id no valido, introduzca valores numéricos");
				}
				
			}

		});
		logIn.setBorder(BorderFactory.createBevelBorder(0));
		logIn.setBackground(new Color(39, 174, 95));
		logIn.setPreferredSize(new Dimension(50, 50));
		logIn.setMinimumSize(new Dimension(50, 50));
		logIn.setMaximumSize(new Dimension(50, 50));
		logIn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		logIn.setToolTipText("Acceder como trabajador");
		JLabel logInfo = new JLabel();
		logInfo.setVisible(false);
		logInfo.setFont(new Font("sans-serif", 1, 17));
		logInfo.setForeground(Color.white);

		JButton logout = new JButton();
		logout.setVisible(false);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logIn.setVisible(true);
				logout.setVisible(false);
				topMenu.setVisible(false);
				logInfo.setVisible(false);
				logged = null;
			}

		});
		logout.setBorder(BorderFactory.createBevelBorder(0));
		logout.setBackground(new Color(236, 115, 115));
		logout.setPreferredSize(new Dimension(50, 50));
		logout.setMinimumSize(new Dimension(50, 50));
		logout.setMaximumSize(new Dimension(50, 50));
		logout.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logout_icon.png")));
		logout.setToolTipText("Acceder como trabajador");
		
		JButton home = new JButton();
		home.setBackground(new Color(39, 174, 95));
		home.setForeground(Color.white);
		home.setIcon(new ImageIcon(getClass().getClassLoader().getResource("casa_icon.png")));
		home.setToolTipText("Volver al menu principal");
		home.setBorder(BorderFactory.createBevelBorder(0));
		home.setPreferredSize(new Dimension(50, 50));
		home.setMinimumSize(new Dimension(50, 50));
		home.setMaximumSize(new Dimension(50, 50));
		home.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL,0)); 		
				dispose();
			}
			
		});

		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		if (logged!=null) {
			logIn.setVisible(false);
			logout.setVisible(true);
			logInfo.setText("Usted está identificado como " + logged.getNombre());
			logInfo.setVisible(true);
			
		}
		topPanel.add(home);
		topPanel.add(logIn);
		topPanel.add(logout);
		topPanel.add(Box.createRigidArea(new Dimension(10,0)));
		topPanel.add(logInfo);

		return topPanel;
	}

	public JPanel creaBotPanel() {
		JPanel botPanel = new JPanel(new BorderLayout());
		botPanel.setPreferredSize(new Dimension(10, 60));
		botPanel.setMinimumSize(new Dimension(10, 60));
		botPanel.setBackground(new Color(39, 174, 95));

		return botPanel;
	}

	public JPanel creaMidPanel() {
		JPanel midpanel = new JPanel();
		midpanel.setLayout(new BoxLayout(midpanel, BoxLayout.Y_AXIS));
		midpanel.setBackground(new Color(197, 233, 205));

		JLabel bienvenida = new JLabel("Bienvenido a Supermercado");
		bienvenida.setLayout(new BorderLayout());
		bienvenida.setForeground(new Color(57, 119, 70));
		bienvenida.setPreferredSize(new Dimension(200, 50));
		bienvenida.setFont(new Font("Comic Sans MS", 1, 30));
		bienvenida.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		JLabel icono = new JLabel();
		icono.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_mediano.png")));
		icono.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		
		JButton compra = new JButton();
		compra.setBackground(new Color(39, 174, 95));
		compra.setPreferredSize(new Dimension(200, 80));
		compra.setText("INICIAR COMPRA");
		compra.setFont(new Font("sans-serif", 1, 20));
		compra.setForeground(Color.white);
		compra.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Carro_icon.png")));
		compra.setBorder(BorderFactory.createBevelBorder(0));
		compra.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		compra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = -1;
				
				if(logged != null){
					id = logged.getIDTrabajador();
				}
				Controller.getInstance().action(new Context(Eventos.ABRIR_COMPRA, id));
				dispose();
			}

		});

		midpanel.add(Box.createRigidArea(new Dimension(0, 70)));
		midpanel.add(bienvenida);
		midpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		midpanel.add(icono);
		midpanel.add(Box.createRigidArea(new Dimension(0, 50)));
		midpanel.add(compra);

		midpanel.setVisible(true);
		return midpanel;
	}
	
	
	public JPanel crearPubli(String url){
		JPanel publiPanel = new JPanel();
		publiPanel.setLayout(new BoxLayout(publiPanel, BoxLayout.Y_AXIS));
		publiPanel.setBackground(new Color(197, 233, 205));
		JLabel publi = new JLabel();
		publi.setIcon(new ImageIcon(getClass().getClassLoader().getResource(url)));
		publi.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		
		
		publiPanel.add(publi);
		return publiPanel;
	}

	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_IDENTIFICAR_TRABAJADOR_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido identificar al trabajador.");
		}
		Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_SUPER));
		dispose();
	}

	
}
