package net.kielsaenz.consultorio.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.kielsaenz.consultorio.dao.UbigeoDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Departamento;
import net.kielsaenz.consultorio.model.Distrito;
import net.kielsaenz.consultorio.model.Provincia;

public class UbigeoDaoHbn implements UbigeoDao {

    private Locale locale;

    public UbigeoDaoHbn() {
        this(Locale.getDefault());
    }

    public UbigeoDaoHbn(Locale locale) {
        this.locale = locale;
    }

    @Override
    public List<Departamento> getDepartamentos() throws DaoException {
        List<Departamento> departamentos = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            departamentos = hs.getNamedQuery("Departamento.getDepartamentos").list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            departamentos = new ArrayList<Departamento>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return departamentos;
    }

    @Override
    public List<Distrito> getDistritosPorProvincia(String departamentoId,
            String provinciaId) throws DaoException {
        if (departamentoId == null || departamentoId.isEmpty()
                || departamentoId.length() != 2) {
            throw new DaoException("consultorio.dao.error.1700", locale);
        }
        if (provinciaId == null || provinciaId.isEmpty()
                || provinciaId.length() != 2) {
            throw new DaoException("consultorio.dao.error.1701", locale);
        }
        List<Distrito> distritos = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            distritos = hs.getNamedQuery("Distrito.getDistritosPorProvincia").setString("departamentoId", departamentoId).setString(
                    "provinciaId", provinciaId).list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            distritos = new ArrayList<Distrito>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return distritos;
    }

    @Override
    public List<Provincia> getProvinciasPorDepartamento(String departamentoId)
            throws DaoException {
        if (departamentoId == null || departamentoId.isEmpty()
                || departamentoId.length() != 2) {
            throw new DaoException("consultorio.dao.error.1700", locale);
        }
        List<Provincia> provincias = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            provincias = hs.getNamedQuery(
                    "Provincia.getProvinciasPorDepartamento").setString(
                    "departamentoId", departamentoId).list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            provincias = new ArrayList<Provincia>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return provincias;
    }

    @Override
    public Departamento getDepartamentoPorId(String departamentoId)
            throws DaoException {
        if (departamentoId == null || departamentoId.isEmpty()
                || departamentoId.length() != 2) {
            throw new DaoException("consultorio.dao.error.1700", locale);
        }
        Departamento departamento = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            departamento = (Departamento) hs.getNamedQuery(
                    "Departamento.getDepartamentoPorId").setString(
                    "departamentoId", departamentoId).uniqueResult();
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
        return departamento;
    }

    @Override
    public Distrito getDistritoPorId(String departamentoId, String provinciaId,
            String distritoId) throws DaoException {
        if (departamentoId == null || departamentoId.isEmpty()
                || departamentoId.length() != 2) {
            throw new DaoException("consultorio.dao.error.1700", locale);
        }
        if (provinciaId == null || provinciaId.isEmpty()
                || provinciaId.length() != 2) {
            throw new DaoException("consultorio.dao.error.1701", locale);
        }
        if (distritoId == null || distritoId.isEmpty()
                || distritoId.length() != 2) {
            throw new DaoException("consultorio.dao.error.1702", locale);
        }
        Distrito distrito = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            distrito = (Distrito) hs.getNamedQuery("Distrito.getDistritoPorId").setString("departamentoId", departamentoId).setString(
                    "provinciaId", provinciaId).setString(
                    "distritoId", distritoId).uniqueResult();
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
        return distrito;
    }

    @Override
    public List<Distrito> getDistritosPorProvincia(Provincia provincia)
            throws DaoException {
        if (provincia == null) {
            throw new DaoException("consultorio.dao.error.1703", locale);
        }
        return getDistritosPorProvincia(provincia.getDepartamentoId(),
                provincia.getProvinciaId());
    }

    @Override
    public Provincia getProvinciaPorId(String departamentoId, String provinciaId)
            throws DaoException {
        if (departamentoId == null || departamentoId.isEmpty()
                || departamentoId.length() != 2) {
            throw new DaoException("consultorio.dao.error.1700", locale);
        }
        if (provinciaId == null || provinciaId.isEmpty()
                || provinciaId.length() != 2) {
            throw new DaoException("consultorio.dao.error.1701", locale);
        }
        Provincia provincia = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            provincia = (Provincia) hs.getNamedQuery(
                    "Provincia.getProvinciaPorId").setString("departamentoId",
                    departamentoId).setString("provinciaId", provinciaId).uniqueResult();
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
        return provincia;
    }

    @Override
    public List<Provincia> getProvinciasPorDepartamento(
            Departamento departamento) throws DaoException {
        if (departamento == null) {
            throw new DaoException("consultorio.dao.error.1704", locale);
        }
        return getProvinciasPorDepartamento(departamento.getDepartamentoId());
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
