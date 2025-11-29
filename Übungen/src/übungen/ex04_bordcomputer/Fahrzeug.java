package übungen.ex04_bordcomputer;

import java.math.BigDecimal;
import java.math.RoundingMode;

import math.MathUtils;

public class Fahrzeug {
	private int mileage;
	private double fuelLevel;
	private double fuelCapacity;
	private double consumptionPer100km;
	
	public Fahrzeug(int mileage, double fuelLevel, double fuelCapacity, double consumptionPer100km) {
		this.mileage = mileage;
		this.fuelLevel = fuelLevel;
		this.fuelCapacity = fuelCapacity;
		this.consumptionPer100km = consumptionPer100km;
	}
	
	public int getMileage() {
	    return mileage;
	}

	public double getFuelLevel() {
	    return fuelLevel;
	}

	public double getFuelCapacity() {
	    return fuelCapacity;
	}

	public double getConsumptionPer100km() {
	    return consumptionPer100km;
	}
	
	public double[] getData() {
		return new double[] {(double) this.mileage, this.fuelLevel, this.fuelCapacity, this.consumptionPer100km};
	}
	
	public int drive (int kmDriven) {
		BigDecimal bigDec = new BigDecimal(kmDriven);
		bigDec = bigDec.divide(new BigDecimal(100));
		bigDec = bigDec.multiply(new BigDecimal(this.consumptionPer100km));
		double consumption = bigDec.doubleValue();
		
		if (this.fuelLevel < consumption) {
			BigDecimal bigDec2 = new BigDecimal(this.fuelLevel);
			bigDec2 = bigDec2.divide(new BigDecimal(this.consumptionPer100km), RoundingMode.HALF_UP);
			bigDec2 = bigDec2.multiply(new BigDecimal(100));
			kmDriven = bigDec2.intValue();
			this.fuelLevel = 0;
			this.mileage += kmDriven;
			return kmDriven;
		}
		
		this.fuelLevel -= consumption;
		this.mileage += kmDriven;
		return kmDriven;
	}
	
	// returns new fuel level
	public double refuel (double refuelAmount) {
		double newFuelLevel = this.fuelLevel + refuelAmount;
		newFuelLevel = MathUtils.clamp(newFuelLevel, 0, this.fuelCapacity);
		
		this.fuelLevel = newFuelLevel;
		return this.fuelLevel;
	}
	
	// returns the amount of fuel exceeding the fuel capacity.
	public double _refuel (double refuelAmount) {
		double newFuelLevel = this.fuelLevel + refuelAmount;
		double excessFuel = 0;
		
		if (newFuelLevel > this.fuelCapacity) {
			newFuelLevel = this.fuelCapacity;
			excessFuel = newFuelLevel - this.fuelCapacity;
		}
		
		this.fuelLevel = newFuelLevel;
		return excessFuel;
	}
	
	
}
