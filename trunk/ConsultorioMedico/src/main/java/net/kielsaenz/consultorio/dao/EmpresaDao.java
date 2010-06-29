package net.kielsaenz.consultorio.dao;

import java.util.List;
import java.util.Locale;

import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;

public interface EmpresaDao {

    public Locale getLocale();

    public void setLocale(Locale locale);

    public List<Empresa> getEmpresas() throws DaoException;

    public Empresa getEmpresaPorId(Integer empresaId) throws DaoException;

    public Empresa getEmpresaPorRUC(String ruc) throws DaoException;

    public List<Empresa> getEmpresaPorRazonSocial(String razonSocial, boolean aplicarLike, int tipo) throws DaoException;

    public boolean insertar(Empresa empresa) throws DaoException;

    public boolean eliminar(Empresa empresa) throws DaoException;

    public boolean actualizar(Empresa empresa) throws DaoException;
}
