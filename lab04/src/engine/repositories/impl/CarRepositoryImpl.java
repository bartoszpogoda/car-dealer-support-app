package engine.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import engine.domain.Car;
import engine.repositories.CarRepository;

public class CarRepositoryImpl implements CarRepository {

	private List<Car> carList;

	public CarRepositoryImpl() {
		carList = new ArrayList<Car>();
	}

	@Override
	public void addCar(Car carToAdd) {
		carList.add(carToAdd);
	}

	@Override
	public Car getCarById(int carId) {
		for (Car c : carList) {
			if (c.getId() == carId)
				return c;
		}
		return null;
	}

	@Override
	public List<Car> getListOfCars() {
		return carList;
	}

}
