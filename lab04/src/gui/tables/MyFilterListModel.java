package gui.tables;

import javax.swing.AbstractListModel;

import engine.AppEngine;

public class MyFilterListModel extends AbstractListModel<String> {

	AppEngine engine;
	
	public MyFilterListModel(AppEngine engine) {
		this.engine = engine;
	}
	
	@Override
	public int getSize() {
		return engine.getFilterList().size();
	}
	
	public void update(){
		this.fireContentsChanged(this, 0, getSize());
	}

	@Override
	public String getElementAt(int index) {
		return engine.getFilterList().get(index).toString();
	}

}
