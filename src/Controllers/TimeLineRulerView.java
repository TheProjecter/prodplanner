package Controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JPanel;

public class TimeLineRulerView extends JPanel
{
	static final int DAY=5;
	private String text;
	private int startDate, endDate;
	private int week;
	private int daay;

    int malarBrada1=-70*5;
    
    int malarBrada2=-70*5;

	
	public TimeLineRulerView(String text)
	{
		this.text = text;
		setBackground(new Color(255,255,204));
		
		Calendar now = Calendar.getInstance();
//		now.add(Calendar.DAY_OF_YEAR, -21);
		week = now.get(Calendar.WEEK_OF_YEAR);
		daay = now.get(Calendar.DAY_OF_WEEK)-2;
		System.out.println("DAAY:" + daay);

		
	}
		
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(malarBrada1,0);
		malarBrada2=malarBrada1;

		g.drawLine(-300, 29, 1050, 29);
		//System.out.println(getWidth());
		int w = 0;
		for (int i =0; i < 1050; i++) {
			if (i%5 == 0) {
				if((i+daay*5)%7 == 0){ // varje ny vecka(måndag) ska börja med en lite högre linje
					int week2=week+1;
					g.drawString("" + (week2+w), i, 12);
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
		w=0;
		for (int i =0; i > -300; i--) {
			if (i%5 == 0) {
				if((i+daay*5)%7 == 0){ // varje ny vecka(måndag) ska börja med en lite högre linje
					int week2=week;

					g.drawString("" + (week2-w), i, 12);
					g.drawLine(i, 30, i, 15);
					w++;
					if (w == 0) {
						w = 52;
					}
				}
				else{
					g.drawLine(i, 30, i, 25);
				}
				
			}
			
		}
	}
	public void moveBoard(int value) {
//		System.out.println(value);
		malarBrada1=-value*5;
		repaint();
	}

	public void scrollBarAdjustments(int value) {
		moveBoard(value);
	}
}
