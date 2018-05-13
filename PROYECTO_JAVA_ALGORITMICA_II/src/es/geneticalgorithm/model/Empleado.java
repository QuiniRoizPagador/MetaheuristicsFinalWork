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
 * Clase que extenderá de la clase abstracta Persona para implementar Empleado,
 * que se usará a modo de Médico.
 *
 * @author Quini Roiz
 */
public class Empleado extends Persona implements Comparable<Empleado> {

    private float coste;
    private int max_clientes;

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
     * @param coste coste de la contratación del empleado
     * @param max_clientes número máximo de pacientes o clientes que puede tener
     *
     * @see Calendar
     */
    public Empleado(int id, String nombre, String apellido1, String apellidos2, String dni,
                char sexo, String direccion, String municipio, String provincia,
                int cp, String movil, String telefono, String correo, Calendar nacimiento,
                int dir_y, int dir_x, float coste, int max_clientes) {
        super(id, nombre, apellido1, apellidos2, dni, sexo, direccion, municipio, provincia, cp,
                    movil, telefono, correo, nacimiento, dir_y, dir_x);
        this.coste = coste;
        this.max_clientes = max_clientes;
    }

    /**
     * @return the coste
     */
    public float getCoste() {
        return coste;
    }

    /**
     * @param coste the coste to set
     */
    public void setCoste(float coste) {
        this.coste = coste;
    }

    /**
     * @return the max_clientes
     */
    public int getMax_clientes() {
        return max_clientes;
    }

    /**
     * @param max_clientes the max_clientes to set
     */
    public void setMax_clientes(int max_clientes) {
        this.max_clientes = max_clientes;
    }

    @Override
    public int compareTo(Empleado o) {
        int res;
        if (getCoste() - o.getCoste() > 0) {
            res = 1;
        } else if (getCoste() - o.getCoste() < 0) {
            res = -1;
        } else {
            res = 0;
        }
        return res;
    }

    @Override
    public String toString() {
        return "\n----------------------------"
                    + "\n----- EMPLEADO " + getNombre() + " " + getApellido1() + " " + getApellidos2() + " ------"
                    + "\n----------------------------"
                    + super.toString()
                    + "\nCoste: " + getCoste()
                    + "\nNúmero máximo de pacientes: " + getMax_clientes();
    }

}
