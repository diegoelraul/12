package net.kielsaenz.consultorio.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import net.kielsaenz.consultorio.dao.EspecialidadDao;
import net.kielsaenz.consultorio.model.Especialidad;

/**
 * Esta clase contiene los métodos de persistencia implementados
 * @author dew - Grupo 04
 */
public class EspecialidadDaoHbn implements EspecialidadDao {

    /**
     * Obtiene todas las especialidades registradas
     * @return List<Especialidad> lista de especialidad
     */
    public List<Especialidad> getEspecialidades() {
        List<Especialidad> especialidades = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = HibernateUtil.getSessionFactory();
         try {
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Especialidad e order by e.nombre asc");
            especialidades = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if(htx != null && htx.isActive()){
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    System.out.println("No se pudo realizar el rollback...");
                }
            }
            especialidades = new ArrayList<Especialidad>();
            e.printStackTrace();
        }
        return especialidades;
    }

    /**
     * Obtiene una especialidad por su id
     * @param especialidadId identificador de especialidad
     * @return Especialidad datos de la especialidad
     */
    public Especialidad getEspecialidadPorId(Integer especialidadId) {
        if (especialidadId == null) {
            return null;
        }
        Especialidad especialidad = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = HibernateUtil.getSessionFactory();
        try {
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            especialidad = (Especialidad)hs.get(Especialidad.class, especialidadId);
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
        return especialidad;
    }
}
