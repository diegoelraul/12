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

import net.kielsaenz.consultorio.dao.InterfaceDao;
import net.kielsaenz.consultorio.dao.SucursalDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Sucursal;

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
	public Sucursal getSucursalPorId(Integer sucursalId) throws DaoException {
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
					.createQuery("from Sucursal s order by s.empresa.empresaId, s.nombre asc");
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
	public List<Sucursal> getSucursalesPorEmpresa(Empresa empresa)
			throws DaoException {
		if(empresa == null){
			throw new DaoException("consultorio.dao.error.1103", locale);
		}
		return getSucursalesPorEmpresa(empresa.getEmpresaId());
	}

	@Override
	public List<Sucursal> getSucursalesPorEmpresa(Integer empresaId)
			throws DaoException {
		if(empresaId == null){
			throw new DaoException("consultorio.dao.error.1100", locale);
		}
		List<Sucursal> sucursales = null;
		Session hs = null;
		Transaction htx = null;
		SessionFactory hsf = null;
		try {
			hsf = HibernateUtil.getSessionFactory();
			hs = hsf.getCurrentSession();
			htx = hs.beginTransaction();
			Query hqlQuery = hs
					.createQuery("from Sucursal s where s.empresa = :empresaId order by s.nombre asc");
			hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
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
	public List<Sucursal> getSucursalesPorEmpresaNombre(Empresa empresa,
			String nombre, boolean aplicarLike, int tipo) throws DaoException {
		if(empresa == null){
			throw new DaoException("consultorio.dao.error.1103", locale);
		}
		if(nombre == null || nombre.isEmpty()){
			throw new DaoException("consultorio.dao.error.1202", locale);
		}
		return getSucursalesPorEmpresaNombre(empresa.getEmpresaId(), nombre, aplicarLike, tipo);
	}

	@Override
	public List<Sucursal> getSucursalesPorEmpresaNombre(Integer empresaId,
			String nombre, boolean aplicarLike, int tipo) throws DaoException {
		if(empresaId == null){
			throw new DaoException("consultorio.dao.error.1100", locale);
		}
		List<Sucursal> sucursales = null;
		Session hs = null;
		Transaction htx = null;
		SessionFactory hsf = null;
		try {
			hsf = HibernateUtil.getSessionFactory();
			hs = hsf.getCurrentSession();
			htx = hs.beginTransaction();
			Query hqlQuery = null;
			StringBuffer query = new StringBuffer(
					"select s from Sucursal s where s.empresa.empresaId = :empresaId and ");

			if (tipo == InterfaceDao.TO_LOWER_CASE) {
				query.append("LOWER(s.nombre) ");
			} else if (tipo == InterfaceDao.TO_UPPER_CASE) {
				query.append("UPPER(s.nombre) ");
			} else {
				query.append("s.nombre ");
			}

			if (aplicarLike) {
				query.append("like :nombre");
			} else {
				query.append("= :nombre");
			}

			hqlQuery = hs.createQuery(query.toString());

			if (tipo == InterfaceDao.TO_LOWER_CASE) {
				if (aplicarLike) {
					StringBuffer param01 = new StringBuffer("%").append(
							nombre).append("%");
					hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
					hqlQuery.setParameter("nombre", param01.toString().toLowerCase(),
							Hibernate.STRING);
				} else {
					hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
					hqlQuery.setParameter("nombre", nombre.toLowerCase(),
							Hibernate.STRING);
				}
			} else if (tipo == InterfaceDao.TO_UPPER_CASE) {
				if (aplicarLike) {
					StringBuffer param01 = new StringBuffer("%").append(
							nombre).append("%");
					hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
					hqlQuery.setParameter("nombre", param01.toString().toUpperCase(),
							Hibernate.STRING);
				} else {
					hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
					hqlQuery.setParameter("nombre", nombre.toUpperCase(),
							Hibernate.STRING);
				}
			} else {
				if (aplicarLike) {
					StringBuffer param01 = new StringBuffer("%").append(
							nombre).append("%");
					hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
					hqlQuery.setParameter("razonSocial", param01.toString(),
							Hibernate.STRING);
				} else {
					hqlQuery.setParameter("empresaId", empresaId, Hibernate.INTEGER);
					hqlQuery.setParameter("razonSocial", nombre,
							Hibernate.STRING);
				}
			}
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
	public boolean insertar(Sucursal sucursal) throws DaoException {
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
			Integer sucursalId = (Integer) hs.save(sucursal);
			sucursal.setSucursalId(sucursalId);
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
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public Locale getLocale() {
		return locale;
	}
}
