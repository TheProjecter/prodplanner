package Models;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;


/**
 * @author Simonko
 *
 */
public class Task {
	static final int DAY=5;
	public String costumer;
	int duration;
	GregorianCalendar earliestDate = new GregorianCalendar();
	GregorianCalendar startDateG = new GregorianCalendar();
	GregorianCalendar endDateG = new GregorianCalendar();
	public int startDate;
	public int endDate;

	int id=0;
	//Rectangle rectangle=new Rectangle();
	public int line; // vilken rad en task ligger på.
	
	/**
	 * Construktor.
	 * @param task id-number
	 * @param costumer the name of the costumer
	 * @param duration the job-duration in days
	 * @param start year
	 * @param start month
	 * @param start date
	 */
    public Task(int id, String costumer, int duration) {
    	this.id=id;
    	this.costumer= costumer;
    	this.duration= duration;
    	this.line=0;
        earliestDate = new GregorianCalendar();
        earliestDate=new GregorianCalendar(earliestDate.get(Calendar.YEAR), (earliestDate.get(Calendar.MONTH)), earliestDate.get(Calendar.DAY_OF_MONTH));
//    	startDate = null;
//    	endDate = null;
        startDate=0;
        endDate=0;

//    	System.out.println("start1 " + startDate.get(Calendar.DAY_OF_MONTH) + " " + (startDate.get(Calendar.MONTH)+1)  + " " + startDate.get(Calendar.YEAR));
    }
	/**
	 * Construktor.
	 * No parameters
	 */
	public Task() {
		// behövs parametrar?
	}
	public void setStartDate(int i) {
		startDate=i;
	}
	public void setEndDate(int i) {
		endDate=i;
	}
	public void setLine(int i) {
		line=i;
	}
	public int getLine() {
		return line;
	}

	
	/**
	 * Bara för att testa.
	 * @return skriver ut en task.
	 */
	public void skrivUt() {
//		System.out.println("kund: " + getCostumer());
//		System.out.println("duration: " + getDuration());
//		System.out.println("start: " + getStringStartDate());
//		System.out.println("end: " + getStringEndDate());
    	//System.out.println("start " + getStartDate().getTime());
    	//System.out.println("end " + getEndDate().getTime());
	}

	public String getCostumer() {
		return this.costumer;
	}

	public void setCostumer(String costumer) {
		this.costumer = costumer;
	}

	public int getDuration() {
		return this.duration;
	}



	public void setEarliestDate(GregorianCalendar startDate) {
		this.earliestDate = startDate;
	}
	public GregorianCalendar getEarliestDate() {
		return earliestDate;
	}
	/**
	 * return task-id
	 * @return int id, the task id .
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * Calclate End-date.
	 * @return the endDate.
	 */
	public GregorianCalendar getLatestDate() {
		GregorianCalendar endDate = (GregorianCalendar) earliestDate.clone();
		endDate.add(Calendar.DAY_OF_MONTH, duration);
		return endDate;
	}
	/**
	 * Calclate End-date.
	 * @return Long the endDate in milli seconds.
	 */
	public long getLatestDateInMillis() {
		GregorianCalendar endDate = (GregorianCalendar) earliestDate.clone();
		endDate.add(Calendar.DAY_OF_MONTH, duration);
		return endDate.getTimeInMillis();
	}
	
	/**
	 * Calclate start date.
	 * @return Long the endDate in milli seconds.
	 */
	public long getEarliestDateInMillis() {
		return earliestDate.getTimeInMillis();
	}
	public String getStringLatestDate() {
		GregorianCalendar endDate = (GregorianCalendar) earliestDate.clone();
		endDate.add(Calendar.DAY_OF_MONTH, duration);
		String svar = "";
		svar += endDate.get(Calendar.YEAR) + " ";
		svar += (endDate.get(Calendar.MONTH)+1) + " ";
		svar += endDate.get(Calendar.DAY_OF_MONTH);
		return svar;
	}

	public String getStringEarliestDate() {
		String svar = "";
		svar += earliestDate.get(Calendar.YEAR) + " ";
		svar += (earliestDate.get(Calendar.MONTH)+1) + " ";
		svar += earliestDate.get(Calendar.DAY_OF_MONTH);
		return svar;
	}
	public boolean setStringDate(String date) {
		StringTokenizer took = new StringTokenizer(date);
		if (took.countTokens()<3){
			return false;
		}
		GregorianCalendar copyDate = (GregorianCalendar) earliestDate.clone();
		String y = took.nextToken();
		String m = took.nextToken();
		String d = took.nextToken();

		int year=new Integer(y);
		int month=new Integer(m);
		int day=new Integer(d);
		if (month<1 || month>12 ){
			System.out.println("något blev fel!");
			return false;
		}
		copyDate.set(year, month-1, 1);

		if (day<1 || day>copyDate.getActualMaximum(Calendar.DAY_OF_MONTH)){
			return false;
		}
		earliestDate.set(year, month-1, day);
		return true;
	}

	public void setDuration(int duration) {
		this.duration=duration;
	}
	public int getEarliestInDays() {
		long a =getEarliestDateInMillis()/(1000*60*60*24);
		long b =new GregorianCalendar().getTimeInMillis()/(1000*60*60*24);
		GregorianCalendar temp=new GregorianCalendar();
		System.out.println((int) a-(int) b);
		
		
		return (int) (a-b)+1;// + 1 för timmar osv.
	}
	public int getLatestInDays() {
		long a =getLatestDateInMillis()/(1000*60*60*24);
		long b =new GregorianCalendar().getTimeInMillis()/(1000*60*60*24);

		System.out.println(a-b);
		return (int) (a-b);
	}
	public void setTimeLine(int temp) {
		line=temp;
		
	}
	public int getDurationOfTask() {
		
		return (endDate-startDate);
	}
	public String getStringStartDate() {
		GregorianCalendar temp = new GregorianCalendar();
		temp.add(Calendar.DAY_OF_MONTH, startDate/5);
		String svar = "";
		svar += temp.get(Calendar.YEAR) + " ";
		svar += (temp.get(Calendar.MONTH)+1) + " ";
		svar += temp.get(Calendar.DAY_OF_MONTH);
		return svar;
	}
	public String getStringEndDate() {
		GregorianCalendar temp = new GregorianCalendar();
		temp.add(Calendar.DAY_OF_MONTH, endDate/5);
		String svar = "";
		svar += temp.get(Calendar.YEAR) + " ";
		svar += (temp.get(Calendar.MONTH)+1) + " ";
		svar += temp.get(Calendar.DAY_OF_MONTH);
		return svar;
	}
}
