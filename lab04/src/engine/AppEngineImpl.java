package engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import engine.domain.Car;
import engine.domain.HistoryElement;
import engine.domain.ReservationElement;
import engine.filters.ICarFilter;
import engine.repositories.CarRepository;
import engine.repositories.HistoryRepository;
import engine.repositories.ReservationRepository;

public class AppEngineImpl implements AppEngine {

	private List<ICarFilter> filters;

	private CarRepository carRepo;
	private ReservationRepository reservationRepo;
	private HistoryRepository historyRepo;

	public AppEngineImpl(CarRepository carRepo, ReservationRepository reservationRepo, HistoryRepository historyRepo) {

		filters = new ArrayList<ICarFilter>();

		this.carRepo = carRepo;
		this.reservationRepo = reservationRepo;
		this.historyRepo = historyRepo;
	}

	@Override
	public void addCar(Car car) {
		carRepo.addCar(car);
	}

	@Override
	public void reserveCar(int ID, Date reservationDate) {
		Car car = carRepo.getCarById(ID);

		reservationRepo.addElement(new ReservationElement(ID, reservationDate));
		car.setState(State.Reserved);
	}

	@Override
	public void sellCar(int ID, Date sellDate) {
		Car car = carRepo.getCarById(ID);

		historyRepo.addElement(new HistoryElement(ID, sellDate));
		reservationRepo.removeElementByCarId(ID);

		car.setState(State.Sold);
	}

	/**
	 * overrides filter if same type
	 */
	@Override
	public void addFilter(ICarFilter filter) {

		ICarFilter filterToReplace = null;

		for (ICarFilter f : filters) {
			if (f.getClass().equals(filter.getClass())) {
				filterToReplace = f;
			}
		}

		if (filterToReplace != null)
			filters.remove(filterToReplace);

		filters.add(filter);
	}

	@Override
	public void removeFilter(int filterId) {
		filters.remove(filterId);
	}

	@Override
	public List<ICarFilter> getFilterList() {
		return filters;
	}

	@Override
	public List<Car> getListOfCarsFiltered() {

		List<Car> filteredList = new ArrayList<Car>();

		for (Car car : carRepo.getListOfCars()) {
			boolean passesFilters = true;

			for (ICarFilter filter : filters) {
				if (!filter.filter(car)) {
					passesFilters = false;
					break;
				}
			}

			if (passesFilters)
				filteredList.add(car);
		}

		return filteredList;
	}

	@Override
	public Car getCarById(int carId) {
		return carRepo.getCarById(carId);
	}

	@Override
	public List<HistoryElement> getListOfHistoryElements() {
		return historyRepo.getElements();
	}

	@Override
	public List<ReservationElement> getListOfReservationElements() {
		return reservationRepo.getElements();
	}

}
