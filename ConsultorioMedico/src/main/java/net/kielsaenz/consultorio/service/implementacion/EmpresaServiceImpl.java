package net.kielsaenz.consultorio.service.implementacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

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
            if (validarEmpresaIdNoNulo(empresaId)) {
                return empresaDao.getEmpresaPorId(empresaId);
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
    public Empresa getEmpresaPorRUC(String ruc) throws ServiceException,
            DaoException {
        try {
            if (validarRuc(ruc)) {
                return empresaDao.getEmpresaPorRUC(ruc);
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
    public List<Empresa> getEmpresaPorRazonSocial(String razonSocial,
            boolean aplicarLike, int tipo) throws ServiceException,
            DaoException {
        try {
            if (validarRazonSocialNoNulo(razonSocial)) {
                return empresaDao.getEmpresaPorRazonSocial(razonSocial,
                        aplicarLike, tipo);
            }
            return new ArrayList<Empresa>();
        } catch (DaoException d) {
            throw d;
        } catch (ServiceException s) {
            throw s;
        } catch (Exception e) {
            throw new ServiceException(e.hashCode(), e.getMessage());
        }
    }

    @Override
    public List<Empresa> getEmpresas() throws ServiceException, DaoException {
        try {
            return empresaDao.getEmpresas();
        } catch (DaoException d) {
            throw d;
        } catch (Exception ex) {
            throw new ServiceException(ex.hashCode(), ex.getMessage());
        }
    }

    @Override
    public boolean actualizar(Empresa empresa) throws ServiceException,
            DaoException {
        try {
            // Se valida la empresa
            if (validarEmpresaNoNulo(empresa) && validarRuc(empresa.getRuc())) {
                return empresaDao.actualizar(empresa);
            } else {
                return false;
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
    public boolean eliminar(Empresa empresa) throws ServiceException,
            DaoException {
        try {
            // Se valida la empresa
            if (validarEmpresaNoNulo(empresa)) {
                return empresaDao.eliminar(empresa);
            }
            return false;
        } catch (DaoException d) {
            throw d;
        } catch (ServiceException s) {
            throw s;
        } catch (Exception e) {
            throw new ServiceException(e.hashCode(), e.getMessage());
        }
    }

    @Override
    public boolean insertar(Empresa empresa) throws ServiceException,
            DaoException {
        try {
            // Se valida la empresa
            if (validarEmpresaNoNulo(empresa)
                    && validarEmpresaIdNoRepetido(empresa.getEmpresaId())
                    && validarRuc(empresa.getRuc())
                    && validarRucNoRepetido(empresa.getRuc())
                    && validarRazonSocialNoRepetido(empresa.getRazonSocial())) {
                empresa.toUpperCase();
                return empresaDao.insertar(empresa);
            } else {
                return false;
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
    public boolean validarEmpresaNoNulo(Empresa empresa)
            throws ServiceException {
        // Se verifica que la empresa no sea nula
        if (empresa == null) {
            throw new ServiceException("consultorio.service.error.1104", locale);
        }
        return true;
    }

    @Override
    public boolean validarEmpresaIdNoRepetido(Integer empresaId)
            throws ServiceException, DaoException {
        // Se verifica que el ID no sea nulo
        if (validarEmpresaIdNoNulo(empresaId)) {
            // Se verifica que el ID de la empresa no este repetido
            Empresa empresaAux = this.getEmpresaPorId(empresaId);
            if (empresaAux != null) {
                throw new ServiceException("consultorio.service.error.1108",
                        locale);
            }
        }
        return true;
    }

    @Override
    public boolean validarEmpresaIdNoNulo(Integer empresaId) throws ServiceException {
        // Se verifica que el ID no sea nulo
        if (empresaId == null) {
            throw new ServiceException("consultorio.service.error.1105", locale);
        }
        return true;
    }

    @Override
    public boolean validarRuc(String ruc) throws ServiceException {
        // Se verifica que el ruc no sea nulo
        if (validarRucNoNulo(ruc)) {
            // Se verifica que el ruc este compuesto por números
            if (!StringUtils.isNumeric(ruc)) {
                throw new ServiceException("consultorio.service.error.1102",
                        locale);
            }
            // Se verifica que el ruc tenga exactamente 11 caracteres
            if (ruc.length() != 11) {
                throw new ServiceException("consultorio.service.error.1101",
                        locale);
            }
        }
        return true;
    }

    @Override
    public boolean validarRucNoNulo(String ruc) throws ServiceException {
        // Se verifica que el ruc no sea nulo
        if (StringUtils.isBlank(ruc)) {
            throw new ServiceException("consultorio.service.error.1107", locale);
        }
        return true;
    }

    @Override
    public boolean validarRucNoRepetido(String ruc) throws ServiceException,
            DaoException {
        // Se verifica que el ruc no esté registrado
        Empresa empresaAux = this.getEmpresaPorRUC(ruc);
        if (empresaAux != null) {
            throw new ServiceException("consultorio.service.error.1100", locale);
        }
        return true;
    }

    @Override
    public boolean validarRazonSocialNoNulo(String razonSocial)
            throws ServiceException {
        // Se verifica que el ruc no sea nulo
        if (StringUtils.isBlank(razonSocial)) {
            throw new ServiceException("consultorio.service.error.1109", locale);
        }
        return true;
    }

    @Override
    public boolean validarRazonSocialNoRepetido(String razonSocial)
            throws ServiceException, DaoException {
        // Se verifica que la razón social no esté registrada
        List<Empresa> empresasAux = this.getEmpresaPorRazonSocial(razonSocial,
                false, InterfaceDao.TO_UPPER_CASE);
        if (empresasAux.size() > 0) {
            throw new ServiceException("consultorio.service.error.1103", locale);
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

    public EmpresaDao getEmpresaDao() {
        return empresaDao;
    }

    public void setEmpresaDao(EmpresaDao empresaDao) {
        this.empresaDao = empresaDao;
    }
}
