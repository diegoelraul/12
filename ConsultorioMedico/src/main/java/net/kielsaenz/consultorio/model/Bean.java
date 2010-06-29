package net.kielsaenz.consultorio.model;

import java.io.Serializable;

/**
 * Clase padre de todos los modelos
 * @author dew - Grupo 04
 */
public abstract class Bean implements Serializable {

    public static String ACTIVO = "S";
    public static String NO_ACTIVO = "N";
    public static String SI = "S";
    public static String NO = "N";

    /**
     * Obtiene el nombre de la clase
     * @return nombre de clase
     */
    public String getReferencia() {
        return getClass().getName();
    }

    public abstract String toString();
    public abstract void toUpperCase();
    public abstract void toLowerCase();
}
