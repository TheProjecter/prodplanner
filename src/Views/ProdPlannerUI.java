package Views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controllers.TaskController;

public class ProdPlannerUI {

	private JFrame jFrame = null;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JTable jTable = null;
	private int rowCount=0;
	TaskController taskController = new TaskController();  //  @jve:decl-index=0:
    DefaultTableModel model = new DefaultTableModel();


	public ProdPlannerUI(String title)
	{
		getJFrame(title);
	}
	
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame(String title) {
		if (jFrame == null) {
			
			
			jFrame = new JFrame(title);
			jFrame.setSize(new Dimension(708, 331));
			jFrame.setContentPane(getJContentPane());
			jFrame.setVisible(true);
			
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
 			jContentPane.setLayout(null);
 			jContentPane.add(getJButton(), null);
 			jContentPane.add(getJTable(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(30, 232, 106, 31));
			jButton.setText("New Task");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					taskController.addTask();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					System.out.println(jTable.getRowCount());
					System.out.println(jTable.getColumnCount());
					String[] data = new String[]{"Costumer", "Duration", "Start", "End", "Earliest", "Latest"}; 

					model.addRow(data);
					//model.insertRow(rowCount++, new Object[]{"fd","fd","fd","sd","as","asd"});
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
//			String[] titles = new String[]{"Costumer", "Duration", "Start", "End", "Earliest", "Latest"}; 
//			Object[][] data = {{"", "", "", "", "", ""}};
//			Vector<String> vec = new Vector<String>();
			String[] titles = new String[6]; 
			model.setColumnIdentifiers(titles);

			String[] data = new String[]{"Costumer", "Duration", "Start", "End", "Earliest", "Latest"}; 
			model.addRow(data);

//			model.setColumnIdentifiers(vec);
//			vec.addElement("Costumer");
//			vec.addElement("Duration");
//			vec.addElement("Start");
//			vec.addElement("End");
//			vec.addElement("Earliest");
//			vec.addElement("Latest");
		
			jTable = new JTable(model);
			
			jTable.setBounds(new Rectangle(300, 197, 375, 80));
			
			model.addTableModelListener(new javax.swing.event.TableModelListener() {
				public void tableChanged(javax.swing.event.TableModelEvent e) {
			        TableModel model = (TableModel)e.getSource();
			        int a = e.getColumn();
			        int b = e.getFirstRow();
			        try {
						System.out.println("tableChanged()" + a + ", " + b + ", " + model.getValueAt(b, a)); // TODO Auto-generated Event stub tableChanged()
					   // taskController.addDuration(model.getValueAt(a, b));


					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
	
				}
			});
		}
		return jTable;
	}

}
