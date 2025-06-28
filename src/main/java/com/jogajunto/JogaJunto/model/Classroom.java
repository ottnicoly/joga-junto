package com.jogajunto.JogaJunto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jogajunto.JogaJunto.model.enums.DaysOfWeek;
import com.jogajunto.JogaJunto.dto.RequestClassroomDTO;
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
    private String classType;
    private String classTime;

    //diz ao JPA para armazenar a lista em uma tabela separada, mas ainda manter a relação com a entidade
    @ElementCollection //diz ao JPA q essa lista n é outra entidade e sim uma coleção
    @CollectionTable(name = "classroom_days", joinColumns = @JoinColumn(name = "classroom_id"))
    @Column(name = "day")
    private List<DaysOfWeek> daysOfWeek;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Student> students;

    public Classroom (RequestClassroomDTO data) {
        this.classType = data.classType();
        this.classTime = data.classTime();
        this.daysOfWeek = data.daysOfWeek();
    }

}
