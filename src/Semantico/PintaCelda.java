package Semantico;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Javier
 */
public class PintaCelda extends DefaultTableCellRenderer {

//    Vista v = new Vista();
    Analizador_Sem a = new Analizador_Sem();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setOpaque(true);

        if (table.getValueAt(row, column) == String.valueOf(false)) {//renglon 3 columna
            this.setForeground(Color.RED);
        } else {
            this.setForeground(Color.BLACK);
        }

        return this;
    }
}
