package com.thirdparty.billing;

import java.util.HashMap;
import java.util.List;

public class BillingProgramC {

    private final HashMap<String,InvoiceC> invoices = new HashMap<>();

    public BillingProgramC() {
    }

    public InvoiceC getOrNewInvoice(String plateNr){
        InvoiceC invoice = invoices.getOrDefault(plateNr,new InvoiceC(plateNr));
        invoices.put(plateNr,invoice);
        return invoice;
    }
}
