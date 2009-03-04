package Controllers;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Models.Box;
import Models.Task;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
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
	ArrayList<Task> tasks = new ArrayList<Task>();	

    private Rectangle2D rect2;
    private Rectangle2D rectangle;
    private int lineX1=0;
    private int lineX2=0;

    Cursor cursor;
    int s=0;//typ av grab.
    double s2=0;// typ2 vid grab
    int s3=0;
    int k=0; //bit som h�ller koll p� vilket h�ll en box g�r �t.
    int k2=-1; //vilket objekt som dras
    private int x, y; // mousepos in pane
    int malarBrada1=70*5;
    int malarBrada2=70*5;
    
    JTable table=null;
    DefaultTableModel model;

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
			g2d.drawLine(-450, 30*i, 790, 30*i);
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
			        	drawInfoBox(g2d, this.x, this.y, i);
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
	    if(k2!=-1 && k2<rects.size()){
	    	g2d.drawLine(rects.get(k2).getLine1(), 0, rects.get(k2).getLine1(), 150);
	    	g2d.drawLine(rects.get(k2).getLine2(), 0, rects.get(k2).getLine2(), 150);
		}
	    else if(k2>=rects.size()){
//	    	g2d.drawLine(rects.get(k2).getLine1(), 0, rects.get(k2).getLine1(), 150);
//	    	g2d.drawLine(rects.get(k2).getLine2(), 0, rects.get(k2).getLine2(), 150);
		}
	    else{
		    if(lineX1==lineX2){
		    	
		    }else{
		    	g2d.drawLine(lineX1, 0, lineX1, 150);
		    	g2d.drawLine(lineX2, 0, lineX2, 150);
		    }
		}
	}
	private boolean mouseOverBox(int x, int y, int w, int h) {
		if ( ( (this.x < x+w)&&(this.x > x)) && ((this.y > y) && (this.y < y+h))) {
//			System.out.println("X = " + x + " Y = " + y + " w = " + w + " h = " + h);	  
			return true;
		}
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
	   h = 100;
	   //cust = dur = earliest = latest = start = end = null;
	  // cust = tasks.get(id).costumer;
	   dur = "" + tasks.get(id).getDurationOfTask();
	   earliest = tasks.get(id).getStringEarliestDate();
	   latest = tasks.get(id).getStringLatestDate();
	   start = "" + tasks.get(id).startDate;
	   end = "" + tasks.get(id).endDate;
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
	   
	   //g2d.drawString("Customer: " + cust, x+5, y+15);
	   g2d.drawString("Duration: " + dur, x+5, y+15);
	   g2d.drawString("Earliest: " + earliest, x+5, y+35);
	   g2d.drawString("Latest: " + latest, x+5, y+55);
	   g2d.drawString("Start: " + start, x+5, y+75);
	   g2d.drawString("End: " + end, x+5, y+95);
   }
    
    class EventMouseListener extends MouseAdapter {
    	public void mousePressed(MouseEvent event) {
    		int x = event.getX()-malarBrada1;
//    		System.out.println("Klick= " + x);
    		int y = event.getY();
       	  	Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);
       	  		for (int i =0; i<rects.size();i++){
       	  			if(rects.get(i)!=null){
       	  				if (rects.get(i).getRect().intersects(r)) {
       	  					k2=i;
       	  					rect2=rects.get(i).getRect();
       	  					rectangle = rects.get(i).getRect().getBounds2D();
       	  					rects.get(i).setP1(x);
       	  					rects.get(i).setQ1(event.getY());
       	  					if(s3==0){
	       	  					if(x>rects.get(i).getP() && x<=rects.get(i).getP()+12){
	       	  						s=1;
	       	  						//s2=rects.get(i).getWidth();
	       	  					}
	       	  					else if(x>rects.get(i).getP()+(rects.get(i).getWidth()-12) && x<=rects.get(i).getP()+rects.get(i).getWidth()){
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
	   	  
    		int x = event.getX()-malarBrada1;
////    		System.out.println("Klick= " + x);
    		int y = event.getY();
//       	  	Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);

       	  	if(rects.size()>0){
//   	  			if (rects.get(k2).getRect().intersects(r)) {
   	  				rectangle = rects.get(k2).getRect().getBounds2D();
   	  				rect2 = rects.get(k2).getRect();
	   	  			rects.get(k2).centerWidth();
   	  				rects.get(k2).centerQ();
//	   	  				System.out.println("s " + s + ", s2 " + s2 + " s3 " + s3);
   	  				if(s==1){
   	   	  				rects.get(k2).centerP();

   	  				}
   	  				else if(s==2){
   	  					rects.get(k2).setP(s2);
   	   	  				rects.get(k2).centerP();
   	  				}
	   	  			else{
	    				for (int j=0; j<rects.size();j++){
	    					if(j!=k2){
		    					if (rects.get(j).getRect().contains(x, y)){
		    						//F�rs�ker hj�lpa den rekursiva funktionen
		    						int startp=(int) rects.get(j).getP();
//		    						/System.out.println((int) (rects.get(k2).getP()));
		    						rects.get(k2).setP(startp);
		    						rects.get(j).setP(startp+(int) rects.get(k2).getWidth());
		    						rects.get(k2).setP(rects.get(j).getP());
		    						fixIntersect(k2,2);
		    						if ((int) (rects.get(k2).getP())==startp){
		    							System.out.println("det Gick bra");
		    							System.out.println((int) (rects.get(k2).getP()));
		    							System.out.println((int) (startp));
		    						}else {
			    						rects.get(k2).setP(startp);
			    						fixIntersect(k2,2);
		    							System.out.println("det Gick inte bra");
		    						}
		    					}
	    					}
		    				else{
		       	   	  			rects.get(k2).centerP();
		    					fixIntersect(k2,2);
	    					}
	    				}
	   	  				if(s2!=0){
	   	  					rects.get(k2).setWidth(s2);
	   	  				}
	   	  			}
   	  			
       	  	}
       	  	s=0;
       	  	s3=0;
       	  	s2=0;
       	  	for(int k =0; k<rects.size();k++){
       	  		tasks.get(k).setStartDate((int) rects.get(k).p);
       	  		tasks.get(k).setEndDate((int) (rects.get(k).p+rects.get(k).width));
       	  		int temp = rects.get(k).calcTimeLine();
       	  		tasks.get(k).setTimeLine(temp);
       	  		
       	  		int startTemp=tasks.get(k).startDate;
       	  		int endTemp=tasks.get(k).endDate;
       	  		int lineTemp=tasks.get(k).line;
       	  		model.setValueAt(startTemp,k,4);
       	  		model.setValueAt(endTemp,k,5);
       	  		model.setValueAt(lineTemp,k,6);
       	 		
       	  	}
       	  	
// 			System.out.println("s " + s + ", s2 " + s2 + " s3 " + s3);
    	}
    	public void mouseClicked(MouseEvent event) {
    		int x = event.getX()-malarBrada1;
    		int y = event.getY();
       	  	Rectangle r = new Rectangle(x - 1, y - 1,2 , 2);	
   	  		for (int i =0; i<rects.size();i++){
   	  			if(rects.get(i)!=null){
   	  				if (rects.get(i).getRect().intersects(r)) {
   	  					rects.get(i).selection(true);
   	  					k2=i;
   	  					table.setRowSelectionInterval(k2, k2);
   	  				}
   	  				else{
   	  					rects.get(i).selection(false);
   	  				}
   	  			}
   	  		}
       	  	for (int i = 0; i<rects.size();i++){
       	  	rects.get(i).selection(false);
       	  	}  	
   	  			if(rects.size()>0){
       	  			if (rects.get(k2).getRect().intersects(r)) {
       	  				rect2=rects.get(k2).getRect();
       	  				rectangle = rects.get(k2).getRect().getBounds2D();
       	  				rects.get(k2).selection(true);
       	  			}
   	  			}
       	  	}
    	}
    	class EventMouseMotionListener extends MouseMotionAdapter {
    		public void mouseDragged(MouseEvent event) {
    			int x = event.getX()-malarBrada1;
//        		System.out.println("Klick= " + x);
				if(rects.size()>0){
//    				if (rects.get(k2).getRect().contains(x, event.getY())) {
		    			rectangle = null;
		    			rect2 = rects.get(k2).getRect();
		    			//event.getX()>rects.get(i).getP() && event.getX()<=rects.get(i).getP()+12
		    			if(s==1){
		    	    		fixIntersect(k2,-1);
    	    				rects.get(k2).dragLeft(x);
		    			}
//			    			event.getX()>rects.get(i).getP()+(rects.get(i).getWidth()-12) && event.getX()<=rects.get(i).getP()+rects.get(i).getWidth()
		    			else if(s==2){
		    				rects.get(k2).dragRight(x);
		    				fixIntersect(k2,1);
		    			}
		    			else{
    	    				rects.get(k2).dragCenter(x, event.getY());
		    			}
//					}
				
	    		}
		        repaint();
	        } 
	    public void mouseMoved(MouseEvent event) {
	    	cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	    	x = event.getX()-malarBrada1;
//    		System.out.println("Klick= " + x);
	    	y = event.getY();
	    }
    }

	public void addTask(int id) {
		// width baseras p� duration
		Task tempTask=new Task(id,"", 35);
		Box temp=new Box(0, 5, 45, 20, id,malarBrada1);
		rects.add(temp);
		tasks.add(tempTask);
	}

	public void fixIntersect(int i, int k){
		int numberOfIntersects=0;
		int firstHit=-1;
		for (int j =0; j<rects.size();j++){
			if(j!=i && rects.get(i).getRect()!=null && rects.get(j).getRect()!=null){
				if(rects.get(i).getRect().intersects(rects.get(j).getRect())){
					if(k==2){
						rects.get(i).setP(rects.get(j).getP());
						rects.get(j).addP((int) (rects.get(i).getP()+rects.get(i).getWidth()-rects.get(j).getP()));
						rects.get(j).addP(numberOfIntersects*5);
						if(numberOfIntersects==0){
							firstHit=j;
							k=1;
						}
					}
					else if(k>0){
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

//	public void setData(String name, String dur, String e, String l, String s, String ed, int selectedTask)
//	{
//		System.out.println("SetData: " + name);
//		rects.get(selectedTask).setData(name, dur, e, l, s, ed);
//		repaint();
//	}
	public void setCostumer(int id,String name)
	{
		rects.get(id).setName(name);
		tasks.get(id).setCostumer(name);
		repaint();
	}
	public void setSelection(boolean b, int st)
	{
		k2=st;
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
	public void paintDuration(int selectedTask)
	{
		int x1=getEarliestDateInDays(selectedTask);
		int x2=getLatestDateInDays(selectedTask);
		lineX1=x1*5-1;
		lineX2=x2*5-1;
		rects.get(k2).setDuration(x1*5-1,x2*5-1);
		repaint();
	}
	public void moveBoard(int value) {
		malarBrada1=-value*5;
		for(int i = 0; i<rects.size();i++){
			rects.get(i).addP((malarBrada1-malarBrada2)/11);
		}
		repaint();
	}

	public void drop(int id) {
		tasks.remove(id);
		rects.get(id).remove();
		rects.get(id).setRect(0,0,0,0);
		repaint();
		rects.set(id, null);
		rects.remove(id);
	}
	public void setDuration(int i, int duration) {
		tasks.get(i).setDuration(duration);
		lineX2=lineX1-1+duration*5;
		rects.get(k2).setLine2(duration);
		repaint();
	}
	public String getEarliestDate(int rowCount) {
		return tasks.get(rowCount).getStringEarliestDate();
	}
	public String getLatestDate(int rowCount) {
		return tasks.get(rowCount).getStringLatestDate();
	}
	public int getEarliestDateInDays(int selectedTask) {
		return tasks.get(selectedTask).getEarliestInDays();
	}
	public int getLatestDateInDays(int selectedTask) {
		return tasks.get(selectedTask).getLatestInDays();
	}
	public Boolean addEarliestDate(int i, String date) {
		return tasks.get(i).setStringDate(date);
	}

	public void tableChanged(TableModelEvent e) {
		TableModel model = (TableModel)e.getSource();
        int a = e.getColumn();
        int b = e.getFirstRow();
        
        try {
        	
//			System.out.println(a + ", " + b + ", " + model.getValueAt(b, a));  
			if(a==0){ 
				setCostumer(b,(String)model.getValueAt(b, a));
			}
			else if(a==1){ //duration
				setDuration(b, Integer.parseInt((String)model.getValueAt(b, a)));
				model.setValueAt(getLatestDate(b),b,3);
				
				// grym oneliner haha

			}
			else if(a==2){ //start date
				if (!addEarliestDate(b,(String) model.getValueAt(b, a))){
					model.setValueAt(getEarliestDate(b) ,b,2);
					//TLDraw.setEarliest(Integer.parseInt((String)model.getValueAt(b, a)));
				}
				paintDuration(b);
				model.setValueAt(getLatestDate(b) ,b,3);
			}
			else{
				//System.out.println("Other change " + a);
			}

		} catch (Exception e1) {
		}

	}

	public void valueChanged(ListSelectionEvent e, int selectedTask,int getSelectedRow) {
		if (getSelectedRow >= 0) {
			if(getArraySize()>selectedTask){
				for (int i = 0; i<rects.size();i++){
		       	  	rects.get(i).selection(false);
	       	 	}
				setSelection(true,getSelectedRow);
				paintDuration(selectedTask);
	       }
	        
		}
	}

	public void scrollBarAdjustments(int value) {
		moveBoard(value);
	}

	public void deleteEvent(ActionEvent e, int markerad, JButton deleteButton) {
		try{
			int temp=markerad;
			System.out.print("Du vill ta bort rad: " + markerad + " som har id: "+ temp);
			drop(markerad);

		} catch (Exception e1) {
		}
		
	}
	
	public void getImportentObjects(JTable table,  DefaultTableModel model) {
		this.table =table;
		this.model = model;
	}
}
