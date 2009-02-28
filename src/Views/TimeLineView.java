package Views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class TimeLineView {
	
	public TimeLineView()
	{
		
	}
	
	public void paint (Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	    g2.draw(new Rectangle2D.Double(10, 10,
                400,
                200));
	}
}
