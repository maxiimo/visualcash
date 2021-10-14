/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.visualcash.security.controller;

import com.backend.visualcash.dto.UserInfo;
import com.backend.visualcash.security.jwt.JwtProvider;
import com.backend.visualcash.security.service.UsuarioService;
import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabian
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/info")
    public ResponseEntity info(Principal principal) {
        String email = principal.getName();
        Optional<UserInfo> userInfo = usuarioService.getUserInfo(email);
        return new ResponseEntity(userInfo, HttpStatus.OK);
    }
    @GetMapping("/delete")
    public void delete(@RequestParam int id) {
             usuarioService.deleteById(id);
    } 
}