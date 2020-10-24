package com.digitalinka.restpreventa.controller;

import com.digitalinka.restpreventa.model.response.ProductListResponse;
import com.digitalinka.restpreventa.model.response.UserResponse;
import com.digitalinka.restpreventa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("preventaGps")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("bonifItem")
    public ProductListResponse bonifItem(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "LI_BONIF_ITEM";
        Object[] pPage = new Object[2];
        pPage[0] = "v_PAGE";
        pPage[1] = page;

        Object[] pLimit = new Object[2];
        pLimit[0] = "v_LIMIT";
        pLimit[1] = limit;

        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pPage);
        parametros.add(pLimit);
        return productService.bonifItem(parametros);
    }
}
