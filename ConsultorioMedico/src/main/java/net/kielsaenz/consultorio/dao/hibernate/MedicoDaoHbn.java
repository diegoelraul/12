package net.kielsaenz.consultorio.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.MedicoDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Medico;
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
public class MedicoDaoHbn implements MedicoDao{

    private Locale locale;

    public MedicoDaoHbn() {
        this(Locale.getDefault());
    }

    public MedicoDaoHbn(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Medico> getMedicos() throws DaoException {
        List<Medico> medicos = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Medico");
            medicos = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            medicos = new ArrayList<Medico>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return medicos;
    }

    public Medico getMedicoPorId(Integer medicoId) throws DaoException {
        if (medicoId == null) {
            throw new DaoException("consultorio.dao.error.1400", locale);
        }
        Medico medico = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            medico = (Medico) hs.get(Medico.class, medicoId);
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
        return medico;
    }

    public List<Medico> getMedicosPorEmpresa(Empresa empresa) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getMedicosPorEmpresa(empresa.getEmpresaId());
    }

    public List<Medico> getMedicosPorEmpresa(Integer empresaId) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        List<Medico> medicos = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Medico p where p.empresa = :empresaId");
            hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
            medicos = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            medicos = new ArrayList<Medico>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return medicos;
    }

    public List<Medico> getMedicosPorEmpresaNombre(Empresa empresa,
            String nombre, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getMedicosPorEmpresaNombre(empresa.getEmpresaId(), nombre, aplicarLike);
    }

    public List<Medico> getMedicosPorEmpresaNombre(Integer empresaId,
            String nombre, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1402", locale);
        }
        List<Medico> medicos = null;
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
            query.append("select m from Medico m where m.empresa = :empresaId and ");
            query.append("UPPER(m.nombres) ");

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

            medicos = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            medicos = new ArrayList<Medico>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return medicos;
    }

    public List<Medico> getMedicosPorEmpresaApPaterno(Empresa empresa,
            String apellidoPaterno, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getMedicosPorEmpresaApPaterno(empresa.getEmpresaId(), apellidoPaterno, aplicarLike);
    }

    public List<Medico> getMedicosPorEmpresaApPaterno(Integer empresaId,
            String apellidoPaterno, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (apellidoPaterno == null || apellidoPaterno.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1403", locale);
        }
        List<Medico> medicos = null;
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
            query.append("select m from Medico m where m.empresa = :empresaId and ");
            query.append("UPPER(m.apellidoPaterno) ");

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

            medicos = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            medicos = new ArrayList<Medico>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return medicos;
    }

    public List<Medico> getMedicosPorEmpresaApMaterno(Empresa empresa,
            String apellidoMaterno, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getMedicosPorEmpresaApMaterno(empresa.getEmpresaId(), apellidoMaterno, aplicarLike);
    }

    public List<Medico> getMedicosPorEmpresaApMaterno(Integer empresaId,
            String apellidoMaterno, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (apellidoMaterno == null || apellidoMaterno.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1404", locale);
        }
        List<Medico> medicos = null;
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
            query.append("select m from Medico m where m.empresa = :empresaId and ");
            query.append("UPPER(m.apellidoMaterno) ");

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

            medicos = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            medicos = new ArrayList<Medico>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return medicos;
    }

    public List<Medico> getMedicosPorEmpresaNroDocumento(Empresa empresa,
            String nroDocumento, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getMedicosPorEmpresaNroDocumento(empresa.getEmpresaId(), nroDocumento, aplicarLike);
    }

    public List<Medico> getMedicosPorEmpresaNroDocumento(Integer empresaId,
            String nroDocumento, boolean aplicarLike) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (nroDocumento == null || nroDocumento.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1405", locale);
        }
        List<Medico> medicos = null;
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
            query.append("select m from Medico m where m.empresa = :empresaId and ");
            query.append("UPPER(m.nroDocumento) ");

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

            medicos = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            medicos = new ArrayList<Medico>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return medicos;
    }

    public List<Medico> getMedicosPorEmpresaNroColegiatura(Empresa empresa,
            String nroColegiatura, boolean aplicarLike) throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getMedicosPorEmpresaNroColegiatura(empresa.getEmpresaId(), nroColegiatura, aplicarLike);
    }

    public List<Medico> getMedicosPorEmpresaNroColegiatura(Integer empresaId,
            String nroColegiatura, boolean aplicarLike) throws DaoException {
                if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (nroColegiatura == null || nroColegiatura.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1406", locale);
        }
        List<Medico> medicos = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        //nroColegiatura = nroColegiatura.toUpperCase(locale);
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = null;
            StringBuilder query = new StringBuilder();
            query.append("select m from Medico m where m.empresa = :empresaId and ");
            query.append("UPPER(m.nroColegiatura) ");

            if (aplicarLike) {
                query.append("like :nroColegiatura");
            } else {
                query.append("= :nroColegiatura");
            }

            hqlQuery = hs.createQuery(query.toString());

            if (aplicarLike) {
                StringBuffer param01 = new StringBuffer("%").append(nroColegiatura).append("%");
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("nroColegiatura", param01.toString(), Hibernate.STRING);
            } else {
                hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
                hqlQuery.setParameter("nroColegiatura", nroColegiatura, Hibernate.STRING);
            }

            medicos = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            medicos = new ArrayList<Medico>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return medicos;
    }

    public boolean insertar(Medico medico) throws DaoException {
        if (medico == null) {
            throw new DaoException("consultorio.dao.error.1401", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Integer medicoId = (Integer) hs.save(medico);
            medico.setPersonaId(medicoId);
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

    public boolean eliminar(Medico medico) throws DaoException {
        if (medico == null) {
            throw new DaoException("consultorio.dao.error.1401", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = HibernateUtil.getSessionFactory();
        try {
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.delete(medico);
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

    public boolean actualizar(Medico medico) throws DaoException {
        if (medico == null) {
            throw new DaoException("consultorio.dao.error.1401", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.update(medico);
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
