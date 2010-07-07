package net.kielsaenz.consultorio.dao;

import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Especialidad;

/**
 *
 * @author Kiel
 */
public interface EspecialidadDao {

    public Locale getLocale();

    public void setLocale(Locale locale);

    public List<Especialidad> getEspecialidades() throws DaoException;

    public Especialidad getEspecialidadPorId(Integer especialidadId) throws DaoException;

    public List<Especialidad> getEspecialidadesPorEmpresa(Empresa empresa)
            throws DaoException;

    public List<Especialidad> getEspecialidadesPorEmpresa(Integer empresaId)
            throws DaoException;

    public List<Especialidad> getEspecialidadesPorEmpresaNombre(Empresa empresa,
            String nombre, boolean aplicarLike) throws DaoException;

    public List<Especialidad> getEspecialidadesPorEmpresaNombre(Integer empresaId,
            String nombre, boolean aplicarLike) throws DaoException;

    public boolean insertar(Especialidad especialidad) throws DaoException;

    public boolean eliminar(Especialidad especialidad) throws DaoException;

    public boolean actualizar(Especialidad especialidad) throws DaoException;

}
