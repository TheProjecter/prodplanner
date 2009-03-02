package Views;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TimeLineView extends JPanel
{
	private String text;
	
	public TimeLineView(String text)
	{
		this.text = text;
	}
	
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		
		
		int width = getWidth();
	    int height = getHeight();
	    
	    Rectangle rect1 = new Rectangle(50,20);
	    g2d.draw(rect1);
	    
		/*for (int i = 0; i < Tasks.size(); i++) {
		 	g.setColor(Color.red);
		 	//g.(Tasks.get(i));
		}*/
	    
	    repaint();
	}
	
	public void addTask(int task)
	{
		Rectangle tmp = new Rectangle();
		tmp.setSize(new Dimension(50, 20));
		
		//Tasks.add(tmp);
	}

}
