package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.*;

public class TimeLineView extends JPanel
{
	private String text;
    double p, q, width, height;
    int p1, q1, p2, q2;
    Rectangle2D rect1,rect2;
    Rectangle2D rectangle;
    Cursor cursor;


	
	public TimeLineView(String text)
	{
		this.text = text;
		p = 5;
		q = 5;
		width = 30;
		height = 20;
		addMouseListener(new EventMouseListener());
		addMouseMotionListener(new EventMouseMotionListener());
	}
	
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;


	    rect1 = new Rectangle2D.Double(p, q, width, height);
	    g2d.draw(rect1);
	    if (rectangle != null) {
	    	drawSquares(g2d, rectangle);
        }
        if (cursor != null){
        	setCursor(cursor);
		}
	    
		/*for (int i = 0; i < Tasks.size(); i++) {
		 	g.setColor(Color.red);
		 	//g.(Tasks.get(i));
		}*/
	    
	    repaint();
	}
    public void drawSquares(Graphics2D g2, Rectangle2D rect) {
	    double p = rect.getX();
	    double q = rect.getY();
	    double width = rect.getWidth();
	    double height = rect.getHeight();
	    g2.setColor(Color.black);
    }
   class EventMouseListener extends MouseAdapter {
	      public void mousePressed(MouseEvent event) {
	          rect2 = rect1;
	          rectangle = rect1.getBounds2D();
	          display(rect2);
	          p1 = event.getX();
	          q1 = event.getY();
	      }
	      public void mouseReleased(MouseEvent event) {
	          rectangle = rect1.getBounds2D();
	          rect2 = rect1;
	          display(rect2);
	       }
	      public void mouseClicked(MouseEvent event) {
	          rect2 = rect1;
	          rectangle = rect1.getBounds2D();
	          display(rect2);
	        }
	   }
	    class EventMouseMotionListener extends MouseMotionAdapter {
	      public void mouseDragged(MouseEvent event) {
	        if (rect1.contains(event.getX(), event.getY())) {
	          rectangle = null;
	          rect2 = rect1;
	          p2 = event.getX();
	          q2 = event.getY();
	          p = p + p2 - p1;
	          //q = q + q2 - q1;     // bort-kommenterat för att "tasken ska ligga på en linje och inte flyta runt i y-led
	          p1 = p2;
	          q1 = q2;	// bort-kommenterat för att "tasken ska ligga på en linje och inte flyta runt i y-led
	        }
	        if (rect2 != null)
	          display(rect2);
	        repaint();
	      }
	    public void mouseMoved(MouseEvent event) {
	         cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	           }
	        }
	      public void display(Shape shape) {
	      double p = rect2.getX();
	      double q = rect2.getY();
	      double width= rect2.getWidth();
	      double height = rect2.getHeight();
    }

		public void addTask(int rowCount) {
			// TODO Auto-generated method stub
			
		}
  }

