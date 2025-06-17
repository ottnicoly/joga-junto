package com.jogajunto.JogaJunto.repository.auth;

import com.jogajunto.JogaJunto.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  Role findByName(String name);

}
