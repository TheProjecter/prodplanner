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
    double mp,mq, mwidth, mheight;
    int p1, q1, p2, q2;
    Rectangle2D rect1,rect2, mrect1;
    
    Rectangle2D rectangle;
    Cursor cursor;


	
	public TimeLineView(String text, Color bg)
	{
		this.text = text;
		setBackground(bg);
		
		
		p = 5;
		q = 5;
		width = 75;
		height = 20;
		
		mp=90;
		mq = 5;
		mwidth = 75;
		mheight = 20;
		
		addMouseListener(new EventMouseListener());
		addMouseMotionListener(new EventMouseMotionListener());
	}
	
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;


	    rect1 = new Rectangle2D.Double(p, q, width, height);
	    mrect1 = new Rectangle2D.Double(mp, mq, mwidth, mheight);

	    g2d.draw(rect1);	    
	    g2d.fill(rect1);
	    g2d.setColor(new Color(255,255,255));
	    g2d.drawString("Customer",(int)rect1.getX()+10,(int)rect1.getY()+15);
	    g2d.setColor(new Color(0,0,0));
	    g2d.draw(mrect1);	    
	    g2d.fill(mrect1);
	    g2d.setColor(new Color(255,255,255));
	    g2d.drawString("Customer",(int)mrect1.getX()+10,(int)mrect1.getY()+15);
	    if (rectangle != null) {
	    	drawSquares(g2d, rectangle);
        }
        if (cursor != null){
        	setCursor(cursor);
		}
	    
	    
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
	    	  int x = event.getX();
	          int y = event.getY();
	       	  Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);

	       	  if (rect1.intersects(r)) {
    		   	  rect2=rect1;
    		   	  rectangle = rect1.getBounds2D();
    		   	System.out.println("Du tr�ffade f�rsta");
    		  }else if(mrect1.intersects(r)){
    			  rect2=mrect1;
    		   	  rectangle = mrect1.getBounds2D();
    		   	System.out.println("Du tr�ffade andra");
    		  }
    		  else{
    			  System.out.println("Du missade b�da");
    		  }
//    		  rect2 = rect1;
//	          rectangle = rect1.getBounds2D();
	          display(rect2);
	          p1 = event.getX();
	          q1 = event.getY();
	      }
	      public void mouseReleased(MouseEvent event) {
	    	  
	    	  int x = event.getX();
	          int y = event.getY();
	       	  Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);
	       	  if (rect1.intersects(r)) {
	       		  
	       		  rectangle = rect1.getBounds2D();
	       		  rect2 = rect1;
	       		  System.out.println("Du sl�ppte p� f�rsta");
    		  }else if(mrect1.intersects(r)){
    			  rectangle = mrect1.getBounds2D();
    	          rect2 = mrect1;
    	          System.out.println("Du sl�ppte p� andra");
    		  }
    		  else{
    			  System.out.println("Du kunde inte sl�ppa n�gon");
    		  }    	  
	          display(rect2);
	       }
	      public void mouseClicked(MouseEvent event) {
	    	  int x = event.getX();
	          int y = event.getY();
	       	  Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);

	       	  if (rect1.intersects(r)) {
    		   	  rect2=rect1;
    		   	  rectangle = rect1.getBounds2D();
    		   	  System.out.println("Du tr�ffade f�rsta");
    		  }else if(mrect1.intersects(r)){
    			  rect2=mrect1;
    		   	  rectangle = mrect1.getBounds2D();
    		   	  System.out.println("Du tr�ffade andra");
    		  }
    		  else{
    			  System.out.println("Du missade b�da");
    		  }
	          display(rect2);
	      }
	}
    class EventMouseMotionListener extends MouseMotionAdapter {
    	public void mouseDragged(MouseEvent event) {
    		if (rect1.contains(event.getX(), event.getY())) {
    			rectangle = null;
    			rect2 = rect1;
    			if(event.getX()>p && event.getX()<=p+10){
    				p2 = event.getX();
					p = p + p2 - p1;
					width=width + (p1-p2);
					p1 = p2;
    			}
    			else if(event.getX()>p+(width-10) && event.getX()<=p+width){
    				p2 = event.getX();
    				System.out.println(p2-p1);
    				width+=p2-p1;
    				p1 = p2;  
    			}
    			else{
    				p2 = event.getX();
    				p = p + p2 - p1;
    				p1 = p2;
    			}
			}
    		else{
    			rectangle = null;
    			rect2 = mrect1;
    			if(event.getX()>mp && event.getX()<=p+10){
    				p2 = event.getX();
					mp = mp + p2 - p1;
					mwidth=mwidth + (p1-p2);
					p1 = p2;
    			}
    			else if(event.getX()>mp+(mwidth-10) && event.getX()<=mp+mwidth){
    				p2 = event.getX();
    				System.out.println(p2-p1);
    				mwidth+=p2-p1;
    				p1 = p2;  
    			}
    			else{
    				p2 = event.getX();
    				mp = mp + p2 - p1;
    				p1 = p2;
    			}
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

