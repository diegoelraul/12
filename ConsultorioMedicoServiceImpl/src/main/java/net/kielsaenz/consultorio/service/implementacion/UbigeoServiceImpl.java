package net.kielsaenz.consultorio.service.implementacion;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import net.kielsaenz.consultorio.dao.UbigeoDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Departamento;
import net.kielsaenz.consultorio.model.Distrito;
import net.kielsaenz.consultorio.model.Provincia;
import net.kielsaenz.consultorio.service.UbigeoService;
import net.kielsaenz.consultorio.service.exception.ServiceException;

public class UbigeoServiceImpl implements UbigeoService {

	private Locale locale;
	private UbigeoDao ubigeoDao;

	public UbigeoServiceImpl() {
		this(Locale.getDefault());
	}

	public UbigeoServiceImpl(Locale locale) {
		this.locale = locale;
	}

	@Override
	public Departamento getDepartamentoPorId(String departamentoId)
			throws ServiceException, DaoException {
		try {
			// Se valida el id del departamento
			if (validarDepartamentoId(departamentoId)) {
				return ubigeoDao.getDepartamentoPorId(departamentoId);
			} else {
				return null;
			}
		} catch (DaoException d) {
			throw d;
		} catch (ServiceException s) {
			throw s;
		} catch (Exception e) {
			throw new ServiceException(e.hashCode(), e.getMessage());
		}
	}

	@Override
	public List<Departamento> getDepartamentos() throws ServiceException,
			DaoException {
		try {
			return ubigeoDao.getDepartamentos();
		} catch (DaoException d) {
			throw d;
		} catch (Exception e) {
			throw new ServiceException(e.hashCode(), e.getMessage());
		}
	}

	@Override
	public Distrito getDistritoPorId(String departamentoId, String provinciaId,
			String distritoId) throws ServiceException, DaoException {
		try {
			// Se valida el id del departamento
			if (validarDepartamentoId(departamentoId)) {
				// Se valida el id de la provincia
				if (validarProvinciaId(provinciaId)) {
					// Se valida el id del distrito
					if (validarDistritoId(distritoId)) {
						return ubigeoDao.getDistritoPorId(departamentoId,
								provinciaId, distritoId);
					}
				}
			}
			return null;
		} catch (DaoException d) {
			throw d;
		} catch (ServiceException s) {
			throw s;
		} catch (Exception e) {
			throw new ServiceException(e.hashCode(), e.getMessage());
		}
	}

	@Override
	public List<Distrito> getDistritosPorProvincia(Provincia provincia)
			throws ServiceException, DaoException {
		try {
			// Se valida el id del departamento
			if (validarProvinciaNoNulo(provincia)) {
				return this.getDistritosPorProvincia(provincia.getDepartamentoId(), provincia.getProvinciaId());
			} else {
				return null;
			}
		} catch (DaoException d) {
			throw d;
		} catch (ServiceException s) {
			throw s;
		} catch (Exception e) {
			throw new ServiceException(e.hashCode(), e.getMessage());
		}
	}

	@Override
	public List<Distrito> getDistritosPorProvincia(String departamentoId,
			String provinciaId) throws ServiceException, DaoException {
		try {
			// Se valida el id del departamento
			if (validarDepartamentoId(departamentoId)) {
				// Se valida el id de la provincia
				if (validarProvinciaId(provinciaId)) {
					return ubigeoDao.getDistritosPorProvincia(departamentoId,
							provinciaId);
				}
			}
			return null;
		} catch (DaoException d) {
			throw d;
		} catch (ServiceException s) {
			throw s;
		} catch (Exception e) {
			throw new ServiceException(e.hashCode(), e.getMessage());
		}
	}

	@Override
	public Provincia getProvinciaPorId(String departamentoId, String provinciaId)
			throws ServiceException, DaoException {
		try {
			// Se valida el id del departamento
			if (validarDepartamentoId(departamentoId)) {
				// Se valida el id de la provincia
				if (validarProvinciaId(provinciaId)) {
					return ubigeoDao.getProvinciaPorId(departamentoId,
							provinciaId);
				}
			}
			return null;
		} catch (DaoException d) {
			throw d;
		} catch (ServiceException s) {
			throw s;
		} catch (Exception e) {
			throw new ServiceException(e.hashCode(), e.getMessage());
		}
	}

	@Override
	public List<Provincia> getProvinciasPorDepartamento(
			Departamento departamento) throws ServiceException, DaoException {
		try {
			// Se valida el id del departamento
			if (validarDepartamentoNoNulo(departamento)) {
				return this.getProvinciasPorDepartamento(departamento.getDepartamentoId());
			} else {
				return null;
			}
		} catch (DaoException d) {
			throw d;
		} catch (ServiceException s) {
			throw s;
		} catch (Exception e) {
			throw new ServiceException(e.hashCode(), e.getMessage());
		}
	}

	@Override
	public List<Provincia> getProvinciasPorDepartamento(String departamentoId)
			throws ServiceException, DaoException {
		try {
			// Se valida el id del departamento
			if (validarDepartamentoId(departamentoId)) {
				return ubigeoDao.getProvinciasPorDepartamento(departamentoId);
			}
			return null;
		} catch (DaoException d) {
			throw d;
		} catch (ServiceException s) {
			throw s;
		} catch (Exception e) {
			throw new ServiceException(e.hashCode(), e.getMessage());
		}
	}

	private boolean validarDepartamentoId(String departamentoId) throws ServiceException {
		// Se valida que el id no sea nulo o este vacio
		if (StringUtils.isEmpty(departamentoId)) {
			throw new ServiceException("consultorio.service.error.1700", locale);
		}
		// Se valida que el id contenga solo numeros
		if (StringUtils.isNumeric(departamentoId)) {
			throw new ServiceException("consultorio.service.error.1701", locale);
		}
		// se valida que el id tenga dos caracteres
		if (departamentoId.length() != 2) {
			throw new ServiceException("consultorio.service.error.1702", locale);
		}
		return true;
	}

	private boolean validarProvinciaId(String provinciaId) throws ServiceException {
		// Se valida que el id no sea nulo o este vacio
		if (StringUtils.isEmpty(provinciaId)) {
			throw new ServiceException("consultorio.service.error.1703", locale);
		}
		// Se valida que el id contenga solo numeros
		if (StringUtils.isNumeric(provinciaId)) {
			throw new ServiceException("consultorio.service.error.1704", locale);
		}
		// se valida que el id tenga dos caracteres
		if (provinciaId.length() != 2) {
			throw new ServiceException("consultorio.service.error.1705", locale);
		}
		return true;
	}

	private boolean validarDistritoId(String distritoId) throws ServiceException {
		// Se valida que el id no sea nulo o este vacio
		if (StringUtils.isEmpty(distritoId)) {
			throw new ServiceException("consultorio.service.error.1706", locale);
		}
		// Se valida que el id contenga solo numeros
		if (StringUtils.isNumeric(distritoId)) {
			throw new ServiceException("consultorio.service.error.1707", locale);
		}
		// se valida que el id tenga dos caracteres
		if (distritoId.length() != 2) {
			throw new ServiceException("consultorio.service.error.1708", locale);
		}
		return true;
	}
	
	private boolean validarDepartamentoNoNulo(Departamento departamento) throws ServiceException {
		// Se valida que el id no sea nulo o este vacio
		if (departamento == null) {
			throw new ServiceException("consultorio.service.error.1709", locale);
		}
		return true;
	}
	
	private boolean validarProvinciaNoNulo(Provincia provincia) throws ServiceException {
		// Se valida que el id no sea nulo o este vacio
		if (provincia == null) {
			throw new ServiceException("consultorio.service.error.1710", locale);
		}
		return true;
	}
	
	private boolean validarDistritoNoNulo(Distrito distrito) throws ServiceException {
		// Se valida que el id no sea nulo o este vacio
		if (distrito == null) {
			throw new ServiceException("consultorio.service.error.1711", locale);
		}
		return true;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public UbigeoDao getUbigeoDao() {
		return ubigeoDao;
	}

	public void setUbigeoDao(UbigeoDao ubigeoDao) {
		this.ubigeoDao = ubigeoDao;
	}
}
