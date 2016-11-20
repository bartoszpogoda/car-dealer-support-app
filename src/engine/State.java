package engine;

public enum State {
	Avaliable, Reserved, Sold;

	public static State getState(String string) {
		if (string.equalsIgnoreCase("Dostêpny"))
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
			return "Dostêpny";
		else if (this == State.Reserved)
			return "Zarezerwowany";
		else
			return "Sprzedany";
	}

}
