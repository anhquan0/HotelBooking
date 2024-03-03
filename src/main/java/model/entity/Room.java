package model.entity;

import java.util.Date;

public class Room {
    private int roomId;
    private String roomType;
    private double price;
    private String status;
    private String Description;
    private String IMG;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    private boolean isDelete;
    private Date deletedDate;
    private String deletedBy;

    public Room() {
    }

    public Room(int roomId, String roomType, double price, String status, String description, String IMG, Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean isDelete, Date deletedDate, String deletedBy) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
        Description = description;
        this.IMG = IMG;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.isDelete = isDelete;
        this.deletedDate = deletedDate;
        this.deletedBy = deletedBy;
    }

    public Room(int roomId, String roomType, double price, String status, String description, String IMG) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
        Description = description;
        this.IMG = IMG;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
}
