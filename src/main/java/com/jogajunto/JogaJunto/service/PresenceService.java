package com.jogajunto.JogaJunto.service;

import com.jogajunto.JogaJunto.dto.PresenceRequestDTO;
import com.jogajunto.JogaJunto.model.Classroom;
import com.jogajunto.JogaJunto.model.Presence;
import com.jogajunto.JogaJunto.model.Student;
import com.jogajunto.JogaJunto.repository.ClassroomRepository;
import com.jogajunto.JogaJunto.repository.PresenceRepository;
import com.jogajunto.JogaJunto.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PresenceService {
    @Autowired
    private PresenceRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public Presence addPresence(PresenceRequestDTO dto) {
        Student student = studentRepository.findById(dto.studentId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Classroom classroom = classroomRepository.findById(dto.classroomId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

        Presence presence = new Presence();
        presence.setDate(dto.date());
        presence.setPresence(dto.presence());
        presence.setStudent(student);
        presence.setClassroom(classroom);

        return repository.save(presence);
    }

    public Presence updatePresence(PresenceRequestDTO dto) {
        Presence presence = repository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Presença não encontrada"));

        presence.setPresence(dto.presence());
        return repository.save(presence);
    }

    public List<Presence> getPresencesByClassroomAndDate(Integer classroomId, LocalDate date) {
        return repository.findAllByClassroomIdAndDate(classroomId, date);
    }

    public List<Presence> getPresencesByClassroomAndMonth(Integer classroomId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return repository.findAllByClassroomIdAndDateBetween(classroomId, start, end);
    }

    public void processCsv(MultipartFile file, Classroom classroom) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = br.readLine(); // lê e descarta o cabeçalho
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // separador TAB

                if (data.length != 3) {
                    throw new IllegalArgumentException("Linha inválida no CSV: " + line);
                }

                LocalDate date = LocalDate.parse(data[0].trim(), formatter);
                String studentName = data[1].trim();
                String statusStr = data[2].trim();

                Student student = studentRepository.findByName(studentName)
                        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado: " + studentName));

                Presence presence = new Presence();
                presence.setStudent(student);
                presence.setDate(date);
                presence.setClassroom(classroom);

                if ("C".equalsIgnoreCase(statusStr)) {
                    presence.setPresence(true);
                } else if ("F".equalsIgnoreCase(statusStr)) {
                    presence.setPresence(false);
                } else {
                    throw new IllegalArgumentException("Status inválido no CSV: " + statusStr);
                }

                repository.save(presence);
            }
        }
    }

    public List<Student> getStudentsByClassroom(Integer classroomId) {
        return studentRepository.findByClassrooms_Id(classroomId);
    }

}
