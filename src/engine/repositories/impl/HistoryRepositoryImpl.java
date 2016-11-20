package engine.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import engine.domain.HistoryElement;
import engine.repositories.HistoryRepository;

public class HistoryRepositoryImpl implements HistoryRepository {
	
	private List<HistoryElement> historyList;
	
	public HistoryRepositoryImpl() {
		historyList = new ArrayList<HistoryElement>();
	}
	
	@Override
	public void addElement(HistoryElement element) {
		historyList.add(element);

	}

	@Override
	public List<HistoryElement> getElements() {
		return historyList;
	}

}
