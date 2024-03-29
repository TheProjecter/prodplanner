package Models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * @author Simonko
 *
 */
public class Box extends Rectangle2D{
	static final int DAY=5;
	
	public double p;
	public double q;
	public double width;
	double height;
	int p1, p2, q1, q2;
	int id;
	int k1, k2;
	public String namn, dur, earliest, latest, start, end;
	private Color color;
	private boolean isSelected = false;
    public int lineX1;
    public int lineX2;
    int line;
	
    public Box(double p, double q, double width, double height, int id, int malarBrada1, int i, int j) {
    	line=0;
    	this.id=id;
		this.namn= ""+id;
    	k1=0;
    	k2=0;
    	this.p=45*id;
    	this.q=155;
    	this.width=width;
    	this.height=height;
    	this.p1=p1+malarBrada1;
    	this.p2=p2+malarBrada1;
    	this.q1=q1;
    	this.q2=q2;
    	lineX1=i;
    	lineX2=j;

    	
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
		if(p%DAY==0){
		}
		else if(p%DAY>DAY/2){
  			 p=p+(DAY-p%DAY);
		}else{
  			 p=p-(p%DAY);
  		 }

	}
	public void centerWidth() {

		if(width%DAY==0){
			
 		}
		else if(width%DAY>DAY/2){
			width=width+(DAY-width%DAY);
 		}
 		else{
 			width=width-(width%DAY);
 		}
//		if(width==0){
//			width=15;
//		}
	}
	public void centerQ() {
		if(q%30==0){
			
		}
		else if(q%30>15){
			q=q+(30-q%30);
 		}
 		else{
 			q=q-(q%30);
 		}
		q+=5;
		if (q<0 || q>155){
			q=155;
			
		}

	}


	public void dragLeft(int x) {
		p2 = x;
		p = p + p2 - p1;
		width=width + (p1-p2);
		p1 = p2;
		k1=-1;
		k2=-1;
		if(width<9){
			width=10;
		}
	}


	public void dragRight(int x) {
		p2 = x;

		width+=p2-p1;
		p1 = p2;
		k1=1;
		k2=1;
		if(width<9){
			width=10;
		}


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
       	
		p1 = p2;
		q = q + q2 - q1;     // bort-kommenterat f�r att "tasken ska ligga p� en linje och inte flyta runt i y-led
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
			return 1;
		}else if(k2!=0 && k2<0){
			return -1;
		}else if(k1>=0){
			return 1;
		}
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
//	public void setData(String name, String dur, String e, String l, String s, String ed)
//	{
//		this.namn = name;
//		this.dur = dur;
//		this.earliest = e;
//		this.latest = l;
//		this.start = s;
//		this.end = ed;
//	}
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
	public void setDuration(int x1, int x2)
	{
		this.lineX1=x1;
		this.lineX2=x2;
	}
	public int getLine1()
	{
		return this.lineX1;
	}
	public int getLine2()
	{
		return this.lineX2;
	}

	public void setLine2(int width) {
		lineX2=lineX1-1+width*5;
	}

	public int calcTimeLine() {
		if (q==5){
			return 1;
		}
		else if(q==35){
			return 2;
		}
		else if(q==65){
			return 3;
		}
		else if(q==95){
			return 4;
		}
		else if(q==125){
			return 5;
		}
		else if(q==155){
			return 6;
		}
		else{}
		return 0;
	}

	public void setLine1(int x1) {
		lineX1=x1;
	}
}
	