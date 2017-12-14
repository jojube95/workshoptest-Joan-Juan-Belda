package com.thirdparty.billing;

import java.util.ArrayList;
import java.util.List;

public class InvoiceC {

    private final List<TaskC> tasks = new ArrayList<>();
    private final String plateNr;

    public InvoiceC(String plateNr) {
        this.plateNr = plateNr;
    }

    public void addTask(double price, String workDone){
        tasks.add(new TaskC(workDone,price));
    }

    public double getTotalPrice(){
        double price = 0;
        for (TaskC taskC : tasks) {
            price+=taskC.getPrice();
        }
        return price;
    }
}
