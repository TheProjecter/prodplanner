package Views;

import javax.swing.*;
import java.awt.*;



public class TimeLineRulerView extends JPanel
{
	private String text;
	private int startDate, endDate;
	
	public TimeLineRulerView(String text)
	{
		this.text = text;
		setBackground(new Color(255,255,204));
	}
	
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawLine(0, 30, getWidth(), 30);
		
		for (int i = 0; i < getWidth(); i++) {
			if (i%30 == 0) {
				g.drawLine(i, 30, i, 25);
				
				
			}
			
		}
	}

}
