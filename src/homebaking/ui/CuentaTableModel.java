package homebaking.ui;

import homebaking.model.Cuenta;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CuentaTableModel extends AbstractTableModel {

    /**
     * INDICES DE MIS COLUMNAS
     */
    private static final int COLUMNA_NUMERO = 0;
    private static final int COLUMNA_TIPO = 1;
    private static final int COLUMNA_SALDO = 2;
    private static final int COLUMNA_TITULAR = 3;

    /**
     * NOMBRES DE LOS ENCABEZADOS
     */
    private String[] nombresColumnas = {"NUMERO", "TIPO", "SALDO", "TITULAR"};
    /**
     * TIPOS DE CADA COLUMNA (EN EL MISMO ORDEN DE LOS ENCABEZADOS)
     */
    private Class[] tiposColumnas = {Integer.class, String.class, double.class, String.class};

    private List<Cuenta> contenido;

    /**
     * CONSTRUCTOR VACIO
     */
    public CuentaTableModel() {
        contenido = new ArrayList<Cuenta>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public CuentaTableModel(List<Cuenta> contenidoInicial) {
        contenido = contenidoInicial;
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    /**
     * OTRO METODO QUE HAY QUE PISAR
     */
    public int getRowCount() {
        return contenido.size();
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public Object getValueAt(int rowIndex, int columnIndex) {

        Cuenta c = contenido.get(rowIndex);

        Object result = null;
        switch(columnIndex) {
            case COLUMNA_NUMERO:
                result = c.getNumero();
                break;
            case COLUMNA_TIPO:
                result = c.getTipo();
                break;
            case COLUMNA_SALDO:
                result = c.getSaldo();
                break;
            case COLUMNA_TITULAR:
                result = c.getTitular().getUsername();
                break;
            default:
                result = new String("");
        }

        return result;
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public String getColumnName(int col) {
        return nombresColumnas[col];
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public Class getColumnClass(int col) {
        return tiposColumnas[col];
    }




    /**
     * GETTER DE MIS FILAS
     * @return
     */
    public List<Cuenta> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Cuenta> contenido) {
        this.contenido = contenido;
    }
}
