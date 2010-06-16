package net.kielsaenz.consultorio.service.implementacion;

import java.util.List;
import java.util.Locale;

import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.InterfaceDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.service.EmpresaService;
import net.kielsaenz.consultorio.service.exception.ServiceException;

public class EmpresaServiceImpl implements EmpresaService {

	private Locale locale;
	private EmpresaDao empresaDao;

	public EmpresaServiceImpl() {
		this(Locale.getDefault());
	}

	public EmpresaServiceImpl(Locale locale) {
		this.locale = locale;
	}

	@Override
	public Empresa getEmpresaPorId(Integer empresaId) throws ServiceException,
			DaoException {
		try {
			return empresaDao.getEmpresaPorId(empresaId);
		} catch (Exception ex) {
			throw new ServiceException(ex.hashCode(), ex.getMessage());
		}
	}

	@Override
	public Empresa getEmpresaPorRUC(String ruc) throws ServiceException,
			DaoException {
		try {
			return empresaDao.getEmpresaPorRUC(ruc);
		} catch (Exception ex) {
			throw new ServiceException(ex.hashCode(), ex.getMessage());
		}
	}

	@Override
	public List<Empresa> getEmpresaPorRazonSocial(String razonSocial,
			boolean aplicarLike, int tipo) throws ServiceException,
			DaoException {
		try {
			return empresaDao.getEmpresaPorRazonSocial(razonSocial,
					aplicarLike, tipo);
		} catch (Exception ex) {
			throw new ServiceException(ex.hashCode(), ex.getMessage());
		}
	}

	@Override
	public List<Empresa> getEmpresas() throws ServiceException, DaoException {
		try {
			return empresaDao.getEmpresas();
		} catch (Exception ex) {
			throw new ServiceException(ex.hashCode(), ex.getMessage());
		}
	}

	@Override
	public boolean actualizar(Empresa empresa) throws ServiceException,
			DaoException {
		try {
			if (validarNoRepetidos(empresa)) {
				return empresaDao.insertar(empresa);
			} else {
				return false;
			}
		} catch (Exception ex) {
			throw new ServiceException(ex.hashCode(), ex.getMessage());
		}
	}

	@Override
	public boolean eliminar(Empresa empresa) throws ServiceException,
			DaoException {
		try {
			return empresaDao.eliminar(empresa);
		} catch (Exception ex) {
			throw new ServiceException(ex.hashCode(), ex.getMessage());
		}
	}

	@Override
	public boolean insertar(Empresa empresa) throws ServiceException,
			DaoException {
		try {
			if (validarNoRepetidos(empresa)) {
				return empresaDao.insertar(empresa);
			} else {
				return false;
			}
		} catch (Exception ex) {
			throw new ServiceException(ex.hashCode(), ex.getMessage());
		}
	}

	public boolean validarNoRepetidos(Empresa empresa) throws ServiceException,
			DaoException, Exception {
		// Se verifica que la empresa no sea nula
		if (empresa == null) {
			throw new ServiceException("consultorio.service.error.1104", locale);
		}
		// Se verifica que el ruc tenga exactamente 11 caracteres
		String ruc = empresa.getRuc();
		if (ruc == null || ruc.isEmpty() || ruc.length() != 11) {
			throw new ServiceException("consultorio.service.error.1101", locale);
		}
		// Se verifica que el ruc este compuesto por números
		for (int i = 0; i < ruc.length(); i++) {
			char c = ruc.charAt(i);
			if (!Character.isDigit(c)) {
				throw new ServiceException("consultorio.service.error.1102",
						locale);
			}
		}
		// Se verifica que el ruc no esté registrado
		Empresa empresaAux = this.getEmpresaPorRUC(ruc);
		if (empresaAux != null) {
			throw new ServiceException("consultorio.service.error.1100", locale);
		}
		// Se verifica que la razón social no esté registrada
		List<Empresa> empresasAux = this.getEmpresaPorRazonSocial(empresa
				.getRazonSocial(), false, InterfaceDao.TO_LOWER_CASE);
		if (empresasAux.size() > 0) {
			throw new ServiceException("consultorio.service.error.1103", locale);
		}
		return true;
	}

	public EmpresaDao getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(EmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
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
