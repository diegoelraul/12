package net.kielsaenz.consultorio.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;

public class EmpresaDaoHbn implements EmpresaDao {

    private Locale locale;

    public EmpresaDaoHbn() {
        this(Locale.getDefault());
    }

    public EmpresaDaoHbn(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Empresa getEmpresaPorId(Integer empresaId) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        Empresa empresa = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            empresa = (Empresa) hs.get(Empresa.class, empresaId);
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
        return empresa;
    }

    @Override
    public Empresa getEmpresaPorRUC(String ruc) throws DaoException {
        if (ruc == null || ruc.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1101", locale);
        }
        Empresa empresa = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("select e from Empresa e where e.ruc = :ruc");
            hqlQuery.setParameter("ruc", ruc, Hibernate.STRING);
            empresa = (Empresa) hqlQuery.uniqueResult();
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
        return empresa;
    }

    @Override
    public List<Empresa> getEmpresaPorRazonSocial(String razonSocial,
            boolean aplicarLike) throws DaoException {
        if (razonSocial == null) {
            throw new DaoException("consultorio.dao.error.1102", locale);
        }
        List<Empresa> empresas = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        razonSocial = razonSocial.toUpperCase(locale);
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = null;
            StringBuilder query = new StringBuilder();
            query.append("select e from Empresa e where ");
            query.append("UPPER(e.razonSocial) ");
            if (aplicarLike) {
                query.append("like :razonSocial");
            } else {
                query.append("= :razonSocial");
            }

            hqlQuery = hs.createQuery(query.toString());

            if (aplicarLike) {
                StringBuffer param01 = new StringBuffer("%").append(razonSocial).append("%");
                hqlQuery.setParameter("razonSocial", param01.toString(), Hibernate.STRING);
            } else {
                hqlQuery.setParameter("razonSocial", razonSocial, Hibernate.STRING);
            }

            empresas = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            empresas = new ArrayList<Empresa>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return empresas;
    }

    @Override
    public List<Empresa> getEmpresas() throws DaoException {
        List<Empresa> empresas = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Empresa e order by e.razonSocial asc");
            empresas = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            empresas = new ArrayList<Empresa>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return empresas;
    }

    @Override
    public boolean insertar(Empresa empresa) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Integer empresaId = (Integer) hs.save(empresa);
            empresa.setEmpresaId(empresaId);
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

    @Override
    public boolean actualizar(Empresa empresa) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.update(empresa);
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

    @Override
    public boolean eliminar(Empresa empresa) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = HibernateUtil.getSessionFactory();
        try {
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.delete(empresa);
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

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
