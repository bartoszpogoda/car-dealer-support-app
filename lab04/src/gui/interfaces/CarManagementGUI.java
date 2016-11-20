package gui.interfaces;

import gui.controllers.CarManagementController;

public interface CarManagementGUI {
	public int getSelectedCarID();

	public void setCarManagementController(CarManagementController controller);
}
