package com.jogajunto.JogaJunto.scheduler;

import com.jogajunto.JogaJunto.model.Student;
import com.jogajunto.JogaJunto.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PaymentStatusScheduler {

    @Autowired
    private StudentRepository studentRepository;

    @Scheduled(cron = "0 0 0 1 * *") // todo dia 1º às 00:00
    public void resetPaymentStatus() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            student.setPaymentUpToDate(false);
        }
        studentRepository.saveAll(students);
    }

}
