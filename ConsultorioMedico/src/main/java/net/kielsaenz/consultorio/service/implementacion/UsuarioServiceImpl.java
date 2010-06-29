package net.kielsaenz.consultorio.service.implementacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.UsuarioDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Usuario;
import net.kielsaenz.consultorio.service.EmpresaService;
import net.kielsaenz.consultorio.service.UsuarioService;
import net.kielsaenz.consultorio.service.exception.ServiceException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Kiel
 */
public class UsuarioServiceImpl implements UsuarioService {

    private Locale locale;
    private UsuarioDao usuarioDao;
    private EmpresaService empresaService;

    public UsuarioServiceImpl() {
        this(Locale.getDefault());
    }

    public UsuarioServiceImpl(Locale locale) {
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
    public boolean insertar(Usuario usuario) throws ServiceException, DaoException {
        try {
            // Se valida el usuario
            if (validarUsuarioNoNulo(usuario)
                    && validarUsuarioIdNoRepetido(usuario.getUsuarioId())
                    && validarUsername(usuario.getUsername())
                    && validarPassword(usuario.getPassword())
                    && validarUsernameNoRepetido(usuario.getEmpresa().getEmpresaId(), usuario.getUsername())) {
                usuario.toLowerCase();
                return usuarioDao.insertar(usuario);
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
    public boolean actualizar(Usuario usuario) throws ServiceException, DaoException {
        try {
            // Se valida el usuario
            if (validarUsuarioNoNulo(usuario) && validarUsername(usuario.getUsername())
                    && validarPassword(usuario.getPassword())
                    && validarUsernameNoRepetido(usuario.getEmpresa().getEmpresaId(), usuario.getUsername())) {
                usuario.toLowerCase();
                return usuarioDao.actualizar(usuario);
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
    public boolean eliminar(Usuario usuario) throws ServiceException, DaoException {
        try {
            // Se valida el usuario
            if (validarUsuarioNoNulo(usuario)) {
                return usuarioDao.eliminar(usuario);
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
    public Usuario getUsuarioPorId(Integer usuarioId) throws ServiceException, DaoException {
        try {
            if (validarUsuarioIdNoNulo(usuarioId)) {
                return usuarioDao.getUsuarioPorId(usuarioId);
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
    public List<Usuario> getUsuarios() throws ServiceException, DaoException {
        try {
            return usuarioDao.getUsuarios();
        } catch (DaoException d) {
            throw d;
        } catch (Exception ex) {
            throw new ServiceException(ex.hashCode(), ex.getMessage());
        }
    }

    @Override
    public List<Usuario> getUsuariosPorEmpresa(Empresa empresa) throws ServiceException, DaoException {
        try {
            if (empresaService.validarEmpresaNoNulo(empresa)) {
                return this.getUsuariosPorEmpresa(empresa.getEmpresaId());
            }
            return new ArrayList<Usuario>();
        } catch (DaoException d) {
            throw d;
        } catch (ServiceException s) {
            throw s;
        } catch (Exception e) {
            throw new ServiceException(e.hashCode(), e.getMessage());
        }
    }

    @Override
    public List<Usuario> getUsuariosPorEmpresa(Integer empresaId) throws ServiceException, DaoException {
        try {
            if (empresaService.validarEmpresaIdNoNulo(empresaId)) {
                return usuarioDao.getUsuariosPorEmpresa(empresaId);
            }
            return new ArrayList<Usuario>();
        } catch (DaoException d) {
            throw d;
        } catch (ServiceException s) {
            throw s;
        } catch (Exception e) {
            throw new ServiceException(e.hashCode(), e.getMessage());
        }
    }

    @Override
    public Usuario getUsuarioPorEmpresaUsername(Empresa empresa, String username) throws ServiceException, DaoException {
        try {
            if (empresaService.validarEmpresaNoNulo(empresa)) {
                return this.getUsuarioPorEmpresaUsername(empresa.getEmpresaId(), username);
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
    public Usuario getUsuarioPorEmpresaUsername(Integer empresaId, String username) throws ServiceException, DaoException {
        try {
            if (empresaService.validarEmpresaIdNoNulo(empresaId) && validarUsernameNoNulo(username)) {
                return usuarioDao.getUsuarioPorEmpresaUsername(empresaId, username);
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
    public boolean validarUsuarioNoNulo(Usuario usuario) throws ServiceException {
        // Se verifica que el usuario no sea nulo
        if (usuario == null) {
            throw new ServiceException("consultorio.service.error.1800", locale);
        }
        return true;
    }

    @Override
    public boolean validarUsuarioIdNoRepetido(Integer usuarioId)
            throws ServiceException, DaoException {
        // Se verifica que el ID no sea nulo
        if (validarUsuarioIdNoNulo(usuarioId)) {
            // Se verifica que el ID del usuario no este repetido
            Usuario usuarioAux = this.getUsuarioPorId(usuarioId);
            if (usuarioAux != null) {
                throw new ServiceException("consultorio.service.error.1801",
                        locale);
            }
        }
        return true;
    }

    @Override
    public boolean validarUsuarioIdNoNulo(Integer usuarioId) throws ServiceException {
        // Se verifica que el ID no sea nulo
        if (usuarioId == null) {
            throw new ServiceException("consultorio.service.error.1802", locale);
        }
        return true;
    }

    @Override
    public boolean validarUsername(String username) throws ServiceException {
        // Se verifica que el username no sea nulo
        if (validarUsernameNoNulo(username)) {
            // Se verifica la longitud del username
            int longitud = StringUtils.length(username);
            if (longitud < 5 || longitud > 20) {
                throw new ServiceException("consultorio.service.error.1804", locale);
            }
            // Se verifica que el username sea alfabético
            if (!StringUtils.isAlpha(username)) {
                throw new ServiceException("consultorio.service.error.1805", locale);
            }
        }
        return true;
    }

    @Override
    public boolean validarUsernameNoNulo(String username) throws ServiceException {
        // Se verifica que el username no sea nulo
        if (StringUtils.isBlank(username)) {
            throw new ServiceException("consultorio.service.error.1803", locale);
        }
        return true;
    }

    @Override
    public boolean validarPassword(String password) throws ServiceException {
        // Se verifica que el password no sea nulo
        if (validarPasswordNoNulo(password)) {
            // Se verifica la longitud del password
            int longitud = StringUtils.length(password);
            if (longitud < 5 || longitud > 20) {
                throw new ServiceException("consultorio.service.error.1807", locale);
            }
            // Se verifica que el password sea alfanumerico con/sin espacios
            if (!StringUtils.isAlphanumericSpace(password)) {
                throw new ServiceException("consultorio.service.error.1808", locale);
            }
        }
        return true;
    }

    @Override
    public boolean validarPasswordNoNulo(String password) throws ServiceException {
        // Se verifica que el password no sea nulo
        if (StringUtils.isBlank(password)) {
            throw new ServiceException("consultorio.service.error.1806", locale);
        }
        return true;
    }

    @Override
    public boolean validarUsernameNoRepetido(Integer empresaId, String username) throws ServiceException, DaoException {
        // Se verifica que el usuario no este repetido para una misma empresa
        Usuario usuarioAux = this.getUsuarioPorEmpresaUsername(empresaId, username);
        if (usuarioAux != null) {
            throw new ServiceException("consultorio.service.error.1809", locale);
        }
        return true;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public EmpresaService getEmpresaService() {
        return empresaService;
    }

    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }
}
