package net.kielsaenz.consultorio.service;

import java.util.List;
import java.util.Locale;

import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Departamento;
import net.kielsaenz.consultorio.model.Distrito;
import net.kielsaenz.consultorio.model.Provincia;
import net.kielsaenz.consultorio.service.exception.ServiceException;

public interface UbigeoService {

	public Locale getLocale();

	public void setLocale(Locale locale);

	public Departamento getDepartamentoPorId(String departamentoId)
			throws ServiceException, DaoException;

	public List<Departamento> getDepartamentos() throws ServiceException,
			DaoException;

	public Provincia getProvinciaPorId(String departamentoId, String provinciaId)
			throws ServiceException, DaoException;

	public List<Provincia> getProvinciasPorDepartamento(
			Departamento departamento) throws ServiceException, DaoException;

	public List<Provincia> getProvinciasPorDepartamento(String departamentoId)
			throws ServiceException, DaoException;

	public Distrito getDistritoPorId(String departamentoId, String provinciaId,
			String distritoId) throws ServiceException, DaoException;

	public List<Distrito> getDistritosPorProvincia(Provincia provincia)
			throws ServiceException, DaoException;

	public List<Distrito> getDistritosPorProvincia(String departamentoId,
			String provinciaId) throws ServiceException, DaoException;
}
