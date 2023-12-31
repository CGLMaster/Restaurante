package Presentación.Producto.VistasCasos_de_UsoPr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import Negocio.Producto.TDistribuye;
import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

public class VMostrarVinculados extends JFrame implements IGUI {
	private List<TDistribuye> vinculados;
	private JComboBox<String> comboVinc;
	int id_Producto;
	JLabel infoIDProd, infoIDProv;

	public VMostrarVinculados(List<TDistribuye> vinculados) {
		this.vinculados = vinculados;
		id_Producto = vinculados.get(0).getIdProducto();
		init_GUI();

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
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		// BACK BUTTON
		JPanel backButtonContainer = backButtonContainer();

		// TITULO
		JLabel titleLabel = new JLabel("Proveedores vinculados con el producto con id:" + id_Producto);
		titleLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 20));

	
		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);

		// INFO
		infoIDProd = new JLabel("ID_Producto: " + id_Producto);
		infoIDProv = new JLabel();
		
		// COMBOBOXPANEL
		JPanel comboPanel = new JPanel();
		comboPanel.setLayout(new BoxLayout(comboPanel,BoxLayout.X_AXIS));
		JLabel infoComboBox = new JLabel("ID_Proveedor:  ");
		comboVinc = initComboBox();
		comboVinc.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TDistribuye aux = vinculados.get(comboVinc.getSelectedIndex());
				infoIDProv.setText("");
				infoIDProv.setText("ID_Proveedor: " + aux.getIdProveedor());
			}
			
		});
		
		// NEW UNLINK BUTTON
		JButton unlinkButton = new JButton();
		unlinkButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("unlink_icon.jpg")));
		unlinkButton.setToolTipText("Desvincularlo de su proveedor");
		unlinkButton.setPreferredSize(new Dimension(25, 25));
		unlinkButton.setBackground(new Color(236, 115, 115));
		unlinkButton.setBorderPainted(false);	
		unlinkButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id_Prov = vinculados.get(comboVinc.getSelectedIndex()).getIdProveedor();
				
				int res = JOptionPane.showConfirmDialog(null, "¿Esta seguro de desvincular al "
						+ "producto con ID: " + id_Producto + " del "
								+ "proveedor con ID: " + id_Prov);
				
				if(res == 0){
					TDistribuye desvinc = new TDistribuye(id_Producto,id_Prov);
					Controller.getInstance().action(new Context(Eventos.DESVINCULAR_PRODUCTO_PROVEEDOR, desvinc));
				}
				
			}
					
		});
			
		comboPanel.add(infoComboBox);
		comboPanel.add(comboVinc);
		comboPanel.add(Box.createRigidArea(new Dimension(10,0)));
		comboPanel.add(unlinkButton);
		comboPanel.setMaximumSize(new Dimension(200,30));
		
		// BOTON VINCULAR
		JPanel buttonPanel = new JPanel();
		JButton vincularButton = new JButton("Vincular Nuevo Proveedor");
		vincularButton.setBackground(new Color(39, 174, 95));
		vincularButton.setBorderPainted(false);
		vincularButton.setForeground(Color.white);
		vincularButton.setUI(new MetalButtonUI(){
			protected Color getDisabledTextColor(){
				return Color.white;
			}
		});
		vincularButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(new Context(Eventos.VINCULAR_PRODUCTO_PROVEEDOR, id_Producto));
				dispose();
			}
			
		});
		
		
		buttonPanel.add(vincularButton);

		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(comboPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		contentContainer.add(infoIDProd);
		contentContainer.add(infoIDProv);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		mainPanel.add(buttonPanel);
		
		this.pack();
		this.setVisible(true);

	}

	private JComboBox<String> initComboBox() {
		JComboBox<String> combo;

		combo = new JComboBox<String>();

		for (TDistribuye t : vinculados) {
			combo.addItem(Integer.toString(t.getIdProveedor()));
		}
		
		infoIDProv.setText("ID_Proveedor: " + vinculados.get(0).getIdProveedor());

		return combo;
	}

	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Productos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(JPanel.LEFT_ALIGNMENT);

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
		
		if(res.getEvento() == Eventos.RES_DESVINCULAR_PRODUCTO_PROVEEDOR_OK){
			JOptionPane.showMessageDialog(null, "Desvinculacion realizada correctamente");
		}else if(res.getEvento() == Eventos.RES_DESVINCULAR_PRODUCTO_PROVEEDOR_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la desvinculacion");
		}
		Controller.getInstance().action(new Context(Eventos.BUSCAR_TODOS_PRODUCTO));
		dispose();
		
	}

}
