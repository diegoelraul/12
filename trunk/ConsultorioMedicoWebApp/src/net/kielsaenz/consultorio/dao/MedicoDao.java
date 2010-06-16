package net.kielsaenz.consultorio.dao;

import java.util.List;
import net.kielsaenz.consultorio.model.Especialidad;
import net.kielsaenz.consultorio.model.Medico;

/**
 * Esta clase contiene los métodos a ser implementados por las clases de persistencia
 * @author dew - Grupo 04
 */
public interface MedicoDao {

    /**
     * Obtiene un médico por su id
     * @param medicoId identificador del medico
     * @return Medico datos del medico
     */
    Medico getMedicoPorId(Integer medicoId);

    /**
     * Obtiene todas los médicos de una especialidad específica
     * @param especialidad datos de la especialidad
     * @return List<Medico> lista de medicos encontrados
     */
    List<Medico> getMedicosPorEspecialidad(Especialidad especialidad);

    /**
     * Obtiene todas los médicos de una especialidad específica
     * @param especialidadId identificador de la especialidad
     * @return List<Medico> lista de medicos
     */
    List<Medico> getMedicosPorEspecialidad(Integer especialidadId);

}
