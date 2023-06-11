package Presentación;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentación.Command.Context;
import Presentación.Controller.Controller;
import Presentación.Controller.Eventos;
import Presentación.FactoriaVistas.IGUI;

public class VPrincipal extends JFrame implements IGUI{
	public VPrincipal(){
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	private void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400,100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono2.png")).getImage();
		this.setIconImage(iconFrame);
		
		JPanelConFondo principal = new JPanelConFondo();
		principal.setPreferredSize(new Dimension(1150, 750));
		principal.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo.PNG")).getImage());
		principal.setLayout(new BorderLayout());
		JButton superMer = new JButton();
		superMer.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_grande_portada.png")));
		superMer.setPreferredSize(new Dimension(575, 375));
		superMer.setFocusPainted(false);
		superMer.setBorderPainted(false);
		superMer.setContentAreaFilled(false);
		superMer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Context c = new Context(Eventos.CREAR_VPRINCIPAL_SUPER); 
				Controller.getInstance().action(c);
				dispose();
			}
			
		});
		
		JButton rest = new JButton();
		rest.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_restaurante_portada.PNG")));
		rest.setPreferredSize(new Dimension(575, 375));
		rest.setFocusPainted(false);
		rest.setBorderPainted(false);
		rest.setContentAreaFilled(false);
		rest.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Context c = new Context(Eventos.CREAR_VPRINCIPAL_REST); 
				Controller.getInstance().action(c);
				dispose();
			}
			
		});
		
		principal.add(superMer, BorderLayout.EAST);
		principal.add(rest,BorderLayout.WEST);
		this.add(principal);
		this.setMinimumSize(new Dimension(1150,750));
		this.setVisible(true);
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}
