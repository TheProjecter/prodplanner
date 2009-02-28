package Views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import Controllers.TaskController;
public class ProdPlannerUI {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="8,7"
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JTable jTable = null;
	TaskController taskController = new TaskController();  //  @jve:decl-index=0:
    DefaultTableModel model = new DefaultTableModel();
	private JScrollPane tableScroll = null;
	private JButton deleteButton = null;
	//private String title;
	
	public ProdPlannerUI(String title)
	{
		//this.title = title;
		getJFrame();
	}
	
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			
			
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(708, 442));
			jFrame.setContentPane(getJContentPane());
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
 			jContentPane.add(getTableScroll(), null);
 			jContentPane.add(getDeleteButton(), null);
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
			jButton.setBounds(new Rectangle(15, 255, 106, 31));
			jButton.setText("New");
			jButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					taskController.addTask(jTable.getRowCount());
					int duration = taskController.getDuration(jTable.getRowCount());
					String startDate = taskController.getStartDate(jTable.getRowCount());
					String endDate = taskController.getEndDate(jTable.getRowCount());

					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					System.out.println(jTable.getRowCount());
					System.out.println(jTable.getColumnCount());
					
					//model.addRow(data);
					model.insertRow(jTable.getRowCount(), new Object[] {jTable.getRowCount(), "",duration,startDate,endDate} );
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
			String[] titles = new String[]{
					"ID",
					"Customer", 
					"Duration", 
					"Start", 
					"End", 
					"Earliest", 
					"Latest"}; 
			model.setColumnIdentifiers(titles);
		
			jTable = new JTable(model);
			//jTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
			
			
			
			model.addTableModelListener(new javax.swing.event.TableModelListener() {
				public void tableChanged(javax.swing.event.TableModelEvent e) {
			        TableModel model = (TableModel)e.getSource();
			        int a = e.getColumn();
			        int b = e.getFirstRow();
			        try {

						System.out.println(a + ", " + b + ", " + model.getValueAt(b, a));  
						if(a==1){ 
							taskController.addCostumer(b,(String) model.getValueAt(b, a));
						}
						else if(a==2){ //duration
							taskController.addDuration(b,(String) model.getValueAt(b, a));
							model.setValueAt(taskController.getEndDate(b),b,4);

						}
						else if(a==3){ //start date
							taskController.addStartDate(b,(String) model.getValueAt(b, a));
							model.setValueAt(taskController.getEndDate(b),b,4);

						}
						else{
							System.out.println("Other change " + a);
						}
						taskController.printAll();
						System.out.println(taskController.getEndDate(b));

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
	
				}
			});
		}
		return jTable;
	}

	/**
	 * This method initializes tableScroll	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTableScroll() {
		if (tableScroll == null) {
			tableScroll = new JScrollPane(jTable);
			tableScroll.setBounds(new Rectangle(135, 255, 541, 136));
		}
		return tableScroll;
	}

	/**
	 * This method initializes deleteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setBounds(new Rectangle(15, 300, 106, 31));
			deleteButton.setText("Delete");
		}
		return deleteButton;
	}
}
