package gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.AppEngine;
import engine.domain.Car;
import factories.CarFactory;
import factories.exceptions.BrandNotCorrectException;
import factories.exceptions.PriceNotCorrectException;
import factories.exceptions.ProductionYearNotCorrectException;
import gui.interfaces.AddCarGUI;
import gui.interfaces.MainWindowGUI;

public class AddCarController implements ActionListener {

	private AppEngine engine;
	private AddCarGUI gui;
	private MainWindowGUI mainWindow;
	
	public AddCarController(AppEngine engine){
		this.engine = engine;
	}

	public void setGui(AddCarGUI gui) {
		this.gui = gui;
	}

	public void setMainWindowGUI(MainWindowGUI mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("confirmAddCar")){
			String strBrand = gui.getCarBrand();
			String strPrice = gui.getCarPrice();
			String strProductionYear = gui.getCarProductionYear();
			
			Car car = null;
			
			try {
				car = CarFactory.getCar(strBrand, strPrice, strProductionYear);
				engine.addCar(car);
				mainWindow.update();
			
				gui.clearAllTextFields();
				mainWindow.switchToFilterView();
				
			} catch (PriceNotCorrectException e1) {
				mainWindow.reportError("Wprowadzona cena jest nieprawid³owa");
			} catch (ProductionYearNotCorrectException e1) {
				mainWindow.reportError("Wprowadzona data produkcji jest nieprawid³owa");
			} catch (BrandNotCorrectException e1) {
				mainWindow.reportError("Wprowadzona marka jest nieprawid³owa");
			}
			
		}
		else if(e.getActionCommand().equalsIgnoreCase("cancelAddCar")){
			gui.clearAllTextFields();
			mainWindow.switchToFilterView();
		}
		
	}

}
