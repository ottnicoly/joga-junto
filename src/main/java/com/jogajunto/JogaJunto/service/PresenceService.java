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

import java.time.LocalDate;
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
}
