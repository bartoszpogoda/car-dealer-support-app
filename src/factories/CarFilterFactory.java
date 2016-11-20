package factories;

import java.math.BigDecimal;

import engine.State;
import engine.filters.CarFilterBrandEqual;
import engine.filters.CarFilterPriceLessOrEqual;
import engine.filters.CarFilterPriceOverOrEqual;
import engine.filters.CarFilterProductionYearLessOrEqual;
import engine.filters.CarFilterProductionYearOverOrEqual;
import engine.filters.CarFilterStateEqual;
import engine.filters.ICarFilter;
import factories.exceptions.BrandNotCorrectException;
import factories.exceptions.PriceNotCorrectException;
import factories.exceptions.ProductionYearNotCorrectException;
import factories.exceptions.StateNotCorrectException;

public class CarFilterFactory {

	/**
	 * cares about filter data validation
	 */
	public static ICarFilter getFilter(String filterName, String argument) throws PriceNotCorrectException,
			StateNotCorrectException, BrandNotCorrectException, ProductionYearNotCorrectException {
		ICarFilter filter = null;

		if (filterName.equalsIgnoreCase("CarFilterPriceOverOrEqual")) {
			BigDecimal minPrice;

			try {
				minPrice = new BigDecimal(argument.replaceAll(",", "."));
			} catch (NumberFormatException e) {
				throw new PriceNotCorrectException();
			}

			if (minPrice.floatValue() < 0)
				throw new PriceNotCorrectException();

			filter = new CarFilterPriceOverOrEqual(minPrice);
		} else if (filterName.equalsIgnoreCase("CarFilterPriceLessOrEqual")) {
			BigDecimal maxPrice;

			try {
				maxPrice = new BigDecimal(argument.replaceAll(",", "."));
			} catch (NumberFormatException e) {
				throw new PriceNotCorrectException();
			}

			if (maxPrice.floatValue() < 0)
				throw new PriceNotCorrectException();

			filter = new CarFilterPriceLessOrEqual(maxPrice);
		} else if (filterName.equalsIgnoreCase("CarFilterProductionYearOverOrEqual")) {
			int minProductionYear;

			try {
				minProductionYear = Integer.parseInt(argument);
			} catch (NumberFormatException e) {
				throw new ProductionYearNotCorrectException();
			}

			filter = new CarFilterProductionYearOverOrEqual(minProductionYear);
		} else if (filterName.equalsIgnoreCase("CarFilterProductionYearLessOrEqual")) {
			int maxProductionYear;

			try {
				maxProductionYear = Integer.parseInt(argument);
			} catch (NumberFormatException e) {
				throw new ProductionYearNotCorrectException();
			}

			filter = new CarFilterProductionYearLessOrEqual(maxProductionYear);
		} else if (filterName.equalsIgnoreCase("CarFilterStateEqual")) {
			State state = State.getState(argument);
			if (state == null)
				throw new StateNotCorrectException();
			filter = new CarFilterStateEqual(state);

		} else if (filterName.equalsIgnoreCase("CarFilterBrandEqual")) {
			if (argument.equals(""))
				throw new BrandNotCorrectException();

			filter = new CarFilterBrandEqual(argument);
		}

		return filter;
	}
}
