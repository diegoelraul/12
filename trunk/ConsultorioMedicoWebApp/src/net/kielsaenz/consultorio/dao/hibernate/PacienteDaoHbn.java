package net.kielsaenz.consultorio.dao.hibernate;

import net.kielsaenz.consultorio.dao.PacienteDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import net.kielsaenz.consultorio.model.Paciente;

/**
 * Esta clase contiene los métodos de persistencia implementados
 * @author dew - Grupo 04
 */
public class PacienteDaoHbn implements PacienteDao {

    /**
     * Obtiene un Paciente por su Id
     * @param pacienteId identificador del paciente
     * @return Paciente datos del paciente
     */
    public Paciente getPacientePorId(Integer pacienteId) {
        if (pacienteId == null) {
            return null;
        }
        Paciente paciente = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = HibernateUtil.getSessionFactory();
        try {
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            paciente = (Paciente)hs.get(Paciente.class, pacienteId);
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
        return paciente;
    }
}
