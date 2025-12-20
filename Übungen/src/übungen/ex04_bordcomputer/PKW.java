package übungen.ex04_bordcomputer;

public class PKW extends Fahrzeug {
	
	public PKW(int mileage, double fuelLevel, double fuelCapacity, double consumptionPer100km) {
		super(mileage, fuelLevel, fuelCapacity, consumptionPer100km);
	}

	public PKW() {
		super(0, 60, 60, 6.4);
	}
}
