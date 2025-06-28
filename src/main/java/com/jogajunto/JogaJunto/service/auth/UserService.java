package com.jogajunto.JogaJunto.service.auth;

import com.jogajunto.JogaJunto.dto.CreateUserRequest;
import com.jogajunto.JogaJunto.dto.TeacherRequestDTO;
import com.jogajunto.JogaJunto.model.Teacher;
import com.jogajunto.JogaJunto.model.auth.Role;
import com.jogajunto.JogaJunto.model.auth.User;
import com.jogajunto.JogaJunto.repository.TeacherRepository;
import com.jogajunto.JogaJunto.repository.auth.RoleRepository;
import com.jogajunto.JogaJunto.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    TeacherRepository teacherRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void newUser(@RequestBody CreateUserRequest dto) {

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        var userFromDb = userRepository.findByUsername(dto.username());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<User> getUser(){

        var users = userRepository.findAll();

        return users;
    }

    public void registerTeacherWithUser(TeacherRequestDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Usuário já existe");
        }

        var role = roleRepository.findByName(Role.Values.BASIC.name());
        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(role));

        userRepository.save(user);

        var teacher = new Teacher(dto.name(), dto.email(), dto.phone(), dto.cpf());
        teacher.setUser(user);

        teacherRepository.save(teacher);
    }

}
