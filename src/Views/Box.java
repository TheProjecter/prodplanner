package Views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * @author Simonko
 *
 */
public class Box extends Rectangle2D{
	double p, q, width, height;
	int p1, p2, q1, q2;
	int id;
	int k1, k2;
	public String namn, dur, earliest, latest, start, end;
	private Color color;
	private boolean isSelected = false;
	
    public Box(double p, double q, double width, double height, int id) {
    	this.id=id;
		this.namn= "";
    	k1=0;
    	k2=0;
    	this.p=60*id;
    	this.q=155;
    	this.width=width;
    	this.height=height;
    	this.p1=p1;
    	this.p2=p2;
    	this.q1=q1;
    	this.q2=q2;
    	
    	Random rn = new Random();
    	color = new Color(rn.nextInt(255), rn.nextInt(255), rn.nextInt(255));
    }

	@Override
	public void setRect(double x, double y, double w, double h) {
		p=x;
		q=y;
		width=w;
		height=h;
	}

	public Rectangle2D getRect() {
		return this;
	}


	public void setRect(Rectangle2D rect) {
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
//		System.out.println("Before P: " + p);

		if(p%15>8){
  			 p=p+(15-p%15);
  		  }else{
  			 p=p-(p%15);
  		 }
//		System.out.println("Final P: " + p);

		
	}
	public void centerWidth() {
//		System.out.println("Before W: " + width);

		if(width%15>8){
			width=width+(15-width%15);
 		}else if(width%15==0){
 		}
 		else{
 			width=width-(width%15);
 		}
//		System.out.println("Final W: " + width);
	}
	public void centerQ() {
//		System.out.println("Before W: " + width);

		if(q%30>15){
			q=q+(30-q%30);
 		}else if(q%30==0){
 		}
 		else{
 			q=q-(q%30);
 		}
		q+=5;
		if (q<0 || q>150){
			q=150;
			
		}
//		System.out.println("Final W: " + width);

	}


	public void dragLeft(int x) {
		p2 = x;
		p = p + p2 - p1;
		width=width + (p1-p2);
		//System.out.println(id + " Left");
//		System.out.println(width);
		p1 = p2;
		k1=-1;
		k2=-1;
       	//System.out.println("p2-p1: " + k1 + ", k2:" + k2);

		
	}


	public void dragRight(int x) {
		p2 = x;

		width+=p2-p1;
		p1 = p2;
		k1=1;
		k2=1;
       	//System.out.println("p2-p1: " + k1 + ", k2:" + k2);

//		System.out.println(width);

	}
	
	public Color getColor()
	{
		return this.color;
	}


	public void dragCenter(int x, int y) {
		p2 = x;
		q2 = y;
		p = p + p2 - p1;
		k2=k1;
       	k1= (p2-p1);
       	
       	//System.out.println("p2-p1: " + k1 + ", k2:" + k2);
//       	System.out.println("width: " + width);
		p1 = p2;
		q = q + q2 - q1;     // bort-kommenterat för att "tasken ska ligga på en linje och inte flyta runt i y-led
       	q1 = q2;
	}


	@Override
	public Rectangle2D createIntersection(Rectangle2D r) {
		
		return null;
	}


	@Override
	public Rectangle2D createUnion(Rectangle2D r) {
		
		return null;
	}


	@Override
	public int outcode(double x, double y) {
		return 0;
	}


	@Override
	public double getX() {
		return p;
	}


	@Override
	public double getY() {
		return q;
	}


	@Override
	public boolean isEmpty() {
		
		return false;
	}

	public void addP(int i) {
		p+=i;
		
	}

	public int getAbs() {
		if(k2!=0 && k2 >=0){
			//System.out.println("1. k1=" + k1);
			return 1;
		}else if(k2!=0 && k2<0){
			//System.out.println("2. k1=" + k1);
			return -1;
		}else if(k1>=0){
			//System.out.println("3. k2=" + k2);
			return 1;
		}
		//System.out.println("4. k1=" + k1 + "k2=" + k2);
		return -1;
	}
	public int getID() {
		return id;
	}
	public String getNamn() {
		return namn;
	}
	public void setName(String name)
	{
		this.namn = name;
	}
	
	public void setData(String name, String dur, String e, String l, String s, String ed)
	{
		this.namn = name;
		this.dur = dur;
		this.earliest = e;
		this.latest = l;
		this.start = s;
		this.end = ed;
	}

	public void remove() {
    	this.id=0;
		namn="";
    	k1=0;
    	k2=0;
    	this.p=0;
    	this.q=0;
    	this.width=0;
    	this.height=0;
    	this.p1=0;
    	this.p2=0;
    	this.q1=0;
    	this.q2=0;
		
	}
	
	public void selection(boolean b)
	{
		this.isSelected = b;
	}
	
	public boolean getSelection()
	{
		return this.isSelected;
	}
}
	