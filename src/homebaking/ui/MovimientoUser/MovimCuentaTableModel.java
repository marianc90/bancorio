package homebaking.ui.MovimientoUser;


import homebaking.model.Movimiento;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimCuentaTableModel extends AbstractTableModel {

    /**
     * INDICES DE MIS COLUMNAS
     */
    private static final int COLUMNA_ID = 0;
    private static final int COLUMNA_FECHA = 1;
    private static final int COLUMNA_DESCRIPCION = 2;
    private static final int COLUMNA_MONTO = 3;
    private static final int COLUMNA_TIPO = 4;
    private static final int COLUMNA_CTAORIGEN = 5;
    private static final int COLUMNA_CTADSTINO = 6;

    /**
     * NOMBRES DE LOS ENCABEZADOS
     */
    private String[] nombresColumnas = {"ID","FECHA","DESC.","MONTO","TIPO", "CTA ORIGEN", "CTA DEST."};
    /**
     * TIPOS DE CADA COLUMNA (EN EL MISMO ORDEN DE LOS ENCABEZADOS)
     */
    private Class[] tiposColumnas = {Long.class, Date.class, String.class, double.class, String.class, Integer.class, Integer.class};

    private List<Movimiento> contenido;

    /**
     * CONSTRUCTOR VACIO
     */
    public MovimCuentaTableModel() {
        contenido = new ArrayList<Movimiento>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public MovimCuentaTableModel(List<Movimiento> contenidoInicial) {
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

        Movimiento m = contenido.get(rowIndex);

        Object result = null;
        switch(columnIndex) {
            case COLUMNA_ID:
                result = m.getId();
                break;
            case COLUMNA_FECHA:
                result = m.getFecha();
                break;
            case COLUMNA_DESCRIPCION:
                result = m.getDescripcion();
                break;
            case COLUMNA_MONTO:
                result = m.getMonto();
                break;
            case COLUMNA_TIPO:
                result = m.getTipo();
                break;
            case COLUMNA_CTAORIGEN:
                result = (m.getCuentaOrigen() != null) ? m.getCuentaOrigen().getNumero() : "";
                break;
            case COLUMNA_CTADSTINO:
                result = (m.getCuentaDestino() != null) ? m.getCuentaDestino().getNumero() : "";
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
    public List<Movimiento> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Movimiento> contenido) {
        this.contenido = contenido;
    }
}
