package Presentación.Plato;

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
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.Ingrediente.TIngrediente;
import Negocio.Plato.TBebida;
import Negocio.Plato.TComida;
import Negocio.Plato.TPlato;
import Negocio.Plato.TPlatoConIngredientes;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VBuscarPlatoConIngredientes extends JFrame implements IGUI {
	
	private TPlato plato;
	private List<TIngrediente> ingredientes;
	private TPlatoConIngredientes platoConIngredientes;
	
	public VBuscarPlatoConIngredientes(TPlatoConIngredientes platoConIngredientes) {
		this.plato = platoConIngredientes.getPlato();
		this.ingredientes = platoConIngredientes.getIngredientes();
		this.platoConIngredientes = platoConIngredientes;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		// BACK BUTTON
		JPanel backButtonContainer = backButtonContainer();
		
		// TITULO
		JLabel titleLabel = new JLabel("Mostrar Ingredientes");
		titleLabel.setFont(new Font("sans-serif", 1, 20));
		titleLabel.setForeground(new Color(128, 0, 0));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);

		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);

		// INFO
		JLabel idLabel = new JLabel("ID: " + plato.getId());
		JLabel tipoLabel = new JLabel("Hola");
		JLabel nombreLabel = new JLabel("Nombre: " + plato.getNombre());
		JLabel descripcionLabel = new JLabel("Descripcion: " + plato.getDescripcion());
		JLabel precioLabel = new JLabel("Precio: " + plato.getPrecio());
		JLabel stockLabel = new JLabel("Stock: " + plato.getStock());
		JLabel activoLabel = new JLabel();
		if(plato.getActivo()){
			activoLabel.setText("Activo");
			activoLabel.setForeground(Color.green);
		}else{
			activoLabel.setText("Inactivo");
			activoLabel.setForeground(Color.red);
		}
		
		JLabel categoriaLabel = null;
		JLabel volumenLabel = null;
		
		if (plato instanceof TComida) {
			TComida comida = (TComida) plato;
			categoriaLabel = new JLabel("Categoría: " + comida.getCategoria());
			tipoLabel.setText("Tipo: Comida");
			
		} else if (plato instanceof TBebida) {
			TBebida bebida = (TBebida) plato;
			volumenLabel = new JLabel("Volumen: " + bebida.getVolumen());
			tipoLabel.setText("Tipo: Bebida");
		}
		
		
		// AÑADIR INGREDIENTE CONTAINER
		JPanel aniadirIngredienteContainer = new JPanel();
		aniadirIngredienteContainer.setLayout(new BoxLayout(aniadirIngredienteContainer, BoxLayout.X_AXIS));
		aniadirIngredienteContainer.setBackground(Color.white);
		aniadirIngredienteContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		aniadirIngredienteContainer.setMaximumSize(new Dimension(1000, 100));
		
		JLabel aniadirIngredienteLabel = new JLabel("Añadir ingrediente por ID:  ");
		JTextField idNuevoIngredienteInput = new JTextField(10);
		idNuevoIngredienteInput.setMaximumSize(new Dimension(70, 20));
		JButton botonAniadirIngrediente = new JButton("Añadir");
		botonAniadirIngrediente.setBackground(new Color(128, 0, 0));
		botonAniadirIngrediente.setBorder(BorderFactory.createBevelBorder(0));
		botonAniadirIngrediente.setForeground(Color.white);
		
		botonAniadirIngrediente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id;

				try {
					id = Integer.parseInt(idNuevoIngredienteInput.getText());
					
					HashMap<String, Object> params = new HashMap<String, Object>();
					

					params.put("id_ingrediente", Integer.parseInt(idNuevoIngredienteInput.getText()));
					params.put("id_plato", plato.getId());
					
					
					Controller.getInstance().action(new Context(Eventos.ANIADIR_INGREDIENTE_A_PLATO, params));
					dispose();


				} catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Compruebe el ID");
				}
			}

		});
		
		
		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		contentContainer.add(idLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(tipoLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(nombreLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(descripcionLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(precioLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(stockLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		if(categoriaLabel != null){
			contentContainer.add(categoriaLabel);
		}
		else if(volumenLabel != null){
			contentContainer.add(volumenLabel);
		}
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(activoLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		mainPanel.add(aniadirIngredienteContainer);
	
		aniadirIngredienteContainer.add(aniadirIngredienteLabel);
		aniadirIngredienteContainer.add(idNuevoIngredienteInput);
		aniadirIngredienteContainer.add(Box.createRigidArea(new Dimension(10, 0)));
		aniadirIngredienteContainer.add(botonAniadirIngrediente);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		for (TIngrediente ingrediente : ingredientes) {
			mainPanel.add(ingredientePanel(ingrediente));
			mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("iconoRest2.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel backButtonContainer() {
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("backRest_icon.png")));
		backButton.setToolTipText("Volver a platos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PLATO, 0));
				dispose();
			}
			
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}
	
	private JPanel ingredientePanel(TIngrediente ingrediente) {

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
				
				int res= JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere eliminar el ingrediente con id: " + ingrediente.getID() + " de este plato ?");
				if(res == 0){
					
					HashMap<String, Object> params = new HashMap<String, Object>();
					
					params.put("id_ingrediente", ingrediente.getID());
					params.put("id_plato", plato.getId());

					
					Controller.getInstance().action(new Context(Eventos.ELIMINAR_INGREDIENTE_DE_PLATO, params));
				}


			}

		});

		// LABEL
		JLabel label = new JLabel("ID: " + ingrediente.getID() + "  " + ingrediente.getNombre());
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				Controller.getInstance().action(new Context(Eventos.BUSCAR_INGREDIENTE, ingrediente.getID()));
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

		// CONSTRUIR PANEL
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		buttonContainer.add(deleteButton);

		return panel;
	}
	
	@Override
	public void actualizar(Context res) {
		int evento = res.getEvento();
		
		if (evento == Eventos.RES_ANIADIR_INGREDIENTE_A_PLATO_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido añadir el ingrediente");
		}
		else if (evento == Eventos.RES_ELIMINAR_INGREDIENTE_DE_PLATO_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido eliminar el ingrediente");
		}
		
		Controller.getInstance().action(new Context(Eventos.BUSCAR_PLATO_CON_INGREDIENTES, plato.getId()));
		dispose();
	}
}
