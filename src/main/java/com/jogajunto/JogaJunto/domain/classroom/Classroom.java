package com.jogajunto.JogaJunto.domain.classroom;

import com.jogajunto.JogaJunto.domain.teacher.Teacher;
import jakarta.persistence.*;

import java.util.List;

@Entity
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
    private Teacher teacher;

//    adicionar alunos?

    public Classroom (RequestClassroomDTO data) {
        this.classType = data.classType();
        this.classTime = data.classTime();
        this.daysOfWeek = data.daysOfWeek();
        this.teacher = data.teacher();
    }

}
