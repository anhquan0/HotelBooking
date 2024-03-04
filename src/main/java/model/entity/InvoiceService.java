package model.entity;

import java.util.Date;

public class InvoiceService {
    private int invoiceId;
    private int serviceId;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

    public InvoiceService() {
    }

    public InvoiceService(int invoiceId, int serviceId, Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
        this.invoiceId = invoiceId;
        this.serviceId = serviceId;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
    }

    public InvoiceService(int invoiceId, int serviceId) {
        this.invoiceId = invoiceId;
        this.serviceId = serviceId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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
