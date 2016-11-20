package engine.filters;

import engine.State;
import engine.domain.Car;

public class CarFilterStateEqual implements ICarFilter {

	private State requiredState;
	
	public CarFilterStateEqual(State requiredState) {
		this.requiredState = requiredState;
	}

	@Override
	public boolean filter(Car car) {
		return (car.getState() == requiredState);
	}
	
	@Override
	public String toString() {
		return "Stan = " + requiredState;
	}
	
}
