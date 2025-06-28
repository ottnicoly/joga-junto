package com.jogajunto.JogaJunto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jogajunto.JogaJunto.dto.RequestClassroomDTO;
import com.jogajunto.JogaJunto.model.enums.DaysOfWeek;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "classroom")
public class Classroom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String classType;
    private String classTime;

    @ElementCollection
    @CollectionTable(name = "classroom_days", joinColumns = @JoinColumn(name = "classroom_id"))
    @Column(name = "day")
    @Enumerated(EnumType.STRING)
    private List<DaysOfWeek> daysOfWeek;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher;

    @OneToMany(mappedBy = "classrooms")
    private List<Student> students;

    public Classroom (RequestClassroomDTO data) {
        this.name = data.name();
        this.classType = data.classType();
        this.classTime = data.classTime();
        this.daysOfWeek = data.daysOfWeek();
    }
}
