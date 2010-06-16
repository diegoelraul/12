package net.kielsaenz.consultorio.dao.hibernate;

import net.kielsaenz.consultorio.dao.UsuarioDao;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import net.kielsaenz.consultorio.model.Usuario;

/**
 * Esta clase contiene los métodos de persistencia implementados
 * @author dew - Grupo 04
 */
public class UsuarioDaoHbn implements UsuarioDao {

    /**
     * Obtiene un usuario por su username
     * @param username nombre de usuario
     * @return Usuario datos del usuario
     */
    public Usuario getUsuarioPorUsername(String username) {
        if (username == null) {
            return null;
        }
        Usuario usuario = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = HibernateUtil.getSessionFactory();
        try {
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Usuario u where u.username = :username");
            hqlQuery.setParameter("username", username, Hibernate.STRING);
            usuario = (Usuario)hqlQuery.uniqueResult();
            htx.commit();
        } catch (HibernateException e) {
            if(htx != null && htx.isActive()){
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    System.out.println("No se pudo realizar el rollback...");
                }
            }
            e.printStackTrace();
        }
        return usuario;
    }
}
