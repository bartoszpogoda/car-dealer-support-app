package gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.controllers.AddCarController;
import gui.interfaces.AddCarGUI;

public class PanelAddCar extends JPanel implements AddCarGUI {

	private JTextField tfCarBrand;
	private JTextField tfCarPrice;
	private JTextField tfCarProductionYear; 
	
	private JLabel lCarBrand;
	private JLabel lCarPrice;
	private JLabel lCarProductionYear;
	
	private JPanel actionPanel;
	
	private JButton btnConfirmAddCar;
	private JButton btnCancelAddCar;
	
	public PanelAddCar() {
		initGUI();
	}
	
	private void initGUI(){
		
		
		lCarBrand = new JLabel("Marka");
		lCarPrice = new JLabel("Cena");
		lCarProductionYear = new JLabel("Rok produkcji");
		lCarBrand.setAlignmentX(CENTER_ALIGNMENT);
		lCarPrice.setAlignmentX(CENTER_ALIGNMENT);
		lCarProductionYear.setAlignmentX(CENTER_ALIGNMENT);
		
		tfCarBrand = new JTextField();
		tfCarBrand.setMaximumSize(new Dimension(300,20));
		tfCarPrice = new JTextField();
		tfCarPrice.setMaximumSize(new Dimension(300,20));
		tfCarProductionYear = new JTextField();
		tfCarProductionYear.setMaximumSize(new Dimension(300,20));
		
		btnConfirmAddCar = new JButton("Potwierdü");
		btnConfirmAddCar.setActionCommand("confirmAddCar");
		
		btnCancelAddCar = new JButton("Anuluj");
		btnCancelAddCar.setActionCommand("cancelAddCar");
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(lCarBrand);
		this.add(tfCarBrand);
		this.add(lCarPrice);
		this.add(tfCarPrice);
		this.add(lCarProductionYear);
		this.add(tfCarProductionYear);
		this.add(Box.createVerticalStrut(50));
		
		actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.LINE_AXIS));
		//actionPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		
		actionPanel.add(btnConfirmAddCar);
		actionPanel.add(btnCancelAddCar);
		
		this.add(actionPanel);
		
	}
	
	
	@Override
	public String getCarBrand() {
		return tfCarBrand.getText();
	}

	@Override
	public String getCarPrice() {
		return tfCarPrice.getText();
	}

	@Override
	public String getCarProductionYear() {
		return tfCarProductionYear.getText();
	}

	@Override
	public void setAddCarController(AddCarController addCarController) {
		btnCancelAddCar.addActionListener(addCarController);
		btnConfirmAddCar.addActionListener(addCarController);
		
	}

	@Override
	public void clearAllTextFields() {
		tfCarBrand.setText("");
		tfCarPrice.setText("");
		tfCarProductionYear.setText("");
		
	}
	
	

}
