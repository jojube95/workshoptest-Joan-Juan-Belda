package com.thirdparty.billing;

import java.util.HashMap;
import java.util.Map;

public class BillingProgramB {
    private final Map<String,Double> invoices = new HashMap<String,Double>();
    private final Map<String,String> workDone = new HashMap<String,String>();

    public BillingProgramB() {
    }

    public void addToInvoice(String plateNumber, double price, String taskDone){

        double currentPrice = getInvoicePrice(plateNumber);
        invoices.put(plateNumber,price+currentPrice);

        String currentWorkDone = getWorkDone(plateNumber);
        workDone.put(plateNumber,currentWorkDone.length()>0?currentWorkDone+"\n"+taskDone:taskDone);
    }

    public String getWorkDone(String plateNumber) {
        return workDone.getOrDefault(plateNumber,"");
    }

    public double getInvoicePrice(String plateNumber) {
        return invoices.getOrDefault(plateNumber, 0.0);
    }
}
