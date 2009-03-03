package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.*;

public class CopyOfTimeLineView extends JPanel
{
	private String text;
    double p, q, width, height;
    double mp,mq, mwidth, mheight;
    int p1, q1, p2, q2;
    int mp1, mq1, mp2, mq2;
    Rectangle2D rect1,rect2, mrect1;
    
    Rectangle2D rectangle;
    Cursor cursor;


	
	public CopyOfTimeLineView(String text, Color bg)
	{
		this.text = text;
		setBackground(bg);
		
		
		p = 5;
		q = 5;
		width = 60;
		height = 20;
		
		mp=90;
		mq = 5;
		mwidth = 60;
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
    		   	  p1 = event.getX();
    		   	  q1 = event.getY();
    		   	System.out.println("Du träffade första");
    		  }else if(mrect1.intersects(r)){
    			  rect2=mrect1;
    		   	  rectangle = mrect1.getBounds2D();
    		   	  mp1 = event.getX();
    		   	  mq1 = event.getY();
    		   	  System.out.println("Du träffade andra");
    		  }
    		  else{
    			  System.out.println("Du missade båda");
    		  }
//    		  rect2 = rect1;
//	          rectangle = rect1.getBounds2D();
//	          display(rect2);
	          
	         
	      }
	      public void mouseReleased(MouseEvent event) {
	    	  
	    	  int x = event.getX();
	          int y = event.getY();
	       	  Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);
	       	  if (rect1.intersects(r)) {
	       		  rectangle = rect1.getBounds2D();
	       		  rect2 = rect1;
	       		  if(p%30>15){
	       			 p=p+(30-p%30);
	       		  }else{
	       			 p=p-(p%30);
	       		  }
	       		  if(width%30>15){
	       			  width=width+(30-width%30);
	       		  }else{
	       			  width=width-(width%30);
	       		  }
	       		  System.out.println("Du släppte på första");
    		  }else if(mrect1.intersects(r)){
    			  rectangle = mrect1.getBounds2D();
    	          rect2 = mrect1;
    	          if(mp%30>15){
 	       			 mp=mp+(30-mp%30);
 	       		  }else{
 	       			 mp=mp-(mp%30);
 	       		  }
    	          if(mwidth%30>15){
	       			  mwidth=mwidth+(30-mwidth%30);
	       		  }else{
	       			  mwidth=mwidth-(mwidth%30);
	       		  }

    	          System.out.println("Du släppte på andra");
    		  }
    		  else{
    			  System.out.println("Du kunde inte släppa någon");
    		  }    	  
//	          display(rect2);
	       }
	      public void mouseClicked(MouseEvent event) {
	    	  int x = event.getX();
	          int y = event.getY();
	       	  Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);

	       	  if (rect1.intersects(r)) {
    		   	  rect2=rect1;
    		   	  rectangle = rect1.getBounds2D();
    		   	  System.out.println("Du träffade första");
    		  }else if(mrect1.intersects(r)){
    			  rect2=mrect1;
    		   	  rectangle = mrect1.getBounds2D();
    		   	  System.out.println("Du träffade andra");
    		  }
    		  else{
    			  System.out.println("Du missade båda");
    		  }
//	          display(rect2);
	      }
	}
    class EventMouseMotionListener extends MouseMotionAdapter {
    	public void mouseDragged(MouseEvent event) {
    		if (rect1.contains(event.getX(), event.getY())) {
    			rectangle = null;
    			rect2 = rect1;
    			if(event.getX()>p && event.getX()<=p+12){
    				p2 = event.getX();
					p = p + p2 - p1;
					width=width + (p1-p2);
					p1 = p2;
				
    			}
    			else if(event.getX()>p+(width-12) && event.getX()<=p+width){
    				p2 = event.getX();
    				System.out.println(p2-p1);
    				width+=p2-p1;
    				p1 = p2;  
    			}
    			else{
    				p2 = event.getX();
    				q2 = event.getY();
    				p = p + p2 - p1;
    				p1 = p2;
    				q = q + q2 - q1;     // bort-kommenterat för att "tasken ska ligga på en linje och inte flyta runt i y-led
   		        	q1 = q2;	
    			}
			}
    		else if(mrect1.contains(event.getX(), event.getY())){
    			rectangle = null;
    			rect2 = mrect1;
    			if(event.getX()>mp && event.getX()<=mp+10){
    				mp2 = event.getX();
					mp = mp + mp2 - mp1;
					mwidth=mwidth + (mp1-mp2);
					mp1 = mp2;
    			}
    			else if(event.getX()>mp+(mwidth-10) && event.getX()<=mp+mwidth){
    				mp2 = event.getX();
    				System.out.println(mp2-mp1);
    				mwidth+=mp2-mp1;
    				mp1 = mp2;  
    			}
    			else{
    				mp2 = event.getX();
    				mp = mp + mp2 - mp1;
    				mp1 = mp2;
    			}
    		}
    		else{
    			System.out.println("inget händer");
    		}
	        if (rect2 != null)
//	        	display(rect2);
	        repaint();
        } 
	    public void mouseMoved(MouseEvent event) {
	    	cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	    }
    }
//    public void display(Shape shape) {
//    	double p = rect2.getX();
//    	double q = rect2.getY();
//    	double width= rect2.getWidth();
//    	double height = rect2.getHeight();
//    }
	public void addTask(int rowCount) {
			// TODO Auto-generated method stub
			
	}
}

