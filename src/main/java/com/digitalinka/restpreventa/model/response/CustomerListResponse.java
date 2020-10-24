package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Customer;

import java.util.List;

public class CustomerListResponse {

    private List<Customer> customers;
    private StatusResponse status;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
