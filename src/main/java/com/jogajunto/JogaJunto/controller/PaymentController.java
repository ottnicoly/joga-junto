package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.dto.PaymentDTO;
import com.jogajunto.JogaJunto.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/csv")
    public ResponseEntity<String> uploadPaymentsCsv(@RequestParam("file") MultipartFile file) {
        try {
            List<PaymentDTO> payments = paymentService.parseCsv(file);
            paymentService.processCsvPayments(payments);
            return ResponseEntity.ok("Pagamentos processados com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao processar CSV: " + e.getMessage());
        }
    }


}
