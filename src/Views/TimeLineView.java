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
	public ArrayList<Box> rects = new ArrayList<Box>();
    private Rectangle2D rect2;
    private Rectangle2D rectangle;
    Cursor cursor;
    int s=0;//typ av grab.
    double s2=0;// typ2 vid grab
    int s3=0;
    int k=0; //bit som håller koll på vilket håll en box går åt.


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
		
		for (int i=1; i<=5; i++) {
			g2d.setColor(new Color(0,0,0));
			g2d.drawLine(0, 30*i, 541, 30*i);
		}
		
		for (int i =0; i<rects.size();i++){
			if(rects.get(i)!=null){
				g2d.setColor(new Color(90,0,50));
				g2d.draw(rects.get(i).getRect());
				if (paintAsSelected(i)) {
					g2d.setColor(new Color(255,255,204));
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
       	  		for (int i =0; i<rects.size();i++){
       	  			if(rects.get(i)!=null){
       	  				if (rects.get(i).getRect().intersects(r)) {
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
       	  	for (int i =0; i<rects.size();i++){
	       	  	if(rects.get(i)!=null){
	   	  			if (rects.get(i).getRect().intersects(r)) {
	   	  				rectangle = rects.get(i).getRect().getBounds2D();
	   	  				rect2 = rects.get(i).getRect();

	   	  				rects.get(i).centerP();
	   	  				rects.get(i).centerWidth();
//	   	  				System.out.println("s " + s + ", s2 " + s2 + " s3 " + s3);
	   	  				if(s==1){
	   	  					//rects.get(i).setWidth(s2);
	   	  				}
	   	  				else if(s==2){
	   	  					rects.get(i).setP(s2);
	   	  				}
		   	  			else{
		   	  				if(s2!=0){
		   	  					rects.get(i).setWidth(s2);
		   	  				}
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
       	  		for (int i =0; i<rects.size();i++){
       	  			if(rects.get(i)!=null){
	       	  			if (rects.get(i).getRect().intersects(r)) {
	       	  				rect2=rects.get(i).getRect();
	       	  				rectangle = rects.get(i).getRect().getBounds2D();
	       	  			}
       	  			}
       	  		}

    		}
    	}
    	class EventMouseMotionListener extends MouseMotionAdapter {
    		public void mouseDragged(MouseEvent event) {
    			for (int i =0; i<rects.size();i++){
    				if(rects.get(i)!=null){
	    				if (rects.get(i).getRect().contains(event.getX(), event.getY())) {
			    			rectangle = null;
			    			rect2 = rects.get(i).getRect();
			    			//event.getX()>rects.get(i).getP() && event.getX()<=rects.get(i).getP()+12
			    			if(s==1){
			    	    		for (int j =0; j<rects.size();j++){
			        				if(rects.get(j)!=null){
	
				    	    			if (i!=j && !rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    						rects.get(i).dragLeft(event.getX());
				    	    			}
				    	    			else if(rects.size()>0 && i!=j && rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    	    				rects.get(j).addP(-15);
				    	    				fixIntersect(j,-1);
				    	    			}
				    	    			else if(rects.size()==1){
				    	    				rects.get(i).dragLeft(event.getX());
				    	    			}
			        				}
			    	    		}
			    				
			    			}
//			    			event.getX()>rects.get(i).getP()+(rects.get(i).getWidth()-12) && event.getX()<=rects.get(i).getP()+rects.get(i).getWidth()
			    			else if(s==2){
			    				for (int j =0; j<rects.size();j++){
			        				if(rects.get(j)!=null){
			        					
				    					if (i!=j && !rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    	    				rects.get(i).dragRight(event.getX());
				    	    			}
				    	    			else if(rects.size()>0 && i!=j && rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    	    				rects.get(j).addP(15);
				    	    				fixIntersect(j,1);
				    	    			}
				    	    			else if(rects.size()==1){
				    	    				rects.get(i).dragRight(event.getX());
				    	    			}
			        				}
			    	    		}
			    				
			    			}
			    			else{
			    				for (int j =0; j<rects.size();j++){
			        				if(rects.get(j)!=null){

				    	    			if (i!=j && !rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    	    				rects.get(i).dragCenter(event.getX(), event.getY());	
				    	    			}
				    	    			else if(rects.size()>0 && i!=j && rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    	    				int k = rects.get(i).getAbs();
				    	    				rects.get(j).addP(15*k);
				    	    				System.out.println(k); // höger blir negativt och vänster.
				    	    				System.out.println("1. ID " + rects.get(j).getID() + ", antal steg " + 15*k);
				    	    				fixIntersect(j,k);
			
				    	    			}
				    	    			else if(rects.size()==1){
				    	    				rects.get(i).dragCenter(event.getX(), event.getY());
				    	    			}
			        				}
			    	    		}
			    				
			    			}
						}
    				}
	    		}
		        repaint();
	        } 
	    public void mouseMoved(MouseEvent event) {
	    	cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	    }
    }

	public void addTask(int id) {
		// width baseras på duration
		Box temp=new Box(id*45, 5, 45, 20, id);
		rects.add(temp);
	}
	public void fixIntersect(int id, int k) {
		for (int j =0; j<rects.size();j++){
			if(rects.get(j)!=null){

				if(rects.size()>0 && id!=j && rects.get(id).getRect().intersects(rects.get(j).getRect())){
					rects.get(j).addP((15*k));
					System.out.println("2. ID " + rects.get(j).getID() + ", antal steg " + 	15*k);
				}
			}
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
	
	public void setName(String name, int selectedTask)
	{
		rects.get(selectedTask).setName(name);
		repaint();
	}
	
	public void setLength(int width, int selectedTask)
	{
		rects.get(selectedTask).width = (double)width*5;
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
}
