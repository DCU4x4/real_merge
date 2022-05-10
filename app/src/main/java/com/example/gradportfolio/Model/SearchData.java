package com.example.gradportfolio.Model;

public class SearchData {
    String productName;
    String brand_title;
    String product_price;
    int imageId;
    public SearchData(String productName, String brand_title, String product_price, int imageId)
    {
        this.productName = productName;
        this.brand_title = brand_title;
        this.product_price = product_price;
        this.imageId = imageId;
    }

    public String getProductName(){
        return productName;
    }
    public String getBrand_title(){
        return brand_title;
    }
    public String getProduct_price(){
        return product_price;
    }
    public int getImageId(){
        return imageId;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public void setBrand_title(String brand_title){
        this.brand_title = brand_title;
    }

    public void setProduct_price(String product_price){
        this.product_price = product_price;
    }

    public void setImageId(int imageId){
        this.imageId = imageId;
    }




}