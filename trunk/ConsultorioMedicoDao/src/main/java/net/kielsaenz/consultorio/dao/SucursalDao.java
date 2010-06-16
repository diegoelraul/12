package net.kielsaenz.consultorio.dao;

import java.util.List;
import java.util.Locale;

import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Sucursal;
import net.kielsaenz.consultorio.model.SucursalId;

public interface SucursalDao {

	public Locale getLocale();

	public void setLocale(Locale locale);

	public List<Sucursal> getSucursales() throws DaoException;

	public Sucursal getSucursalPorId(Integer empresaId, Integer sucursalId) throws DaoException;

	public Sucursal getSucursalPorId(SucursalId sucursalId) throws DaoException;

	public List<Sucursal> getSucursalesPorEmpresa(Integer empresaId)
			throws DaoException;

	public List<Sucursal> getSucursalesPorEmpresa(Empresa empresa)
			throws DaoException;

	public List<Sucursal> getSucursalesPorEmpresaNombre(Integer empresaId,
			String nombre, boolean aplicarLike, int tipo) throws DaoException;

	public List<Sucursal> getSucursalesPorEmpresaNombre(Empresa empresa,
			String nombre, boolean aplicarLike, int tipo) throws DaoException;

	public boolean insertar(Sucursal sucursal) throws DaoException;

	public boolean eliminar(Sucursal sucursal) throws DaoException;

	public boolean actualizar(Sucursal sucursal) throws DaoException;
}
