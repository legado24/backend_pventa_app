package com.digitalinka.restpreventa.service;

import com.digitalinka.restpreventa.model.response.ProductListResponse;

import java.util.List;

public interface ProductService {
    ProductListResponse bonifItem(List<Object[]> parametrosString);

}
