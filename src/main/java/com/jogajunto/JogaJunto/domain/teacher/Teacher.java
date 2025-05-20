package com.jogajunto.JogaJunto.domain.teacher;

import com.jogajunto.JogaJunto.domain.classroom.Classroom;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date birthDate;
    private String email;
    private String phone;

    public Teacher (TeacherRequestDTO data) {
        this.name = data.name();
        this.birthDate = data.birthDate();
        this.email = data.email();
        this.phone = data.phone();
    }

    @OneToMany
    private List<Classroom> classrooms;

}
