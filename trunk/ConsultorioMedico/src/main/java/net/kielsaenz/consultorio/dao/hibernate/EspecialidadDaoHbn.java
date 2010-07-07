package net.kielsaenz.consultorio.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.EspecialidadDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Especialidad;
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
public class EspecialidadDaoHbn implements EspecialidadDao{

    private Locale locale;

    public EspecialidadDaoHbn() {
        this(Locale.getDefault());
    }

    public EspecialidadDaoHbn(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Especialidad> getEspecialidades() throws DaoException {
        List<Especialidad> especialidades = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Especialidad e order by e.empresa.empresaId, e.nombre asc");
            especialidades = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            especialidades = new ArrayList<Especialidad>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return especialidades;
    }

    public Especialidad getEspecialidadPorId(Integer especialidadId) throws DaoException {
        if (especialidadId == null) {
            throw new DaoException("consultorio.dao.error.1500", locale);
        }
        Especialidad especialidad = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            especialidad = (Especialidad) hs.get(Especialidad.class, especialidadId);
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
        return especialidad;
    }

    public List<Especialidad> getEspecialidadesPorEmpresa(Empresa empresa) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getEspecialidadesPorEmpresa(empresa.getEmpresaId());
    }

    public List<Especialidad> getEspecialidadesPorEmpresa(Integer empresaId) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        List<Especialidad> especialidades = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Especialidad e where e.empresa = :empresaId order by e.nombre asc");
            hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
            especialidades = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            especialidades = new ArrayList<Especialidad>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return especialidades;
    }

    public List<Especialidad> getEspecialidadesPorEmpresaNombre(Empresa empresa,
            String nombre, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getEspecialidadesPorEmpresaNombre(empresa.getEmpresaId(), nombre, aplicarLike);
    }

    public List<Especialidad> getEspecialidadesPorEmpresaNombre(Integer empresaId,
            String nombre, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1502", locale);
        }
        List<Especialidad> especialidades = null;
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
            query.append("select e from Especialidad e where e.empresa = :empresaId and ");
            query.append("e.nombre ");

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

            especialidades = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            especialidades = new ArrayList<Especialidad>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return especialidades;
    }

    public boolean insertar(Especialidad especialidad) throws DaoException {
        if (especialidad == null) {
            throw new DaoException("consultorio.dao.error.1501", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Integer especialidadId = (Integer) hs.save(especialidad);
            especialidad.setEspecialidadId(especialidadId);
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

    public boolean eliminar(Especialidad especialidad) throws DaoException {
        if (especialidad == null) {
            throw new DaoException("consultorio.dao.error.1501", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = HibernateUtil.getSessionFactory();
        try {
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.delete(especialidad);
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

    public boolean actualizar(Especialidad especialidad) throws DaoException {
        if (especialidad == null) {
            throw new DaoException("consultorio.dao.error.1501", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.update(especialidad);
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
