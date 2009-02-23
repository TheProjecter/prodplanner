package Controllers;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import Models.Task;


public class TaskController {
	ArrayList<Task> tasks = new ArrayList<Task>();	
	
	
	/**
	 * Construktor.
	 */
    public TaskController() {
    }
    
    
	/**
	 * moveMent - Om en en task crashar med en annan efter att
	 * man har flyttat den.
	 * 
	 * @return true om flytten är ok!
	 * @param args
	 */
	public boolean moveStartDate(Task a) {
		for (int i=0; i<tasks.size();i++){
			if (a.getId()!=tasks.get(i).getId()){
				//om task a's start datum ligger i någon annan tasks-intervall... då returnerar vi false.
				if (a.getStartDate().getTimeInMillis()<tasks.get(i).getEndDate().getTimeInMillis() && a.getStartDate().getTimeInMillis()>tasks.get(i).getStartDate().getTimeInMillis()){
					return false;
					//Du har försökt ändra Task a's start-datum?
				}
				else {				
				}
			}
		}
		return true;
	}
	
	/**
	 * moveMent - Om en en task crashar med en annan efter att
	 * man har ändrat/flyttat dess duration.
	 * 
	 * @return true om flytten är ok!
	 * @param args
	 */
	public boolean moveEndDate(Task a) {
		for (int i=0; i<tasks.size();i++){
			if (a.getId()!=tasks.get(i).getId()){
				//om task a's start datum ligger i någon annan tasks-intervall... då returnerar vi false.
				if (a.getStartDateInMillis()<tasks.get(i).getEndDateInMillis() && a.getStartDate().getTimeInMillis()>tasks.get(i).getStartDateInMillis()){
					return false;
					//Du har försökt ändra Task a's start-datum?
				}
			}
		}
		return true;
	}
	/**
	 * addTask - Lägger till en task i ArrayListen och skapar ett nytt Task element.
	 * 
	 * @param args
	 */
	public void addTask() {
		Task temp = new Task();
		tasks.add(temp);
		System.out.println("Ny task skapad");	
	}
}