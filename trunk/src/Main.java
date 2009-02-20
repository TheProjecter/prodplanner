import java.util.ArrayList;

/**
 * 
 */

/**
 * Prodplanner
 * 
 * @author pompa
 *
 */
public class Main {
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * moveMent - Om en en task crashar med en annan efter att
	 * man har flyttat den.
	 * 
	 * @return true om flytten �r ok!
	 * @param args
	 */
	public boolean moveStartDate(Task a) {
		for (int i=0; i<tasks.size();i++){
			if (a.getId()!=tasks.get(i).getId()){
				//om task a's start datum ligger i n�gon annan tasks-intervall... d� returnerar vi false.
				if (a.getStartDate().getTimeInMillis()<tasks.get(i).getEndDate().getTimeInMillis() && a.getStartDate().getTimeInMillis()>tasks.get(i).getStartDate().getTimeInMillis()){
					return false;
					//Du har f�rs�kt �ndra Task a's start-datum?
				}
			}
		}
		
		return true;
	}

}
