package ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CobraVenta extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	
	public CobraVenta(String cantidad) {
	    setBounds(0, 34 , 700, 400);
	    setIconImage(new ImageIcon(getClass().getClassLoader().getResource("resources/logo.jpg")).getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		//Boton entrega
		JButton btnPagoCliente = new JButton("");
		btnPagoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		btnPagoCliente.setIcon(new ImageIcon(CobraVenta.class.getResource("/resources/botonEntregaNormal.png")));
		btnPagoCliente.setBounds(0, 272, 134, 50);
		contentPane.add(btnPagoCliente);
		btnPagoCliente.setFocusPainted(false);
		btnPagoCliente.setBorderPainted(false);
		cambiarFondoPresionado(btnPagoCliente, "/resources/botonEntregaPresionado.png", "/resources/botonEntregaNormal.png");	
		
		//Boton pago con tarjeta
		
		JButton btnPagoTarjeta = new JButton("");
		btnPagoTarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		btnPagoTarjeta.setIcon(new ImageIcon(CobraVenta.class.getResource("/resources/botonTarjetaNormal.png")));
		btnPagoTarjeta.setBounds(0, 164, 134, 50);
		contentPane.add(btnPagoTarjeta);
		btnPagoTarjeta.setFocusPainted(false);
		btnPagoTarjeta.setBorderPainted(false);
		cambiarFondoPresionado(btnPagoTarjeta, "/resources/botonTarjetaPresionado.png", "/resources/botonTarjetaNormal.png");	
		
		//Boton pago con efectivo
		
		JButton btnPagoEfectivo = new JButton("");
		btnPagoEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPagoEfectivo.setIcon(new ImageIcon(CobraVenta.class.getResource("/resources/btnEfectivoNormal.png")));
		btnPagoEfectivo.setBounds(0, 56, 134, 50);
		contentPane.add(btnPagoEfectivo);
		btnPagoEfectivo.setFocusPainted(false);
		btnPagoEfectivo.setBorderPainted(false);
		cambiarFondoPresionado(btnPagoEfectivo, "/resources/btnEfectivoPresionado.png","/resources/btnEfectivoNormal.png");
		
		//Panel izquierda
		
		JLabel lblPanelIzquierda = new JLabel("");
		lblPanelIzquierda.setIcon(new ImageIcon(CobraVenta.class.getResource("/resources/diseñoCobrar.png")));
		lblPanelIzquierda.setBounds(0, 0, 134, 361);
		contentPane.add(lblPanelIzquierda);
		
		JLabel lblTotalACobrar = new JLabel("Total a cobrar");
		lblTotalACobrar.setFont(new Font("Arial", Font.BOLD, 20));
		lblTotalACobrar.setBounds(294, 42, 144, 50);
		contentPane.add(lblTotalACobrar);
		
		JLabel lbl$ = new JLabel("$ ");
		lbl$.setForeground(Color.GREEN);
		lbl$.setBackground(Color.GREEN);
		lbl$.setFont(new Font("Arial", Font.BOLD, 30));
		lbl$.setBounds(294, 87, 29, 59);
		contentPane.add(lbl$);
		
		JLabel lblACobrar = new JLabel("0,00");
		lblACobrar.setForeground(Color.GREEN);
		lblACobrar.setFont(new Font("Arial", Font.BOLD, 30));
		lblACobrar.setBackground(Color.GREEN);
		lblACobrar.setBounds(314, 87, 199, 59);
		contentPane.add(lblACobrar);
		
		lblACobrar.setText(cantidad);
		
		JLabel lbl$_Rojo = new JLabel("$ ");
		lbl$_Rojo.setForeground(Color.RED);
		lbl$_Rojo.setFont(new Font("Arial", Font.BOLD, 30));
		lbl$_Rojo.setBackground(Color.RED);
		lbl$_Rojo.setBounds(294, 211, 29, 59);
		contentPane.add(lbl$_Rojo);
		
		JLabel lblVuelto = new JLabel("0,00");
		lblVuelto.setForeground(Color.RED);
		lblVuelto.setFont(new Font("Arial", Font.BOLD, 30));
		lblVuelto.setBackground(Color.RED);
		lblVuelto.setBounds(314, 211, 144, 59);
		contentPane.add(lblVuelto);
		
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        if (textField.getText().isEmpty()) {
		            lblVuelto.setText("0,00");
		        } else {
		            try {
		                double cantidadIngresada = Double.parseDouble(textField.getText());
		                double totalACobrar = Double.parseDouble(lblACobrar.getText());
		                double vuelto = cantidadIngresada - totalACobrar;
		                if (vuelto < 0) {
		                    lblVuelto.setText("0,00");
		                } else {
		                    lblVuelto.setText(String.format("%.2f", vuelto));
		                }
		            } catch (NumberFormatException ex) {
		            }
		        }
		    }
		});


		textField.setFont(new Font("Arial", Font.BOLD, 30));
		textField.setBounds(294, 150, 167, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.setForeground(Color.BLUE);
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCobrar.setFont(new Font("Arial", Font.BOLD, 15));
		btnCobrar.setIcon(new ImageIcon(CobraVenta.class.getResource("/resources/marca-de-verificacion.png")));
		btnCobrar.setBounds(244, 272, 122, 41);
		contentPane.add(btnCobrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.BLUE);
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));
		btnCancelar.setIcon(new ImageIcon(CobraVenta.class.getResource("/resources/cancelar.png")));
		btnCancelar.setBounds(379, 272, 134, 41);
		contentPane.add(btnCancelar);
	}
	
	public void cambiarFondoPresionado (JButton boton, String imagenPresionado,String imagenNormal) {
		boton.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	boton.setIcon(new ImageIcon(Sistema.class.getResource(imagenPresionado)));
		    }
		    public void mouseExited(MouseEvent e) {
		    	boton.setIcon(new ImageIcon(Sistema.class.getResource(imagenNormal)));
		    }
		});
		
		
		
	}
}
