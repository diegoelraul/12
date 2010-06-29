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

    public boolean insertar(Persona persona) throws DaoException;

    public boolean eliminar(Persona persona) throws DaoException;

    public boolean actualizar(Persona persona) throws DaoException;
}
