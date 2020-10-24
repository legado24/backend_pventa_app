package com.digitalinka.restpreventa.service;

import com.digitalinka.restpreventa.dao.ProductDao;
import com.digitalinka.restpreventa.model.response.ProductListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Override
    public ProductListResponse bonifItem(List<Object[]> parametrosString) {
        return productDao.bonifItem(parametrosString);
    }
}
