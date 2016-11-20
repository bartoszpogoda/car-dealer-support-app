package gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.AppEngine;
import engine.filters.ICarFilter;
import factories.CarFilterFactory;
import factories.exceptions.BrandNotCorrectException;
import factories.exceptions.PriceNotCorrectException;
import factories.exceptions.ProductionYearNotCorrectException;
import factories.exceptions.StateNotCorrectException;
import gui.interfaces.FilterGUI;
import gui.interfaces.MainWindowGUI;

public class FilterController implements ActionListener {

	private AppEngine engine;
	private FilterGUI gui;
	private MainWindowGUI mainWindow;

	public FilterController(AppEngine engine) {
		this.engine = engine;
	}

	public void setGui(FilterGUI gui) {
		this.gui = gui;
	}

	public void setMainWindowGUI(MainWindowGUI mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("addFilter")) {
			String filterType = gui.getNewFilterType();
			String filterParameter = gui.getNewFilterParameter();

			ICarFilter carFilter = null;

			try {
				carFilter = CarFilterFactory.getFilter(filterType, filterParameter);
				engine.addFilter(carFilter);
				mainWindow.update();
				gui.updateFilterList();

			} catch (PriceNotCorrectException e1) {
				mainWindow.reportError("Wprowadzona cena jest nieprawid³owa");
			} catch (StateNotCorrectException e1) {
				mainWindow.reportError("Wprowadzony stan nie istnieje");
			} catch (BrandNotCorrectException e1) {
				mainWindow.reportError("Wprowadzona marka jest nieprawid³owa");
			} catch (ProductionYearNotCorrectException e1) {
				mainWindow.reportError("Wprowadzona data produkcji jest nieprawid³owa");
			}

		} else if (e.getActionCommand().equalsIgnoreCase("deleteFilter")) {
			int filterId = gui.getSelectedFilterId();
			engine.removeFilter(filterId);

			mainWindow.update();
			gui.updateFilterList();
		}

	}

}
