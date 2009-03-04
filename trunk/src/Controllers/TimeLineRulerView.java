package Controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
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
    int malarBrada1=-70*5;
    int malarBrada2=-70*5;

	
	public TimeLineRulerView(String text)
	{
		this.text = text;
		setBackground(new Color(255,255,204));
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, -21);
		week = now.get(Calendar.WEEK_OF_YEAR);
		
	}
		
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(malarBrada1,0);
		malarBrada2=malarBrada1;

		g.drawLine(0, 30, getWidth(), 30);
		System.out.println(getWidth());
		int w = 0;
		for (int i = -(350+100); i < 790; i++) {
			if (i%5 == 0) {
				if(i%7 == 0){ // varje ny vecka(m�ndag) ska b�rja med en lite h�gre linje
					
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
	public void moveBoard(int value) {
		malarBrada1=-value*5;
		repaint();
	}

	public void scrollBarAdjustments(int value) {
		moveBoard(value);
	}
}
