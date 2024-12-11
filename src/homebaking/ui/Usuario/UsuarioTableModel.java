package homebaking.ui.Usuario;

import homebaking.model.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UsuarioTableModel extends AbstractTableModel {

    /**
     * INDICES DE MIS COLUMNAS
     */
    private static final int COLUMNA_ID = 0;
    private static final int COLUMNA_NOMBRE = 1;
    private static final int COLUMNA_EMAIL = 2;

    /**
     * NOMBRES DE LOS ENCABEZADOS
     */
    private String[] nombresColumnas = {"ID", "Nombre", "Email"};
    /**
     * TIPOS DE CADA COLUMNA (EN EL MISMO ORDEN DE LOS ENCABEZADOS)
     */
    private Class[] tiposColumnas = {Integer.class, String.class, String.class};

    private List<User> contenido;

    /**
     * CONSTRUCTOR VACIO
     */
    public UsuarioTableModel() {
        contenido = new ArrayList<User>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public UsuarioTableModel(List<User> contenidoInicial) {
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

        User u = contenido.get(rowIndex);

        Object result = null;
        switch(columnIndex) {
            case COLUMNA_ID:
                result = u.getId();
                break;
            case COLUMNA_NOMBRE:
                result = u.getUsername();
                break;
            case COLUMNA_EMAIL:
                result = u.getEmail();
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
    public List<User> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<User> contenido) {
        this.contenido = contenido;
    }
}
