package engine.filters;

import engine.domain.Car;

public class CarFilterProductionYearLessOrEqual implements ICarFilter {

	private int maxProductionYear;
	
	public CarFilterProductionYearLessOrEqual(int maxProductionYear) {
		this.maxProductionYear = maxProductionYear;
	}
	
	@Override
	public boolean filter(Car car) {
		return car.getProductionYear() <= maxProductionYear;
	}

	@Override
	public String toString() {
		return "Rok produkcji <= " + maxProductionYear;
	}
}
