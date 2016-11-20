package engine.filters;

import engine.domain.Car;

public class CarFilterBrandEqual implements ICarFilter {

	String requestedBrandName;
	
	public CarFilterBrandEqual(String requestedBrandName) {
		this.requestedBrandName = requestedBrandName;
	}

	@Override
	public boolean filter(Car car) {
		return car.getBrand().equalsIgnoreCase(requestedBrandName);
	}

	@Override
	public String toString() {
		return "Marka = " + Character.toUpperCase(requestedBrandName.charAt(0)) + requestedBrandName.substring(1).toLowerCase();
	}
}
