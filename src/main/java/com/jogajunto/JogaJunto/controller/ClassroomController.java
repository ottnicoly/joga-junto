package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.domain.classroom.RequestClassroomDTO;
import com.jogajunto.JogaJunto.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    ClassroomService service;

    @GetMapping
    public ResponseEntity allClassroom(){
        return ResponseEntity.ok("ok");
    }

    @PostMapping
    public ResponseEntity registerClassRoom(@RequestBody @Validated RequestClassroomDTO data){
        service.registerClass(data);
        return ResponseEntity.ok().build();
    }

}