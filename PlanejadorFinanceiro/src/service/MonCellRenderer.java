package service;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MonCellRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);


        
        if (this.getText().contains("Total") || this.getText().contains("LongoPrazo") || this.getText().contains("Rendimentos") || this.getText().contains("Ocasional") || this.getText().contains("Despesas")) {
        	
            setFont(new Font("Monospaced", Font.BOLD, 19));
        } 
        if (this.getText().contains("Resultado")) {
        	
            setFont(new Font("Monospaced", Font.BOLD, 19));
        } 
        
        return this;
    }
}