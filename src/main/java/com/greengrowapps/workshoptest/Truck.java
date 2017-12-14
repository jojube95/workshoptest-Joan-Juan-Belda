package com.greengrowapps.workshoptest;

public class Truck extends Vehicle {
	private String parkedOn;
	private int weight;
	private int height;
	
	public Truck(String plateNumber, String model, int kms, Owner owner, String status, String parkedOn, int weight, int height) {
		super(plateNumber, model, kms, owner, status);
		this.parkedOn = parkedOn;
		this.weight = weight;
		this.height = height;
	}

	public String getParkedOn() {
		return parkedOn;
	}

	public void setParkedOn(String parkedOn) {
		this.parkedOn = parkedOn;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	

}
