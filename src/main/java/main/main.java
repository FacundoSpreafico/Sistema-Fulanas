package main;
import java.sql.SQLException;

import javax.swing.UnsupportedLookAndFeelException;

import gestores.GestorArticulos;
import ui.Sistema;

public class main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
		Sistema menu = new Sistema();
		menu.setVisible(true);
		
		
		
	
		//GestorArticulos.getInstance().cargarArticulo(2, 30, 39, 40000, "Jeans blanco");
		
		
	}

}
