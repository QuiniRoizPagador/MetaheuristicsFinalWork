/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Clase abstracta para implementar dos tipos de personas: Cliente y Empleado,
 * que se usarán a modo de Paciente y Médico.
 *
 * @author Quini Roiz
 */
public abstract class Persona {

    private int id;
    private String nombre;
    private String apellido1;
    private String apellidos2;
    private String dni;
    private char sexo;
    private String direccion;
    private String municipio;
    private String provincia;
    private int cp;
    private String movil;
    private String telefono;
    private String correo;
    private Calendar nacimiento;
    private int dir_y;
    private int dir_x;

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
    protected Persona(int id, String nombre, String apellido1, String apellidos2, String dni,
                char sexo, String direccion, String municipio, String provincia,
                int cp, String movil, String telefono, String correo, Calendar nacimiento, int dir_y, int dir_x) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellidos2 = apellidos2;
        this.dni = dni;
        this.sexo = sexo;
        this.direccion = direccion;
        this.municipio = municipio;
        this.provincia = provincia;
        this.cp = cp;
        this.movil = movil;
        this.telefono = telefono;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.dir_y = dir_y;
        this.dir_x = dir_x;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellidos2
     */
    public String getApellidos2() {
        return apellidos2;
    }

    /**
     * @param apellidos2 the apellidos2 to set
     */
    public void setApellidos2(String apellidos2) {
        this.apellidos2 = apellidos2;
    }

    /**
     * @return the sexo
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the cp
     */
    public int getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * @return the movil
     */
    public String getMovil() {
        return movil;
    }

    /**
     * @param movil the movil to set
     */
    public void setMovil(String movil) {
        this.movil = movil;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the nacimiento
     */
    public Calendar getNacimiento() {
        return nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void setNacimiento(Calendar nacimiento) {
        this.nacimiento = nacimiento;
    }

    /**
     * @return the dir_y
     */
    public int getDir_y() {
        return dir_y;
    }

    /**
     * @param dir_y the dir_y to set
     */
    public void setDir_y(int dir_y) {
        this.dir_y = dir_y;
    }

    /**
     * @return the dir_x
     */
    public int getDir_x() {
        return dir_x;
    }

    /**
     * @param dir_x the dir_x to set
     */
    public void setDir_x(int dir_x) {
        this.dir_x = dir_x;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "\nSexo: " + getSexo()
                    + "\nDirección: " + getDireccion()
                    + "\nMunicipio: " + getMunicipio()
                    + "\nProvincia: " + getProvincia()
                    + "\nCódigo Postal: " + getCp()
                    + "\nMóvil: " + getMovil()
                    + "\nTeléfono: " + getTelefono()
                    + "\nCorreo: " + getCorreo()
                    + "\nNacimiento: " + sdf.format(getNacimiento().getTime());
    }

}
