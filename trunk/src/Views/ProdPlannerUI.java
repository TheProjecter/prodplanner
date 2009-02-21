package Views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JTable;

public class ProdPlannerUI {

	private JFrame jFrame = null;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JTable jTable = null;
	

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
			jTable = new JTable();
			jTable.setBounds(new Rectangle(300, 197, 375, 80));
			
		}
		return jTable;
	}

}
