package Views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controllers.TaskController;

public class ProdPlannerUI {

	private JFrame jFrame = null;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JTable jTable = null;
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
				    model.insertRow(0, new Object[]{""});
					
					
					
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
			jTable = new JTable(model);
			jTable.setBounds(new Rectangle(300, 197, 375, 80));
			model.addColumn("Costumer");
			model.addColumn("Duration");
			model.addColumn("Start");
			model.addColumn("End");
			model.addColumn("Earliest");
			model.addColumn("Latest");
			
			
		}
		return jTable;
	}

}
