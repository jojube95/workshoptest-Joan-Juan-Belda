package com.thirdparty.billing;

public class TaskC {

    private final String work;
    private final double price;

    public TaskC(String work, double price) {
        this.work = work;
        this.price = price;
    }

    public String getWork() {
        return work;
    }

    public double getPrice() {
        return price;
    }
}
