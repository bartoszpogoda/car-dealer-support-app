package gui.interfaces;

import gui.controllers.FilterController;

public interface FilterGUI {
	public String getNewFilterType();

	public String getNewFilterParameter();

	public int getSelectedFilterId();

	public void updateFilterList();

	public void setFilterController(FilterController filterController);
}
