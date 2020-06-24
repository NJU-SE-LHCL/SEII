package com.example.hotel.vo;

public class SearchVO {

    private String name;
    private String address;
    private String checkInDate;
    private String checkOutDate;
    private String roomType;
    private String bizRegion;
    private Integer lowerPrice;
    private Integer upperPrice;
    private Integer lowerRate;
    private Integer upperRate;


    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public Integer getLowerPrice() {
        return lowerPrice;
    }

    public Integer getLowerRate() {
        return lowerRate;
    }

    public Integer getUpperPrice() {
        return upperPrice;
    }

    public Integer getUpperRate() {
        return upperRate;
    }

    public String getBizRegion() {
        return bizRegion;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setBizRegion(String bizRegion) {
        this.bizRegion = bizRegion;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setLowerPrice(Integer lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public void setLowerRate(Integer lowerRate) {
        this.lowerRate = lowerRate;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setUpperPrice(Integer upperPrice) {
        this.upperPrice = upperPrice;
    }

    public void setUpperRate(Integer upperRate) {
        this.upperRate = upperRate;
    }

}
