package net.kielsaenz.consultorio.dao;

import net.kielsaenz.consultorio.model.Paciente;

/**
 * Esta clase contiene los métodos a ser implementados por las clases de persistencia
 * @author dew - Grupo 04
 */
public interface PacienteDao {

    /**
     * Obtiene un Paciente por su Id
     * @param pacienteId identificador del paciente
     * @return Paciente datos del paciente
     */
    Paciente getPacientePorId(Integer pacienteId);

}
