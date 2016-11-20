package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import gui.controllers.FilterController;
import gui.interfaces.FilterGUI;
import gui.tables.MyFilterListModel;

public class PanelFilters extends JPanel implements FilterGUI {
	
	private final String[] filterNames = { "Cena co najmniej", 
							"Cena maksymalnie", 
							"Rok produkcji co najmniej",
							"Rok produkcji maksymalnie",
							"Marka",
							"Stan"};
	private final String[] filterEngineTranslations = { "CarFilterPriceOverOrEqual", 
										"CarFilterPriceLessOrEqual",
										"CarFilterProductionYearOverOrEqual",
										"CarFilterProductionYearLessOrEqual",
										"CarFilterBrandEqual",
										"CarFilterStateEqual"};
	
	private JComboBox<String> filterTypesList;
	private JTextField parameterField;
	private JButton btnAddFilter;
	
	private JPanel panelAddFilter;
	private JButton btnDeleteSelectedFilter;
	
	private JList<String> filterList;
	private JScrollPane scrollPaneFilterList;
	
	
	private MyFilterListModel myFilterListModel;
	

	public PanelFilters(){
				
		panelAddFilter = new JPanel();
		initPanelAddFilter();
		
		this.add(Box.createRigidArea(new Dimension(10,10)));
		

		initFilterList();
		
		btnDeleteSelectedFilter = new JButton("Usuñ filtr");
		btnDeleteSelectedFilter.setActionCommand("deleteFilter");
		btnDeleteSelectedFilter.setAlignmentX(CENTER_ALIGNMENT);
		this.add(btnDeleteSelectedFilter);
		
		this.setPreferredSize(new Dimension(100,400));
		
	}
	
	private void initFilterList() {
		
		filterList = new JList<String>();
		filterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//filterList.addListSelectionListener(this);
		//filterList.setVisibleRowCount(5);
		
		scrollPaneFilterList = new JScrollPane(filterList);
		scrollPaneFilterList.setPreferredSize(new Dimension(200,50));
		this.add(scrollPaneFilterList);
	}

	private void initPanelAddFilter(){
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		panelAddFilter.setLayout(new BoxLayout(panelAddFilter, BoxLayout.PAGE_AXIS));
		panelAddFilter.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panelAddFilter.setMaximumSize(new Dimension(400,50));
		
		filterTypesList = new JComboBox<String>(filterNames);
		filterTypesList.setSelectedIndex(0);
		filterTypesList.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				parameterField.setText("");
				
			}
		});
		
		parameterField = new JTextField();
		btnAddFilter = new JButton("Dodaj");
		btnAddFilter.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnAddFilter.setActionCommand("addFilter");
		
		panelAddFilter.add(filterTypesList);
		panelAddFilter.add(parameterField);
		panelAddFilter.add(btnAddFilter);
		
		this.add(panelAddFilter);
	}
	
	public void setListModel(MyFilterListModel model){
		this.filterList.setModel(model);
		myFilterListModel = model;
	}
	
	@Override
	public void setFilterController(FilterController filterController) {
		btnAddFilter.addActionListener(filterController);
		btnDeleteSelectedFilter.addActionListener(filterController);
	}
	
	@Override
	public String getNewFilterType() {
		return filterEngineTranslations[filterTypesList.getSelectedIndex()];
	}

	@Override
	public String getNewFilterParameter() {
		return parameterField.getText();
	}

	@Override
	public int getSelectedFilterId() {
		return filterList.getSelectedIndex();
	}

	@Override
	public void updateFilterList() {
		myFilterListModel.update();
		
	}

}
