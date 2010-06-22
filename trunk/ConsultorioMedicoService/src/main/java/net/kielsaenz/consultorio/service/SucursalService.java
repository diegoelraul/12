package net.kielsaenz.consultorio.service;

import java.util.List;
import java.util.Locale;

import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Sucursal;
import net.kielsaenz.consultorio.service.exception.ServiceException;

public interface SucursalService {

	public Locale getLocale();

	public void setLocale(Locale locale);

	public List<Sucursal> getSucursales() throws ServiceException, DaoException;

	public Sucursal getSucursalPorId(Integer sucursalId)
			throws ServiceException, DaoException;

	public List<Sucursal> getSucursalesPorEmpresa(Empresa empresa)
			throws ServiceException, DaoException;

	public List<Sucursal> getSucursalesPorEmpresa(Integer empresaId)
			throws ServiceException, DaoException;

	public List<Sucursal> getSucursalesPorEmpresaNombre(Empresa empresa,
			String nombre, boolean aplicarLike, int tipo)
			throws ServiceException, DaoException;

	public List<Sucursal> getSucursalesPorEmpresaNombre(Integer empresaId,
			String nombre, boolean aplicarLike, int tipo)
			throws ServiceException, DaoException;

	public boolean insertar(Sucursal sucursal) throws ServiceException,
			DaoException;

	public boolean eliminar(Sucursal sucursal) throws ServiceException,
			DaoException;

	public boolean actualizar(Sucursal sucursal) throws ServiceException,
			DaoException;
}
