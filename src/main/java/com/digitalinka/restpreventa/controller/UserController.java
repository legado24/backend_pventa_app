package com.digitalinka.restpreventa.controller;

import com.digitalinka.restpreventa.model.response.StatusResponse;
import com.digitalinka.restpreventa.model.User;
import com.digitalinka.restpreventa.model.response.UserResponse;
import com.digitalinka.restpreventa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("preventaGps")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("login")
    public UserResponse getClientesByDiaV3(@RequestBody User user) throws Exception {
        return userService.loginByUserEmail(user.getUserEmail(), user.getPassword());
    }


    @GetMapping("userByEmail")
    public UserResponse userByEmail(@RequestParam(value = "dni") String dni) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "LI_USER_BY_DNI";
        Object[] pDni = new Object[2];
        pDni[0] = "v_DNI";
        pDni[1] = dni;
        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pDni);
        return userService.userByDni(parametros);
    }

    @PutMapping("updateUserLogin")
    public StatusResponse updateUserLogin(@RequestBody User user) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "UP_USER_LOGIN";

        Object[] pUsername = new Object[2];
        pUsername[0] = "v_USERNAME";
        pUsername[1] = user.getUsername();

        Object[] pPassword = new Object[2];
        pPassword[0] = "v_PASSWORD";
        pPassword[1] = user.getPassword();

        Object[] pCelular = new Object[2];
        pCelular[0] = "v_CELULAR";
        pCelular[1] = user.getCelular();

        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pUsername);
        parametros.add(pPassword);
        parametros.add(pCelular);
        return userService.updateUserLogin(parametros);
    }
}
