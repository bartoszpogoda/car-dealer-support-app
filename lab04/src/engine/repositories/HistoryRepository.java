package engine.repositories;

import java.util.List;

import engine.domain.HistoryElement;

public interface HistoryRepository {

	public void addElement(HistoryElement element);

	public List<HistoryElement> getElements();
}
