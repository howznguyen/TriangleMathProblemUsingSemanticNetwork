package TriangleMathProblemUsingSemanticNetwork.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ColorRenderer extends DefaultTableCellRenderer {
    public ColorRenderer() {
        super();
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(value.equals(-1)) {
            cell.setBackground(Color.CYAN);
        } else if(value.equals(1)){
            cell.setBackground(Color.YELLOW);
        }else{
            cell.setBackground(Color.WHITE);
        }

        return cell;
    }
}