package engine;

public enum State {
	Avaliable, Reserved, Sold;

	public static State getState(String string) {
		if (string.equalsIgnoreCase("Dost�pny"))
			return State.Avaliable;
		else if (string.equalsIgnoreCase("Zarezerwowany"))
			return State.Reserved;
		else if (string.equalsIgnoreCase("Sprzedany"))
			return State.Sold;

		return null;
	}

	@Override
	public String toString() {
		if (this == State.Avaliable)
			return "Dost�pny";
		else if (this == State.Reserved)
			return "Zarezerwowany";
		else
			return "Sprzedany";
	}

}
