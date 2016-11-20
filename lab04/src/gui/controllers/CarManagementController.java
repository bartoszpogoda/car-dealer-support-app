package gui.controllers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import engine.AppEngine;
import engine.State;
import gui.interfaces.CarManagementGUI;
import gui.interfaces.MainWindowGUI;

public class CarManagementController implements ActionListener {
	
	private AppEngine engine;
	private CarManagementGUI gui;
	private MainWindowGUI mainWindow;
	
	private JDatePickerImpl datePicker;
	private JDialog dialog;
	
	public CarManagementController(AppEngine engine) {
		this.engine = engine;
	}
	
	public void setGui(CarManagementGUI gui) {
		this.gui = gui;
	}
	
	public void setMainWindowGUI(MainWindowGUI mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("reserveCar")){
			int carId = gui.getSelectedCarID();
			
			if(engine.getCarById(carId).getState() != State.Avaliable){
				mainWindow.reportError("Samochód nie jest dostêpny");
				return;
			}
				
			
			UtilDateModel model = new UtilDateModel();
			Properties proper = new Properties();
			proper.setProperty("text.today", "Dzisiaj");
			JDatePanelImpl datePanel = new JDatePanelImpl(model,proper);
			datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
			
			dialog = mainWindow.createModalDialog();
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			panel.add(new JLabel("WprowadŸ date rezerwacji"));
			panel.add(datePicker);
			datePicker.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(Box.createRigidArea(new Dimension(10,10)));
			JButton btnConfirm = new JButton("PotwierdŸ rezerwacjê");
			btnConfirm.setActionCommand("btnConfirmReservation");
			btnConfirm.addActionListener(this);
			panel.add(btnConfirm);
			
			dialog.setContentPane(panel);
			dialog.pack();
			
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
		else if(e.getActionCommand().equalsIgnoreCase("sellCar")){
			int carId = gui.getSelectedCarID();
			
			if(engine.getCarById(carId).getState() == State.Sold){
				mainWindow.reportError("Samochód zosta³ ju¿ sprzedany");
				return;
			}
				
			
			UtilDateModel model = new UtilDateModel();
			Properties proper = new Properties();
			proper.setProperty("text.today", "Dzisiaj");
			JDatePanelImpl datePanel = new JDatePanelImpl(model,proper);
			datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
			
			dialog = mainWindow.createModalDialog();
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			if(engine.getCarById(carId).getState() == State.Reserved){
				panel.add(new JLabel("Uwaga: Sprzedajesz zarezerwowany samochód"));
			}
			
			panel.add(new JLabel("WprowadŸ datê sprzeda¿y"));
			panel.add(datePicker);
			datePicker.setAlignmentX(Component.LEFT_ALIGNMENT);
			panel.add(Box.createRigidArea(new Dimension(10,10)));
			JButton btnConfirm = new JButton("PotwierdŸ sprzeda¿");
			btnConfirm.setActionCommand("btnConfirmSell");
			btnConfirm.addActionListener(this);
			panel.add(btnConfirm);
			
			dialog.setContentPane(panel);
			dialog.pack();
			
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
		else if(e.getActionCommand().equalsIgnoreCase("btnConfirmReservation")){
			Date date = (Date)datePicker.getModel().getValue();
			
			if(date==null){
				mainWindow.reportError("Wybierz datê rezerwacji");
				return;
			}
			
			int carId = gui.getSelectedCarID();
			engine.reserveCar(carId, date);
			
			dialog.dispose();
			mainWindow.update();
			
		}
		else if(e.getActionCommand().equalsIgnoreCase("btnConfirmSell")){
			Date date = (Date)datePicker.getModel().getValue();
			
			if(date==null){
				mainWindow.reportError("Wybierz datê sprzeda¿y");
				return;
			}
			
			int carId = gui.getSelectedCarID();
			engine.sellCar(carId, date);
			
			dialog.dispose();
			mainWindow.update();
			
		}
		else if(e.getActionCommand().equalsIgnoreCase("btnAddCar")){
			mainWindow.switchToAddCarView();
		}
	}

}
