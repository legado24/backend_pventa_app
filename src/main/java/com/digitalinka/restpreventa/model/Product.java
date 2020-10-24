package com.digitalinka.restpreventa.model;

public class Product {
    private String code;
    private String description;
    private String uri;
    private Double price;
    private boolean isBonif;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isBonif() {
        return isBonif;
    }

    public void setBonif(boolean bonif) {
        isBonif = bonif;
    }
}
