package com.app.ghazi.recyclerviewexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

    @SerializedName("auction_id")
    @Expose
    private Integer auctionId;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("system_time")
    @Expose
    private Object systemTime;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("owner")
    @Expose
    private Integer owner;
    @SerializedName("owner_status")
    @Expose
    private Object ownerStatus;
    @SerializedName("auction_status")
    @Expose
    private Object auctionStatus;
    @SerializedName("start_price")
    @Expose
    private Integer startPrice;
    @SerializedName("multiple_price")
    @Expose
    private Integer multiplePrice;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("brand_id")
    @Expose
    private Integer brandId;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;
    @SerializedName("added")
    @Expose
    private String added;

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Object getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Object systemTime) {
        this.systemTime = systemTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Object getOwnerStatus() {
        return ownerStatus;
    }

    public void setOwnerStatus(Object ownerStatus) {
        this.ownerStatus = ownerStatus;
    }

    public Object getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(Object auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getMultiplePrice() {
        return multiplePrice;
    }

    public void setMultiplePrice(Integer multiplePrice) {
        this.multiplePrice = multiplePrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

}