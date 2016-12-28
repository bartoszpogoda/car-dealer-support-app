package lab04;

import java.util.Random;

import engine.AppEngine;
import engine.AppEngineImpl;
import engine.domain.Car;
import engine.repositories.CarRepository;
import engine.repositories.HistoryRepository;
import engine.repositories.ReservationRepository;
import engine.repositories.impl.CarRepositoryImpl;
import engine.repositories.impl.HistoryRepositoryImpl;
import engine.repositories.impl.ReservationRepositoryImpl;
import factories.CarFactory;
import factories.exceptions.BrandNotCorrectException;
import factories.exceptions.PriceNotCorrectException;
import factories.exceptions.ProductionYearNotCorrectException;
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

public class AppFillRun {
	public static void main(String[] args) {
		
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
		        public void run() {
		        	//FontSizeChanger.setDefaultSize(15);
		        	
		        	//		 			app construction
		    		
		    		// 			ENGINE construction
		    		CarRepository carRepo = new CarRepositoryImpl();
		    		ReservationRepository reservRepo = new ReservationRepositoryImpl();
		    		HistoryRepository historyRepo = new HistoryRepositoryImpl();
		    		

		    		AppEngine engine = new AppEngineImpl(carRepo, reservRepo, historyRepo);
		    		
		    		
		    		//			GUI construction
		    		MainWindow mainWindow = new MainWindow(); //engine?
		    		
		    		
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
		    		
		    		// --FILL
		    		
		    		String[] brands = {"Mercedes", "Audi", "Opel", "Lamborghini", "Fiat", "Ferrari"};
		    		Random rand = new Random();
		    		int n;// = 1000;
		    		try{
		    			n = (args.length == 0) ? 1000 : Integer.parseInt(args[0]);
		    		} catch(NumberFormatException e){
		    			n = 1000;
		    		}
		    		
		    		for(int i = 0 ; i < n ; i++){
		    			String randomBrand = brands[rand.nextInt(brands.length)];
		    			String randomPrice = Integer.toString(rand.nextInt(50000) + 40000) + "," + Integer.toString(rand.nextInt(100));
		    			String randomProductionYear = Integer.toString(rand.nextInt(20) + 1995);
		    			
		    			
		    			try {
							Car randomCar = CarFactory.getCar(randomBrand, randomPrice, randomProductionYear);
							engine.addCar(randomCar);
		    			} catch (PriceNotCorrectException | ProductionYearNotCorrectException
								| BrandNotCorrectException e) {
							e.printStackTrace();
						}
		    		}
		    		
		        }
		    });
	}
}
