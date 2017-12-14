package com.greengrowapps.workshoptest;

import java.util.LinkedList;

import com.thirdparty.billing.BillingProgramA;
import com.thirdparty.billing.BillingProgramB;
import com.thirdparty.billing.BillingProgramC;

public class Area {

	private LinkedList<Vehicle> vehiculos = new LinkedList<>();

	private String key;
	
	private int maxHeight;
	
	private BillingProgramA billingProgramA;
	
	private BillingProgramB billingProgramB;
	
	private BillingProgramC billingProgramC;

	public Area(String key, int maxHeight, BillingProgramA billingProgramA) {
		this.key = key;
		this.maxHeight = maxHeight;
		this.billingProgramA = billingProgramA;
	}

	public Area(String key, int maxHeight, BillingProgramB billingProgramB) {
		this.key = key;
		this.maxHeight = maxHeight;
		this.billingProgramB = billingProgramB;
	}

	public Area(String key, int maxHeight, BillingProgramC billingProgramC) {
		this.key = key;
		this.maxHeight = maxHeight;
		this.billingProgramC = billingProgramC;
	}

	public LinkedList<Vehicle> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(LinkedList<Vehicle> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public BillingProgramA getBillingProgramA() {
		return billingProgramA;
	}

	public void setBillingProgramA(BillingProgramA billingProgramA) {
		this.billingProgramA = billingProgramA;
	}

	public BillingProgramB getBillingProgramB() {
		return billingProgramB;
	}

	public void setBillingProgramB(BillingProgramB billingProgramB) {
		this.billingProgramB = billingProgramB;
	}

	public BillingProgramC getBillingProgramC() {
		return billingProgramC;
	}

	public void setBillingProgramC(BillingProgramC billingProgramC) {
		this.billingProgramC = billingProgramC;
	}
	
	
	
	
}
