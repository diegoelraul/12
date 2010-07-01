package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.MedicoDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Medico;

/**
 *
 * @author Kiel
 */
public class MedicoDaoHbn implements MedicoDao{

    public Locale getLocale() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLocale(Locale locale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicos() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Medico getMedicoPorId(Integer medicoId) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresa(Empresa empresa) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresa(Integer empresaId) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaNombre(Empresa empresa, String nombre, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaNombre(Integer empresaId, String nombre, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaApPaterno(Empresa empresa, String apellidoPaterno, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaApPaterno(Integer empresaId, String apellidoPaterno, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaApMaterno(Empresa empresa, String apellidoMaterno, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaApMaterno(Integer empresaId, String apellidoMaterno, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaNroDocumento(Empresa empresa, String nroDocumento, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaNroDocumento(Integer empresaId, String nroDocumento, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaNroColegiatura(Empresa empresa, String nroColegiatura, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medico> getMedicosPorEmpresaNroColegiatura(Integer empresaId, String nroColegiatura, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean insertar(Medico medico) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean eliminar(Medico medico) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean actualizar(Medico medico) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
