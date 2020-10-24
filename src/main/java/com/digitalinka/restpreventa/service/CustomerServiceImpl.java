package com.digitalinka.restpreventa.service;


import com.digitalinka.restpreventa.dao.CustomerDao;
import com.digitalinka.restpreventa.model.response.CustomerListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;

    @Override
    public CustomerListResponse getClienteList(String usuario) {
        return customerDao.getClienteList(usuario);
    }
}
