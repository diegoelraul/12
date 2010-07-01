package net.kielsaenz.consultorio.service;

import java.util.List;
import java.util.Locale;

import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.service.exception.ServiceException;

public interface EmpresaService {

    public Locale getLocale();

    public void setLocale(Locale locale);

    public List<Empresa> getEmpresas() throws ServiceException, DaoException;

    public Empresa getEmpresaPorId(Integer empresaId) throws ServiceException,
            DaoException;

    public Empresa getEmpresaPorRUC(String ruc) throws ServiceException,
            DaoException;

    public List<Empresa> getEmpresaPorRazonSocial(String razonSocial,
            boolean aplicarLike) throws ServiceException, DaoException;

    public boolean insertar(Empresa empresa) throws ServiceException,
            DaoException;

    public boolean eliminar(Empresa empresa) throws ServiceException,
            DaoException;

    public boolean actualizar(Empresa empresa) throws ServiceException,
            DaoException;

    public boolean validarEmpresaNoNulo(Empresa empresa)
            throws ServiceException;

    public boolean validarEmpresaIdNoRepetido(Integer empresaId)
            throws ServiceException, DaoException;

    public boolean validarEmpresaIdNoNulo(Integer empresaId) throws ServiceException;

    public boolean validarRuc(String ruc) throws ServiceException;

    public boolean validarRucNoNulo(String ruc) throws ServiceException;

    public boolean validarRucNoRepetido(String ruc) throws ServiceException,
            DaoException;

    public boolean validarRazonSocialNoNulo(String razonSocial)
            throws ServiceException;

    public boolean validarRazonSocialNoRepetido(String razonSocial)
            throws ServiceException, DaoException;
}
