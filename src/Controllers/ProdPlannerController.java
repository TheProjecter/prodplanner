package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

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
