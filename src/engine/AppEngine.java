package engine;

import java.util.Date;
import java.util.List;

import engine.domain.Car;
import engine.domain.HistoryElement;
import engine.domain.ReservationElement;
import engine.filters.ICarFilter;

public interface AppEngine {

	public void addCar(Car car);

	public void reserveCar(int ID, Date reservationDate);

	public void sellCar(int ID, Date sellDate);

	public void addFilter(ICarFilter filter);

	public void removeFilter(int filterId);

	public List<ICarFilter> getFilterList();

	public List<Car> getListOfCarsFiltered();

	public Car getCarById(int carId);

	public List<HistoryElement> getListOfHistoryElements();

	public List<ReservationElement> getListOfReservationElements();
}
