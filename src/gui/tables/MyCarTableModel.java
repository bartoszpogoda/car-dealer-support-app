package gui.tables;

import javax.swing.table.AbstractTableModel;

import engine.AppEngine;
import engine.domain.Car;

public class MyCarTableModel extends AbstractTableModel {

	AppEngine engine;
	
	String[] columnNames = {"ID",
            "Marka",
            "Rok produkcji",
			"Cena [z³]", "Stan"};
	
	
	
	public MyCarTableModel(AppEngine engine) {
		super();
		this.engine = engine;
		
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return engine.getListOfCarsFiltered().size();
	}

	
	
	@Override
	public Object getValueAt(int arg0, int arg1) {
		
		Car car = engine.getListOfCarsFiltered().get(arg0);
		
		if(arg1 == 0){
			return car.getId();
		} else if(arg1 == 1){
			return car.getBrand();
		} else if(arg1 == 2){
			return car.getProductionYear();
		} else if(arg1 == 3){
			return car.getPrice();
		} else if(arg1 == 4){
			return car.getState();
		}
		
		return null;
	}

}
