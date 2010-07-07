/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.kielsaenz.consultorio.dao.springjdbc;

import java.util.List;
import java.util.Locale;
import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Empresa;

/**
 *
 * @author Kiel
 */
public class EmpresaDaoSpringJdbc implements EmpresaDao{

    private Locale locale;

    public EmpresaDaoSpringJdbc(){
        this(Locale.getDefault());
    }

    public EmpresaDaoSpringJdbc(Locale locale){
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Empresa> getEmpresas() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Empresa getEmpresaPorId(Integer empresaId) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Empresa getEmpresaPorRUC(String ruc) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Empresa> getEmpresaPorRazonSocial(String razonSocial, boolean aplicarLike) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean insertar(Empresa empresa) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean eliminar(Empresa empresa) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean actualizar(Empresa empresa) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
