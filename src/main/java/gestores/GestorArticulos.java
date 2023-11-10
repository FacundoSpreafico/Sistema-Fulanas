package gestores;

import java.util.List;

import javax.swing.JTextField;

import DAO.ArticuloDAO;
import entitys.Articulo;

public class GestorArticulos {
	private static GestorArticulos _INSTANCE;
	
	public static GestorArticulos getInstance() {
		if (_INSTANCE == null) {
			_INSTANCE = new GestorArticulos();
		}
		return _INSTANCE;
	}
	    
	public void cargarArticulo (int codArticulo,int stock, int talle, double precio, String descripcion) {
		new ArticuloDAO().createArticulo(codArticulo, stock, talle, precio, descripcion);
	}
	
	public List<Articulo> queryArticulo (int idArticulo) {
		return new ArticuloDAO().queryArticulo(idArticulo);
	}
	
	public List<Articulo> queryContarArticulo(boolean mostrarDisponible) {
		return new ArticuloDAO().queryContarArticulo(mostrarDisponible);
	}
	
	public String[][] cargarDatosArticulo(Integer contador, boolean mostrarDisponible) {
		return new ArticuloDAO().cargarDatosArticulo(contador,mostrarDisponible);
	}
	
	public String[][] contarDatosQueryArticulo (JTextField textField,boolean mostrarDisponible){
		return new ArticuloDAO().contarDatosQueryArticulo(textField,mostrarDisponible);
	}
	public Articulo getArticuloPorCodigoYTalle(Integer codigo,Integer talle) {
		return new ArticuloDAO().getArticuloPorCodigoYTalle(codigo,talle);
	}
	public Integer cantidadTalles(Integer codigo) {
		return new ArticuloDAO().cantidadTalles(codigo);
	}
	public void updateArticulo(int codigoArticulo, int talle, int nuevoStock, double nuevoPrecio, String nuevaDescripcion) {
		new ArticuloDAO().updateArticulo(codigoArticulo, talle, nuevoStock,nuevoPrecio,nuevaDescripcion);
		
	}
	
	
}
