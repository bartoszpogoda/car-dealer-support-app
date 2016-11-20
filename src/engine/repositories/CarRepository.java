package engine.repositories;

import java.util.List;

import engine.domain.Car;

public interface CarRepository {

	public void addCar(Car carToAdd);

	public Car getCarById(int carId);

	public List<Car> getListOfCars();

}
