package com.greengrowapps.workshoptest;

public class Vehicle {

	private String plateNumber;
	private String model;
	private int kms;
	private Owner owner;
	private String status;
	
	public Vehicle(String plateNumber, String model, int kms, Owner owner, String status) {
		super();
		this.plateNumber = plateNumber;
		this.model = model;
		this.kms = kms;
		this.owner = owner;
		this.status = status;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getKms() {
		return kms;
	}

	public void setKms(int kms) {
		this.kms = kms;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
