package engine.domain;

import java.util.Date;

public class ReservationElement {
	private int carID;
	private Date reservationDate;
	
	public ReservationElement(int carID, Date reservationDate) {
		this.carID = carID;
		this.reservationDate = reservationDate;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	
}
