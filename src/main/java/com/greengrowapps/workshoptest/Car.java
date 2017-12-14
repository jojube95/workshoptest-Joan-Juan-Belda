package com.greengrowapps.workshoptest;

public class Car extends Vehicle {
	private String parkedOn;
	
	public Car(String plateNumber, String model, int kms, Owner owner, String status, String parkedOn) {
		super(plateNumber, model, kms, owner, status);
		this.setParkedOn(parkedOn);
	}

	public String getParkedOn() {
		return parkedOn;
	}

	public void setParkedOn(String parkedOn) {
		this.parkedOn = parkedOn;
	}
	

}
