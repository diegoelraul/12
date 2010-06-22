package net.kielsaenz.consultorio.service.implementacion;

import java.util.List;
import java.util.Locale;

import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Sucursal;
import net.kielsaenz.consultorio.service.SucursalService;

public class SucursalServiceImpl implements SucursalService{

	@Override
	public boolean actualizar(Sucursal sucursal) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Sucursal sucursal) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sucursal getSucursalPorId(Integer sucursalId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sucursal> getSucursales() throws DaoException {
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
	public List<Sucursal> getSucursalesPorEmpresa(Integer empresaId)
			throws DaoException {
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
	public List<Sucursal> getSucursalesPorEmpresaNombre(Integer empresaId,
			String nombre, boolean aplicarLike, int tipo) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(Sucursal sucursal) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLocale(Locale locale) {
		// TODO Auto-generated method stub
		
	}

}
