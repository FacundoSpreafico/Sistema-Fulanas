package DAO;
import java.util.List;

import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entitys.Articulo;

public class ArticuloDAO {
	   private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulo.class).buildSessionFactory();
       public void createArticulo(int codArticulo,int stock, int talle, double precio, String descripcion) {
    	   Session session = sessionFactory.openSession();
    	   
    	   try {	
                Articulo articulo = new Articulo(codArticulo,talle,stock,descripcion,precio);	
                session.beginTransaction();
    		    session.save(articulo);
    		    session.getTransaction().commit();
    		    session.close();  
    		   }
    	   catch(Exception e) {
			   e.printStackTrace();
    	   }
    	   
       }
       
       public List<Articulo> queryArticulo(int idArticulo) {
    	    Session session = sessionFactory.openSession();
    	    try {
    	        String hql = "FROM Articulo a WHERE a.id.codArticulo = :idArticulo";
    	        Query<Articulo> query = session.createQuery(hql);
    	        query.setParameter("idArticulo", idArticulo);
    	        return query.list();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return null;
    	    } finally {
    	        session.close();
    	    }
    	}
       
       public List<Articulo> queryContarArticulo(boolean todos) {
   	    Session session = sessionFactory.openSession();
   	    String hql;
   	    if (todos == true) {
	        hql = "FROM Articulo WHERE disponible = true";
	    	}
	    	else {
	    	hql = "FROM Articulo";
	    	}
   	    try {
   	    	
   	        Query<Articulo> query = session.createQuery(hql);
   	        return query.list();
   	    } catch (Exception e) {
   	        e.printStackTrace();
   	        return null;
   	    } finally {
   	        session.close();
   	    }
   	}
       
       public String[][] cargarDatosArticulo(Integer contador, boolean todos) {
           Session session = sessionFactory.openSession();
           String hql;
           try {
        	   if (todos == true) {
               hql = "FROM Articulo WHERE disponible = true";
        	   }
        	   else {
        	   hql = "FROM Articulo";   
        	   }
               Query<Articulo> query = session.createQuery(hql, Articulo.class);
               query.setMaxResults(contador);
               List<Articulo> articulos = query.list();
               String[][] M_datos = new String[articulos.size()][6];
               for (int i = 0; i < articulos.size(); i++) {
                   Articulo articulo = articulos.get(i);
                   M_datos[i][0] = String.valueOf(articulo.getId());
                   M_datos[i][1] = String.valueOf(articulo.getTalle());
                   M_datos[i][2] = String.valueOf(articulo.getStock());
                   M_datos[i][3] = articulo.getDescripcion().toUpperCase();
                   M_datos[i][4] = String.valueOf(articulo.getPrecio());
               }
               return M_datos;
           } finally {
               session.close();
           }
       }

       public String[][] contarDatosQueryArticulo(JTextField textField,boolean mostrarDisponible) {
    	    Session session = sessionFactory.openSession();
    	    String[][] M_datos = null;
    	    String countHql,hql;
    	    try {
    	        session.beginTransaction();

    	        String codigoTexto = textField.getText().trim();
    	        
    	        // Verificar si el tamaño del texto es 0
    	        if (codigoTexto.length() == 0) {
    	            // Cargar todos los datos
    	            M_datos = cargarDatosArticulo(queryContarArticulo(mostrarDisponible).size(), mostrarDisponible);
    	        } else if (codigoTexto.matches("\\d+")) {
    	            int codigo = Integer.parseInt(codigoTexto);
                    
    	            if (mostrarDisponible) {
    	            countHql = "SELECT COUNT(*) FROM Articulo a WHERE CAST(a.id.codArticulo AS STRING) LIKE :codigo AND disponible = true";
    	            }
    	            else {
    	            countHql = "SELECT COUNT(*) FROM Articulo a WHERE CAST(a.id.codArticulo AS STRING) LIKE :codigo";
    	            }
    	            Query<Long> countQuery = session.createQuery(countHql, Long.class);
    	            countQuery.setParameter("codigo", codigo + "%");
    	            long valor = countQuery.getSingleResult();

    	            if (mostrarDisponible) {
    	            hql = "FROM Articulo a WHERE CAST(a.id.codArticulo AS STRING) LIKE :codigo AND disponible = true";
    	            }
    	            else {
    	            hql = "FROM Articulo a WHERE CAST(a.id.codArticulo AS STRING) LIKE :codigo";
    	            }
    	            Query<Articulo> query = session.createQuery(hql, Articulo.class);
    	            query.setParameter("codigo",codigo + "%");

    	            List<Articulo> articulos = query.getResultList();
    	            M_datos = new String[(int) valor][6];

    	            int cont = 0;
    	            for (Articulo articulo : articulos) {
    	                M_datos[cont][0] = String.valueOf(articulo.getId());
    	                M_datos[cont][1] = String.valueOf(articulo.getTalle());
    	                M_datos[cont][2] = String.valueOf(articulo.getStock());
    	                M_datos[cont][3] = articulo.getDescripcion().toUpperCase();
    	                M_datos[cont][4] = String.valueOf(articulo.getPrecio());
    	                M_datos[cont][5] = String.valueOf(articulo.isDisponible());
    	                cont++;
    	            }
    	        }
    	        session.getTransaction().commit();
    	    } catch (Exception e) {
    	        if (session != null) {
    	            session.getTransaction().rollback();
    	        }
    	        e.printStackTrace();
    	    } finally {
    	        if (session != null) {
    	            session.close();
    	        }
    	    }
    	    return M_datos;
    	}

       public Articulo getArticuloPorCodigoYTalle(Integer codigo,Integer talle) {
    	   Session session = sessionFactory.openSession();
   	    try {
   	        String hql = "FROM Articulo a WHERE a.id.codArticulo = :idArticulo AND a.id.talle = :talle";
   	        Query<Articulo> query = session.createQuery(hql);
   	        query.setParameter("idArticulo", codigo);
   	        query.setParameter("talle", talle);
   	        return query.getSingleResult();
   	    } catch (Exception e) {
   	        e.printStackTrace();
   	        return null;
   	    } finally {
   	        session.close();
   	    }
    
       }

       public Integer cantidadTalles(Integer codigo) {
    	   Session session = sessionFactory.openSession();
      	    try {
      	        String hql = "SELECT COUNT(*) FROM Articulo a WHERE a.id.codArticulo = :idArticulo";
      	        Query<Integer> query = session.createQuery(hql);
      	        query.setParameter("idArticulo", codigo);
      	        return Integer.parseInt(query.getSingleResult().toString());
      	    } catch (Exception e) {
      	        e.printStackTrace();
      	        return null;
      	    } finally {
      	        session.close();
      	    }
       }
       
       public void updateArticulo(int codigoArticulo, int talle, int nuevoStock, double nuevoPrecio, String nuevaDescripcion) {
    	    Session session = sessionFactory.openSession();

    	    try {
    	        session.beginTransaction();
    	        // Hacer una consulta para encontrar el artículo por código y talle
    	        Query<Articulo> query = session.createQuery("FROM Articulo WHERE id.codArticulo = :codigoArticulo AND id.talle = :talle", Articulo.class);
    	        query.setParameter("codigoArticulo", codigoArticulo);
    	        query.setParameter("talle", talle);
    	        
    	        // Obtener el resultado de la consulta
    	        Articulo articulo = query.uniqueResult();

    	        if (articulo != null) {
    	            // Actualizar los atributos del artículo
    	            articulo.setStock(nuevoStock);
    	            articulo.setPrecio(nuevoPrecio);
    	            articulo.setDescripcion(nuevaDescripcion);
    	            
    	            // Actualizar el artículo en la base de datos
    	            session.update(articulo);
    	            session.getTransaction().commit();
    	        } else {
    	            System.out.println("Artículo no encontrado con código " + codigoArticulo + " y talle " + talle);
    	        }
    	    } catch (Exception e) {
    	        if (session.getTransaction() != null) {
    	            session.getTransaction().rollback();
    	        }
    	        e.printStackTrace();
    	    } finally {
    	        session.close();
    	    }
    	}

}
