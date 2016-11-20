package engine.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import engine.domain.ReservationElement;
import engine.repositories.ReservationRepository;

public class ReservationRepositoryImpl implements ReservationRepository {

	private List<ReservationElement> reservationList;

	public ReservationRepositoryImpl() {
		reservationList = new ArrayList<ReservationElement>();
	}

	@Override
	public void addElement(ReservationElement element) {
		this.reservationList.add(element);

	}

	@Override
	public void removeElementByCarId(int carId) {
		ReservationElement elementToRemove = null;

		for (ReservationElement e : reservationList) {
			if (e.getCarID() == carId) {
				elementToRemove = e;
				break;
			}
		}

		if (elementToRemove != null)
			reservationList.remove(elementToRemove);

	}

	@Override
	public List<ReservationElement> getElements() {
		return reservationList;
	}

}
