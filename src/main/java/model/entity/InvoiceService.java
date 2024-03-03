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
}
