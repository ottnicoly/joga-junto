package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.service.FinancialResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial")
public class FinancialResponsibleController {

    @Autowired
    FinancialResponsibleService financialService;

    @PostMapping
    public ResponseEntity<Void> registerFinancial(){

        return ResponseEntity.ok().build();
    }


}
