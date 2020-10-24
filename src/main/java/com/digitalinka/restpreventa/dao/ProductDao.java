package com.digitalinka.restpreventa.dao;

import com.digitalinka.restpreventa.model.response.ProductListResponse;
import com.digitalinka.restpreventa.model.response.UserResponse;

import java.util.List;

public interface ProductDao {
    ProductListResponse bonifItem(List<Object[]> parametrosString);

}
