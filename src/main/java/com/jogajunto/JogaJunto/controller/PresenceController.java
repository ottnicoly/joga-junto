package com.jogajunto.JogaJunto.controller;

import com.jogajunto.JogaJunto.dto.PresenceRequestDTO;
import com.jogajunto.JogaJunto.model.Classroom;
import com.jogajunto.JogaJunto.model.Presence;
import com.jogajunto.JogaJunto.repository.ClassroomRepository;
import com.jogajunto.JogaJunto.service.PaymentService;
import com.jogajunto.JogaJunto.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/presences")
public class PresenceController {

    @Autowired
    private PresenceService service;

    @Autowired
    ClassroomRepository classroomRepository;

    @PostMapping
    public ResponseEntity<Presence> addPresence(@RequestBody PresenceRequestDTO dto) {
        Presence presence = service.addPresence(dto);
        return ResponseEntity.ok(presence);
    }

    @PutMapping
    public ResponseEntity<Presence> updatePresence(@RequestBody PresenceRequestDTO dto) {
        Presence presence = service.updatePresence(dto);
        return ResponseEntity.ok(presence);
    }

    @GetMapping("/byClassroomAndDate/{classroomId}/{date}")
    public ResponseEntity<List<Presence>> getPresencesByClassroomAndDate(
            @PathVariable Integer classroomId,
            @PathVariable String date) {

        LocalDate localDate = LocalDate.parse(date);
        List<Presence> presences = service.getPresencesByClassroomAndDate(classroomId, localDate);
        return ResponseEntity.ok(presences);
    }

    @GetMapping("/byClassroomAndMonth/{classroomId}/{year}/{month}")
    public ResponseEntity<List<Presence>> getPresencesByClassroomAndMonth(
            @PathVariable Integer classroomId,
            @PathVariable int year,
            @PathVariable int month) {

        List<Presence> presences = service.getPresencesByClassroomAndMonth(classroomId, year, month);
        return ResponseEntity.ok(presences);
    }

    @PostMapping("{classroomId}/upload")
    public ResponseEntity<?> uploadCsv(
            @PathVariable Integer classroomId,
            @RequestParam("file") MultipartFile file) throws IOException {

        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom not found"));

        service.processCsv(file, classroom);

        return ResponseEntity.ok("Attendance registered successfully");
    }

}
