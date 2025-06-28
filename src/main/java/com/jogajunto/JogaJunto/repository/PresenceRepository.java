package com.jogajunto.JogaJunto.repository;

import com.jogajunto.JogaJunto.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PresenceRepository extends JpaRepository<Presence, Integer> {

    List<Presence> findAllByClassroomIdAndDate(Integer classroomId, LocalDate date);

    List<Presence> findAllByClassroomIdAndDateBetween(Integer classroomId, LocalDate start, LocalDate end);

}
