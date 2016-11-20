package engine.repositories;

import java.util.List;

import engine.domain.ReservationElement;

public interface ReservationRepository {

	public void addElement(ReservationElement element);

	public void removeElementByCarId(int carId);

	public List<ReservationElement> getElements();
}
