package com.jogajunto.JogaJunto.service;

import com.jogajunto.JogaJunto.dto.PaymentDTO;
import com.jogajunto.JogaJunto.model.FinancialResponsible;
import com.jogajunto.JogaJunto.model.Payment;
import com.jogajunto.JogaJunto.model.Student;
import com.jogajunto.JogaJunto.repository.FinancialResponsibleRepository;
import com.jogajunto.JogaJunto.repository.PaymentRepository;
import com.jogajunto.JogaJunto.repository.StudentRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private FinancialResponsibleRepository responsibleRepository;

    @Autowired
    private StudentRepository studentRepository;

    public void processCsvPayments(List<PaymentDTO> paymentsFromCsv) {
        for (PaymentDTO dto : paymentsFromCsv) {
            String payerName = dto.payer();
            FinancialResponsible responsible = responsibleRepository.findByNameContainingIgnoreCase(payerName);

            if (responsible == null) {
                System.out.println("Responsável não encontrado: " + payerName);
                continue;
            }

            List<Student> students = studentRepository.findByFinancialResponsiblesContaining(responsible);

            students.sort((a, b) -> a.getId().compareTo(b.getId()));

            double amountRemaining = dto.amount();

            for (Student student : students) {
                double fee = student.getMonthlyFee();
                if (amountRemaining >= fee) {
                    student.setPaymentUpToDate(true);
                    amountRemaining -= fee;
                } else {
                    break;
                }
            }

            studentRepository.saveAll(students);

            Payment payment = new Payment();
            payment.setPaymentDate(dto.paymentDate());
            payment.setAmount(dto.amount());
            payment.setResponsible(responsible);

            paymentRepository.save(payment);
        }
    }

    public List<PaymentDTO> parseCsv(MultipartFile file) throws IOException {
        List<PaymentDTO> payments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("Data Lançamento", "Descrição", "Valor")
                    .withFirstRecordAsHeader()
                    .parse(reader);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (CSVRecord record : records) {
                LocalDate date = LocalDate.parse(record.get("Data Lançamento"), formatter);
                String payer = record.get("Descrição");
                Double amount = Double.parseDouble(record.get("Valor"));

                payments.add(new PaymentDTO(date, payer, amount));
            }
        }
        return payments;
    }

}



