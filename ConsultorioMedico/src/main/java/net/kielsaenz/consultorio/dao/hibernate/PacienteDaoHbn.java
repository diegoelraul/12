package net.kielsaenz.consultorio.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.PacienteDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Paciente;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kiel
 */
public class PacienteDaoHbn implements PacienteDao {

    private Locale locale;

    public PacienteDaoHbn() {
        this(Locale.getDefault());
    }

    public PacienteDaoHbn(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Paciente> getPacientes() throws DaoException {
        List<Paciente> pacientes = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Paciente");
            pacientes = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            pacientes = new ArrayList<Paciente>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return pacientes;
    }

    public Paciente getPacientePorId(Integer pacienteId) throws DaoException {
        if (pacienteId == null) {
            throw new DaoException("consultorio.dao.error.1900", locale);
        }
        Paciente paciente = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            paciente = (Paciente) hs.get(Paciente.class, pacienteId);
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return paciente;
    }

    public List<Paciente> getPacientesPorEmpresa(Empresa empresa) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getPacientesPorEmpresa(empresa.getEmpresaId());
    }

    public List<Paciente> getPacientesPorEmpresa(Integer empresaId) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        List<Paciente> pacientes = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Paciente p where p.empresa = :empresaId");
            hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
            pacientes = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            pacientes = new ArrayList<Paciente>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return pacientes;
    }

    public List<Paciente> getPacientesPorEmpresaNombre(Empresa empresa,
            String nombre, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getPacientesPorEmpresaNombre(empresa.getEmpresaId(), nombre, aplicarLike);
    }

    public List<Paciente> getPacientesPorEmpresaNombre(Integer empresaId,
            String nombre, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1902", locale);
        }
        List<Paciente> pacientes = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        nombre = nombre.toUpperCase(locale);
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = null;
            StringBuilder query = new StringBuilder();
            query.append("select p from Paciente p where p.empresa = :empresaId and ");
            query.append("UPPER(p.nombres) ");

            if (aplicarLike) {
                query.append("like :nombres");
            } else {
                query.append("= :nombres");
            }

            hqlQuery = hs.createQuery(query.toString());

            if (aplicarLike) {
                StringBuffer param01 = new StringBuffer("%").append(nombre).append("%");
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("nombres", param01.toString(), Hibernate.STRING);
            } else {
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("nombres", nombre, Hibernate.STRING);
            }

            pacientes = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            pacientes = new ArrayList<Paciente>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return pacientes;
    }

    public List<Paciente> getPacientesPorEmpresaApPaterno(Empresa empresa,
            String apellidoPaterno, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getPacientesPorEmpresaApPaterno(empresa.getEmpresaId(), apellidoPaterno, aplicarLike);
    }

    public List<Paciente> getPacientesPorEmpresaApPaterno(Integer empresaId,
            String apellidoPaterno, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1903", locale);
        }
        List<Paciente> pacientes = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        apellidoPaterno = apellidoPaterno.toUpperCase(locale);
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = null;
            StringBuilder query = new StringBuilder();
            query.append("select p from Paciente p where p.empresa = :empresaId and ");
            query.append("UPPER(p.apellidoPaterno) ");

            if (aplicarLike) {
                query.append("like :apellidoPaterno");
            } else {
                query.append("= :apellidoPaterno");
            }

            hqlQuery = hs.createQuery(query.toString());

            if (aplicarLike) {
                StringBuffer param01 = new StringBuffer("%").append(
                        apellidoPaterno).append("%");
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("apellidoPaterno", param01.toString(),
                        Hibernate.STRING);
            } else {
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("apellidoPaterno", apellidoPaterno,
                        Hibernate.STRING);
            }

            pacientes = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            pacientes = new ArrayList<Paciente>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return pacientes;
    }

    public List<Paciente> getPacientesPorEmpresaApMaterno(Empresa empresa,
            String apellidoMaterno, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getPacientesPorEmpresaApMaterno(empresa.getEmpresaId(), apellidoMaterno, aplicarLike);
    }

    public List<Paciente> getPacientesPorEmpresaApMaterno(Integer empresaId,
            String apellidoMaterno, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1904", locale);
        }
        List<Paciente> pacientes = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        apellidoMaterno = apellidoMaterno.toUpperCase(locale);
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = null;
            StringBuilder query = new StringBuilder();
            query.append("select p from Paciente p where p.empresa = :empresaId and ");
            query.append("UPPER(p.apellidoMaterno) ");

            if (aplicarLike) {
                query.append("like :apellidoMaterno");
            } else {
                query.append("= :apellidoMaterno");
            }

            hqlQuery = hs.createQuery(query.toString());

            if (aplicarLike) {
                StringBuffer param01 = new StringBuffer("%").append(apellidoMaterno).append("%");
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("apellidoMaterno", param01.toString(), Hibernate.STRING);
            } else {
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("apellidoMaterno", apellidoMaterno, Hibernate.STRING);
            }

            pacientes = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            pacientes = new ArrayList<Paciente>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return pacientes;
    }

    public List<Paciente> getPacientesPorEmpresaNroDocumento(Empresa empresa,
            String nroDocumento, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getPacientesPorEmpresaNroDocumento(empresa.getEmpresaId(), nroDocumento, aplicarLike);
    }

    public List<Paciente> getPacientesPorEmpresaNroDocumento(Integer empresaId,
            String nroDocumento, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (nroDocumento == null || nroDocumento.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1905", locale);
        }
        List<Paciente> pacientes = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        //nroDocumento = nroDocumento.toUpperCase(locale);
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = null;
            StringBuilder query = new StringBuilder();
            query.append("select p from Paciente p where p.empresa = :empresaId and ");
            query.append("UPPER(p.nroDocumento) ");

            if (aplicarLike) {
                query.append("like :nroDocumento");
            } else {
                query.append("= :nroDocumento");
            }

            hqlQuery = hs.createQuery(query.toString());

            if (aplicarLike) {
                StringBuffer param01 = new StringBuffer("%").append(nroDocumento).append("%");
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("nroDocumento", param01.toString(), Hibernate.STRING);
            } else {
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("nroDocumento", nroDocumento, Hibernate.STRING);
            }

            pacientes = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            pacientes = new ArrayList<Paciente>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return pacientes;
    }

    public boolean insertar(Paciente paciente) throws DaoException {
        if (paciente == null) {
            throw new DaoException("consultorio.dao.error.1901", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Integer pacienteId = (Integer) hs.save(paciente);
            paciente.setPersonaId(pacienteId);
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return true;
    }

    public boolean eliminar(Paciente paciente) throws DaoException {
        if (paciente == null) {
            throw new DaoException("consultorio.dao.error.1901", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = HibernateUtil.getSessionFactory();
        try {
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.delete(paciente);
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return true;
    }

    public boolean actualizar(Paciente paciente) throws DaoException {
        if (paciente == null) {
            throw new DaoException("consultorio.dao.error.1901", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.update(paciente);
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return true;
    }
}
