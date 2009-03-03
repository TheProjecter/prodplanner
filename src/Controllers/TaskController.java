package Controllers;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import Models.Task;


public class TaskController {
	ArrayList<Task> tasks = new ArrayList<Task>();	
	
    public TaskController() 
    {
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
				if (a.getStartDate().getTimeInMillis()<tasks.get(i).getLatestDate().getTimeInMillis() && a.getStartDate().getTimeInMillis()>tasks.get(i).getStartDate().getTimeInMillis()){
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
				//om task a's start datum ligger i någon annan 
				//tasks-intervall... då returnerar vi false.
				if (a.getEarliestDateInMillis()<tasks.get(i).getLatestDateInMillis() && a.getStartDate().getTimeInMillis()>tasks.get(i).getEarliestDateInMillis()){
					return false;
					//Du har försökt ändra Task a's start-datum?
				}
			}
		}
		return true;
	}
	/**
	 * addTask - Lägger till en task i ArrayListen och 
	 * skapar ett nytt Task element.
	 * 
	 * @param args
	 */
	public void addTask(int ID) {
		Task temp = new Task(ID,"", 10); // TODO: Fixa rätt input.
		tasks.add(temp);
		System.out.println("Ny task skapad");
	}




	public void printAll() {
		for (int i=0; i<tasks.size();i++){
			System.out.println("id " + i);
			tasks.get(i).skrivUt();
		}
	}

	public int getDuration(int i) {
		return tasks.get(i).getDuration();
	}

	public String getLatestDate(int i) {
		return tasks.get(i).getStringLatestDate();
	}

	public String getEarliestDate(int i) {
		return tasks.get(i).getStringEarliestDate();
	}

	public void addCostumer(int i, String costumer) {
		tasks.get(i).setCostumer(costumer);
		
	}
	public void addDuration(int i, String duration) {
		tasks.get(i).setDuration(duration);
		
	}
	public Boolean addEarliestDate(int i, String date) {
		return tasks.get(i).setStringDate(date);
	}

	public void drop(int temp) {
		tasks.remove(temp);
	}

	public int getEarliestDateInDays(int selectedTask) {
		return tasks.get(selectedTask).getEarliestInDays();
	}

	public int getLatestDateInDays(int selectedTask) {

		return tasks.get(selectedTask).getLatestInDays();
	}
}