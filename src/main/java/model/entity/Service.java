package model.entity;

import java.util.Date;

public class Service {
    private int serviceId;
    private String name;
    private double price;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;


    public Service() {
    }

    public Service(int serviceId, String name, double price, Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
    }

    public Service(int serviceId, String name, double price) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
