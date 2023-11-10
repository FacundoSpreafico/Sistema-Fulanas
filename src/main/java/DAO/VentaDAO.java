package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entitys.Articulo;

public class VentaDAO {
	 public void createVenta(int codArticulo,int stock, int talle, double precio, String descripcion) {
  	   SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulo.class).buildSessionFactory();
  	   
  	   Session session = sessionFactory.openSession();
  	   
  	   try {	
            Articulo articulo = new Articulo(codArticulo,stock,talle,descripcion,precio);	
            session.beginTransaction();
  		    session.save(articulo);
  		    session.getTransaction().commit();
  		    session.close();  
  		   }
  	   catch(Exception e) {
			   e.printStackTrace();
  	   }
  	   
     }
     
}
