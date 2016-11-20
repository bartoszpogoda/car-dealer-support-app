package engine.domain;

import java.math.BigDecimal;

import engine.State;

public class Car {
	private static int ID_COUNTER = 0; 
	
	private int id;
	private String brand;
	private BigDecimal price;
	private int productionYear;
	
	private State state;
	
	public Car(String brand, BigDecimal price, int productionYear) {
		
		this.id = ID_COUNTER++;
		this.brand = brand;
		this.price = price;
		this.productionYear = productionYear;
		this.state = State.Avaliable;
	}
	
	public Car(String brand, BigDecimal price, int productionYear, State state) {
		this(brand,price,productionYear);
		
		this.state = state;
	}


	public int getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
