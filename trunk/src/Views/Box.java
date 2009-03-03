package Views;

import java.awt.geom.Rectangle2D;

/**
 * @author Simonko
 *
 */
public class Box {
	Rectangle2D rect;
	double p, q, width, height;
	int p1, p2, q1, q2;
	
	
    public Box(double p, double q, double width, double height) {
    	this.rect= new Rectangle2D.Double(p, q, width, height);
    	this.p=p;
    	this.width=width;
    	this.height=height;
    	this.p1=p1;
    	this.p2=p2;
    	this.q1=q1;
    	this.q2=q2;
    }


	public Rectangle2D getRect() {
		return rect;
	}


	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}


	public double getP() {
		return p;
	}


	public void setP(double p) {
		this.p = p;
	}


	public double getQ() {
		return q;
	}


	public void setQ(double q) {
		this.q = q;
	}


	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public int getP1() {
		return p1;
	}


	public void setP1(int p1) {
		this.p1 = p1;
	}


	public int getP2() {
		return p2;
	}


	public void setP2(int p2) {
		this.p2 = p2;
	}


	public int getQ1() {
		return q1;
	}


	public void setQ1(int q1) {
		this.q1 = q1;
	}


	public int getQ2() {
		return q2;
	}


	public void setQ2(int q2) {
		this.q2 = q2;
	}


	public void centerP() {
		if(p%30>15){
  			 p=p+(30-p%30);
  		  }else{
  			 p=p-(p%30);
  		 }
		
	}
	public void centerWidth() {
		if(width%30>15){
			width=width+(30-width%30);
 		}else{
 			width=width-(width%30);
 		}
	}


	public void dragLeft(int x) {
		p2 = x;
		p = p + p2 - p1;
		width=width + (p1-p2);
		p1 = p2;
		
	}


	public void dragRight(int x) {
		p2 = x;
		System.out.println(p2-p1);
		width+=p2-p1;
		p1 = p2;  
		
	}


	public void dragCenter(int x, int y) {
		p2 = x;
		q2 = y;
		p = p + p2 - p1;
		p1 = p2;
		q = q + q2 - q1;     // bort-kommenterat för att "tasken ska ligga på en linje och inte flyta runt i y-led
       	q1 = q2;
		
	}
}
	