package com.digitalinka.restpreventa.service;


import com.digitalinka.restpreventa.model.response.CustomerListResponse;

public interface CustomerService {
    CustomerListResponse getClienteList(String usuario);

}
