import Controllers.ProdPlannerController;
import Views.ProdPlannerUI;

/**
 * Main class
 * 
 * Executing the ProdPlannerUI class for viewing the
 * application. 
 * 
 * @author pompa, simonko
 * @version 0.1.0
 */

public class Main {
	
	/** Global settings for the application **/
	private static final String VERSION = "0.2.0";
	private static final String TITLE = "ProdPlanner";

	public static void main(String[] args) {
		String title = TITLE + " " + VERSION;
		new ProdPlannerController(new ProdPlannerUI());
	
	}
}