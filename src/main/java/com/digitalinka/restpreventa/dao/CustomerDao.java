package com.digitalinka.restpreventa.dao;


import com.digitalinka.restpreventa.model.response.CustomerListResponse;

public interface CustomerDao {
    CustomerListResponse getClienteList(String usuario);
}
