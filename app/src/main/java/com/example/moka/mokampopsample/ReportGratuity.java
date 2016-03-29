package com.example.moka.mokampopsample;

/**
 * Created by ferdi on 8/18/2015.
 */
public class ReportGratuity {
    private long id;
    private String name;
    private double amount;
    private long total;
    private long payment_id;
    private String created_at;
    private String updated_at;
    private long gratuity_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(long payment_id) {
        this.payment_id = payment_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public long getGratuity_id() {
        return gratuity_id;
    }

    public void setGratuity_id(long gratuity_id) {
        this.gratuity_id = gratuity_id;
    }
}
