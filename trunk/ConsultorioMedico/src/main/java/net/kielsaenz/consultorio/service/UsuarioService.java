package net.kielsaenz.consultorio.service;

import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Usuario;
import net.kielsaenz.consultorio.service.exception.ServiceException;

/**
 *
 * @author Kiel
 */
public interface UsuarioService {

    public Locale getLocale();

    public void setLocale(Locale locale);

    public boolean insertar(Usuario usuario) throws ServiceException, DaoException;

    public boolean actualizar(Usuario usuario) throws ServiceException, DaoException;

    public boolean eliminar(Usuario usuario) throws ServiceException, DaoException;

    public Usuario getUsuarioPorId(Integer usuarioId) throws ServiceException, DaoException;

    public List<Usuario> getUsuarios() throws ServiceException, DaoException;

    public List<Usuario> getUsuariosPorEmpresa(Empresa empresa)
            throws ServiceException, DaoException;

    public List<Usuario> getUsuariosPorEmpresa(Integer empresaId)
            throws ServiceException, DaoException;

    public Usuario getUsuarioPorEmpresaUsername(Empresa empresa, String username)
            throws ServiceException, DaoException;

    public Usuario getUsuarioPorEmpresaUsername(Integer empresaId,
            String username) throws ServiceException, DaoException;

    public boolean validarUsuarioNoNulo(Usuario usuario) throws ServiceException;

    public boolean validarUsuarioIdNoRepetido(Integer usuarioId)
            throws ServiceException, DaoException;

    public boolean validarUsuarioIdNoNulo(Integer usuarioId) throws ServiceException;

    public boolean validarUsername(String username) throws ServiceException;

    public boolean validarUsernameNoNulo(String username) throws ServiceException;

    public boolean validarPassword(String password) throws ServiceException;

    public boolean validarPasswordNoNulo(String password) throws ServiceException;

    public boolean validarUsernameNoRepetido(Integer empresaId, String username) throws ServiceException, DaoException;
}
