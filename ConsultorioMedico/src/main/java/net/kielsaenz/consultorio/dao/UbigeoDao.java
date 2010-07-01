package net.kielsaenz.consultorio.dao;

import java.util.List;
import java.util.Locale;

import net.kielsaenz.consultorio.dao.exception.DaoException;
import net.kielsaenz.consultorio.model.Departamento;
import net.kielsaenz.consultorio.model.Distrito;
import net.kielsaenz.consultorio.model.Provincia;

public interface UbigeoDao {

    public Locale getLocale();

    public void setLocale(Locale locale);

    public Departamento getDepartamentoPorId(String departamentoId)
            throws DaoException;

    public List<Departamento> getDepartamentos() throws DaoException;

    public Provincia getProvinciaPorId(String departamentoId, String provinciaId)
            throws DaoException;

    public List<Provincia> getProvinciasPorDepartamento(
            Departamento departamento) throws DaoException;

    public List<Provincia> getProvinciasPorDepartamento(String departamentoId)
            throws DaoException;

    public Distrito getDistritoPorId(String departamentoId, String provinciaId,
            String distritoId) throws DaoException;

    public List<Distrito> getDistritosPorProvincia(Provincia provincia)
            throws DaoException;

    public List<Distrito> getDistritosPorProvincia(String departamentoId,
            String provinciaId) throws DaoException;
}
