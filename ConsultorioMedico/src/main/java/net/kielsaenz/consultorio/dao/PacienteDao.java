package net.kielsaenz.consultorio.dao;

import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Paciente;

/**
 *
 * @author Kiel
 */
public interface PacienteDao {

    public Locale getLocale();

    public void setLocale(Locale locale);

    public List<Paciente> getPacientes() throws DaoException;

    public Paciente getPacientePorId(Integer pacienteId) throws DaoException;

    public List<Paciente> getPacientesPorEmpresa(Empresa empresa)
            throws DaoException;

    public List<Paciente> getPacientesPorEmpresa(Integer empresaId)
            throws DaoException;

    public List<Paciente> getPacientesPorEmpresaNombre(Empresa empresa,
            String nombre, boolean aplicarLike) throws DaoException;

    public List<Paciente> getPacientesPorEmpresaNombre(Integer empresaId,
            String nombre, boolean aplicarLike) throws DaoException;

    public List<Paciente> getPacientesPorEmpresaApPaterno(Empresa empresa,
            String apellidoPaterno, boolean aplicarLike) throws DaoException;

    public List<Paciente> getPacientesPorEmpresaApPaterno(Integer empresaId,
            String apellidoPaterno, boolean aplicarLike) throws DaoException;

    public List<Paciente> getPacientesPorEmpresaApMaterno(Empresa empresa,
            String apellidoMaterno, boolean aplicarLike) throws DaoException;

    public List<Paciente> getPacientesPorEmpresaApMaterno(Integer empresaId,
            String apellidoMaterno, boolean aplicarLike) throws DaoException;

    public List<Paciente> getPacientesPorEmpresaNroDocumento(Empresa empresa,
            String nroDocumento, boolean aplicarLike) throws DaoException;

    public List<Paciente> getPacientesPorEmpresaNroDocumento(Integer empresaId,
            String nroDocumento, boolean aplicarLike) throws DaoException;

    public boolean insertar(Paciente paciente) throws DaoException;

    public boolean eliminar(Paciente paciente) throws DaoException;

    public boolean actualizar(Paciente paciente) throws DaoException;
}
