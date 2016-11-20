package factories;

import java.math.BigDecimal;

import engine.domain.Car;
import factories.exceptions.BrandNotCorrectException;
import factories.exceptions.PriceNotCorrectException;
import factories.exceptions.ProductionYearNotCorrectException;

public class CarFactory {
	/**
	 * cares about data validation - throws exceptions
	 */
	public static Car getCar(String strBrand, String strPrice, String strProductionYear)
			throws PriceNotCorrectException, ProductionYearNotCorrectException, BrandNotCorrectException {
		Car car = null;

		BigDecimal price;
		int productionYear;

		if (strBrand.equalsIgnoreCase(""))
			throw new BrandNotCorrectException();

		try {
			price = new BigDecimal(strPrice.replaceAll(",", "."));
		} catch (NumberFormatException e) {
			throw new PriceNotCorrectException();
		}

		try {
			productionYear = Integer.parseInt(strProductionYear);
		} catch (NumberFormatException e) {
			throw new ProductionYearNotCorrectException();
		}

		car = new Car(strBrand, price, productionYear);

		return car;
	}
}
