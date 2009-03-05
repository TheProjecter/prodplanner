package Views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
//import Controllers.TaskController;
import Controllers.TimeLineRulerView;
import Controllers.TimeLineView;

import javax.swing.JLabel;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JScrollBar;

public class ProdPlannerUI {
	static final int DAY=5;
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="8,7"
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JTable jTable = null;
	//TaskController taskController = new TaskController();  //  @jve:decl-index=0:
    DefaultTableModel model = new DefaultTableModel();
	private JScrollPane tableScroll = null;
	private JButton deleteButton = null;
	private JPanel timeLinePane = null;
	private JLabel timeLineLabels = null;
	private JLabel L1 = null;
	private JLabel L2 = null;
	private JLabel L3 = null;
	private JLabel L4 = null;
	private JLabel Park = null;
	private JLabel L5 = null;
	private TimeLineView TLDraw = null;
	private TimeLineView ParkDraw = null;
	private TimeLineRulerView TimeLineDraw = null;
	private int count=0;
//	private ArrayList<Integer> idOnLine = new ArrayList<Integer>();  //  @jve:decl-index=0:

	private int selectedTask;
	private JScrollBar jScrollBar = null;
	private JLabel timeLineLabel = null;

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
			timeLineLabel = new JLabel();
			timeLineLabel.setBounds(new Rectangle(90, 15, 46, 31));
			timeLineLabel.setText("Weeks");
			L5 = new JLabel();
			L5.setBounds(new Rectangle(90, 165, 46, 31));
			L5.setText("L5");
			Park = new JLabel();
			Park.setBounds(new Rectangle(90, 195, 46, 31));
			Park.setText("Park");
			L4 = new JLabel();
			L4.setBounds(new Rectangle(90, 135, 46, 31));
			L4.setText("L4");
			L3 = new JLabel();
			L3.setBounds(new Rectangle(90, 105, 46, 31));
			L3.setText("L3");
			L2 = new JLabel();
			L2.setBounds(new Rectangle(90, 75, 46, 31));
			L2.setText("L2");
			L1 = new JLabel();
			L1.setBounds(new Rectangle(90, 45, 46, 31));
			L1.setText("L1");
			timeLineLabels = new JLabel();
			timeLineLabels.setBounds(new Rectangle(15, 14, 106, 201));
			jContentPane = new JPanel();
 			jContentPane.setLayout(null);
 			jContentPane.add(getJButton(), null);
 			jTable=getJTable();
 			jContentPane.add(jTable, null);
 			jContentPane.add(getTableScroll(), null);
 			jContentPane.add(getDeleteButton(), null);
 			jContentPane.add(getTimeLinePane(), null);
 			TLDraw.getImportentObjects(jTable, model);
 			jContentPane.add(L1, null);
 			jContentPane.add(L2, null);
 			jContentPane.add(L3, null);
 			jContentPane.add(L4, null);
 			jContentPane.add(Park, null);
 			jContentPane.add(L5, null);
 			jContentPane.add(getJScrollBar(), null); 			
 			jContentPane.add(timeLineLabel, null);
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
					int asdfasdf=0;
					do{asdfasdf++;
						
						
					
					System.out.println(count);
//					idOnLine.add(count);
//					taskController.addTask(count);
					TLDraw.addTask(jTable.getRowCount());

					String earliestDate = TLDraw.getEarliestDate(jTable.getRowCount());
					String latestDate = TLDraw.getLatestDate(jTable.getRowCount());

					model.insertRow(jTable.getRowCount(), new Object[] {"",35,earliestDate,latestDate,"","","Park"} );
					
					//TLDraw.setData("", "" + duration, earliestDate, latestDate, "", "", (int)idOnLine.get(count));
					
					count++;
					}while(asdfasdf<5);
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
					"Earliest", 
					"Latest", 
					"Start", 
					"End",
					"L"};
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


			model.addTableModelListener(new javax.swing.event.TableModelListener() {
				public void tableChanged(javax.swing.event.TableModelEvent e) {
			        TLDraw.tableChanged(e);
				}
			});
			
			jTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						TLDraw.valueChanged(e, selectedTask, jTable.getSelectedRow());
					}
				}
		
			);
			

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
					int markerad=jTable.getSelectedRow();
					TLDraw.deleteEvent(e, markerad, deleteButton);
					try{
						model.removeRow(markerad);
//						idOnLine.remove(markerad);
					}
					catch(Exception e1){
						
					}
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
		Color bg;
		
		if (timeLinePane == null) {
			
			timeLinePane = new JPanel();
			timeLinePane.setOpaque(true);
			timeLinePane.setLayout(null);
			timeLinePane.setBounds(new Rectangle(135, 16, 541, 225));
			timeLinePane.add(getTimeLineDraw(), getTimeLineDraw().getName());
			
			bg = new Color(255, 255, 255);
			timeLinePane.add(getTLDraw("TimeLinePane",bg, new Dimension(541, 180), new Point(0,30)));
			
			
		}
		return timeLinePane;
	}

	/**
	 * This method initializes timeLineView	
	 * 	
	 * @return Views.TimeLineView	
	 */
	private TimeLineView getTLDraw(String txt, Color bg, Dimension d, Point p) {
		
		if (TLDraw == null) {
			TLDraw = new TimeLineView(txt, bg);
			TLDraw.setSize(d);
			TLDraw.setLocation(p);
		}
		return TLDraw;
	}
	
	
	private TimeLineView getParkDraw() {
		if (ParkDraw == null) {
			ParkDraw = new TimeLineView("Park", new Color(204,204,255));
			ParkDraw.setSize(new Dimension(541, 31));
			ParkDraw.setLocation(new Point(0, 180));
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

	/**
	 * This method initializes jScrollBar	
	 * 	
	 * @return javax.swing.JScrollBar	
	 */
	private JScrollBar getJScrollBar() {
		if (jScrollBar == null) {
			jScrollBar = new JScrollBar(0);

			jScrollBar.setMaximum(100);
			jScrollBar.setMinimum(-50);
			jScrollBar.setBounds(new Rectangle(135, 0, 540, 16));
			TLDraw.scrollBarAdjustments(-20);
			TimeLineDraw.scrollBarAdjustments(-20);
			jScrollBar.setValue(-20);
			
			jScrollBar.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
				public void adjustmentValueChanged(java.awt.event.AdjustmentEvent e) {
					TLDraw.scrollBarAdjustments(jScrollBar.getValue());
					TimeLineDraw.scrollBarAdjustments(jScrollBar.getValue());
				}
			});
		}
		return jScrollBar;
	}
}
