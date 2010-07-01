package net.kielsaenz.consultorio.dao;

import java.util.List;
import java.util.Locale;

import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Usuario;

public interface UsuarioDao {

    public Locale getLocale();

    public void setLocale(Locale locale);

    public boolean insertar(Usuario usuario) throws DaoException;

    public boolean actualizar(Usuario usuario) throws DaoException;

    public boolean eliminar(Usuario usuario) throws DaoException;

    public Usuario getUsuarioPorId(Integer usuarioId) throws DaoException;

    public List<Usuario> getUsuarios() throws DaoException;

    public List<Usuario> getUsuariosPorEmpresa(Empresa empresa)
            throws DaoException;

    public List<Usuario> getUsuariosPorEmpresa(Integer empresaId)
            throws DaoException;

    public Usuario getUsuarioPorEmpresaUsername(Empresa empresa, String username)
            throws DaoException;

    public Usuario getUsuarioPorEmpresaUsername(Integer empresaId,
            String username) throws DaoException;
}
