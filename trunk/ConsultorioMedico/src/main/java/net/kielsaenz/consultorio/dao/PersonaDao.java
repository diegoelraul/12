package net.kielsaenz.consultorio.dao;

import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Persona;

/**
 *
 * @author Kiel
 */
public interface PersonaDao {

    public Locale getLocale();

    public void setLocale(Locale locale);

    public List<Persona> getPersonas() throws DaoException;

    public Persona getPersonaPorId(Integer personaId) throws DaoException;

    public List<Persona> getPersonasPorEmpresa(Empresa empresa)
            throws DaoException;

    public List<Persona> getPersonasPorEmpresa(Integer empresaId)
            throws DaoException;

    public List<Persona> getPersonasPorEmpresaNombre(Empresa empresa,
            String nombre, boolean aplicarLike) throws DaoException;

    public List<Persona> getPersonasPorEmpresaNombre(Integer empresaId,
            String nombre, boolean aplicarLike) throws DaoException;

    public List<Persona> getPersonasPorEmpresaApPaterno(Empresa empresa,
            String apellidoPaterno, boolean aplicarLike) throws DaoException;

    public List<Persona> getPersonasPorEmpresaApPaterno(Integer empresaId,
            String apellidoPaterno, boolean aplicarLike) throws DaoException;

    public List<Persona> getPersonasPorEmpresaApMaterno(Empresa empresa,
            String apellidoMaterno, boolean aplicarLike) throws DaoException;

    public List<Persona> getPersonasPorEmpresaApMaterno(Integer empresaId,
            String apellidoMaterno, boolean aplicarLike) throws DaoException;

    public List<Persona> getPersonasPorEmpresaNroDocumento(Empresa empresa,
            String nroDocumento, boolean aplicarLike) throws DaoException;

    public List<Persona> getPersonasPorEmpresaNroDocumento(Integer empresaId,
            String nroDocumento, boolean aplicarLike) throws DaoException;
}
