package net.kielsaenz.consultorio.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.kielsaenz.consultorio.dao.SucursalDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Sucursal;
import net.kielsaenz.consultorio.model.SucursalId;

public class SucursalDaoHbn implements SucursalDao{

	private Locale locale;
	
	public SucursalDaoHbn(){
		this(Locale.getDefault());
	}
	
	public SucursalDaoHbn(Locale locale){
		this.locale = locale;
	}

	@Override
	public boolean actualizar(Sucursal sucursal) throws DaoException {
		if (sucursal == null) {
			throw new DaoException("consultorio.dao.error.1201", locale);
		}
		Session hs = null;
		Transaction htx = null;
		SessionFactory hsf = null;
		try {
			hsf = HibernateUtil.getSessionFactory();
			hs = hsf.getCurrentSession();
			htx = hs.beginTransaction();
			hs.update(sucursal);
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
	public boolean eliminar(Sucursal sucursal) throws DaoException {
		if (sucursal == null) {
			throw new DaoException("consultorio.dao.error.1201", locale);
		}
		Session hs = null;
		Transaction htx = null;
		SessionFactory hsf = HibernateUtil.getSessionFactory();
		try {
			hs = hsf.getCurrentSession();
			htx = hs.beginTransaction();
			hs.delete(sucursal);
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
	public Sucursal getSucursalPorId(SucursalId sucursalId) throws DaoException {
		if (sucursalId == null) {
			throw new DaoException("consultorio.dao.error.1200", locale);
		}
		Sucursal sucursal = null;
		Session hs = null;
		Transaction htx = null;
		SessionFactory hsf = null;
		try {
			hsf = HibernateUtil.getSessionFactory();
			hs = hsf.getCurrentSession();
			htx = hs.beginTransaction();
			sucursal = (Sucursal) hs.get(Sucursal.class, sucursalId);
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
		return sucursal;
	}

	@Override
	public Sucursal getSucursalPorId(Integer empresaId, Integer sucursalId)
			throws DaoException {
		if(empresaId == null){
			throw new DaoException("consultorio.dao.error.1100", locale);
		}
		if(sucursalId == null){
			throw new DaoException("consultorio.dao.error.1202", locale);
		}
		return getSucursalPorId(new SucursalId(empresaId, sucursalId));
	}
	
	@Override
	public List<Sucursal> getSucursales() throws DaoException {
		List<Sucursal> sucursales = null;
		Session hs = null;
		Transaction htx = null;
		SessionFactory hsf = null;
		try {
			hsf = HibernateUtil.getSessionFactory();
			hs = hsf.getCurrentSession();
			htx = hs.beginTransaction();
			Query hqlQuery = hs
					.createQuery("from Sucursal s order by s.sucursalId.empresaId, s.nombre asc");
			sucursales = hqlQuery.list();
			htx.commit();
		} catch (HibernateException e) {
			if (htx != null && htx.isActive()) {
				try {
					htx.rollback();
				} catch (HibernateException e2) {
					throw new DaoException("consultorio.dao.error.0001", locale);
				}
			}
			sucursales = new ArrayList<Sucursal>();
			e.printStackTrace();
			throw new DaoException(e.hashCode(), e.getMessage());
		}
		return sucursales;
	}

	@Override
	public List<Sucursal> getSucursalesPorEmpresa(Integer empresaId)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sucursal> getSucursalesPorEmpresa(Empresa empresa)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sucursal> getSucursalesPorEmpresaNombre(Integer empresaId,
			String nombre, boolean aplicarLike, int tipo) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sucursal> getSucursalesPorEmpresaNombre(Empresa empresa,
			String nombre, boolean aplicarLike, int tipo) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(Sucursal sucursal) throws DaoException {
		return false;
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public Locale getLocale() {
		return locale;
	}
}
