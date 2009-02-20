package Models;
import java.util.Calendar;
import java.util.GregorianCalendar;


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
    public Task(int id, String costumer, int duration, int year, int month, int date) {
    	this.id=id;
    	this.costumer= costumer;
    	this.duration= duration;
        startDate = new GregorianCalendar(year, month, date);
    	System.out.println("start1 " + startDate.getTime());
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Task test = new Task(1, "nånne", 1, 2009, 0, 28);
		test.skrivUt();
		
	}
	
	/**
	 * Bara för att testa.
	 * @return skriver ut en task.
	 */
	public void skrivUt() {
		System.out.println("kund: " + getCostumer());
		System.out.println("duration: " + getDuration());
    	System.out.println("start " + getStartDate().getTime());
    	System.out.println("end " + getEndDate().getTime());
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

	public void setDuration(int duration) {
		this.duration = duration;
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
}
