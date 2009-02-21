package Controllers;
import java.util.ArrayList;
import Models.Task;


public class TaskController {
	ArrayList<Task> tasks = new ArrayList<Task>();	
	
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
}