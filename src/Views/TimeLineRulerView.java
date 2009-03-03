package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeLineRulerView extends JPanel
{
	private String text;
	private int startDate, endDate;
	private int week;
	
	public TimeLineRulerView(String text)
	{
		this.text = text;
		setBackground(new Color(255,255,204));
		
		Calendar now = Calendar.getInstance();
		week = now.get(Calendar.WEEK_OF_YEAR);
		
	}
		
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawLine(0, 30, getWidth(), 30);
		
		int w = 0;
		for (int i = 0; i < getWidth(); i++) {
			if (i%5 == 0) {
				if(i%7 == 0){ // varje ny vecka(måndag) ska börja med en lite högre linje
					
					g.drawString("" + (week+w), i, 12);
					g.drawLine(i, 30, i, 15);
					w++;
					if (w == 53) {
						w = 0;
					}
				}
				else{
					g.drawLine(i, 30, i, 25);
				}
				
			}
			
		}
	}
}
