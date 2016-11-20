package engine.filters;

import engine.domain.Car;

public class CarFilterProductionYearOverOrEqual implements ICarFilter {

	private int minProductionYear;
	
	public CarFilterProductionYearOverOrEqual(int minProductionYear) {
		this.minProductionYear = minProductionYear;
	}
	
	@Override
	public boolean filter(Car car) {
		return car.getProductionYear() >= minProductionYear;
	}
	
	@Override
	public String toString() {
		return "Rok produkcji >= " + minProductionYear;
	}

}
