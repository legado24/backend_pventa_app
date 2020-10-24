package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Product;

import java.util.List;

public class ProductListResponse {
    private List<Product> products;
    private StatusResponse status;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
