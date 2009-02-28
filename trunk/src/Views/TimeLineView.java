package Views;

import javax.swing.*;
import java.awt.*;



public class TimeLineView extends JPanel
{
	public void paintComponent(Graphics2D g) {
		g.setColor(Color.blue);
	    g.drawRect(10, 10, 80, 30);
	    

	}
   
	public void update(Graphics2D g) {
     	 paintComponent(g);
	}

}
