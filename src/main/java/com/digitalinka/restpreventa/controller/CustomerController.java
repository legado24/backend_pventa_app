package com.digitalinka.restpreventa.controller;


import com.digitalinka.restpreventa.model.response.CustomerListResponse;
import com.digitalinka.restpreventa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("preventaGps")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("clientesByDiaV3")
    public CustomerListResponse getClientesByDiaV3(@RequestParam(value="usuario") String usuario) throws Exception {
        return customerService.getClienteList(usuario);

    }

}
