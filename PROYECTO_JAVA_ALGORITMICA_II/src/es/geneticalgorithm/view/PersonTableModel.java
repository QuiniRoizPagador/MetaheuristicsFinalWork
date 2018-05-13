/**
 * Copyright (c) 2018, Joaquín Roiz Pagador y colaboradores.
 * <p>
 * Libre distribución haciendo referencia al autor.
 *
 *
 */
package es.geneticalgorithm.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import es.geneticalgorithm.model.Cliente;
import es.geneticalgorithm.model.Persona;

/**
 * Clase modelo para una tabla de la vista principal.
 *
 * @param <E> Tipo genérico que herede de Persona con el que trabajar
 * @see TableModel
 * @see List
 * @see Cliente
 *
 *
 * @author Quini Roiz
 */
public class PersonTableModel<E extends Persona> implements TableModel {

    private List<E> data = new ArrayList<>();
    private final List<TableModelListener> listeners = new ArrayList<>();

    /**
     * Devuelve el listado de datos.
     *
     * @return listado de datos.
     *
     * @see List
     */
    public List<E> getClientes() {
        return data;
    }

    /**
     * Actualiza el valor del listado de datos
     *
     * @param data nuevo valor del listado de datos.
     *
     * @see List
     * @see Cliente
     */
    public void setPersona(List<E> data) {
        this.data = data;
        fireContentsChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String value = null;
        switch (columnIndex) {
            case 0:
                value = "Nombre";
                break;
            case 1:
                value = "Apellidos";
                break;
            case 2:
                value = "Teléfono";
                break;
            case 3:
                value = "Correo";
                break;
        }
        return value;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data.get(columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; //No permito editar celdas
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        E p = data.get(rowIndex);
        String value = null;
        switch (columnIndex) {
            case 0:
                value = p.getNombre();
                break;
            case 1:
                value = p.getApellido1() + " " + p.getApellidos2();
                break;
            case 2:
                value = p.getTelefono();
                break;
            case 3:
                value = p.getCorreo();
                break;
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    /**
     * Disparador que actualizará los nuevos datos de la tabla.
     */
    protected void fireContentsChanged() {
        //TableModelEvent event = new TableModelEvent(this,0,getRowCount());
        TableModelEvent event = new TableModelEvent(this, 0, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        listeners.forEach((listener) -> {
            listener.tableChanged(event);
        });
    }
}
