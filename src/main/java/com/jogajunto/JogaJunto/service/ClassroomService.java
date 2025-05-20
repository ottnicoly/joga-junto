package com.jogajunto.JogaJunto.service;

import com.jogajunto.JogaJunto.domain.classroom.Classroom;
import com.jogajunto.JogaJunto.domain.classroom.ClassroomRepository;
import com.jogajunto.JogaJunto.domain.classroom.RequestClassroomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {

    @Autowired
    ClassroomRepository repository;

    public Classroom getAllClassroom(){
        return null;
    }

    public Classroom registerClass(RequestClassroomDTO data) {
        Classroom classroom = new Classroom(data);
        repository.save(classroom);
        return classroom;
    }
}
