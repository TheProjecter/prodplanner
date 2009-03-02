package Views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import Controllers.TaskController;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Point;

public class ProdPlannerUI {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="8,7"
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JTable jTable = null;
	TaskController taskController = new TaskController();  //  @jve:decl-index=0:
    DefaultTableModel model = new DefaultTableModel();
	private JScrollPane tableScroll = null;
	private JButton deleteButton = null;
	private TimeLineView timeLine = null;  //  @jve:decl-index=0:
	private JPanel timeLinePane = null;
	private JLabel timeLineLabels = null;
	private JLabel L1 = null;
	private JLabel L2 = null;
	private JLabel L3 = null;
	private JLabel L4 = null;
	private JLabel Park = null;
	private JLabel L5 = null;
	private TimeLineView L1Draw = null;
	private TimeLineView L2Draw = null;
	private TimeLineView L3Draw = null;
	private TimeLineView L4Draw = null;
	private TimeLineView L5Draw = null;
	private TimeLineView ParkDraw = null;
	private TimeLineRulerView TimeLineDraw = null;

	public ProdPlannerUI()
	{
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
			jFrame.setTitle("ProdPlanner");
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
			L5 = new JLabel();
			L5.setBounds(new Rectangle(75, 165, 46, 31));
			L5.setText("L5");
			Park = new JLabel();
			Park.setBounds(new Rectangle(75, 210, 46, 31));
			Park.setText("Park");
			L4 = new JLabel();
			L4.setBounds(new Rectangle(75, 135, 46, 31));
			L4.setText("L4");
			L3 = new JLabel();
			L3.setBounds(new Rectangle(75, 105, 46, 31));
			L3.setText("L3");
			L2 = new JLabel();
			L2.setBounds(new Rectangle(75, 75, 46, 31));
			L2.setText("L2");
			L1 = new JLabel();
			L1.setBounds(new Rectangle(75, 45, 46, 31));
			L1.setText("L1");
			timeLineLabels = new JLabel();
			timeLineLabels.setBounds(new Rectangle(15, 14, 106, 201));
			jContentPane = new JPanel();
			
 			jContentPane.setLayout(null);
 			
 			jContentPane.add(getJButton(), null);
 			jContentPane.add(getJTable(), null);
 			jContentPane.add(getTableScroll(), null);
 			jContentPane.add(getDeleteButton(), null);
 			jContentPane.add(getTimeLinePane(), null);
 			jContentPane.add(L1, null);
 			jContentPane.add(L2, null);
 			jContentPane.add(L3, null);
 			jContentPane.add(L4, null);
 			jContentPane.add(Park, null);
 			jContentPane.add(L5, null);
 			
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
					ParkDraw.addTask(jTable.getRowCount());
					int duration = taskController.getDuration(jTable.getRowCount());
					String startDate = taskController.getStartDate(jTable.getRowCount());
					String endDate = taskController.getEndDate(jTable.getRowCount());

					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					System.out.println(jTable.getRowCount());
					System.out.println(jTable.getColumnCount());
					
					//model.addRow(data);
					model.insertRow(jTable.getRowCount(), new Object[] {"",duration,startDate,endDate} );
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
					"Customer", 
					"Duration", 
					"Start", 
					"End", 
					"Earliest", 
					"Latest"}; 
			model.setColumnIdentifiers(titles);
		
			jTable = new JTable(model){
			
				
				public boolean isCellEditable(int rowIndex, int vColIndex) {
			        if (vColIndex< 3) {
			            return true;
			        } else {
			            return false;
			        }

				}
			};
			jTable.setDragEnabled(true);
			
			
			model.addTableModelListener(new javax.swing.event.TableModelListener() {
				public void tableChanged(javax.swing.event.TableModelEvent e) {
			        TableModel model = (TableModel)e.getSource();
			        int a = e.getColumn();
			        int b = e.getFirstRow();
			        try {

						System.out.println(a + ", " + b + ", " + model.getValueAt(b, a));  
						if(a==0){ 
							taskController.addCostumer(b,(String) model.getValueAt(b, a));
						}
						else if(a==1){ //duration
							taskController.addDuration(b,(String) model.getValueAt(b, a));
							model.setValueAt(taskController.getEndDate(b),b,3);

						}
						else if(a==2){ //start date
							if (!taskController.addStartDate(b,(String) model.getValueAt(b, a))){
								model.setValueAt(taskController.getStartDate(b) ,b,2);
							}
							model.setValueAt(taskController.getEndDate(b) ,b,3);
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
			
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.removeRow(jTable.getSelectedRow());
					
				}});
			
		}
		return deleteButton;
	}

	/**
	 * This method initializes timeLinePane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTimeLinePane() {
		if (timeLinePane == null) {
			
			timeLinePane = new JPanel();
			
			timeLinePane.setLayout(null);
			timeLinePane.setBounds(new Rectangle(135, 16, 541, 225));
			timeLinePane.setBackground(Color.white);
			
			timeLinePane.add(getTimeLineDraw(), getTimeLineDraw().getName());
			timeLinePane.add(getL1Draw(), getL1Draw().getName());
			timeLinePane.add(getL2Draw(), getL2Draw().getName());
			timeLinePane.add(getL3Draw(), getL3Draw().getName());
			timeLinePane.add(getL4Draw(), getL4Draw().getName());
			timeLinePane.add(getL5Draw(), getL5Draw().getName());
			timeLinePane.add(getParkDraw(), getParkDraw().getName());
			
			
		}
		return timeLinePane;
	}

	/**
	 * This method initializes timeLineView	
	 * 	
	 * @return Views.TimeLineView	
	 */
	private TimeLineView getL1Draw() {
		if (L1Draw == null) {
			L1Draw = new TimeLineView("L1");
			L1Draw.setSize(new Dimension(541, 31));
			L1Draw.setLocation(new Point(0, 30));
		}
		return L1Draw;
	}
	
	private TimeLineView getL2Draw() {
		if (L2Draw == null) {
			L2Draw = new TimeLineView("L2");
			L2Draw.setSize(new Dimension(541, 31));
			L2Draw.setLocation(new Point(0, 60));
		}
		return L2Draw;
	}
	
	private TimeLineView getL3Draw() {
		if (L3Draw == null) {
			L3Draw = new TimeLineView("L3");
			L3Draw.setSize(new Dimension(541, 31));
			L3Draw.setLocation(new Point(0, 90));
		}
		return L3Draw;
	}
	
	private TimeLineView getL4Draw() {
		if (L4Draw == null) {
			L4Draw = new TimeLineView("L4");
			L4Draw.setSize(new Dimension(541, 31));
			L4Draw.setLocation(new Point(0, 120));
		}
		return L4Draw;
	}
	
	private TimeLineView getL5Draw() {
		if (L5Draw == null) {
			L5Draw = new TimeLineView("L5");
			L5Draw.setSize(new Dimension(541, 31));
			L5Draw.setLocation(new Point(0, 150));
		}
		return L5Draw;
	}
	
	private TimeLineView getParkDraw() {
		if (ParkDraw == null) {
			ParkDraw = new TimeLineView("Park");
			ParkDraw.setSize(new Dimension(541, 31));
			ParkDraw.setLocation(new Point(0, 195));
		}
		return ParkDraw;
	}
	private TimeLineRulerView getTimeLineDraw() {
		if (TimeLineDraw == null) {
			TimeLineDraw = new TimeLineRulerView("Timeline");
			TimeLineDraw.setSize(new Dimension(541, 31));
			TimeLineDraw.setLocation(new Point(0, 0));
		}
		return TimeLineDraw;
	}
}
