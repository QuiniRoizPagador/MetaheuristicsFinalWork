/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model;

import java.util.Calendar;

/**
 * Clase que extenderá de la clase abstracta Persona para implementar Cliente,
 * que se usará a modo de Paciente.
 *
 * @author Quini Roiz
 */
public class Cliente extends Persona implements Comparable<Persona> {

    /**
     * Constructor con todos los parámetros necesarios para instanciar el objeto
     * a partir de una lectura de fichero.
     *
     * @param id identificación de la persona.
     * @param nombre denominación personal de la persona.
     * @param apellido1 primer apellido de la persona.
     * @param apellidos2 segundo apellido de la persona.
     * @param dni NIF o identificación persona nacional de la persona.
     * @param sexo identificación sexual de la persona.
     * @param direccion lugar de residencia de la persona.
     * @param municipio municipio al que pertenece la persona.
     * @param provincia provincia a la que pertenece el municipio.
     * @param cp código postal del lugar de residencia.
     * @param movil teléfono móvil para contacto.
     * @param telefono teléfono de contacto personal.
     * @param correo correo personal de la persona.
     * @param nacimiento fecha de nacimiento de la persona.
     * @param dir_y coordenada y del lugar de residencia.
     * @param dir_x coordenada x del lugar de residencia.
     *
     * @see Calendar
     */
    public Cliente(int id, String nombre, String apellido1, String apellidos2, String dni,
                char sexo, String direccion, String municipio, String provincia,
                int cp, String movil, String telefono, String correo, Calendar nacimiento, int dir_y, int dir_x) {
        super(id, nombre, apellido1, apellidos2, dni, sexo, direccion,
                    municipio, provincia, cp, movil, telefono, correo,
                    nacimiento, dir_y, dir_x);
    }

    @Override
    public String toString() {
        return "\n----------------------------"
                    + "\n----- CLIENTE " + getNombre() + " " + getApellido1() + " " + getApellidos2() + " ------"
                    + "\n----------------------------"
                    + super.toString();
    }

    @Override
    public int compareTo(Persona o) {
        return getDni().compareTo(o.getDni());
    }

}
