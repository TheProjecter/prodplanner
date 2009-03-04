package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.*;

public class TimeLineView extends JPanel
{

	private String text;
	public ArrayList<Box> rects = new ArrayList<Box>();
    private Rectangle2D rect2;
    private Rectangle2D rectangle;
    private int lineX1=0;
    private int lineX2=0;
    
    
    Cursor cursor;
    int s=0;//typ av grab.
    double s2=0;// typ2 vid grab
    int s3=0;
    int k=0; //bit som håller koll på vilket håll en box går åt.
    int k2=-1; //vilket objekt som dras
    private int x, y; // mousepos in pane
    int malarBrada1=70*5;
    int malarBrada2=70*5;

	public TimeLineView(String text, Color bg)
	{
		this.text = text;
		setBackground(bg);
		addMouseListener(new EventMouseListener());
		addMouseMotionListener(new EventMouseMotionListener());
	}
	
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.translate(malarBrada1,0);
		malarBrada2=malarBrada1;
		for (int i=1; i<=5; i++) {
			g2d.setColor(new Color(0,0,0));
			g2d.drawLine(0, 30*i, 541, 30*i);
		}
		
		for (int i =0; i<rects.size();i++){
			if(rects.get(i)!=null){
				g2d.setColor(new Color(90,0,50));
				g2d.draw(rects.get(i).getRect());
				if (paintAsSelected(i)) {
					g2d.setColor(new Color(255,0,0));
				} else {
					g2d.setColor(rects.get(i).getColor());
				}					    
			    g2d.fill(rects.get(i).getRect());
			   
			    // draws a shadow effect behind the text.
			    g2d.setColor(new Color(0,0,0));
			    g2d.drawString(rects.get(i).getNamn(),(int)rects.get(i).getRect().getX()+11,(int)rects.get(i).getRect().getY()+16);
			    // draws the text
			    g2d.setColor(new Color(255,255,255));
			    g2d.drawString(rects.get(i).getNamn(),(int)rects.get(i).getRect().getX()+10,(int)rects.get(i).getRect().getY()+15);
			    draw2Lines(g2d);
			    if (mouseOverBox((int)rects.get(i).getX(), (int)rects.get(i).getY(), (int)rects.get(i).getWidth(), (int)rects.get(i).getHeight())) {
		        	drawInfoBox(g2d, this.x, this.y, rects.get(i).getID());
				}
			}
		}
	    if (rectangle != null) {
	    	drawSquares(g2d, rectangle);
        }
        if (cursor != null){
        	setCursor(cursor);
		}
	    repaint();
	}
	
	private void draw2Lines(Graphics2D g2d) {	
	    g2d.setColor(Color.red);
	    if(lineX1==lineX2){
	    	
	    }else{
		    g2d.drawLine(lineX1, 0, lineX1, 150);
		    g2d.drawLine(lineX2, 0, lineX2, 150);
	    }
	}

	private boolean mouseOverBox(int x, int y, int w, int h) {
//		if ( ( (this.x < x+w)&&(this.x > x)) && ((this.y > y) && (this.y < y+h))) {
//			//System.out.println("X = " + x + " Y = " + y + " w = " + w + " h = " + h);
//			  
//			return true;
//		}
//		
		return false;
		
	}
    public void drawSquares(Graphics2D g2, Rectangle2D rect) {
    	double p = rect.getX();
	    double q = rect.getY();
	    double width = rect.getWidth();
	    double height = rect.getHeight();
	    g2.setColor(Color.black);
	}
    
   public void drawInfoBox(Graphics2D g2d, int x, int y, int id)
   {
	   int w, h;
	   String cust , dur, earliest, latest, start, end;
	   w = 120;
	   h = 120;
	   //cust = dur = earliest = latest = start = end = null;
	   cust = rects.get(id).namn;
	   dur = rects.get(id).dur;
	   earliest = rects.get(id).earliest;
	   latest = rects.get(id).latest;
	   start = rects.get(id).start;
	   end = rects.get(id).end;
	   //y = x = 10; // this is gonna be dynamic
	  // cust = rects.get(id).getNamn();
	   
	   if (y > 90) {
		// we've reached a point where we need to draw
		   // the box upwards.
		   y = y - h;
	   }
	   
	   g2d.setColor(new Color(255,255,204));
	   g2d.fillRect(x, y, w, h);
	   g2d.setColor(new Color(0,0,0));
	   g2d.drawRect(x, y, w, h);
	   
	   g2d.drawString("Customer: " + cust, x+5, y+15);
	   g2d.drawString("Duration: " + dur, x+5, y+35);
	   g2d.drawString("Earliest: " + earliest, x+5, y+55);
	   g2d.drawString("Latest: " + latest, x+5, y+75);
	   g2d.drawString("Start: " + start, x+5, y+95);
	   g2d.drawString("End: " + end, x+5, y+115);
   }
    
    class EventMouseListener extends MouseAdapter {
    	public void mousePressed(MouseEvent event) {
    		int x = event.getX();
    		int y = event.getY();
       	  	Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);
       	  		for (int i =0; i<rects.size();i++){
       	  			if(rects.get(i)!=null){
       	  				if (rects.get(i).getRect().intersects(r)) {
       	  					k2=i;
       	  					rect2=rects.get(i).getRect();
       	  					rectangle = rects.get(i).getRect().getBounds2D();
       	  					rects.get(i).setP1(event.getX());
       	  					rects.get(i).setQ1(event.getY());
       	  					if(s3==0){
	       	  					if(event.getX()>rects.get(i).getP() && event.getX()<=rects.get(i).getP()+12){
	       	  						s=1;
	       	  						//s2=rects.get(i).getWidth();
	       	  					}
	       	  					else if(event.getX()>rects.get(i).getP()+(rects.get(i).getWidth()-12) && event.getX()<=rects.get(i).getP()+rects.get(i).getWidth()){
	       	  						s=2;
	       	  						s2=rects.get(i).getP();
	       	  					}
	       	  					else{
	       	  						s=3;
	       	  						s2=rects.get(i).getWidth();
	       	  					}
	       	  					s3=1;
       	  					}
       	  				}
       	  			}
       	  		}
    	}
    	public void mouseReleased(MouseEvent event) {
	   	  
    		int x = event.getX();
    		int y = event.getY();
       	  	Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);
       	  	for(int i =0;i<rects.size();i++){
       	  		System.out.println(event.getX());
       	  		System.out.println(rects.get(i).p);
       	  	}
       	  	if(rects.get(k2)!=null){
   	  			if (rects.get(k2).getRect().intersects(r)) {
   	  				rectangle = rects.get(k2).getRect().getBounds2D();
   	  				rect2 = rects.get(k2).getRect();
   	  				rects.get(k2).centerP();
   	  				rects.get(k2).centerWidth();
   	  				rects.get(k2).centerQ();
//	   	  				System.out.println("s " + s + ", s2 " + s2 + " s3 " + s3);
   	  				if(s==1){
   	  					//rects.get(i).setWidth(s2);
   	  				}
   	  				else if(s==2){
   	  					rects.get(k2).setP(s2);
   	  				}
	   	  			else{
	    				for (int j=0; j<rects.size();j++){
	    					if (rects.get(j).getRect().contains(event.getX(), event.getY())){
	    						rects.get(k2).setP(rects.get(j).getP());
	    						fixIntersect(k2,2);
	    					}
	    					else{
	    						fixIntersect(k2,2);
	    					}
	    				}
	   	  				if(s2!=0){
	   	  					rects.get(k2).setWidth(s2);
	   	  				}
	   	  			}
   	  			}
       	  	}
       	  	s=0;
       	  	s3=0;
       	  	s2=0;
// 			System.out.println("s " + s + ", s2 " + s2 + " s3 " + s3);
    	}
    	public void mouseClicked(MouseEvent event) {
    		int x = event.getX();
    		int y = event.getY();
       	  	Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);		
   	  			if(rects.get(k2)!=null){
       	  			if (rects.get(k2).getRect().intersects(r)) {
       	  				rect2=rects.get(k2).getRect();
       	  				rectangle = rects.get(k2).getRect().getBounds2D();
       	  			}
   	  			}
       	  	}
    	}
    	class EventMouseMotionListener extends MouseMotionAdapter {
    		public void mouseDragged(MouseEvent event) {
				if(rects.get(k2)!=null){
    				if (rects.get(k2).getRect().contains(event.getX(), event.getY())) {
		    			rectangle = null;
		    			rect2 = rects.get(k2).getRect();
		    			//event.getX()>rects.get(i).getP() && event.getX()<=rects.get(i).getP()+12
		    			if(s==1){
		    	    		fixIntersect(k2,-1);
    	    				rects.get(k2).dragLeft(event.getX());
		    			}
//			    			event.getX()>rects.get(i).getP()+(rects.get(i).getWidth()-12) && event.getX()<=rects.get(i).getP()+rects.get(i).getWidth()
		    			else if(s==2){
		    				rects.get(k2).dragRight(event.getX());
		    				fixIntersect(k2,1);
		    			}
		    			else{
    	    				rects.get(k2).dragCenter(event.getX(), event.getY());
		    			}
					}
				
	    		}
		        repaint();
	        } 
	    public void mouseMoved(MouseEvent event) {
	    	cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	    	x = event.getX();
	    	y = event.getY();
	    }
    }

	public void addTask(int id) {
		// width baseras på duration
		Box temp=new Box(0, 5, 45, 20, id,malarBrada1);
		rects.add(temp);
	}

	public void fixIntersect(int i, int k){
		int numberOfIntersects=0;
		int firstHit=-1;
		for (int j =0; j<rects.size();j++){
			if(j!=i && rects.get(i).getRect()!=null && rects.get(j).getRect()!=null){
				if(rects.get(i).getRect().intersects(rects.get(j).getRect())){
//					if(k==2){
//						rects.get(i).setP(rects.get(j).getP());
//						rects.get(j).addP((int) (rects.get(i).getP()+rects.get(i).getWidth()-rects.get(j).getP()));
//						rects.get(j).addP(numberOfIntersects*5);
//						if(numberOfIntersects==0){
//							firstHit=j;
//							k=1;
//						}
//					}
					if(k>0){
						rects.get(j).addP((int) (rects.get(i).getP()+rects.get(i).getWidth()-rects.get(j).getP()));
						//rects.get(j).addP(numberOfIntersects*5);
						if(numberOfIntersects==0){
							firstHit=j;
						}
					}
					else{
						rects.get(j).setP((int)((rects.get(i).getP()-rects.get(j).getWidth())));
						//rects.get(j).addP(numberOfIntersects*-5);
						if(numberOfIntersects==0){
							firstHit=j;
						}
					}
					numberOfIntersects++;
					//System.out.println("2. ID " + rects.get(j).getID() + ", antal steg " + 	15*k);
				}
			}
		}
		if(firstHit!=-1){
			fixIntersect(firstHit,k);
			//System.out.println("en rekursiv operation: " + numberOfIntersects);
		}
	}

	public void dropTask(int selectedTask) {
//		System.out.println("selectedTask: "  + selectedTask);
		rects.get(selectedTask).remove();
		rects.get(selectedTask).setRect(0,0,0,0);
		repaint();
		rects.set(selectedTask, null);
		rects.remove(selectedTask);
		//Ta bort en task.
	}
	
	public void setData(String name, String dur, String e, String l, String s, String ed, int selectedTask)
	{
		System.out.println("SetData: " + name);
		rects.get(selectedTask).setData(name, dur, e, l, s, ed);
		repaint();
	}
	
	public void setName(String name, int id)
	{
		rects.get(id).setName(name);
		repaint();
	}
	
	public void setSelection(boolean b, int st)
	{
		rects.get(st).selection(b);
	}
	
	public boolean paintAsSelected(int st)
	{
		if (rects.get(st).getSelection() == true) {
			return true;
		}
		
		return false;
	}

	public int getArraySize() {
		return rects.size();
	}
	public void paintDuration(int x1, int x2)
	{
		lineX1=x1*5-1;
		lineX2=x2*5-1;
		repaint();
	}
	public void setLength(int width)
	{
		lineX2=lineX1-1+width*5;
		repaint();
	}

	public void moveBoard(int value) {
		malarBrada1=value*5;
		for(int i = 0; i<rects.size();i++){
			rects.get(i).addP((malarBrada1-malarBrada2)/10);
		}
		repaint();
	}
}
