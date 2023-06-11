package Presentación;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.Personal.TAdmin;
import Negocio.Personal.TPersonal;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;
import Presentación.Pedido.VMostrarAyudaRest;


public class VPrincipalRest extends JFrame implements IGUI{

	private static TPersonal logged;
	private boolean desplegado;
	public VPrincipalRest() {
		super();
		desplegado = true;
		initGUI();
		this.setLocationRelativeTo(null);
	}
	
	public VPrincipalRest(TPersonal loggedPersonal) {
		super();
		logged =  loggedPersonal;
		desplegado = true;
		initGUI();
		this.setLocationRelativeTo(null);
	}

	public void initGUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400,100);
		
		JPanelConFondo mainpanel = new JPanelConFondo();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_pizarra.png")).getImage());
		JPanel topPanel = creaTopPanel();
		JPanel midPanel = creaMidPanel();

		this.setPreferredSize(new Dimension(1150, 750));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainpanel);
		mainpanel.add(topPanel, BorderLayout.WEST);
		mainpanel.add(midPanel, BorderLayout.CENTER);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);

		this.pack();
		this.setResizable(true);
		this.setVisible(true);
	}


	public JPanel creaTopPanel() {
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(128, 0, 0));
		topPanel.setPreferredSize(new Dimension(250, 750));
		topPanel.setMinimumSize(new Dimension(250, 750));
		topPanel.setMaximumSize(new Dimension(250, 750));
		
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		JPanel botton = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		top.setBackground(new Color(128, 0, 0));
		top.setPreferredSize(new Dimension(250, 50));
		top.setMinimumSize(new Dimension(250, 50));
		top.setMaximumSize(new Dimension(250, 50));
		
		JButton home = new JButton();
		home.setBackground(new Color(128, 0, 0));
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
				logged = null;
				dispose();
			}
			
		});
		
		JButton despleg = new JButton();
		despleg.setBackground(new Color(128, 0, 0));
		despleg.setForeground(Color.white);
		despleg.setIcon(new ImageIcon(getClass().getClassLoader().getResource("desplegable_icon.png")));
		despleg.setToolTipText("Desplegar el menu");
		despleg.setBorder(BorderFactory.createBevelBorder(0));
		despleg.setPreferredSize(new Dimension(50, 50));
		despleg.setMinimumSize(new Dimension(50, 50));
		despleg.setMaximumSize(new Dimension(50, 50));
		top.add(despleg);
		top.add(home);
		despleg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(desplegado) {
					desplegado = !desplegado;
					topPanel.setOpaque(false);
					topPanel.setPreferredSize(new Dimension(54, 750));
					topPanel.setMinimumSize(new Dimension(54, 750));
					topPanel.setMaximumSize(new Dimension(54, 750));
					home.setVisible(false);
					top.setOpaque(false);
					middle.setVisible(false);
					botton.setVisible(false);
				}
				else {
					desplegado = !desplegado;
					topPanel.setOpaque(true);
					topPanel.setBackground(new Color(128, 0, 0));
					topPanel.setPreferredSize(new Dimension(250, 750));
					topPanel.setMinimumSize(new Dimension(250, 750));
					topPanel.setMaximumSize(new Dimension(250, 750));
					top.setBackground(new Color(128, 0, 0));
					home.setVisible(true);
					middle.setVisible(true);
					botton.setVisible(true);
				}
			}
			
		});
		
		middle.setLayout(new GridLayout(6,0,10,5));
		middle.setBackground(new Color(128, 0, 0));
		middle.setPreferredSize(new Dimension(250, 620));
		middle.setMinimumSize(new Dimension(250, 620));
		middle.setMaximumSize(new Dimension(250, 620));
		
		//CLIENTES
		JPanel clientes = new JPanel();
		clientes.setLayout(new GridLayout(1,0));
		clientes.setBackground(new Color(128, 0, 0));
		clientes.setPreferredSize(new Dimension(250, 100));
		clientes.setMinimumSize(new Dimension(250, 100));
		clientes.setMaximumSize(new Dimension(250, 100));
		JButton clienteB = new JButton("Clientes");
		clienteB.setBackground(new Color(128, 0, 0));
		clienteB.setForeground(Color.white);
		clienteB.setToolTipText("Informacion de Clientes");
		clienteB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_CLIENTE_REST)); 		
				dispose();
			}
			
		});
		
		
		clienteB.setFont(new Font("Algerian", 1, 30));
		clienteB.setBorder(null);
		clientes.add(clienteB);
		
		//EMPLEADOS
		JPanel empleados = new JPanel();
		empleados.setLayout(new GridLayout(1,0));
		empleados.setBackground(new Color(128, 0, 0));
		empleados.setPreferredSize(new Dimension(250, 100));
		empleados.setMinimumSize(new Dimension(250, 100));
		empleados.setMaximumSize(new Dimension(250, 100));
		JButton empleadoB = new JButton("Empleados");
		empleadoB.setBackground(new Color(128, 0, 0));
		empleadoB.setForeground(Color.white);
		empleadoB.setToolTipText("Informacion de Empleados");
		empleadoB.setFont(new Font("Algerian", 1, 30));
		empleadoB.setBorder(null);
		empleados.add(empleadoB);
		empleadoB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PERSONAL)); 		
				dispose();
			}
			
		});
		
		//INGREDIENTES
		JPanel ingredientes = new JPanel();
		ingredientes.setLayout(new GridLayout(1,0));
		ingredientes.setBackground(new Color(128, 0, 0));
		ingredientes.setPreferredSize(new Dimension(250, 100));
		ingredientes.setMinimumSize(new Dimension(250, 100));
		ingredientes.setMaximumSize(new Dimension(250, 100));
		JButton ingredienteB = new JButton("Ingredientes");
		ingredienteB.setBackground(new Color(128, 0, 0));
		ingredienteB.setForeground(Color.white);
		ingredienteB.setToolTipText("Informacion de Ingredientes");
		ingredienteB.setFont(new Font("Algerian", 1, 30));
		ingredienteB.setBorder(null);
		ingredientes.add(ingredienteB);
		ingredienteB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_INGREDIENTE,1)); 		
				dispose();
			}
			
		});
		
		//PLATOS
		JPanel platos = new JPanel();
		platos.setLayout(new GridLayout(1,0));
		platos.setBackground(new Color(128, 0, 0));
		platos.setPreferredSize(new Dimension(250, 100));
		platos.setMinimumSize(new Dimension(250, 100));
		platos.setMaximumSize(new Dimension(250, 100));
		JButton platoB = new JButton("Platos");
		platoB.setBackground(new Color(128, 0, 0));
		platoB.setForeground(Color.white);
		platoB.setToolTipText("Informacion de Platos");
		platoB.setFont(new Font("Algerian", 1, 30));
		platoB.setBorder(null);
		platos.add(platoB);
		
		platoB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PLATO, 1)); 		
				dispose();
			}
			
		});
		
		
		//PEDIDOS
		JPanel pedidos = new JPanel();
		pedidos.setLayout(new GridLayout(1,0));
		pedidos.setBackground(new Color(128, 0, 0));
		pedidos.setPreferredSize(new Dimension(250, 100));
		pedidos.setMinimumSize(new Dimension(250, 100));
		pedidos.setMaximumSize(new Dimension(250, 100));
		JButton pedidoB = new JButton("Pedidos");
		pedidoB.setBackground(new Color(128, 0, 0));
		pedidoB.setForeground(Color.white);
		pedidoB.setToolTipText("Informacion de Pedidos");
		pedidoB.setFont(new Font("Algerian", 1, 30));
		pedidoB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PEDIDO)); 		
				dispose();
			}
			
		});
		pedidoB.setBorder(null);
		pedidos.add(pedidoB);
		
		//TURNOS
		JPanel turnos = new JPanel();
		turnos.setLayout(new GridLayout(1,0));
		turnos.setBackground(new Color(128, 0, 0));
		turnos.setPreferredSize(new Dimension(250, 100));
		turnos.setMinimumSize(new Dimension(250, 100));
		turnos.setMaximumSize(new Dimension(250, 100));
		JButton turnoB = new JButton("Turnos");
		turnoB.setBackground(new Color(128, 0, 0));
		turnoB.setForeground(Color.white);
		turnoB.setToolTipText("Informacion de Turnos");
		turnoB.setFont(new Font("Algerian", 1, 30));
		turnoB.setBorder(null);
		turnoB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_TURNO));
				dispose();
				
			}
			
		});
		turnos.add(turnoB);
		
		middle.add(clientes);
		middle.add(empleados);
		middle.add(ingredientes);
		middle.add(platos);
		middle.add(pedidos);
		middle.add(turnos);

		
		botton.setLayout(new BoxLayout(botton, BoxLayout.X_AXIS));
		botton.setBackground(new Color(128, 0, 0));
		botton.setPreferredSize(new Dimension(250, 50));
		botton.setMinimumSize(new Dimension(250, 50));
		botton.setMaximumSize(new Dimension(250, 50));
		
		
		JButton logIn = new JButton("Acceder como empleado");
		JButton logout = new JButton("Cerrar Sesion");
		
		logIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					int id = Integer.parseInt(JOptionPane.showInputDialog("Introduza el id de empleado para loggearse"));
					if(id==-69){	//Clave de Administrador del Sistema
						logged=new TAdmin();
		                JOptionPane.showMessageDialog(null, "", "Admin Mamado", JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getClassLoader().getResource("Panita mamado.jpg")));
						Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_REST, logged));
						dispose();
					}
					else{
						Controller.getInstance().action(new Context(Eventos.IDENTIFICAR_PERSONAL, id));
						dispose();
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "id no valido, introduzca valores numéricos");
				}
			}

		});
		logIn.setBorder(BorderFactory.createBevelBorder(0));
		logIn.setBackground(new Color(128, 0, 0));
		logIn.setForeground(Color.white);
		logIn.setFont(new Font("Bell MT", 1, 18));
		logIn.setPreferredSize(new Dimension(250, 50));
		logIn.setMinimumSize(new Dimension(250, 50));
		logIn.setMaximumSize(new Dimension(250, 50));
		logIn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		logIn.setToolTipText("Acceder como empleado");

		
		logout.setVisible(false);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logged = null;
				Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_REST, 0));
				dispose();
			}

		});
		logout.setBorder(BorderFactory.createBevelBorder(0));
		logout.setBackground(new Color(252, 147, 3));
		logout.setForeground(Color.white);
		logout.setFont(new Font("Bell MT", 1, 18));
		logout.setPreferredSize(new Dimension(250, 50));
		logout.setMinimumSize(new Dimension(250, 50));
		logout.setMaximumSize(new Dimension(250, 50));
		logout.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logout_icon.png")));
		logout.setToolTipText("Acceder como empleado");
		
		if (logged!= null) {
			logIn.setVisible(false);
			logout.setVisible(true);
			clientes.setVisible(true);
			empleados.setVisible(true);
			ingredientes.setVisible(true);
			platos.setVisible(true);
			pedidos.setVisible(true);
			turnos.setVisible(true);
			
		}
		else {
			logIn.setVisible(true);
			logout.setVisible(false);
			clientes.setVisible(false);
			empleados.setVisible(false);
			ingredientes.setVisible(false);
			platos.setVisible(false);
			pedidos.setVisible(false);
			turnos.setVisible(false);
		}
		botton.add(logIn);
		botton.add(logout);
		botton.add(Box.createRigidArea(new Dimension(10,0)));
		
		topPanel.setLayout(new BorderLayout());
		topPanel.add(top, BorderLayout.NORTH);
		topPanel.add(middle, BorderLayout.CENTER);
		topPanel.add(botton, BorderLayout.SOUTH);

		return topPanel;
	}


	public JPanel creaMidPanel() {
		JPanel midpanel = new JPanel();
		midpanel.setLayout(new BorderLayout());
		midpanel.setOpaque(false);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setOpaque(false);

		JLabel bienvenida = new JLabel("Bienvenido a Restaurante");
		bienvenida.setLayout(new BorderLayout());
		bienvenida.setForeground(new Color(128, 0, 0));
		bienvenida.setPreferredSize(new Dimension(200, 50));
		bienvenida.setFont(new Font("Comic Sans MS", 1, 50));
		bienvenida.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		JLabel icono = new JLabel();
		icono.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_restaurante_mediano.PNG")));
		icono.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		
		JButton carta = new JButton();
		carta.setBackground(new Color(128, 0, 0));
		carta.setPreferredSize(new Dimension(1000, 80));
		carta.setMinimumSize(new Dimension(250, 70));
		carta.setMaximumSize(new Dimension(250, 70));
		carta.setText("CARTA");
		carta.setToolTipText("Acceso a los platos de la carta");
		carta.setFont(new Font("Segoe Print", 1, 30));
		carta.setForeground(Color.white);
		carta.setIcon(new ImageIcon(getClass().getClassLoader().getResource("carta_icon.png")));
		carta.setBorder(BorderFactory.createBevelBorder(0));
		carta.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		carta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = -1;
				
				if(logged != null){
					id = logged.getIDPersonal();
				}
				Controller.getInstance().action(new Context(Eventos.CREAR_CARRITO, id));
				dispose();
			}

		});
		
		
		topPanel.add(Box.createRigidArea(new Dimension(0, 150)));
		topPanel.add(bienvenida);
		topPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		topPanel.add(icono);
		topPanel.add(Box.createRigidArea(new Dimension(-10, 50)));
		topPanel.add(carta);
		
		JPanel downPanel = new JPanel();
		downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.X_AXIS));
		downPanel.setOpaque(false);
		downPanel.setPreferredSize(new Dimension(1150, 30));
		downPanel.setMinimumSize(new Dimension(1150, 30));
		downPanel.setMaximumSize(new Dimension(1150, 30));
		
		JLabel logInfo = new JLabel();
		logInfo.setFont(new Font("sans-serif", 1, 24));
		logInfo.setForeground(new Color(128, 0, 0));
		
		downPanel.add(Box.createHorizontalGlue());
		downPanel.add(logInfo);
		downPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		if(logged != null){
			logInfo.setVisible(true);
			logInfo.setText("Empleado: " + logged.getNombre());
		}else logInfo.setVisible(false);
		
		midpanel.add(topPanel, BorderLayout.NORTH);
		midpanel.add(downPanel, BorderLayout.SOUTH);

		return midpanel;
	}

	@Override
	public void actualizar(Context res) {
		if(res.getEvento() == Eventos.RES_IDENTIFICAR_PERSONAL_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido identificar al empleado.");
		}
		Controller.getInstance().action(new Context(Eventos.CREAR_VPRINCIPAL_REST));
		dispose();
	}
	

	
}
