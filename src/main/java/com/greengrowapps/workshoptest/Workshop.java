package com.greengrowapps.workshoptest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.thirdparty.billing.BillingProgramA;
import com.thirdparty.billing.BillingProgramB;
import com.thirdparty.billing.BillingProgramC;

//Resolved by: Joan, Juan belda
public class Workshop {

	private double hourPrice;
	
	//Map of the areas
	private HashMap<String, Area> areas = new HashMap<>();
	
	//Map of the vehicles
	private HashMap<String, Vehicle> vehiculos = new HashMap<>();
	
    private Workshop(String csvData){
    	//Method that transforms the csv data in a objects
    	convertCsvToData(csvData);
    }
    public static Workshop OfVehiclesCSV(String csvData){
        return new Workshop(csvData);
    }

    public void setHourPrice(double hourPrice) {
    	this.hourPrice = hourPrice;
    }

    /*
    * Fell free to modify the third parameter class "Object billingProgram" for a class
    * of your choose, this parameter can be null
    * */
    public void addArea(String key, int maxHeightCm, BillingProgramC billingProgramA) {
    	Area area = new Area(key, maxHeightCm, billingProgramA);
    	
    	this.areas.put(key, area);
    }

    public boolean isWaiting(String plateNumber) {
    	Vehicle vehicle = this.vehiculos.get(plateNumber);
    	
    	if(vehicle.getStatus().equals(Status.waiting)){
    		return true;
    	}
    	else{
    		return false;
    	}
        
    }

    public boolean isInProgress(String plateNumber) {
    	Vehicle vehicle = this.vehiculos.get(plateNumber);
    	
    	if(vehicle.getStatus().equals(Status.inProgress)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    public boolean isDone(String plateNumber) {
    	Vehicle vehicle = this.vehiculos.get(plateNumber);
    	
    	if(vehicle.getStatus().equals(Status.done)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    public Owner getOwnerFrom(String plateNumber) {
    	Vehicle vehicle = this.vehiculos.get(plateNumber);
    	
        return vehicle.getOwner();
    }

    public String getParkedOn(String plateNumber) {
    	Vehicle vehicle = this.vehiculos.get(plateNumber);
    	
        if(vehicle instanceof Bike){
        	throw new RuntimeException();
        	
        }
        else{
        	if(vehicle instanceof Car){
        		return ((Car)vehicle).getParkedOn();
        	}
        	else{
        		if(vehicle instanceof Truck){
        			return ((Truck)vehicle).getParkedOn();
        		}
        	}
        }
		return null;
    }

    public void vehicleToArea(String plateNumber, String areaKey) {
    	Vehicle vehicle = this.vehiculos.get(plateNumber);
    	Area area = this.areas.get(areaKey);
    	vehicle.setStatus(Status.inProgress);
    	if(vehicle instanceof Truck){
    		if(((Truck)vehicle).getWeight() >= area.getMaxHeight()){
    			throw new RuntimeException();
    		}
    	}
    	else{
    		area.getVehiculos().add(vehicle);
    	}
    	
    }

    public void vehicleRepaired(String areaKey) {
    	Area area = this.areas.get(areaKey);
    	area.getVehiculos().remove().setStatus(Status.done);
    	
    }

    public void doWork(String areaKey, String task, double hours) {
    	Area area = this.areas.get(areaKey);
    	
    	if(area.getBillingProgramA() != null){
    		area.getBillingProgramA().addToInvoice(area.getVehiculos().getFirst().getPlateNumber(), hours);
    	}
    	else{
    		if(area.getBillingProgramB() != null){
    			area.getBillingProgramB().addToInvoice(area.getVehiculos().getFirst().getPlateNumber(), (hours * this.hourPrice), task);
    		}
    		else{
    			if(area.getBillingProgramC() != null){
    				String plateNumber = area.getVehiculos().getFirst().getPlateNumber();
    				
    				area.getBillingProgramC().getOrNewInvoice(plateNumber).addTask((hours * this.hourPrice), task);
    			}
    		}
    		
    	}
    }
    
    private void convertCsvToData(String csvData){
    	List<String> aux = new ArrayList<String>(Arrays.asList(csvData.split(";")));
    	//Remove the column values
    	aux.remove(0);
    	aux.remove(0);
    	aux.remove(0);
    	aux.remove(0);
    	aux.remove(0);
    	aux.remove(0);
    	aux.remove(0);
    	aux.remove(0);
    	aux.remove(0);
    	aux.remove(0);
    	//Remove the last \n
    	aux.remove(aux.size()-1);
    	
    	while(!aux.isEmpty()){
    		String plateNumber;
    		String model;
    		int kms;
    		String firstName;
    		String lastName;
    		String phone;
    		String status;
    		String parkedOn;
    		int weight;
    		int height;
    		String type;
    		Owner owner;
    		//Initialize common parameters:
    		plateNumber = aux.remove(0).substring(1);
    		type = aux.remove(0);
    		model = aux.remove(0);
    		kms = Integer.parseInt(aux.remove(0));
    		firstName = aux.remove(0);
    		lastName = aux.remove(0);
    		phone = aux.remove(0);
    		//Initializate particulars variables
    		switch(type){
    		case "Bike":
    			owner = new Owner(firstName, lastName, phone);
    			Bike bike = new Bike(plateNumber, model, kms, owner, Status.waiting);
    			this.vehiculos.put(bike.getPlateNumber(), bike);
    			break;
    			
    		case "Car":
    			parkedOn = aux.remove(0);
    			owner = new Owner(firstName, lastName, phone);
    			Car car = new Car(plateNumber, model, kms, owner, Status.waiting, parkedOn);
    			this.vehiculos.put(car.getPlateNumber(), car);
    			break;
			
    		case "Truck":
    			parkedOn = aux.remove(0);
    			weight = Integer.parseInt(aux.remove(0));
    			height = Integer.parseInt(aux.remove(0));
    			owner = new Owner(firstName, lastName, phone);
    			Truck truck = new Truck(plateNumber, model, kms, owner, Status.waiting, parkedOn, weight, height);
    			this.vehiculos.put(truck.getPlateNumber(), truck);
    			break;
    		}
    		
    		
    		
    	}
    	
    }
}
