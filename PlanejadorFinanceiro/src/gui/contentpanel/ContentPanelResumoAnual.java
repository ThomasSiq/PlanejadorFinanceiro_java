package gui.contentpanel;

import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;

public class ContentPanelResumoAnual extends JPanel {
	private JTable table;
	private JScrollPane tablePanel;
	/**
	 * Create the panel.
	 */
	public ContentPanelResumoAnual() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		table = new JTable();
				
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				JPanel component  = (JPanel) e.getComponent().getParent();
				tablePanel.setBounds(new Rectangle(0,0, component.getBounds().width, component.getBounds().height));
				table.setBounds(tablePanel.getBounds());
			}
		});
		
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Descri\u00E7\u00E3o", "Mensal [R$] (X12)", "Ocasional [R$]", "Total Anual [R$]"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(104);
		table.getColumnModel().getColumn(1).setPreferredWidth(159);
		table.getColumnModel().getColumn(2).setPreferredWidth(112);
		table.getColumnModel().getColumn(3).setPreferredWidth(127);
		setLayout(new BorderLayout(0, 0));
		tablePanel = new JScrollPane(table);
		add(tablePanel);
		
		

	}
	


}
