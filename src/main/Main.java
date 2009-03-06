package main;
import Controllers.ProdPlannerController;
import Views.ProdPlannerUI;

/**
 * Main class
 * 
 * Executing the ProdPlannerUI class for viewing the
 * application. 
 * 
 * @author pompa, simonko
 * @version 1.0.1
 */

public class Main {
	
	/** Global settings for the application **/
	private static final String VERSION = "1.0.1";
	private static final String TITLE = "ProdPlanner";

	public static void main(String[] args) {
		String title = TITLE + " " + VERSION;
		new ProdPlannerController(new ProdPlannerUI());
	
	}
}