package engine.filters;

import java.math.BigDecimal;

import engine.domain.Car;

public class CarFilterPriceLessOrEqual implements ICarFilter {

	private BigDecimal maxPrice;
	
	public CarFilterPriceLessOrEqual(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	@Override
	public boolean filter(Car car) {
		return car.getPrice().compareTo(maxPrice) != 1;
	}

	@Override
	public String toString() {
		return "Cena <= " + maxPrice;
	}

}
