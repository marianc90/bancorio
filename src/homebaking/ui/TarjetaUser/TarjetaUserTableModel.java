package homebaking.ui.TarjetaUser;


import homebaking.model.Tarjeta;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TarjetaUserTableModel extends AbstractTableModel {

    /**
     * INDICES DE MIS COLUMNAS
     */
    private static final int COLUMNA_NUMERO = 0;
    private static final int COLUMNA_TIPO = 1;
    private static final int COLUMNA_SALDO = 2;
    private static final int COLUMNA_LIMITE = 3;
    private static final int COLUMNA_TITULAR = 4;

    /**
     * NOMBRES DE LOS ENCABEZADOS
     */
    private String[] nombresColumnas = {"NUMERO","TIPO","SALDO","LIMITE","TITULAR"};
    /**
     * TIPOS DE CADA COLUMNA (EN EL MISMO ORDEN DE LOS ENCABEZADOS)
     */
    private Class[] tiposColumnas = {Integer.class, String.class, double.class, double.class, String.class};

    private List<Tarjeta> contenido;

    /**
     * CONSTRUCTOR VACIO
     */
    public TarjetaUserTableModel() {
        contenido = new ArrayList<Tarjeta>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public TarjetaUserTableModel(List<Tarjeta> contenidoInicial) {
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

        Tarjeta t = contenido.get(rowIndex);

        Object result = null;
        switch(columnIndex) {
            case COLUMNA_NUMERO:
                result = t.getNumero();
                break;
            case COLUMNA_TIPO:
                result = t.getTipo();
                break;
            case COLUMNA_LIMITE:
                result = t.getDisponible();
                break;
            case COLUMNA_SALDO:
                result = t.getSaldo();
                break;
            case COLUMNA_TITULAR:
                result = t.getTitular().getUsername();
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
    public List<Tarjeta> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Tarjeta> contenido) {
        this.contenido = contenido;
    }
}
