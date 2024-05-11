package com.example.royalautogarage.models;

public class BillingService {
    private static BillingService instance;
    private int totalAmount;
    private int totalWithTax;

    private BillingService() {}

    public static BillingService getInstance() {
        if (instance == null) {
            instance = new BillingService();
        }
        return instance;
    }

    public void updateBillingInfo(int totalAmount, int totalWithTax) {
        this.totalAmount = totalAmount;
        this.totalWithTax = totalWithTax;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTotalWithTax() {
        return totalWithTax;
    }
}
