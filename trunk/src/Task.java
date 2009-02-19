import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * @author Simonko
 *
 */
public class Task {

	static String costumer=null;
	static int duration;
	static GregorianCalendar startDate=new GregorianCalendar();
	
	/**
	 * Construktor.
	 * @param costumer the name of the costumer
	 * @param duration the job-duration in days
	 * @param start year
	 * @param start month
	 * @param start date
	 */
    public Task(String costumer, int duration, int year, int month, int date) {
    	this.costumer= costumer;
    	this.duration= duration;
        startDate = new GregorianCalendar(year, month, date);
    	System.out.println("start1 " + startDate.getTime());
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Task test = new Task("n�nne", 1, 2009, 0, 28);
		test.skrivUt();
		test.skrivUt();
	}
	
	/**
	 * Bara f�r att testa.
	 * @return skriver ut en task.
	 */
	public static void skrivUt() {
		System.out.println("kund: " + getCostumer());
		System.out.println("duration: " + getDuration());
    	System.out.println("start " + getStartDate().getTime());
    	System.out.println("end " + getEndDate().getTime());

	}

	public static String getCostumer() {
		return costumer;
	}

	public static void setCostumer(String costumer) {
		Task.costumer = costumer;
	}

	public static int getDuration() {
		return duration;
	}

	public static void setDuration(int duration) {
		Task.duration = duration;
	}

	public static void setStartDate(GregorianCalendar startDate) {
		Task.startDate = startDate;
	}
	public static GregorianCalendar getStartDate() {
		return startDate;
	}
	/**
	 * Calclate End-date.
	 * @return the endDate.
	 */
	public static GregorianCalendar getEndDate() {
		GregorianCalendar endDate = (GregorianCalendar) startDate.clone();
		endDate.add(Calendar.DAY_OF_MONTH, duration);
		return endDate;
	}
}
