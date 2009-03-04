package Controllers;

import java.awt.event.WindowEvent;

import Views.ProdPlannerUI;

public class ProdPlannerController {

	private ProdPlannerUI view;
		
	public ProdPlannerController(ProdPlannerUI view)
	{
		this.view = view;
		
	}
	
	public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
