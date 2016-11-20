package gui.interfaces;

import gui.controllers.AddCarController;

public interface AddCarGUI {
	public String getCarBrand();

	public String getCarPrice();

	public String getCarProductionYear();

	public void clearAllTextFields();

	public void setAddCarController(AddCarController addCarController);
}
