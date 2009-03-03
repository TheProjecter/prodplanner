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
	ArrayList<Box> rects = new ArrayList<Box>();
    Rectangle2D rect2;
    Rectangle2D rectangle;
    Cursor cursor;
    int s=0;//typ av grab.


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
		for (int i =0; i<rects.size();i++){
			if(rects.get(i)!=null){
			    g2d.setColor(new Color(0,0,0));
			    g2d.draw(rects.get(i).getRect());	    
			    g2d.fill(rects.get(i).getRect());
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
	   	  			}
	       	  	}
       	  	}
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
	       	  				System.out.println("Du träffade första");
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
			    			if(event.getX()>rects.get(i).getP() && event.getX()<=rects.get(i).getP()+12){
			    	    		for (int j =0; j<rects.size();j++){
			        				if(rects.get(j)!=null){
	
				    	    			if (i!=j && !rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    						rects.get(i).dragLeft(event.getX());
				    	    			}
				    	    			else if(rects.size()>0 && i!=j && rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    	    				rects.get(j).addP(-30);
				    	    				fixIntersect(j,-1);
				    	    			}
				    	    			else if(rects.size()==1){
				    	    				rects.get(i).dragLeft(event.getX());
				    	    			}
			        				}
			    	    		}
			    				
			    			}
			    			else if(event.getX()>rects.get(i).getP()+(rects.get(i).getWidth()-12) && event.getX()<=rects.get(i).getP()+rects.get(i).getWidth()){
			    				for (int j =0; j<rects.size();j++){
			        				if(rects.get(j)!=null){

				    					if (i!=j && !rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    	    				rects.get(i).dragRight(event.getX());
				    	    			}
				    	    			else if(rects.size()>0 && i!=j && rects.get(i).getRect().intersects(rects.get(j).getRect())){
				    	    				rects.get(j).addP(30);
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
				    	    				int k = rects.get(j).getAbs();
				    	    				rects.get(j).addP(30*k);
				    	    				System.out.println(k); // höger blir negativt och vänster.
				    	    				System.out.println("1. ID " + rects.get(j).getID() + ", antal steg " + 30*k);
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
		Box temp=new Box(id*60, 5, 60, 20, id);
		rects.add(temp);
	}
	public void fixIntersect(int id, int k) {
		for (int j =0; j<rects.size();j++){
			if(rects.get(j)!=null){

				if(rects.size()>0 && id!=j && rects.get(id).getRect().intersects(rects.get(j).getRect())){
					rects.get(j).addP((30*k));
					System.out.println("2. ID " + rects.get(j).getID() + ", antal steg " + 30*k);
				}
			}
		}
	}

	public void removeTask(int selectedTask) {
		System.out.println(selectedTask);
		rects.get(selectedTask).remove();
		rects.get(selectedTask).setRect(0,0,0,0);
		repaint();
		rects.set(selectedTask, null);
		//Ta bort en task.
	}
}

