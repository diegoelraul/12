package net.kielsaenz.consultorio.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.PersonaDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Persona;
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
public class PersonaDaoHbn implements PersonaDao {

    private Locale locale;

    public PersonaDaoHbn() {
        this(Locale.getDefault());
    }

    public PersonaDaoHbn(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Persona> getPersonas() throws DaoException {
        List<Persona> personas = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Persona");
            personas = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            personas = new ArrayList<Persona>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return personas;
    }

    public Persona getPersonaPorId(Integer personaId) throws DaoException {
        if (personaId == null) {
            throw new DaoException("consultorio.dao.error.1300", locale);
        }
        Persona persona = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            persona = (Persona) hs.get(Persona.class, personaId);
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
        return persona;
    }

    public List<Persona> getPersonasPorEmpresa(Empresa empresa) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getPersonasPorEmpresa(empresa.getEmpresaId());
    }

    public List<Persona> getPersonasPorEmpresa(Integer empresaId) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        List<Persona> personas = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Persona p where p.empresa = :empresaId");
            hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
            personas = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            personas = new ArrayList<Persona>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return personas;
    }

    public List<Persona> getPersonasPorEmpresaNombre(Empresa empresa,
            String nombre, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1302", locale);
        }
        return getPersonasPorEmpresaNombre(empresa.getEmpresaId(), nombre, aplicarLike);
    }

    public List<Persona> getPersonasPorEmpresaNombre(Integer empresaId,
            String nombre, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1302", locale);
        }
        List<Persona> personas = null;
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
            query.append("select p from Persona p where p.empresa = :empresaId and ");
            query.append("UPPER(p.nombre) ");

            if (aplicarLike) {
                query.append("like :nombre");
            } else {
                query.append("= :nombre");
            }

            hqlQuery = hs.createQuery(query.toString());

            if (aplicarLike) {
                StringBuffer param01 = new StringBuffer("%").append(nombre).append("%");
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("nombre", param01.toString(), Hibernate.STRING);
            } else {
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("nombre", nombre, Hibernate.STRING);
            }

            personas = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            personas = new ArrayList<Persona>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return personas;
    }

    public List<Persona> getPersonasPorEmpresaApPaterno(Empresa empresa,
            String apellidoPaterno, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1303", locale);
        }
        return getPersonasPorEmpresaApPaterno(empresa.getEmpresaId(), apellidoPaterno, aplicarLike);
    }

    public List<Persona> getPersonasPorEmpresaApPaterno(Integer empresaId,
            String apellidoPaterno, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1303", locale);
        }
        List<Persona> personas = null;
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
            query.append("select p from Persona p where p.empresa = :empresaId and ");
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

            personas = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            personas = new ArrayList<Persona>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return personas;
    }

    public List<Persona> getPersonasPorEmpresaApMaterno(Empresa empresa,
            String apellidoMaterno, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1304", locale);
        }
        return getPersonasPorEmpresaApMaterno(empresa.getEmpresaId(), apellidoMaterno, aplicarLike);
    }

    public List<Persona> getPersonasPorEmpresaApMaterno(Integer empresaId,
            String apellidoMaterno, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1304", locale);
        }
        List<Persona> personas = null;
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
            query.append("select p from Persona p where p.empresa = :empresaId and ");
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

            personas = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            personas = new ArrayList<Persona>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return personas;
    }

    public List<Persona> getPersonasPorEmpresaNroDocumento(Empresa empresa,
            String nroDocumento, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        if (nroDocumento == null || nroDocumento.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1305", locale);
        }
        return getPersonasPorEmpresaNroDocumento(empresa.getEmpresaId(), nroDocumento, aplicarLike);
    }

    public List<Persona> getPersonasPorEmpresaNroDocumento(Integer empresaId,
            String nroDocumento, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (nroDocumento == null || nroDocumento.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1305", locale);
        }
        List<Persona> personas = null;
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
            query.append("select p from Persona p where p.empresa = :empresaId and ");
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

            personas = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            personas = new ArrayList<Persona>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return personas;
    }
}
