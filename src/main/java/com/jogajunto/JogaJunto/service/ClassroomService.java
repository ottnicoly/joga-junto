package com.jogajunto.JogaJunto.service;

import com.jogajunto.JogaJunto.dao.classroom.ClassroomDAO;
import com.jogajunto.JogaJunto.mapper.ClassroomMapper;
import com.jogajunto.JogaJunto.model.Classroom;
import com.jogajunto.JogaJunto.repository.ClassroomRepository;
import com.jogajunto.JogaJunto.dto.RequestClassroomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ClassroomDAO> getAllClassrooms() {
        List<Classroom> classrooms = repository.findAll();
        return ClassroomMapper.toDAOList(classrooms);
    }
}
