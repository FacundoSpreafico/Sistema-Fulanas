package ui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Constants.Images;
import entitys.Articulo;
import gestores.GestorArticulos;
import com.toedter.components.JSpinField;
import javax.swing.JSeparator;

public class Sistema extends JFrame {

	private JPanel contentPane;
	private JTextField textField_codigo;
	private JTextField textField_Descripcion;
	private JTextField textField_precio;
	private JTextField textField_stock;
	private JTable tablaArticulos;
	private JTable table;
	private JTable tablaVentas;
	JTabbedPane tabbedPaneGeneral;
	private JTextField textField_cantidad;
	private JTextField textFieldAPagar;
	private JTable tableVenta;
	private JTable tableArticulo;
	private JTextField textFieldDescripcion;
	private JTextField textField_Precio;
	private JTextField textField_Stock;
	private JTextField textFieldCodArticulos;
	private JTextField textField_BuscarArticulo;
	private JTextField textField_Talle;
	JToggleButton btnClientes;
	JToggleButton btnArticulos;
	JToggleButton btnVentas;
	private boolean btnVentasPresionado = false;
	private boolean btnClientesPresionado = false;
	private boolean btnArticulosPresionado = false;
	private JRadioButton rdbtnArticulosDisponibles;
	private Choice choiceTalle;
	private JButton btnEliminarCarrito;
    
	@SuppressWarnings("serial")
	public Sistema() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setIconImage(Images.LOGO.getImage());
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalir = new JButton("");
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setIcon(Images.SALIR_NORMAL);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnSalir.setIcon(Images.SALIR_PRESIONADO);
		    }

		    public void mouseExited(MouseEvent e) {
		    	btnSalir.setIcon(Images.SALIR_NORMAL);
		    }
			
		});
		Date fechaActual = new Date();

		JLabel lblEncabezadoSuperior = new JLabel("");
		lblEncabezadoSuperior.setIcon(Images.BANNER_SUPERIOR);
		lblEncabezadoSuperior.setBounds(192, 0, 1072, 91);
		contentPane.add(lblEncabezadoSuperior);
		
	
		JToggleButton btnVentas = new JToggleButton(Images.VENTAS_NORMAL);
		btnVentas.setForeground(new Color(255, 255, 255));
		btnVentas.setBounds(-15, 160, 210, 50);
		contentPane.add(btnVentas);
		btnVentas.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		btnVentas.setBackground(new Color(227, 190, 190));
		btnVentas.setFocusPainted(false);
		btnVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPaneGeneral.setSelectedIndex(0);
                btnVentas.setSelected(true);
            }
        });
		btnVentas.setBorderPainted(false);
		btnVentas.setHorizontalAlignment(SwingConstants.LEFT);
			
		JToggleButton btnArticulos = new JToggleButton(Images.ARTICULOS_NORMAL);
	
		btnArticulos.setPressedIcon(Images.ARTICULOS_PRESIONADO);
		btnArticulos.setForeground(Color.WHITE);
		btnArticulos.setBounds(0, 233, 195, 50);
		contentPane.add(btnArticulos);
		btnArticulos.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		btnArticulos.setBackground(new Color(227, 190, 190));
		btnArticulos.addActionListener(e -> tabbedPaneGeneral.setSelectedIndex(1));
		btnArticulos.setFocusPainted(false);
		btnArticulos.setBorderPainted(false);

		
		JToggleButton btnClientes = new JToggleButton(Images.CLIENTES_NORMAL);
		btnClientes.setPressedIcon(Images.CLIENTES_PRESIONADO);
		btnClientes.addActionListener(e -> tabbedPaneGeneral.setSelectedIndex(2));
		btnClientes.setForeground(Color.WHITE);
		btnClientes.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		btnClientes.setFocusPainted(false);
		btnClientes.setBorderPainted(false);
		btnClientes.setBackground(new Color(227, 190, 190));
		btnClientes.setBounds(0, 304, 195, 50);
		contentPane.add(btnClientes);
		
		
		
		btnArticulos.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        btnArticulos.setIcon(Images.ARTICULOS_PRESIONADO);
		        btnArticulosPresionado = true;
		        
		        if (btnVentasPresionado) {
		            btnVentas.setIcon(Images.VENTAS_NORMAL);
		            btnVentasPresionado = false;
		        }
		        if (btnClientesPresionado) {
		            btnClientes.setIcon(Images.CLIENTES_NORMAL);
		            btnClientesPresionado = false;
		        }
		    }

		    public void mouseEntered(MouseEvent e) {
		        if (!btnArticulosPresionado) {
		            btnArticulos.setIcon(Images.ARTICULOS_PRESIONADO);
		        }
		    }

		    public void mouseExited(MouseEvent e) {
		        if (!btnArticulosPresionado) {
		            btnArticulos.setIcon(Images.ARTICULOS_NORMAL);
		        }
		    }
		});

		btnVentas.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        btnVentas.setIcon(Images.VENTAS_PRESIONADO);
		        btnVentasPresionado = true;
		        
		        if (btnClientesPresionado) {
		            btnClientes.setIcon(Images.CLIENTES_NORMAL);
		            btnClientesPresionado = false;
		        }
		        if (btnArticulosPresionado) {
		            btnArticulos.setIcon(Images.ARTICULOS_NORMAL);
		            btnArticulosPresionado = false;
		        }
		    }

		    public void mouseEntered(MouseEvent e) {
		        if (!btnVentasPresionado) {
		            btnVentas.setIcon(Images.VENTAS_PRESIONADO);
		        }
		    }

		    public void mouseExited(MouseEvent e) {
		        if (!btnVentasPresionado) {
		            btnVentas.setIcon(Images.VENTAS_NORMAL);
		        }
		    }
		});
		
		btnClientes.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        btnClientes.setIcon(Images.CLIENTES_PRESIONADO);
		        btnClientesPresionado = true;
		        
		        if (btnVentasPresionado) {
		            btnVentas.setIcon(Images.VENTAS_NORMAL);
		            btnVentasPresionado = false;
		        }
		        if (btnArticulosPresionado) {
		            btnArticulos.setIcon(Images.ARTICULOS_NORMAL);
		            btnArticulosPresionado = false;
		        }
		    }

		    public void mouseEntered(MouseEvent e) {
		        if (!btnClientesPresionado) {
		            btnClientes.setIcon(Images.CLIENTES_PRESIONADO);
		        }
		    }

		    public void mouseExited(MouseEvent e) {
		        if (!btnClientesPresionado) {
		            btnClientes.setIcon(Images.CLIENTES_NORMAL);
		        }
		    }
		});
		
		
		btnSalir.setFont(new Font("Arial Black", Font.BOLD, 13));
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setBackground(new Color(227, 190, 190));
		btnSalir.setBounds(0, 631, 195, 50);
		contentPane.add(btnSalir);
		
	   
		
		
		JLabel lblEncabezadoIzquierdo = new JLabel("");
		lblEncabezadoIzquierdo.setIcon(Images.BANNER_IZQUIERDO);
		lblEncabezadoIzquierdo.setBounds(0, 0, 195, 681);
		contentPane.add(lblEncabezadoIzquierdo);
		
		tabbedPaneGeneral = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneGeneral.setBounds(192, 68, 1072, 613);
		tabbedPaneGeneral.setBorder(BorderFactory.createEmptyBorder()); 
		contentPane.add(tabbedPaneGeneral);
		

		JPanel Ventas = new JPanel();
		tabbedPaneGeneral.addTab("Ventas", null, Ventas, null);
		Ventas.setLayout(null);
		
		
		JTabbedPane tabbedPaneVentas = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneVentas.setFont(new Font("Arial", Font.PLAIN, 12));
		tabbedPaneVentas.setBounds(0, 0, 1067, 585);
		Ventas.add(tabbedPaneVentas);
		
		JPanel panelBuscar = new JPanel();
		panelBuscar.setBackground(Color.WHITE);
		panelBuscar.setForeground(Color.WHITE);
		tabbedPaneVentas.addTab("Buscar venta", Images.ICONO_BUSCAR, panelBuscar, null);
		panelBuscar.setLayout(null);
		
		JLabel lblSeleccionaFecha_1 = new JLabel("Selecciona una fecha");
		lblSeleccionaFecha_1.setIcon(Images.ICONO_CALENDARIO);
		lblSeleccionaFecha_1.setForeground(Color.BLUE);
		lblSeleccionaFecha_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblSeleccionaFecha_1.setBackground(Color.BLACK);
		lblSeleccionaFecha_1.setBounds(705, 0, 184, 32);
		panelBuscar.add(lblSeleccionaFecha_1);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setDateFormatString("d MMMMM y");
		dateChooser_2.setFocusable(false);
		dateChooser_2.setBounds(882, 0, 170, 32);
		panelBuscar.add(dateChooser_2);
		dateChooser_2.setDate(fechaActual);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 45, 1019, 427);
		panelBuscar.add(scrollPane);
		
		tableVenta = new JTable();
		scrollPane.setViewportView(tableVenta);
		configuracionTabla(tableVenta);
		tableVenta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo venta", "Importe", "Fecha", "Hora"
			}
		));

		JPanel panelNuevaVenta = new JPanel();
		panelNuevaVenta.setBackground(Color.WHITE);
		tabbedPaneVentas.addTab("Nueva venta", Images.ICONO_NUEVA_VENTA, panelNuevaVenta, null);
		panelNuevaVenta.setLayout(null);
		
		textField_codigo = new JTextField();
		textField_codigo.setFont(new Font("Arial", Font.PLAIN, 13));
		textField_codigo.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        try {
		            Integer codigo = Integer.parseInt(textField_codigo.getText().trim());
		            List<Articulo> articulos = GestorArticulos.getInstance().queryArticulo(codigo);
		            
		            choiceTalle.removeAll();
		            textField_Descripcion.setText("");
		            textField_precio.setText("");
		            textField_stock.setText("");
		            
		            for (Articulo unArticulo : articulos) {
		                choiceTalle.addItem(String.valueOf(unArticulo.getTalle()));
		            }
		            
		            if (articulos.size() > 0) {
		                Articulo primerArticulo = articulos.get(0);
		                choiceTalle.select(String.valueOf(primerArticulo.getTalle()));
		                cargarDatosArticulo(primerArticulo);
		            }
		        } catch (NumberFormatException ex) {
		        	choiceTalle.removeAll();
		            textField_Descripcion.setText("");
		            textField_precio.setText("");
		            textField_stock.setText("");
		        }
		    }
		});
		
		textField_codigo.setBounds(154, 42, 118, 20);
		panelNuevaVenta.add(textField_codigo);
		textField_codigo.setBorder(null);
		textField_codigo.setPreferredSize(new Dimension(0, 5));
		
		JLabel lblCodigo = new JLabel("Codigo articulo");
		lblCodigo.setBounds(31, 48, 108, 14);
		panelNuevaVenta.add(lblCodigo);
		lblCodigo.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblTalle = new JLabel("Talle");
		lblTalle.setBounds(31, 106, 50, 14);
		panelNuevaVenta.add(lblTalle);
		lblTalle.setFont(new Font("Arial", Font.BOLD, 13));
		
		choiceTalle = new Choice();
		choiceTalle.setFont(new Font("Arial", Font.PLAIN, 13));
		choiceTalle.setBounds(154, 106, 118, 20);
		panelNuevaVenta.add(choiceTalle);
		choiceTalle.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            Integer codigo = Integer.parseInt(textField_codigo.getText().trim());
		            Integer talle = Integer.parseInt(choiceTalle.getSelectedItem());
		            
		            Articulo articulo = GestorArticulos.getInstance().getArticuloPorCodigoYTalle(codigo, talle);
		            if (articulo != null) {
		                cargarDatosArticulo(articulo);
		            } else {
		                
		                textField_Descripcion.setText("");
		                textField_precio.setText("");
		                textField_stock.setText("");
		            }
		        }
		    }
		});
		
		
		
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(31, 165, 69, 14);
		panelNuevaVenta.add(lblCantidad);
		lblCantidad.setFont(new Font("Arial", Font.BOLD, 13));
		
		textField_cantidad = new JTextField();
		textField_cantidad.setFont(new Font("Arial", Font.PLAIN, 13));
		textField_cantidad.setText("1");
		textField_cantidad.setBounds(154, 160, 118, 20);
		panelNuevaVenta.add(textField_cantidad);
		textField_cantidad.setPreferredSize(new Dimension(0, 5));
		textField_cantidad.setBorder(null);
		
		JLabel lblNombre = new JLabel("Descripcion");
		lblNombre.setBounds(31, 226, 85, 14);
		panelNuevaVenta.add(lblNombre);
		lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
		
			
		textField_Descripcion = new JTextField();
		textField_Descripcion.setBackground(Color.WHITE);
		textField_Descripcion.setFont(new Font("Arial", Font.BOLD, 13));
		textField_Descripcion.setBounds(154, 223, 133, 20);
		panelNuevaVenta.add(textField_Descripcion);
		textField_Descripcion.setEditable(false);
		textField_Descripcion.setColumns(10);
			
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(31, 291, 50, 14);
		panelNuevaVenta.add(lblPrecio);
		lblPrecio.setFont(new Font("Arial", Font.BOLD, 13));
			
		textField_precio = new JTextField();
		textField_precio.setBackground(Color.WHITE);
		textField_precio.setFont(new Font("Arial", Font.BOLD, 13));
		textField_precio.setBounds(154, 288, 118, 20);
		panelNuevaVenta.add(textField_precio);
		textField_precio.setEditable(false);
		textField_precio.setColumns(10);
			
			textField_stock = new JTextField();
			textField_stock.setBackground(Color.WHITE);
			textField_stock.setFont(new Font("Arial", Font.BOLD, 13));
			textField_stock.setBounds(154, 358, 118, 20);
			panelNuevaVenta.add(textField_stock);
			textField_stock.setEditable(false);
			textField_stock.setColumns(10);
			
			JLabel lblStock = new JLabel("Stock");
			lblStock.setBounds(31, 361, 50, 14);
			panelNuevaVenta.add(lblStock);
			lblStock.setFont(new Font("Arial", Font.BOLD, 13));
			lblStock.setForeground(Color.BLACK);
			lblStock.setBackground(Color.BLUE);
			
	
			
			
			//Tabla venta nueva
			
			JScrollPane scrollPane_tablaventas = new JScrollPane();
			scrollPane_tablaventas.setBounds(317, 45, 735, 427);
			panelNuevaVenta.add(scrollPane_tablaventas);
			
			tablaVentas = new JTable();
			tablaVentas.setBackground(Color.WHITE);
			scrollPane_tablaventas.setViewportView(tablaVentas);
			
            configuracionTabla(tablaVentas);
            tablaVentas.setModel(new DefaultTableModel(new Object[][] {},
                    new String[] {"Codigo articulo", "Talle", "Descripcion", "Precio/unidad", "Cantidad", "Total"}) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
           
            tablaVentas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if ( tablaVentas.getSelectedRow() != -1) {
                        btnEliminarCarrito.setEnabled(true);
                    } else {
                        btnEliminarCarrito.setEnabled(false);
                    }
                }
            });
            
			
			JLabel lblSeleccionaFecha = new JLabel("Selecciona una fecha");
			lblSeleccionaFecha.setBounds(705, 0, 184, 32);
			panelNuevaVenta.add(lblSeleccionaFecha);
			lblSeleccionaFecha.setIcon(Images.ICONO_CALENDARIO);
			lblSeleccionaFecha.setForeground(Color.BLUE);
			lblSeleccionaFecha.setBackground(Color.BLACK);
			lblSeleccionaFecha.setFont(new Font("Arial", Font.BOLD, 13));
			
			
			JDateChooser dateChooser_1 = new JDateChooser();
			dateChooser_1.setDateFormatString("d MMMMM y");
			dateChooser_1.setBounds(882, 0, 170, 32);
			panelNuevaVenta.add(dateChooser_1);
			dateChooser_1.setDate(fechaActual);
			
			dateChooser_1.setFocusable(false);
			
			textFieldAPagar = new JTextField();
			textFieldAPagar.setFont(new Font("Arial", Font.BOLD, 20));
			textFieldAPagar.setEditable(false);
			textFieldAPagar.setBackground(SystemColor.info);
			textFieldAPagar.setForeground(Color.BLUE);
			textFieldAPagar.setBounds(882, 483, 170, 53);
			panelNuevaVenta.add(textFieldAPagar);
			textFieldAPagar.setColumns(10);
			
			JLabel lblTotal = new JLabel("A pagar $");
			lblTotal.setIcon(Images.ICONO_TOTAL);
			lblTotal.setForeground(Color.BLUE);
			lblTotal.setFont(new Font("Arial", Font.BOLD, 20));
			lblTotal.setBackground(Color.BLACK);
			lblTotal.setBounds(760, 481, 118, 56);
			panelNuevaVenta.add(lblTotal);
			
			JButton btnAgregarArtVenta = new JButton("");
			btnAgregarArtVenta.setBackground(Color.WHITE);
			btnAgregarArtVenta.setOpaque(true);
			DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
			btnAgregarArtVenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
					Integer codArticulo = Integer.parseInt(textField_codigo.getText().toString());
					String descripcion = textField_Descripcion.getText();
					double precio = Double.parseDouble(textField_precio.getText().toString());
					Integer cantidad = Integer.parseInt(textField_cantidad.getText().toString());
					double total = precio*cantidad;
					Integer stock = Integer.parseInt(textField_stock.getText());
					Integer talle = Integer.parseInt(choiceTalle.getSelectedItem().toString());
					Object[] nuevaFila = {codArticulo,talle,descripcion,precio,cantidad, total}; 
					
					if (cantidad > stock) {
						 JOptionPane.showMessageDialog(null, "No se posee el stock necesario", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
					else {
					modelo.addRow(nuevaFila);
					modelo.fireTableDataChanged();
					textFieldAPagar.setText(String.valueOf(calcularTotal(modelo)));
					textField_codigo.setText("");
					textField_cantidad.setText("1");
					choiceTalle.removeAll();
					textField_Descripcion.setText("");
	                textField_precio.setText("");
	                textField_stock.setText("");
	                textField_codigo.requestFocus();
					}
					}
					catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "No se ingresó ningún producto", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnAgregarArtVenta.setIcon(Images.ICONO_AGREGAR_CARRITO);
			btnAgregarArtVenta.setBounds(31, 432, 50, 40);
			panelNuevaVenta.add(btnAgregarArtVenta);
			
			JButton btnCobrar = new JButton("Cobrar");
			btnCobrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CobraVenta nuevo;
					nuevo = new CobraVenta(String.valueOf(calcularTotal(modelo)));
					nuevo.setVisible(true);
				}});
			
			btnCobrar.setIcon(Images.ICONO_BOTON_COBRAR);
			btnCobrar.setForeground(Color.BLUE);
			btnCobrar.setFont(new Font("Arial", Font.BOLD, 20));
			btnCobrar.setBounds(317, 483, 179, 53);
			panelNuevaVenta.add(btnCobrar);
			
			btnEliminarCarrito = new JButton("");
			btnEliminarCarrito.setBackground(Color.WHITE);
			btnEliminarCarrito.setEnabled(false);
			btnEliminarCarrito.setIcon(Images.ICONO_QUITAR_CARRITO);
			btnEliminarCarrito.setBounds(111, 432, 50, 40);
			panelNuevaVenta.add(btnEliminarCarrito);
			
			btnEliminarCarrito.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        int selectedRowIndex = tablaVentas.getSelectedRow();
			        if (selectedRowIndex != -1) {
			            modelo.removeRow(selectedRowIndex);
			            tablaVentas.clearSelection();
			            btnEliminarCarrito.setEnabled(false);
			        }
			    }
			});

			JSeparator separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBackground(Color.BLACK);
			separator.setBounds(154, 63, 118, 2);
			panelNuevaVenta.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setForeground(Color.BLACK);
			separator_1.setBackground(Color.BLACK);
			separator_1.setBounds(154, 181, 118, 2);
			panelNuevaVenta.add(separator_1);

	        settingsArticulos();
		
		
	}
	
	private void datosTablaArticulos(Boolean mostrarDisponible) throws ClassNotFoundException, SQLException {
		String[] Titulos = {"Codigo articulo", "Talle", "Stock", "Descripcion", "Precio"};
	    DefaultTableModel dtm_datos = new DefaultTableModel();
	    TableRowSorter<TableModel> trs; 
	    
	    String[][] M_datos = null;    
		Integer contador = 0;  
		
        contador = GestorArticulos.getInstance().queryContarArticulo(mostrarDisponible).size();
        M_datos= GestorArticulos.getInstance().cargarDatosArticulo(contador,mostrarDisponible);
            
        dtm_datos = new DefaultTableModel(M_datos, Titulos) { 
            public boolean isCellEditable(int row, int column) { 
                return false;  
            }
        };
        tableArticulo.setModel(dtm_datos); 
        trs = new TableRowSorter<>(dtm_datos); 
        tableArticulo.setRowSorter(trs); 
	}
	
	private void datosTablaVentaParcial() throws ClassNotFoundException, SQLException {
		String[] Titulos = {"Codigo articulo", "Talle", "Stock", "Descripcion", "Precio"};
	    DefaultTableModel dtm_datos = new DefaultTableModel();
	    TableRowSorter<TableModel> trs; 
	    
	    String[][] M_datos = null;    
		Integer contador = 0;  
		
   
        dtm_datos = new DefaultTableModel(M_datos, Titulos) { 
            public boolean isCellEditable(int row, int column) { 
                return false;  
            }
        };
        tableArticulo.setModel(dtm_datos); 
        trs = new TableRowSorter<>(dtm_datos); 
        tableArticulo.setRowSorter(trs); 
	}
	
	
	@SuppressWarnings("serial")
	public void datosTablaArticulosBusqueda(JTextField textField, boolean mostrarDisponible) throws ClassNotFoundException, SQLException {
		String M_datos[][]= null;
		TableRowSorter<TableModel> trs; 
		String[] Titulos = {"Codigo articulo", "Talle", "Stock", "Descripcion", "Precio"}; 
	    DefaultTableModel dtm_datos = new DefaultTableModel(); 
		M_datos = GestorArticulos.getInstance().contarDatosQueryArticulo(textField, mostrarDisponible);
		dtm_datos = new DefaultTableModel(M_datos, Titulos) {
        public boolean isCellEditable(int row, int column) {return false;}
        };
        tableArticulo.setModel(dtm_datos);
        trs = new TableRowSorter<>(dtm_datos);
        tableArticulo.setRowSorter(trs);
	}
	public void cargarDatosArticulo(Articulo articulo) {
	    textField_Descripcion.setText(articulo.getDescripcion().toUpperCase());
	    textField_precio.setText(String.valueOf(articulo.getPrecio()));
	    textField_stock.setText(String.valueOf(articulo.getStock()));
	}
	public double calcularTotal(DefaultTableModel modelo) {
	    double sumaTotal = 0.0;
	    for (int i = 0; i < modelo.getRowCount(); i++) {
	        double total = (double) modelo.getValueAt(i, 5);
	        sumaTotal += total;
	    }
		return sumaTotal;
	}
	
	public void configuracionTabla(JTable tabla) {
	    Color colorPrincipal = new Color(227, 190, 190); // Color #E3BEBE convertido a RGB

	    // Configuración de la tabla
	    tabla.setForeground(Color.BLACK);
	    tabla.setFont(new Font("Arial", Font.PLAIN, 14));
	    tabla.setBackground(Color.WHITE);
	    tabla.setSelectionBackground(new Color(200, 220, 255)); // Color de selección más claro
	    tabla.setSelectionForeground(Color.BLACK);
	    tabla.setRowHeight(35);
	    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    tabla.setIntercellSpacing(new Dimension(10, 10));
	    tabla.setShowGrid(true);
	    tabla.setFillsViewportHeight(true);
	    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tabla.setShowVerticalLines(true);
	    tabla.setShowHorizontalLines(true);
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    tabla.setDefaultRenderer(Object.class, centerRenderer);
	    tabla.getTableHeader().setReorderingAllowed(false);

	    JTableHeader header = tabla.getTableHeader();
	    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            setFont(new Font("Arial", Font.BOLD, 14));
	            setForeground(Color.BLACK); 
	            setBackground(colorPrincipal);
	            setHorizontalAlignment(SwingConstants.CENTER);
	            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	            return this;
	        }
	    };
	    header.setDefaultRenderer(headerRenderer);
	    JViewport viewport = (JViewport) tabla.getParent();
	    viewport.setBackground(Color.WHITE);
	    
	    
	}

	public void settingEditarArticulo(JButton btnEditarArticulo) {
		btnEditarArticulo.setFont(new Font("Arial", Font.BOLD, 13));
		btnEditarArticulo.setBounds(33, 511, 132, 26);
		btnEditarArticulo.setEnabled(false);
		
		btnEditarArticulo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int filaSeleccionada = tableArticulo.getSelectedRow();
		        if (filaSeleccionada != -1) {
		            int codigoArticulo = Integer.parseInt(tableArticulo.getValueAt(filaSeleccionada, 0).toString());
		            int talle = Integer.parseInt(tableArticulo.getValueAt(filaSeleccionada, 1).toString());
		            int stockActual = Integer.parseInt(tableArticulo.getValueAt(filaSeleccionada, 2).toString()); 
		            String descripcion = tableArticulo.getValueAt(filaSeleccionada, 3).toString();
		            double precioActual = Double.parseDouble(tableArticulo.getValueAt(filaSeleccionada, 4).toString()); 
		            Object[] nuevoDatos = new Object[3];
		            JPanel panel = new JPanel();
		            panel.setLayout(new GridLayout(6, 2));
		            JTextField stockField = new JTextField(String.valueOf(stockActual));
		            JTextField precioField = new JTextField(String.valueOf(precioActual));
		            JTextField descripcionField = new JTextField(descripcion);
		            
		            panel.add(new JLabel("Stock:"));
		            panel.add(stockField);
		            panel.add(new JLabel("Precio:"));
		            panel.add(precioField);
		            panel.add(new JLabel("Descripción:"));
		            panel.add(descripcionField);
		            
		            int result = JOptionPane.showConfirmDialog(null, panel, "Editar Artículo",
		                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		            
		            if (result == JOptionPane.OK_OPTION) {
		                try {
		                    int nuevoStock = Integer.parseInt(stockField.getText());
		                    double nuevoPrecio = Double.parseDouble(precioField.getText());
		                    String nuevaDescripcion = descripcionField.getText();
		                    
		                    GestorArticulos.getInstance().updateArticulo(codigoArticulo,talle,nuevoStock,nuevoPrecio,nuevaDescripcion);
		                    JOptionPane.showMessageDialog(null, "Artículo actualizado exitosamente");
		                    boolean presionado = rdbtnArticulosDisponibles.isSelected();
		                    datosTablaArticulosBusqueda(textField_BuscarArticulo,presionado);
		                    btnEditarArticulo.setEnabled(false);
		                } catch (NumberFormatException | ClassNotFoundException | SQLException ex) {
		                    JOptionPane.showMessageDialog(null, "Por favor, ingresa valores válidos.");
		                }
		            }
		        }
		        else {
		        	btnEditarArticulo.setEnabled(false);
		        }
		    }
		});
	}
	
	public void settingTableArticulo(JTable tableArticulo, JButton btnEditarArticulo, JScrollPane scrollPane) throws ClassNotFoundException, SQLException {
		scrollPane.setViewportView(tableArticulo);
		configuracionTabla(tableArticulo);
		
		tableArticulo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && tableArticulo.getSelectedRow() != -1) {
		            btnEditarArticulo.setEnabled(true);
		        }
		    }
		});
	    datosTablaArticulos(true); 
	}
	
	public void settingsRdBtnArtDisponibles(JRadioButton rdbtnArticulosDisponibles) {
		rdbtnArticulosDisponibles.setBackground(Color.WHITE);
		rdbtnArticulosDisponibles.setFont(new Font("Arial", Font.PLAIN, 13));
		rdbtnArticulosDisponibles.setBounds(822, 513, 230, 23);
		rdbtnArticulosDisponibles.setSelected(true);
		
		rdbtnArticulosDisponibles.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (rdbtnArticulosDisponibles.isSelected()) {
		            try {
						datosTablaArticulosBusqueda(textField_BuscarArticulo,true);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
		        } else {
		            try {
						datosTablaArticulosBusqueda(textField_BuscarArticulo,false);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
		        }
		    }
		});
		
		
	}
	
	public void settingsArticulos() throws ClassNotFoundException, SQLException {
        JPanel Articulos = new JPanel();
		
		tabbedPaneGeneral.addTab("Articulos", null, Articulos, null);
		Articulos.setLayout(null);
		
		JTabbedPane tabbedPaneArticulos = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneArticulos.setFont(new Font("Arial", Font.PLAIN, 12));
		tabbedPaneArticulos.setBounds(0, 0, 1067, 585);
		Articulos.add(tabbedPaneArticulos);
		
		
		JPanel panelBuscarArticulos = new JPanel();
		panelBuscarArticulos.setBackground(Color.WHITE);
		tabbedPaneArticulos.addTab("Buscar articulo", null, panelBuscarArticulos, null);
		panelBuscarArticulos.setLayout(null);
		
		JPanel panelNuevoArticulo = new JPanel();
		panelNuevoArticulo.setBackground(Color.WHITE);
		tabbedPaneArticulos.addTab("Registrar articulo", null, panelNuevoArticulo, null);
		
		tabbedPaneArticulos.addTab("Buscar articulos", Images.ICONO_BUSCAR, panelBuscarArticulos, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 59, 1019, 440);
		panelBuscarArticulos.add(scrollPane_1);
		

		JRadioButton rdbtnArticulosDisponibles = new JRadioButton("Solo mostrar articulos disponibles");
		settingsRdBtnArtDisponibles(rdbtnArticulosDisponibles);
		panelBuscarArticulos.add(rdbtnArticulosDisponibles);
		
		JButton btnEditarArticulo = new JButton("Editar articulo");
        settingEditarArticulo(btnEditarArticulo);
        panelBuscarArticulos.add(btnEditarArticulo);

        tableArticulo = new JTable();
		settingTableArticulo(tableArticulo,btnEditarArticulo,scrollPane_1);

	
		JLabel lblBuscarArticulo = new JLabel("Buscar");
		lblBuscarArticulo.setFont(new Font("Arial", Font.BOLD, 13));
		lblBuscarArticulo.setBounds(33, 27, 57, 14);
		panelBuscarArticulos.add(lblBuscarArticulo);
		
		
		textField_BuscarArticulo = new JTextField();
		textField_BuscarArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
					if (rdbtnArticulosDisponibles.isSelected()) {
					try {
						datosTablaArticulosBusqueda(textField_BuscarArticulo,true);
					} catch (ClassNotFoundException | SQLException e1) {
					}
					}
					else {
					try {
						datosTablaArticulosBusqueda(textField_BuscarArticulo,false);
					} catch (ClassNotFoundException | SQLException e1) {
					}
					}
			}
		});

		textField_BuscarArticulo.setBounds(100, 24, 162, 20);
		panelBuscarArticulos.add(textField_BuscarArticulo);
		textField_BuscarArticulo.setColumns(10);

		tabbedPaneArticulos.addTab("Registrar articulo", Images.ICONO_NUEVA_VENTA, panelNuevoArticulo, null);
		panelNuevoArticulo.setLayout(null);
		
	
		
		JLabel lblCodigo_1 = new JLabel("Codigo articulo");
		lblCodigo_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblCodigo_1.setBounds(31, 48, 108, 14);
		panelNuevoArticulo.add(lblCodigo_1);
		
		JLabel lblTalle_1 = new JLabel("Talle");
		lblTalle_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblTalle_1.setBounds(31, 109, 50, 14);
		panelNuevoArticulo.add(lblTalle_1);
		
		JLabel lblStock_1 = new JLabel("Stock");
		lblStock_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblStock_1.setBounds(31, 168, 69, 14);
		panelNuevoArticulo.add(lblStock_1);
		
		JLabel lblNombre_1 = new JLabel("Descripcion");
		lblNombre_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNombre_1.setBounds(31, 229, 85, 14);
		panelNuevoArticulo.add(lblNombre_1);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(159, 226, 133, 20);
		panelNuevoArticulo.add(textFieldDescripcion);
		
		JLabel lblPrecio_1 = new JLabel("Precio");
		lblPrecio_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblPrecio_1.setBounds(31, 294, 50, 14);
		panelNuevoArticulo.add(lblPrecio_1);
		
		textField_Precio = new JTextField();
		textField_Precio.setColumns(10);
		textField_Precio.setBounds(159, 291, 86, 20);
		panelNuevoArticulo.add(textField_Precio);
		
		textField_Stock = new JTextField();
		textField_Stock.setColumns(10);
		textField_Stock.setBounds(159, 165, 133, 20);
		panelNuevoArticulo.add(textField_Stock);
		
		textFieldCodArticulos = new JTextField();
		textFieldCodArticulos.setColumns(10);
		textFieldCodArticulos.setBounds(159, 45, 133, 20);
		panelNuevoArticulo.add(textFieldCodArticulos);
		
		textField_Talle = new JTextField();
		textField_Talle.setColumns(10);
		textField_Talle.setBounds(159, 106, 133, 20);
		panelNuevoArticulo.add(textField_Talle);
		
		JButton btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldCodArticulos.setText(" ");
				textField_Stock.setText(" ");
				textField_Talle.setText(" ");
				textField_Precio.setText(" ");
				textFieldDescripcion.setText(" ");
			}
		});
		btnLimpiar.setIcon(Images.ICONO_LIMPIAR);
		btnLimpiar.setBounds(102, 361, 50, 40);
		panelNuevoArticulo.add(btnLimpiar);
		
		JButton btnAgregarArticulo = new JButton("");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codArticulo = Integer.parseInt(textFieldCodArticulos.getText());
		        int stock = Integer.parseInt(textField_Stock.getText());
		        int talle = Integer.parseInt(textField_Talle.getText());
		        double precio = Double.parseDouble(textField_Precio.getText());
		        String descripcion = textFieldDescripcion.getText();
		        try {
		        GestorArticulos.getInstance().cargarArticulo(codArticulo, stock, talle, precio, descripcion);
		        datosTablaArticulos(true);
		        } catch (ClassNotFoundException | SQLException ex) {
		            ex.printStackTrace();
		        }		       
		        textFieldCodArticulos.setText("");
		        textField_Stock.setText("");
		        textField_Talle.setText("");
		        textField_Precio.setText("");
		        textFieldDescripcion.setText("");
			}
		});
		btnAgregarArticulo.setIcon(Images.ICONO_AGREGAR_CARRITO);
		btnAgregarArticulo.setBounds(31, 361, 50, 40);
		panelNuevoArticulo.add(btnAgregarArticulo);
		
		
		
		JTabbedPane Clientes = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneGeneral.addTab("Clientes", null, Clientes, null);

		for (int i=0; i<tabbedPaneGeneral.getComponentCount(); i++) {
		tabbedPaneGeneral.setEnabledAt(i, false);
		}
        
		
	}
	
}
