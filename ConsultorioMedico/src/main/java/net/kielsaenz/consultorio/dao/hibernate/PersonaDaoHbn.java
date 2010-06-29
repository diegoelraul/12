package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.PersonaDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Persona;

/**
 *
 * @author Kiel
 */
public class PersonaDaoHbn implements PersonaDao{

    public Locale getLocale() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLocale(Locale locale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Persona> getPersonas() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Persona getPersonaPorId(Integer personaId) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Persona> getPersonasPorEmpresa(Empresa empresa) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Persona> getPersonasPorEmpresa(Integer empresaId) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean insertar(Persona persona) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean eliminar(Persona persona) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean actualizar(Persona persona) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
