package com.thirdparty.billing;

import java.util.HashMap;
import java.util.Map;

public class BillingProgramA {

    private final Map<String,Double> invoices = new HashMap<String,Double>();
    private final double hourPrice;

    public BillingProgramA(double hourPrice) {
        this.hourPrice = hourPrice;
    }

    public void addToInvoice(String plateNumber, double hours){
        double currentPrice = getInvoicePrice(plateNumber);
        invoices.put(plateNumber,currentPrice+hours*hourPrice);
    }

    public double getInvoicePrice(String plateNumber) {
        return invoices.getOrDefault(plateNumber, 0.0);
    }
}
