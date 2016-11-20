package gui.tables;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import engine.AppEngine;

public class MyReservationTableModel extends AbstractTableModel {

	AppEngine engine;
	
	String[] columnNames = {"ID",
            "Marka",
            "Rok produkcji",
			"Cena [z³]", "Data rezerwacji"};
	
	
	public MyReservationTableModel(AppEngine engine) {
		super();
		this.engine = engine;
	}
	
	@Override
	public int getRowCount() {
		return engine.getListOfReservationElements().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 4){
			// reserv date
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.format(engine.getListOfReservationElements().get(rowIndex).getReservationDate());
		}
		else{
			// car info
			int carId = engine.getListOfReservationElements().get(rowIndex).getCarID();
		
			if(columnIndex == 0){
				return engine.getCarById(carId).getId();
			} else if(columnIndex == 1){
				return engine.getCarById(carId).getBrand();
			} else if(columnIndex == 2){
				return engine.getCarById(carId).getProductionYear();
			} else if(columnIndex == 3){
				return engine.getCarById(carId).getPrice();
			}
			
			return null;
		}
		
	}
}
