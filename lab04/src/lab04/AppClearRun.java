package lab04;

import engine.AppEngine;
import engine.AppEngineImpl;
import engine.repositories.CarRepository;
import engine.repositories.HistoryRepository;
import engine.repositories.ReservationRepository;
import engine.repositories.impl.CarRepositoryImpl;
import engine.repositories.impl.HistoryRepositoryImpl;
import engine.repositories.impl.ReservationRepositoryImpl;
import gui.MainWindow;
import gui.PanelAddCar;
import gui.PanelFilters;
import gui.controllers.AddCarController;
import gui.controllers.CarManagementController;
import gui.controllers.FilterController;
import gui.tables.MyCarTableModel;
import gui.tables.MyFilterListModel;
import gui.tables.MyHistoryTableModel;
import gui.tables.MyReservationTableModel;

public class AppClearRun {

	
	public static void main(String[] args) {
		
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
		        public void run() {
		        	FontSizeChanger.setDefaultSize(15);
		        			        	
		        	//		 			app construction
		    		
		    		// 			ENGINE construction
		    		CarRepository carRepo = new CarRepositoryImpl();
		    		ReservationRepository reservRepo = new ReservationRepositoryImpl();
		    		HistoryRepository historyRepo = new HistoryRepositoryImpl();
		    		

		    		AppEngine engine = new AppEngineImpl(carRepo, reservRepo, historyRepo);
		    		
		    		
		    		//			GUI construction
		    		MainWindow mainWindow = new MainWindow();
		    		
		    		
		    		// filter components construction
		    		FilterController filterController = new FilterController(engine);
		    		filterController.setMainWindowGUI(mainWindow);
		    		
		    		PanelFilters panelFilters = new PanelFilters();
		    		panelFilters.setFilterController(filterController);
		    		
		    		panelFilters.setListModel(new MyFilterListModel(engine));	
		    		
		    		filterController.setGui(panelFilters);
		    		
		    		mainWindow.setPanelFilters(panelFilters);
		    		mainWindow.switchToFilterView();
		    		
		    		// addCar
		    		AddCarController addCarController = new AddCarController(engine);
		    		PanelAddCar panelAddCar = new PanelAddCar();
		    		
		    		panelAddCar.setAddCarController(addCarController);
		    		addCarController.setGui(panelAddCar);
		    		addCarController.setMainWindowGUI(mainWindow);
		    		mainWindow.setPanelAddCar(panelAddCar);
		    		
		    		// table construction
		    		mainWindow.setCarTableModel(new MyCarTableModel(engine));
		    		CarManagementController carManagementController = new CarManagementController(engine);
		    		carManagementController.setMainWindowGUI(mainWindow);
		    		carManagementController.setGui(mainWindow);
		    		mainWindow.setCarManagementController(carManagementController);
		    		
		    		
		    		// history construction
		    		mainWindow.setHistoryTableModel(new MyHistoryTableModel(engine));
		    		// reservations construcion
		    		mainWindow.setReservationTableModel(new MyReservationTableModel(engine));
		        }
		    });
	}

}
