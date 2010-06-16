package net.kielsaenz.consultorio.dao;

import java.util.List;
import net.kielsaenz.consultorio.model.Especialidad;

/**
 * Esta clase contiene los métodos a ser implementados por las clases de persistencia
 * @author dew - Grupo 04
 */
public interface EspecialidadDao {

    /**
     * Obtiene una especialidad por su id
     * @param especialidadId identificador de especialidad
     * @return Especialidad datos de la especialidad
     */
    public Especialidad getEspecialidadPorId(Integer especialidadId);

    /**
     * Obtiene todas las especialidades registradas
     * @return List<Especialidad> lista de especialidad
     */
    public List<Especialidad> getEspecialidades();

}
