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

import net.kielsaenz.consultorio.dao.UsuarioDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Usuario;

public class UsuarioDaoHbn implements UsuarioDao {

    private Locale locale;

    public UsuarioDaoHbn() {
        this(Locale.getDefault());
    }

    public UsuarioDaoHbn(Locale locale) {
        super();
        this.locale = locale;
    }

    @Override
    public boolean actualizar(Usuario usuario) throws DaoException {
        if (usuario == null) {
            throw new DaoException("consultorio.dao.error.1801", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.update(usuario);
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
    public boolean eliminar(Usuario usuario) throws DaoException {
        if (usuario == null) {
            throw new DaoException("consultorio.dao.error.1801", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = HibernateUtil.getSessionFactory();
        try {
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            hs.delete(usuario);
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
    public Usuario getUsuarioPorEmpresaUsername(Empresa empresa, String username)
            throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        if (username == null || username.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1802", locale);
        }
        return getUsuarioPorEmpresaUsername(empresa.getEmpresaId(), username);
    }

    @Override
    public Usuario getUsuarioPorEmpresaUsername(Integer empresaId,
            String username) throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        if (username == null || username.isEmpty()) {
            throw new DaoException("consultorio.dao.error.1802", locale);
        }
        Usuario usuario = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Usuario u where u.empresa = :empresaId and u.username = :username");
            hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
            hqlQuery.setParameter("username", username, Hibernate.STRING);
            usuario = (Usuario) hqlQuery.uniqueResult();
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
        return usuario;
    }

    @Override
    public Usuario getUsuarioPorId(Integer usuarioId) throws DaoException {
        if (usuarioId == null) {
            throw new DaoException("consultorio.dao.error.1800", locale);
        }
        Usuario usuario = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            usuario = (Usuario) hs.get(Usuario.class, usuarioId);
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
        return usuario;
    }

    public List<Usuario> getUsuarios() throws DaoException {
        List<Usuario> usuarios = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Usuario u order by u.empresa.empresaId, u.username asc");
            usuarios = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            usuarios = new ArrayList<Usuario>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return usuarios;
    }

    @Override
    public List<Usuario> getUsuariosPorEmpresa(Empresa empresa)
            throws DaoException {
        if (empresa == null) {
            throw new DaoException("consultorio.dao.error.1103", locale);
        }
        return getUsuariosPorEmpresa(empresa.getEmpresaId());
    }

    @Override
    public List<Usuario> getUsuariosPorEmpresa(Integer empresaId)
            throws DaoException {
        if (empresaId == null) {
            throw new DaoException("consultorio.dao.error.1100", locale);
        }
        List<Usuario> usuarios = null;
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Usuario u where u.empresa = :empresaId order by u.username asc");
            hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
            usuarios = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if (htx != null && htx.isActive()) {
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    throw new DaoException("consultorio.dao.error.0001", locale);
                }
            }
            usuarios = new ArrayList<Usuario>();
            e.printStackTrace();
            throw new DaoException(e.hashCode(), e.getMessage());
        }
        return usuarios;
    }

    @Override
    public boolean insertar(Usuario usuario) throws DaoException {
        if (usuario == null) {
            throw new DaoException("consultorio.dao.error.1801", locale);
        }
        Session hs = null;
        Transaction htx = null;
        SessionFactory hsf = null;
        try {
            hsf = HibernateUtil.getSessionFactory();
            hs = hsf.getCurrentSession();
            htx = hs.beginTransaction();
            Integer usuarioId = (Integer) hs.save(usuario);
            usuario.setUsuarioId(usuarioId);
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
