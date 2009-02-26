package Models;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;


/**
 * @author Simonko
 *
 */
public class Task {

	String costumer;
	int duration;
	GregorianCalendar startDate = new GregorianCalendar();
	int id=0;
	
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
        startDate = new GregorianCalendar();
        startDate=new GregorianCalendar(startDate.get(Calendar.YEAR), (startDate.get(Calendar.MONTH)), startDate.get(Calendar.DAY_OF_MONTH));
//    	System.out.println("start1 " + startDate.get(Calendar.DAY_OF_MONTH) + " " + (startDate.get(Calendar.MONTH)+1)  + " " + startDate.get(Calendar.YEAR));
    }
	/**
	 * Construktor.
	 * No parameters
	 */
	public Task() {
		// TODO Auto-generated constructor stub
		// beh�vs parametrar?
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Task test = new Task(1, "n�nne", 1);
		test.skrivUt();
		
	}
	
	/**
	 * Bara f�r att testa.
	 * @return skriver ut en task.
	 */
	public void skrivUt() {
		System.out.println("kund: " + getCostumer());
		System.out.println("duration: " + getDuration());
		System.out.println("start: " + getStringStartDate());
		System.out.println("end: " + getStringEndDate());
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



	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}
	public GregorianCalendar getStartDate() {
		return startDate;
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
	public GregorianCalendar getEndDate() {
		GregorianCalendar endDate = (GregorianCalendar) startDate.clone();
		endDate.add(Calendar.DAY_OF_MONTH, duration);
		return endDate;
	}
	/**
	 * Calclate End-date.
	 * @return Long the endDate in milli seconds.
	 */
	public long getEndDateInMillis() {
		GregorianCalendar endDate = (GregorianCalendar) startDate.clone();
		endDate.add(Calendar.DAY_OF_MONTH, duration);
		return endDate.getTimeInMillis();
	}
	
	/**
	 * Calclate start date.
	 * @return Long the endDate in milli seconds.
	 */
	public long getStartDateInMillis() {
		return startDate.getTimeInMillis();
	}
	public String getStringEndDate() {
		GregorianCalendar endDate = (GregorianCalendar) startDate.clone();
		endDate.add(Calendar.DAY_OF_MONTH, duration);
		String svar = "";
		svar += endDate.get(Calendar.YEAR) + " ";
		svar += (endDate.get(Calendar.MONTH)+1) + " ";
		svar += endDate.get(Calendar.DAY_OF_MONTH);
		return svar;
	}
	public String getStringStartDate() {
		String svar = "";
		svar += startDate.get(Calendar.YEAR) + " ";
		svar += (startDate.get(Calendar.MONTH)+1) + " ";
		svar += startDate.get(Calendar.DAY_OF_MONTH);
		return svar;
	}
	public void setStringDate(String date) {
		StringTokenizer took = new StringTokenizer(date);
		int year=new Integer(took.nextToken());
		int month=new Integer(took.nextToken());
		int day=new Integer(took.nextToken());
		startDate.set(year, month-1, day);
	}
	public void setCostumer(int duration) {
		this.duration=duration;
	}
	public void setDuration(String duration) {
		this.duration=new Integer(duration);
	}
	
}
