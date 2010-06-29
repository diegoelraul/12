package net.kielsaenz.consultorio.service.implementacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.InterfaceDao;

import net.kielsaenz.consultorio.dao.SucursalDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Sucursal;
import net.kielsaenz.consultorio.service.EmpresaService;
import net.kielsaenz.consultorio.service.SucursalService;
import net.kielsaenz.consultorio.service.exception.ServiceException;
import org.apache.commons.lang.StringUtils;

public class SucursalServiceImpl implements SucursalService {

    private Locale locale;
    private SucursalDao sucursalDao;
    private EmpresaService empresaService;

    public SucursalServiceImpl() {
        this(Locale.getDefault());
    }

    public SucursalServiceImpl(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public List<Sucursal> getSucursales() throws ServiceException, DaoException {
        try {
            return sucursalDao.getSucursales();
        } catch (DaoException d) {
            throw d;
        } catch (Exception ex) {
            throw new ServiceException(ex.hashCode(), ex.getMessage());
        }
    }

    @Override
    public Sucursal getSucursalPorId(Integer sucursalId) throws ServiceException, DaoException {
        try {
            if (validarSucursalIdNoNulo(sucursalId)) {
                return sucursalDao.getSucursalPorId(sucursalId);
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
    public List<Sucursal> getSucursalesPorEmpresa(Empresa empresa) throws ServiceException, DaoException {
        try {
            if (empresaService.validarEmpresaNoNulo(empresa)) {
                return this.getSucursalesPorEmpresa(empresa.getEmpresaId());
            }
            return new ArrayList<Sucursal>();
        } catch (DaoException d) {
            throw d;
        } catch (ServiceException s) {
            throw s;
        } catch (Exception e) {
            throw new ServiceException(e.hashCode(), e.getMessage());
        }
    }

    @Override
    public List<Sucursal> getSucursalesPorEmpresa(Integer empresaId) throws ServiceException, DaoException {
        try {
            if (empresaService.validarEmpresaIdNoNulo(empresaId)) {
                return sucursalDao.getSucursalesPorEmpresa(empresaId);
            }
            return new ArrayList<Sucursal>();
        } catch (DaoException d) {
            throw d;
        } catch (ServiceException s) {
            throw s;
        } catch (Exception e) {
            throw new ServiceException(e.hashCode(), e.getMessage());
        }
    }

    @Override
    public List<Sucursal> getSucursalesPorEmpresaNombre(Empresa empresa, String nombre, boolean aplicarLike, int tipo) throws ServiceException, DaoException {
        try {
            if (empresaService.validarEmpresaNoNulo(empresa)) {
                return this.getSucursalesPorEmpresaNombre(empresa.getEmpresaId(), nombre, aplicarLike, tipo);
            }
            return new ArrayList<Sucursal>();
        } catch (DaoException d) {
            throw d;
        } catch (ServiceException s) {
            throw s;
        } catch (Exception e) {
            throw new ServiceException(e.hashCode(), e.getMessage());
        }
    }

    @Override
    public List<Sucursal> getSucursalesPorEmpresaNombre(Integer empresaId, String nombre, boolean aplicarLike, int tipo) throws ServiceException, DaoException {
        try {
            if (empresaService.validarEmpresaIdNoNulo(empresaId) && validarNombreNoNulo(nombre)) {
                return sucursalDao.getSucursalesPorEmpresaNombre(empresaId, nombre, aplicarLike, tipo);
            }
            return new ArrayList<Sucursal>();
        } catch (DaoException d) {
            throw d;
        } catch (ServiceException s) {
            throw s;
        } catch (Exception e) {
            throw new ServiceException(e.hashCode(), e.getMessage());
        }
    }

    @Override
    public boolean insertar(Sucursal sucursal) throws ServiceException, DaoException {
        try {
            // Se valida el usuario
            if (validarSucursalNoNulo(sucursal)
                    && validarSucursalIdNoRepetido(sucursal.getSucursalId())
                    && validarNombre(sucursal.getNombre())
                    && validarNombreNoRepetido(sucursal.getEmpresa().getEmpresaId(), sucursal.getNombre())) {
                sucursal.toUpperCase();
                return sucursalDao.insertar(sucursal);
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
    public boolean eliminar(Sucursal sucursal) throws ServiceException, DaoException {
        try {
            // Se valida la sucursal
            if (validarSucursalNoNulo(sucursal)) {
                return sucursalDao.eliminar(sucursal);
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
    public boolean actualizar(Sucursal sucursal) throws ServiceException, DaoException {
        try {
            // Se valida la sucursal
            if (validarSucursalNoNulo(sucursal) && validarNombre(sucursal.getNombre())
                    && validarNombreNoRepetido(sucursal.getEmpresa().getEmpresaId(), sucursal.getNombre())) {
                return sucursalDao.actualizar(sucursal);
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
    public boolean validarSucursalNoNulo(Sucursal sucursal) throws ServiceException {
        // Se valida la sucursal
        if (sucursal == null) {
            throw new ServiceException("consultorio.service.error.1200", locale);
        }
        return true;
    }

    @Override
    public boolean validarSucursalIdNoRepetido(Integer sucursalId)
            throws ServiceException, DaoException {
        // Se verifica que el ID no sea nulo
        if (validarSucursalIdNoNulo(sucursalId)) {
            // Se verifica que el ID de la sucursal no este repetido
            Sucursal sucursalAux = this.getSucursalPorId(sucursalId);
            if (sucursalAux != null) {
                throw new ServiceException("consultorio.service.error.1201",
                        locale);
            }
        }
        return true;
    }

    @Override
    public boolean validarSucursalIdNoNulo(Integer sucursalId) throws ServiceException {
        // Se verifica que el ID no sea nulo
        if (sucursalId == null) {
            throw new ServiceException("consultorio.service.error.1202", locale);
        }
        return true;
    }

    @Override
    public boolean validarNombre(String nombre) throws ServiceException {
        // Se verifica que el nombre no sea nulo
        if (validarNombreNoNulo(nombre)) {
            // Se verifica la longitud del nombre
            int longitud = StringUtils.length(nombre);
            if (longitud < 5 || longitud > 20) {
                throw new ServiceException("consultorio.service.error.1204", locale);
            }
            // Se verifica que el nombre sea alfabético numérico
            if (!StringUtils.isAlphanumericSpace(nombre)) {
                throw new ServiceException("consultorio.service.error.1205", locale);
            }
        }
        return true;
    }

    @Override
    public boolean validarNombreNoNulo(String nombre) throws ServiceException {
        // Se verifica que el nombre no sea nulo
        if (StringUtils.isBlank(nombre)) {
            throw new ServiceException("consultorio.service.error.1203", locale);
        }
        return true;
    }

    @Override
    public boolean validarNombreNoRepetido(Integer empresaId, String nombre) throws ServiceException, DaoException {
        // Se verifica que el usuario no este repetido para una misma empresa
        List<Sucursal> sucursales = this.getSucursalesPorEmpresaNombre(empresaId, nombre, false, InterfaceDao.TO_UPPER_CASE);
        if (sucursales != null && sucursales.size() > 0) {
            throw new ServiceException("consultorio.service.error.1209", locale);
        }
        return true;
    }
}
