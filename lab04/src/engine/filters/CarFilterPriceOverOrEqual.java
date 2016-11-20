package engine.filters;

import java.math.BigDecimal;

import engine.domain.Car;

public class CarFilterPriceOverOrEqual implements ICarFilter {
	
	private BigDecimal minPrice;
	
	public CarFilterPriceOverOrEqual(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	@Override
	public boolean filter(Car car) {
		return (car.getPrice().compareTo(minPrice) >= 0);
	}
	
	@Override
	public String toString() {
		return "Cena >= " + minPrice.toString();
	}
}
