package net.kielsaenz.consultorio.dao;

import java.util.Date;
import java.util.List;
import net.kielsaenz.consultorio.model.Cita;
import net.kielsaenz.consultorio.model.Especialidad;
import net.kielsaenz.consultorio.model.Horario;
import net.kielsaenz.consultorio.model.Medico;
import net.kielsaenz.consultorio.model.Paciente;
import net.kielsaenz.consultorio.model.Persona;

/**
 * Esta clase contiene los métodos a ser implementados por las clases de persistencia
 * @author dew - Grupo 04
 */
public interface CitaDao {

    /**
     * Actualiza una cita
     * @param cita Cita a actualizar
     * @return boolean Condicion de actualizacion -> true=exito/false=fracaso
     */
    public boolean actualizarCita(Cita cita);

    /**
     * Elimina una cita
     * @param cita Cita a eliminar
     * @return boolean Condicion de actualizacion -> true=exito/false=fracaso
     */
    public boolean eliminarCita(Cita cita);

    /**
     * Obtiene la cita de un horario específico
     * @param horario Horario a buscar
     * @return Cita correspndiente al horario
     */
    public Cita getCitaPorHorario(Horario horario);

    /**
     * Obtiene la cita de un horario específico
     * @param horarioId Id del horario a buscar
     * @return Cita correspondiente al horario
     */
    public Cita getCitaPorHorario(Integer horarioId);

    /**
     * Obtiene una cita por su id
     * @param citaId Id de la cita a buscar
     * @return Cita obtenida por el id
     */
    public Cita getCitaPorId(Integer citaId);

    /**
     * Obtiene la cita pendiente de un paciente para una semana, medico y especialidad
     * @param paciente datos del paciente
     * @param fechaInicio fecha de inicio de la cita
     * @param fechaFin fecha de fin de la cita
     * @param especialidad datos de la especialidad medica
     * @param medico datos del medico
     * @return Cita en la semana
     */
    public Cita getCitaSemPendiente(Paciente paciente, Date fechaInicio, Date fechaFin, Medico medico, Especialidad especialidad);

    /**
     * Obtiene la cita pendiente de un paciente para una semana, medico y especialidad
     * @param pacienteId Id del paciente
     * @param fechaInicio fecha de inicio de la cita
     * @param fechaFin fecha de fin de la cita
     * @param medicoId Identificador del medico
     * @param especialidadId Identificador de la especialidad
     * @return Cita en la semana
     */
    public Cita getCitaSemPendiente(Integer pacienteId, Date fechaInicio, Date fechaFin, Integer medicoId, Integer especialidadId);

    /**
     * Obtiene todos las citas pendientes de un paciente
     * @param paciente datos del paciente
     * @return List<Cita> lista de citas pendientes
     */
    public List<Cita> getCitasPendientes(Persona paciente);

    /**
     * Obtiene todos las citas pendientes de un paciente
     * @param pacienteId identificador del paciente
     * @return List<Cita> lista de citas pendientes
     */
    public List<Cita> getCitasPendientes(Integer pacienteId);

    /**
     * Obtiene todos las citas de un médico para una fecha específica
     * @param medico datos del medico
     * @param fecha Fecha de la cita a buscar
     * @param validaHora condicion de validacion de fecha por fragmento hora
     * @return List<Cita> lista de citas obtenidas
     */
    public List<Cita> getCitasPorMedicoFecha(Medico medico, Date fecha, boolean validaHora);

    /**
     * Obtiene todos las citas de un médico para una fecha específica
     * @param medicoId Identificador del medico
     * @param validaHora condicion de validacion de fecha por fragmento hora
     * @param fecha Fecha de la cita a buscar
     * @return List<Cita> lista de citas obtenidas
     */
    public List<Cita> getCitasPorMedicoFecha(Integer medicoId, Date fecha, boolean validaHora);

    /**
     * Obtiene todos las citas de un paciente para una fecha específica
     * @param paciente datos del paciente
     * @param fecha Fecha de la cita a buscar
     * @param validaHora condicion de validacion de fecha por fragmento hora 
     * @return List<Cita> lista de citas obtenidas
     */
    public List<Cita> getCitasPorPacienteFecha(Paciente paciente, Date fecha, boolean validaHora);

    /**
     * Obtiene todos las citas de un paciente para una fecha específica
     * @param pacienteId Identificador del paciente
     * @param fecha Fecha de la cita a buscar
     * @param validaHora condicion de validacion de fecha por fragmento hora
     * @return List<Cita> lista de citas obtenidas
     */
    public List<Cita> getCitasPorPacienteFecha(Integer pacienteId, Date fecha, boolean validaHora);

    /**
     * Ingresa una cita
     * @param paciente datos del paciente
     * @param horario datos del horario
     * @return boolean Condicion de actualizacion -> true=exito/false=fracaso
     */
    public boolean insertarCita(Paciente paciente, Horario horario);

}
