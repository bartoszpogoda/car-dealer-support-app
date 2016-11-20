package engine.domain;

import java.util.Date;

public class HistoryElement {
	private int carID;
	private Date sellDate;
	
	public HistoryElement(int carID, Date sellDate) {
		this.setCarID(carID);
		this.setSellDate(sellDate);
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

}
