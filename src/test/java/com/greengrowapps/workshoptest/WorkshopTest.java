package com.greengrowapps.workshoptest;

import com.thirdparty.billing.BillingProgramA;
import com.thirdparty.billing.BillingProgramB;
import com.thirdparty.billing.BillingProgramC;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkshopTest {

    private static final String CSV_DATA =
                    "PlateNr;Type;Model;Km;FirstName;LastName;PhoneNumber;ParkedOn;Weight;Height;\n"+
                    "1234AAA;Bike;Ducati;60000;Propietario1;Apellido1;60000000;\n"+
                    "1234AAB;Bike;Yamaha;80000;Propietario2;Apellido2;60000001;\n"+
                    "1234AAC;Car;Ford;90000;Propietario3;Apellido3;60000002;Calle;\n"+
                    "1234AAD;Car;Volkswagen;100000;Propietario4;Apellido4;60000003;Parking 1;\n"+
                    "1234AAE;Truck;Mercedes;110000;Propietario5;Apellido5;60000004;Taller;350;4500;\n"+
                    "1234AAF;Truck;Iveco;120000;Propietario6;Apellido6;60000005;Parking 1;350;4500;\n"+
                    "1234AAG;Truck;Volvo;140000;Propietario7;Apellido7;60000006;Calle;400;7500;\n"+
                    "1234AAH;Truck;Scania;200000;Propietario8;Apellido8;60000007;Parking 1;400;7500;\n";

    private static final int LOW_AREA_MAX_HEIGHT = 200;
    private static final int MEDIUM_AREA_MAX_HEIGHT = 360;
    private static final int HIGH_AREA_MAX_HEIGHT = 600;

    private static final String LOW_AREA_KEY = "LowArea";
    private static final String MEDIUM_AREA_KEY = "MediumArea";
    private static final String HIGH_AREA_KEY = "HighArea";


    @Test
    public void testConstructor(){
        Workshop.OfVehiclesCSV(CSV_DATA);
    }

    @Test
    public void testWorkAreaCreate(){
        Workshop workshop = Workshop.OfVehiclesCSV(CSV_DATA);

        workshop.addArea(LOW_AREA_KEY,LOW_AREA_MAX_HEIGHT,null);
        workshop.addArea(MEDIUM_AREA_KEY,MEDIUM_AREA_MAX_HEIGHT,null);
        workshop.addArea(HIGH_AREA_KEY,HIGH_AREA_MAX_HEIGHT,null);
    }

    @Test
    public void testGetOwner(){
        Workshop workshop = Workshop.OfVehiclesCSV(CSV_DATA);

        Owner owner = workshop.getOwnerFrom("1234AAE");

        assertEquals("Propietario5",owner.getFirstName());
    }

    @Test
    public void testGetParkedOn(){
        Workshop workshop = Workshop.OfVehiclesCSV(CSV_DATA);

        String parkedOn = workshop.getParkedOn("1234AAC");

        assertEquals("Calle",parkedOn);
    }

    /*
    Can not get fields that doesn't exist for the vehicle
     */
    @Test
    public void testGetParkedOnFromBike(){
        Workshop workshop = Workshop.OfVehiclesCSV(CSV_DATA);

        try {
            String parkedOn = workshop.getParkedOn("1234AAA");
            fail();
        } catch (Exception e) {
            // the vehicle is a bike, expected exception
        }
    }

    /*
    The vehicles come in work area (vehicleToArea), when come the status change to in progress
    The work area has a queue. When the repaired vehicle (vehicleRepaired) is called the target work area will done 1 vehicle, then the first
    vehicle in queue will have status done
     */
    @Test
    public void testWorkAreaFlow(){
        Workshop workshop = Workshop.OfVehiclesCSV(CSV_DATA);

        workshop.addArea(LOW_AREA_KEY,LOW_AREA_MAX_HEIGHT, null);

        assertTrue(workshop.isWaiting("1234AAA"));
        assertFalse(workshop.isInProgress("1234AAA"));
        assertFalse(workshop.isDone("1234AAA"));

        workshop.vehicleToArea("1234AAA",LOW_AREA_KEY);

        assertFalse(workshop.isWaiting("1234AAA"));
        assertTrue(workshop.isInProgress("1234AAA"));
        assertFalse(workshop.isDone("1234AAA"));

        workshop.vehicleRepaired(LOW_AREA_KEY);

        assertFalse(workshop.isWaiting("1234AAA"));
        assertFalse(workshop.isInProgress("1234AAA"));
        assertTrue(workshop.isDone("1234AAA"));
    }
    /*
    Can not send vehicles to workarea if they exceed the height
     */
    @Test
    public void testExceedHeight(){
        Workshop workshop = Workshop.OfVehiclesCSV(CSV_DATA);
        workshop.addArea(LOW_AREA_KEY,LOW_AREA_MAX_HEIGHT,null);

        try {
            workshop.vehicleToArea("1234AAH",LOW_AREA_KEY);
            fail();
        } catch (Exception e) {
            // the vehicle exceeds the area height
        }
    }

    /*
    When a vehicle is in workarea with the doWork method will be work added to the vehicle bill.
     */
    @Test
    public void testBillingProgramA(){

        Workshop workshop = Workshop.OfVehiclesCSV(CSV_DATA);
        workshop.setHourPrice(30);

        BillingProgramA billingA = new BillingProgramA(30);

        //You cann add or modify code from here
        workshop.addArea(HIGH_AREA_KEY,HIGH_AREA_MAX_HEIGHT, billingA);
        //to here

        workshop.vehicleToArea("1234AAA",HIGH_AREA_KEY);
        workshop.doWork(HIGH_AREA_KEY,"find defects",0.25);
        workshop.doWork(HIGH_AREA_KEY,"oil change",0.5);
        workshop.doWork(HIGH_AREA_KEY,"tires change",0.25);

        workshop.vehicleRepaired(HIGH_AREA_KEY);


        assertEquals(30.0, billingA.getInvoicePrice("1234AAA"),0.01);
    }

    @Test
    public void testBillingProgramB(){

        Workshop workshop = Workshop.OfVehiclesCSV(CSV_DATA);
        workshop.setHourPrice(30);

        BillingProgramB billingB = new BillingProgramB();

        //You cann add or modify code from here
        workshop.addArea(HIGH_AREA_KEY,HIGH_AREA_MAX_HEIGHT, billingB);
        //to here

        workshop.vehicleToArea("1234AAA",HIGH_AREA_KEY);
        workshop.doWork(HIGH_AREA_KEY,"find defects",0.25);
        workshop.doWork(HIGH_AREA_KEY,"oil change",0.5);
        workshop.doWork(HIGH_AREA_KEY,"tires change",0.25);

        workshop.vehicleRepaired(HIGH_AREA_KEY);


        assertEquals(30.0, billingB.getInvoicePrice("1234AAA"),0.01);
        assertEquals("find defects\noil change\ntires change", billingB.getWorkDone("1234AAA"));
    }

    @Test
    public void testBillingProgramC(){

        Workshop workshop = Workshop.OfVehiclesCSV(CSV_DATA);
        workshop.setHourPrice(30);

        BillingProgramC billingC = new BillingProgramC();

        //You cann add or modify code from here
        workshop.addArea(HIGH_AREA_KEY,HIGH_AREA_MAX_HEIGHT, billingC);
        //to here

        workshop.vehicleToArea("1234AAA",HIGH_AREA_KEY);
        workshop.doWork(HIGH_AREA_KEY,"find defects",0.25);
        workshop.doWork(HIGH_AREA_KEY,"oil change",0.5);
        workshop.doWork(HIGH_AREA_KEY,"tires change",0.25);

        workshop.vehicleRepaired(HIGH_AREA_KEY);

        assertEquals(30.0, billingC.getOrNewInvoice("1234AAA").getTotalPrice(),0.01);
    }

}
